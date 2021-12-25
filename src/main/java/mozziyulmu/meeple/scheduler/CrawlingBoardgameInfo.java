package mozziyulmu.meeple.scheduler;

import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.Repository.BoardgameRepository;
import mozziyulmu.meeple.Repository.CategoryRepository;
import mozziyulmu.meeple.Repository.MechanismRepository;
import mozziyulmu.meeple.Repository.PublisherRepository;
import mozziyulmu.meeple.entity.Boardgame;
import mozziyulmu.meeple.entity.Category;
import mozziyulmu.meeple.entity.Mechanism;
import mozziyulmu.meeple.entity.Publisher;
import mozziyulmu.meeple.scheduler.jsonForm.ParseGeekGameData;
import mozziyulmu.meeple.scheduler.jsonForm.ParseGeekPublisher;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static mozziyulmu.meeple.scheduler.BoardgameCompany.KOREA_BOARDGAMES;

@Service
@RequiredArgsConstructor
public class CrawlingBoardgameInfo {
    private final RestTemplate restTemplate;
    private final BoardgameRepository boardgameRepository;
    private final CategoryRepository categoryRepository;
    private final MechanismRepository mechanismRepository;
    private final PublisherRepository publisherRepository;

    static final int ONCE_GEEK_QUERY_COUNT = 50;

    // 보드게임 Publisher 정보(한국 회사만)에서
    // 출시 보드게임을 전체 확인하여 각 보드게임의 고유 번호 획득
    // 해당 번호로 BGG v2 API 통신으로 보드게임 정보 획득
    public void getBoardgameInfo() {
        try {
            getBoardgamesInfoFromPublisher(KOREA_BOARDGAMES);
//            getBoardgameInfo_KoreaBoardgames(BOARDGAME_COMPANY_ID.POPCORN_GAMES);
        } catch (Exception e){
            String message = e.getMessage();
            e.printStackTrace();
            System.out.println("에러 발생 : " + message);
        }
    }

    public void getBoardgamesInfoFromPublisher(BoardgameCompany company_id) throws Exception {
        /*
        * step 1.
        * https://api.geekdo.com/api/geekitem/linkeditems?ajax=1&linkdata_index=boardgame&nosession=1&objectid=8291&objecttype=company&pageid=1&showcount=50&sort=name&subtype=boardgamepublisher
        * 해당 경로로 get 방식 json 데이터 파싱 가능
        * 1회 최대 50개 파싱 가능
        * pageid 변경
        * objectid 변경으로 회사 변경 가능
        * 총 데이터 : config/numitems
        * 아이템 번호 : items배열/ "href": "/boardgame/878/wyatt-earp",
        * */
        List<String> boardgameNos = getPublisherBoardgamesId(company_id);

        /*
        * step 2.
        * https://www.boardgamegeek.com/xmlapi2/thing?stats=1&id=167791,13,14,15
        * 한 번에 여러 개의 요청을 보내야 할 거 같음... 너무 자주 보내면 에러 발생
        * */
        setBoardgameInfo(boardgameNos, company_id);
    }

    private List<String> getPublisherBoardgamesId(BoardgameCompany company) throws Exception {
        List<String> boardgameIds = new ArrayList<>();
        String publisherUrl = "https://api.geekdo.com/api/geekitem/linkeditems?ajax=1&linkdata_index=boardgame&nosession=1&objecttype=company&showcount=50&sort=name&subtype=boardgamepublisher" +
                "&objectid=" + company.getGeekId();
        int page = 1, max_page = 0;
        boolean once = false;
        Gson gson = new Gson();

        do {
            String pageUrl = "&pageid=" + page;
            ParseGeekPublisher parseGeekPublisher
                    = gson.fromJson(
                    restTemplate.getForObject(publisherUrl + pageUrl, String.class),
                    ParseGeekPublisher.class
            );

            for (ParseGeekPublisher.Item item : parseGeekPublisher.getItems())
                boardgameIds.add(item.getObjectid());

            if(!once){
                max_page = (parseGeekPublisher.getConfig().getNumitems() / 50) + 1;
                once = true;
            }

            page++;
        } while (page <= max_page);

        return boardgameIds;
    }

    private void setBoardgameInfo(List<String> boardgameIds, BoardgameCompany company) throws Exception {
        Publisher publisher = publisherRepository.findByKorName(company.getKorName());
        if (publisher == null)
            return;

        String gameUrl = "https://www.boardgamegeek.com/xmlapi2/thing?stats=1&id=";
        String tenGames = "";
        int count = 0, cum_count = 0;
        JacksonXmlModule module = new JacksonXmlModule();
        module.setDefaultUseWrapper(false);
        XmlMapper xmlMapper = new XmlMapper(module);

        for (String boardgameNo : boardgameIds){
            tenGames += boardgameNo;
            count++;
            cum_count++;

            if((count < ONCE_GEEK_QUERY_COUNT) && (cum_count != boardgameIds.size())){
                tenGames += ",";
                continue;
            }
            else{
                ParseGeekGameData parseGeekGameData
                        = xmlMapper.readValue(
                        restTemplate.getForObject(gameUrl + tenGames, String.class),
                        ParseGeekGameData.class
                );

                for (ParseGeekGameData.Item item : parseGeekGameData.getItem()) {
                    if( ! item.getType().equals("boardgame")) // boardgameexpansion 제외
                        continue;

                    String engName = "", korName = "";
                    for (ParseGeekGameData.NameData nameData : item.getName()){
                        if(nameData.getValue().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*"))
                            korName = nameData.getValue();
                        else if(nameData.getType().equals("primary"))
                            engName = nameData.getValue();
                    }
                    if (!StringUtils.hasText(engName))
                        engName = korName;
                    else if(!StringUtils.hasText(korName))
                        korName = engName;

                    List<Category> categories = new ArrayList<>();
                    List<Mechanism> mechanisms = new ArrayList<>();
                    for (ParseGeekGameData.LinkData linkData : item.getLink()) {
                        if(linkData.getType().equals("boardgamecategory")){
                            Category category = categoryRepository.findByEngName(linkData.getValue());
                            if(category != null)
                                categories.add(category);
                        }
                        if(linkData.getType().equals("boardgamemechanic")){
                            Mechanism mechanism = mechanismRepository.findByEngName(linkData.getValue());
                            if(mechanism != null)
                                mechanisms.add(mechanism);
                        }
                    }

                    Boardgame boardgame = new Boardgame(korName, engName)
                            .setPlayers(Integer.parseInt(item.getMinplayers().getValue()),
                                    Integer.parseInt(item.getMaxplayers().getValue()),
                                    0)
                            .setPlayingTime(Integer.parseInt(item.getPlayingtime().getValue()),
                                    Integer.parseInt(item.getPlayingtime().getValue()))
                            .setPublishedYear(Integer.parseInt(item.getYearpublished().getValue()))
                            .setAge(Integer.parseInt(item.getMinage().getValue()))
                            .setGeekData(
                                    Integer.parseInt(item.getId()),
                                    Double.parseDouble(item.getStatistics().getRatings().getAverage().getValue()),
                                    Double.parseDouble(item.getStatistics().getRatings().getAverageweight().getValue())
                            )
                            .setLineComment("")
                            .setPublisher(publisher)
                            .initCategorys(categories)
                            .initMechanism(mechanisms);

                    if(StringUtils.hasText(item.getImage()))
                        boardgame.addGameImage(item.getImage());

                    boardgameRepository.save(boardgame);
                }

                tenGames = "";
                count = 0;
            }
        }
    }
}
