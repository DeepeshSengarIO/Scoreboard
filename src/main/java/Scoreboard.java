import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scoreboard {
    private final HashMap<String, Match> matches;
    public Scoreboard(){
        matches = new HashMap<>();
    }
    public List<MatchSummary> getSummary(){
        List<MatchSummary> result = new ArrayList<>();
        for (Match match : matches.values()) {
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
}
