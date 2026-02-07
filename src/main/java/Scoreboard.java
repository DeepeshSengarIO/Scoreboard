import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scoreboard {
    private final HashMap<String, Match> matches;
    public Scoreboard(){
        matches = new HashMap<>();
    }
    public List<MatchSummary> getSummary(){
        List<Match> ordered = new ArrayList<>(matches.values());
        ordered.sort((p1, p2)->{
                    int sumCompare = Integer.compare(p2.totalScore(), p1.totalScore()); // by descending order of total score
                    if (sumCompare!=0) return sumCompare;
                    return Integer.compare(p2.getStartTime(), p1.getStartTime()); // further compare by startTime
                }
        );
        List<MatchSummary> result = new ArrayList<>();
        for (Match match : ordered) {
            result.add(match.toSummary());
        }
        return result;
    }

    public void startGame(String home, String away, int startTime) {
        matches.put(
                key(home, away),
                new Match(home, away, startTime)
        );
    }
    private String key(String h, String a) {
        return h + "-" + a;
    }
    public void updateScore(String home, String away, int homeScore, int awayScore) {
        String key = key(home, away);
        Match existing = matches.get(key);
        existing.setHomeTeamScore(homeScore);
        existing.setAwayTeamScore(awayScore);
        matches.put(key, existing);
    }
}
