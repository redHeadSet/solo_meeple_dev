package mozziyulmu.meeple.scheduler;

public enum BoardgameCompany {
    KOREA_BOARDGAMES("8291",
                    "코리아 보드게임즈",
                    "Korea Boardgames Co., Ltd.",
                    "코리아 보드게임즈 로고 이미지.jpg"),
    POPCORN_GAMES("41167",
                "팝콘 게임즈",
                "Popcorn Games",
            "팝콘 게임즈 로고 이미지.jpg");
    // 보드엠
    // 스타라이트
    // 로터스 프로그
    // 보드피아
    // 데빌다이스

    private final String geekId;
    private final String korName;
    private final String engName;
    private final String repImagePath;

    BoardgameCompany(String geekId, String korName, String engName, String repImagePath) {
        this.geekId = geekId;
        this.korName = korName;
        this.engName = engName;
        this.repImagePath = repImagePath;
    }
    public String getGeekId() {return geekId;}
    public String getKorName() {return korName;}
    public String getEngName() {return engName;}
    public String getRepImagePath() { return repImagePath;}
}
