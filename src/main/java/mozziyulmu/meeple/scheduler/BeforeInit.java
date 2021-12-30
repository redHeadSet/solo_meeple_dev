package mozziyulmu.meeple.scheduler;

import lombok.RequiredArgsConstructor;
import mozziyulmu.meeple.Repository.*;
import mozziyulmu.meeple.entity.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BeforeInit {
    private final PublisherRepository publisherRepository;
    private final CategoryRepository categoryRepository;
    private final MechanismRepository mechanismRepository;

    private class DataSet{
        String korName;
        String engName;

        public DataSet(String korName, String engName) {
            this.korName = korName;
            this.engName = engName;
        }

        public Category getCateData(){
            return new Category(korName, engName);
        }

        public Mechanism getMechData() {
            return new Mechanism(korName, engName);
        }
    }
    // Mechanism desc 어떻게 할 지...
    // description 어디서 보여줄 것인지...

    // 총 84개 카테고리
    public void setCategory(){
        List<DataSet> dataSets = Arrays.asList(
                new DataSet("추상 전략", "Abstract Strategy"),
                new DataSet("액션 / 순발력", "Action / Dexterity"),
                new DataSet("어드벤처 / 영웅담", "Adventure"),
                new DataSet("이성의 시대", "Age of Reason"), // 영국/프랑스 18세기
                new DataSet("미국 남북 전쟁", "American Civil War"),
                new DataSet("인디언 전쟁", "American Indian War"),
                new DataSet("미국 독립 전쟁", "American Revolutionary War"),
                new DataSet("미국 서부", "American West"),
                new DataSet("고대 / 기원전", "Ancient"),
                new DataSet("동물", "Animals"),
                new DataSet("아라비아", "Arabian"),
                new DataSet("항공 / 비행", "Aviation / Flight"),
                new DataSet("블러핑 / 속임수", "Bluffing"),
                new DataSet("책", "Book"),   // 스토리텔링, 규칙서 등
                new DataSet("카드 게임", "Card Game"),
                new DataSet("어린이용 게임", "Children's Game"),
                new DataSet("도시 건설", "City Building"),
                new DataSet("내전", "Civil War"),
                new DataSet("문명", "Civilization"),
                new DataSet("구성 요소 수집", "Collectible Components"), // 구성 요소를 나눠 구매하는 게임
                new DataSet("만화책 / 스트립", "Comic Book / Strip"),
                new DataSet("추리 / 추론", "Deduction"),
                new DataSet("주사위", "Dice"),
                new DataSet("경제 / 경영 / 자원 관리", "Economic"),
                new DataSet("교육용 / 교육적", "Educational"),
                new DataSet("전자 장치", "Electronic"), // 회로, 컴퓨터 등이 게임에 포함
                new DataSet("환경", "Environmental"),
                new DataSet("게임 확장", "Expansion for Base-game"),
                new DataSet("탐험", "Exploration"),
                new DataSet("팬 확장", "Fan Expansion"),   // 비공식 팬 확장 게임
                new DataSet("판타지", "Fantasy"),
                new DataSet("농업 / 농사", "Farming"),
                new DataSet("전투", "Fighting"),
                new DataSet("게임 시스템", "Game System"),   // 구성 요소가 없는 게임
                new DataSet("공포", "Horror"),
                new DataSet("유머", "Humor"),
                new DataSet("산업 / 제조 / 상품 관리", "Industry / Manufacturing"),
                new DataSet("한국 전쟁", "Korean War"),
                new DataSet("마피아", "Mafia"),
                new DataSet("수학", "Math"),
                new DataSet("성인", "Mature / Adult"),
                new DataSet("미로", "Maze"),
                new DataSet("의학", "Medical"),
                new DataSet("중세 시대", "Medieval"),
                new DataSet("기억력", "Memory"),
                new DataSet("미니어처", "Miniatures"),
                new DataSet("현대 전쟁", "Modern Warfare"), // 2차 세계대전 이후
                new DataSet("영화 / TV / 라디오", "Movies / TV / Radio theme"),
                new DataSet("살인 / 미스터리", "Murder/Mystery"),
                new DataSet("음악", "Music"),
                new DataSet("신화 / 고대 문명", "Mythology"),
                new DataSet("나폴레옹 전쟁", "Napoleonic"),
                new DataSet("바다 / 해상", "Nautical"),
                new DataSet("협상", "Negotiation"),
                new DataSet("소설 기반", "Novel-based"),
                new DataSet("숫자 기반", "Number"),
                new DataSet("파티 게임", "Party Game"),
                new DataSet("파이크 앤 샷", "Pike and Shot"),    // 16~18세기 전쟁 게임
                new DataSet("해적", "Pirates"),
                new DataSet("정치", "Political"),
                new DataSet("나폴레옹 이후", "Post-Napoleonic"),  // 19세기
                new DataSet("선사 시대", "Prehistoric"),        // 석기, 청동기, 철기 시대 등
                new DataSet("PnP 게임", "Print & Play"),      // 직접 인쇄하여 게임
                new DataSet("퍼즐", "Puzzle"),
                new DataSet("레이싱", "Racing"),
                new DataSet("실시간", "Real-time"),
                new DataSet("종교", "Religious"),
                new DataSet("르네상스 시대", "Renaissance"),
                new DataSet("공상 과학", "Science Fiction"),
                new DataSet("우주 탐사", "Space Exploration"),
                new DataSet("스파이", "Spies/Secret Agents"),
                new DataSet("스포츠", "Sports"),
                new DataSet("영토 관리 / 영역 건설", "Territory Building"),
                new DataSet("철도 / 기차", "Trains"),
                new DataSet("교통", "Transportation"),
                new DataSet("여행", "Travel"),
                new DataSet("퀴즈", "Trivia"),
                new DataSet("비디오 게임 기반", "Video Game Theme"),
                new DataSet("베트남 전쟁", "Vietnam War"),
                new DataSet("전쟁 / 군사 행동 / 워게임", "Wargame"),
                new DataSet("단어 게임", "Word Game"),
                new DataSet("제 1 차 세계 대전", "World War I"),
                new DataSet("제 2 차 세계 대전", "World War II"),
                new DataSet("좀비", "Zombies")
        );

        for(DataSet each : dataSets){
            categoryRepository.save(each.getCateData());
        }
    }

    // 총 182개 매커니즘 - 일부 이름 변경 필요성 있음 + 매커니즘 설명 추가할 지 안할 지
    public void setMechanism() {
        List<DataSet> dataSets = Arrays.asList(
                new DataSet("마임", "Acting"),
                new DataSet("액션 드래프팅", "Action Drafting"),
                new DataSet("액션 포인트", "Action Points"),
                new DataSet("액션 대기열", "Action Queue"),
                new DataSet("액션 회복", "Action Retrieval"),
                new DataSet("액션 타이머", "Action Timer"),
                new DataSet("액션 / 이벤트", "Action/Event"),
                new DataSet("어드밴티지 토큰", "Advantage Token"),
                new DataSet("동맹 맺기", "Alliances"),
                new DataSet("영향력", "Area Majority / Influence"),
                new DataSet("맵 조각", "Area Movement"),
                new DataSet("지역 충돌", "Area-Impulse"),
                new DataSet("경매 / 입찰", "Auction/Bidding"),
                new DataSet("경매 : 손재주", "Auction: Dexterity"),
                new DataSet("경매 : 네덜란드식", "Auction: Dutch"),
                new DataSet("경매 : 네덜란드식 우선순위", "Auction: Dutch Priority"),
                new DataSet("경매 : 잉글랜드식", "Auction: English"),
                new DataSet("경매 : 고정 배치", "Auction: Fixed Placement"),
                new DataSet("경매 : 라운드 한 번", "Auction: Once Around"),
                new DataSet("경매 : 비밀 입찰", "Auction: Sealed Bid"),
                new DataSet("경매 : 라운드 무제한", "Auction: Turn Order Until Pass"),
                new DataSet("자동 자원 증가", "Automatic Resource Growth"),
                new DataSet("베팅, 블러핑", "Betting and Bluffing"),
                new DataSet("편향적", "Bias"),
                new DataSet("무작위 아이템", "Bingo"),
                new DataSet("뇌물", "Bribery"),
                new DataSet("카드 행동", "Campaign / Battle Card Driven"),
                new DataSet("카드 드래프팅", "Card Drafting"),
                new DataSet("카드 상호작용", "Card Play Conflict Resolution"),
                new DataSet("1등 잡기 / 불리한 1등", "Catch the Leader"),
                new DataSet("체인", "Chaining"),
                new DataSet("치트-풀", "Chit-Pull System"),
                new DataSet("패쇄 경매", "Closed Economy Auction"),
                new DataSet("명령 카드", "Command Cards"),
                new DataSet("가치 조작", "Commodity Speculation"),
                new DataSet("소통 제한", "Communication Limits"),
                new DataSet("연결", "Connections"),
                new DataSet("입찰 제약 조건", "Constrained Bidding"),
                new DataSet("계약", "Contracts"),
                new DataSet("협력", "Cooperative Game"),
                new DataSet("길 그리기", "Crayon Rail System"),
                new DataSet("치명타", "Critical Hits and Failures"),
                new DataSet("큐브 타워", "Cube Tower"),
                new DataSet("덱 메이킹", "Deck Construction"),
                new DataSet("덱, 백, 풀 빌딩", "Deck, Bag, and Pool Building"),
                new DataSet("추리", "Deduction"),
                new DataSet("지연된 구매", "Delayed Purchase"),
                new DataSet("주사위", "Dice Rolling"),
                new DataSet("특수 주사위", "Die Icon Resolution"),
                new DataSet("상태 주사위 사용", "Different Dice Movement"),
                new DataSet("드래프팅", "Drafting"),
                new DataSet("타임어택", "Elapsed Real Time Ending"),
                new DataSet("둘러싼 땅", "Enclosure"),
                new DataSet("종료 보너스", "End Game Bonuses"),
                new DataSet("이벤트", "Events"),
                new DataSet("피날레 엔딩", "Finale Ending"),
                new DataSet("튕기기", "Flicking"),
                new DataSet("팔로우 액션", "Follow"),
                new DataSet("동시 공개", "Force Commitment"),
                new DataSet("공간 채우기", "Grid Coverage"),
                new DataSet("맵 이동", "Grid Movement"),
                new DataSet("핸드 관리", "Hand Management"),
                new DataSet("육각형 맵 타일", "Hexagon Grid"),
                new DataSet("비밀 이동", "Hidden Movement"),
                new DataSet("숨겨진 역할", "Hidden Roles"),
                new DataSet("비밀 승점", "Hidden Victory Points"),
                new DataSet("최저 점수제", "Highest-Lowest Scoring"),
                new DataSet("뜨거운 감자", "Hot Potato"),
                new DataSet("파이 규칙", "I Cut, You Choose"),
                new DataSet("임펄스 운동", "Impulse Movement"),
                new DataSet("정기 수입", "Income"),
                new DataSet("비인기 자원 가치 증가", "Increase Value of Unchosen Resources"),
                new DataSet("유도", "Induction"),
                new DataSet("턴 방해", "Interrupts"),
                new DataSet("투자", "Investment"),
                new DataSet("막타치기", "Kill Steal"),
                new DataSet("위치 이점", "King of the Hill"),
                new DataSet("사다리 등반", "Ladder Climbing"),
                new DataSet("레이어", "Layering"),
                new DataSet("레거시", "Legacy Game"),
                new DataSet("그리기", "Line Drawing"),
                new DataSet("시선", "Line of Sight"),
                new DataSet("대출", "Loans"),
                new DataSet("턴 상실", "Lose a Turn"),
                new DataSet("만칼라", "Mancala"),
                new DataSet("지도 추가", "Map Addition"),
                new DataSet("지도 변형", "Map Deformation"),
                new DataSet("지도 축소", "Map Reduction"),
                new DataSet("시장", "Market"),
                new DataSet("같은 속성 매칭", "Matching"),
                new DataSet("측정 도구", "Measurement Movement"),
                new DataSet("카드 능력 흡수", "Melding and Splaying"),
                new DataSet("기억력", "Memory"),
                new DataSet("미니맵 시스템", "Minimap Resolution"),
                new DataSet("모듈식 보드", "Modular Board"),
                new DataSet("덱을 통해 이동", "Move Through Deck"),
                new DataSet("이동 포인트", "Movement Points"),
                new DataSet("이동 모양", "Movement Template"),
                new DataSet("여러 유닛 이동", "Moving Multiple Units"),
                new DataSet("멀티 맵", "Multiple Maps"),
                new DataSet("다중 로트 경매", "Multiple-Lot Auction"),
                new DataSet("액션 옵션 선택", "Narrative Choice / Paragraph"),
                new DataSet("협상", "Negotiation"),
                new DataSet("네트워크", "Network and Route Building"),
                new DataSet("1회성 능력", "Once-Per-Game Abilities"),
                new DataSet("액션 토큰 배치", "Order Counters"),
                new DataSet("소유권", "Ownership"),
                new DataSet("종이와 연필", "Paper-and-Pencil"),
                new DataSet("액션 토큰 전달", "Passed Action Token"),
                new DataSet("패턴 빌딩", "Pattern Building"),
                new DataSet("패턴 이동", "Pattern Movement"),
                new DataSet("패턴 인식", "Pattern Recognition"),
                new DataSet("물리적 제거", "Physical Removal"),
                new DataSet("아이템 배송", "Pick-up and Deliver"),
                new DataSet("조각화된 지도", "Pieces as Map"),
                new DataSet("플레이어 제거", "Player Elimination"),
                new DataSet("플레이어 심판", "Player Judge"),
                new DataSet("포인트 이동", "Point to Point Movement"),
                new DataSet("결과 예측", "Predictive Bid"),
                new DataSet("죄수의 딜레마", "Prisoner's Dilemma"),
                new DataSet("계획 이동", "Programmed Movement"),
                new DataSet("고 or 스톱", "Push Your Luck"),
                new DataSet("레이스", "Race"),
                new DataSet("무작위 생산", "Random Production"),
                new DataSet("전투 결과표", "Ratio / Combat Results Table"),
                new DataSet("주사위 재굴림", "Re-rolling and Locking"),
                new DataSet("실시간", "Real-Time"),
                new DataSet("상대적 위치", "Relative Movement"),
                new DataSet("이동 자원 소모", "Resource to Move"),
                new DataSet("가위 바위 보", "Rock-Paper-Scissors"),
                new DataSet("롤 플레이", "Role Playing"),
                new DataSet("비대칭 역할 / 마피아", "Roles with Asymmetric Information"),
                new DataSet("말 이동 주사위", "Roll / Spin and Move"),
                new DataSet("원형 액션 레일", "Rondel"),
                new DataSet("시나리오 게임", "Scenario / Mission / Campaign Game"),
                new DataSet("추가 라운드 게임", "Score-and-Reset Game"),
                new DataSet("비밀 유닛 배치", "Secret Unit Deployment"),
                new DataSet("선택 순서 입찰", "Selection Order Bid"),
                new DataSet("반 협동 게임", "Semi-Cooperative Game"),
                new DataSet("세트 컬렉션", "Set Collection"),
                new DataSet("시뮬레이션", "Simulation"),
                new DataSet("동시 작업 선택", "Simultaneous Action Selection"),
                new DataSet("노래", "Singing"),
                new DataSet("진 사람 1명 / 꼴지 1명", "Single Loser Game"),
                new DataSet("토큰 밀기", "Slide/Push"),
                new DataSet("솔로잉 가능", "Solo / Solitaire Game"),
                new DataSet("순발력", "Speed Matching"),
                new DataSet("사각 맵", "Square Grid"),
                new DataSet("균형잡기", "Stacking and Balancing"),
                new DataSet("난수 비교", "Stat Check Resolution"),
                new DataSet("말 이동", "Static Capture"),
                new DataSet("주식 보유", "Stock Holding"),
                new DataSet("스토리텔링", "Storytelling"),
                new DataSet("서든 데스 엔딩", "Sudden Death Ending"),
                new DataSet("견제", "Take That"),
                new DataSet("단서 암호화", "Targeted Clues"),
                new DataSet("팀 게임", "Team-Based Game"),
                new DataSet("액션 강화", "Tech Trees / Tech Tracks"),
                new DataSet("3차원", "Three Dimensional Movement"),
                new DataSet("타일 놓기", "Tile Placement"),
                new DataSet("타임 트랙", "Time Track"),
                new DataSet("트랙 이동", "Track Movement"),
                new DataSet("거래", "Trading"),
                new DataSet("배신자", "Traitor Game"),
                new DataSet("트릭테이킹", "Trick-taking"),
                new DataSet("줄다리기", "Tug of War"),
                new DataSet("선턴 경매", "Turn Order: Auction"),
                new DataSet("선턴 클레임", "Turn Order: Claim Action"),
                new DataSet("선턴 패스 오더", "Turn Order: Pass Order"),
                new DataSet("선 플레이어 토큰", "Turn Order: Progressive"),
                new DataSet("무작위 선턴", "Turn Order: Random"),
                new DataSet("역할 기반 선턴", "Turn Order: Role Order"),
                new DataSet("통계 기반 선턴", "Turn Order: Stat-Based"),
                new DataSet("가변 순서", "Variable Phase Order"),
                new DataSet("고유 스킬", "Variable Player Powers"),
                new DataSet("가변적 시작 상태", "Variable Setup"),
                new DataSet("자원 승점", "Victory Points as a Resource"),
                new DataSet("투표", "Voting"),
                new DataSet("일꾼 배치", "Worker Placement"),
                new DataSet("일꾼 주사위 배치", "Worker Placement with Dice Workers"),
                new DataSet("전문가 일꾼", "Worker Placement, Different Worker Types"),
                new DataSet("통제 영역", "Zone of Control")
        );

        for(DataSet each : dataSets){
            mechanismRepository.save(each.getMechData());
        }
    }


    private void savePublisher(BoardgameCompany boardgameCompany){
        if(publisherRepository.findByKorName(boardgameCompany.getKorName()) == null)
            publisherRepository.save(new Publisher(boardgameCompany.getKorName(), boardgameCompany.getEngName()));
    }

    // BoardgameCompany enum 에 등록된 퍼블리셔 처리
    public void setPublishers(){
        savePublisher(BoardgameCompany.KOREA_BOARDGAMES);
        savePublisher(BoardgameCompany.POPCORN_GAMES);
    }
}
