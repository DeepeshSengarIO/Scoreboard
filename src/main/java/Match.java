public class Match {
    private final String home;
    private final String away;
    private int homeScore;
    private int awayScore;
    private final int startTime;

    public Match(String home, String away, int startTime) {
        this.home = home;
        this.away = away;
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = startTime;
    }

    public MatchSummary toSummary() {
        return new MatchSummary(home, away, homeScore, awayScore);
    }

    public void setHomeTeamScore(int newScore){
        this.homeScore=newScore;
    }

    public void setAwayTeamScore(int newScore){
        this.awayScore=newScore;
    }
}
