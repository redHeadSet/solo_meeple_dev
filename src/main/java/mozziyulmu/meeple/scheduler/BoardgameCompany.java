package mozziyulmu.meeple.scheduler;

public enum BoardgameCompany {

    KOREA_BOARDGAMES("8291", "코리아 보드게임즈", "Korea Boardgames Co., Ltd."),
    POPCORN_GAMES("41167", "팝콘 게임즈", "Popcorn Games");

    private final String geekId;
    private final String korName;
    private final String engName;

    BoardgameCompany(String geekId, String korName, String engName) {
        this.geekId = geekId;
        this.korName = korName;
        this.engName = engName;
    }
    public String getGeekId() {return geekId;}
    public String getKorName() {return korName;}
    public String getEngName() {return engName;}
}
