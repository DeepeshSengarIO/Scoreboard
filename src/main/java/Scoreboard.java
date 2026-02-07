import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scoreboard {
    private final HashMap<String, Match> matches;
    public Scoreboard(){
        matches = new HashMap<>();
    }

    /**
     * Returns a summary of ongoing matches ordered by:
     * 1. Total score (descending)
     * 2. Most recently started match
     */
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

    /**
     * Starts a new football match with an initial score of 0–0.
     *
     * @throws IllegalArgumentException if the match already exists
     */
    public void startGame(String home, String away, int startTime) {
        String key = key(home, away);
        if (matches.containsKey(key)) {
            throw new IllegalArgumentException("Match already started");
        }
        matches.put(
                key,
                new Match(home, away, startTime)
        );
    }
    private String key(String h, String a) {
        return h + "-" + a;
    }

    /**
     * Updates the score of an existing match.
     *
     * @throws IllegalArgumentException if the match does not exist
     */
    public void updateScore(String home, String away, int homeScore, int awayScore) {
        String key = key(home, away);
        try{
            if (matches.containsKey(key)){
                Match existing = matches.get(key);
                existing.setHomeTeamScore(homeScore);
                existing.setAwayTeamScore(awayScore);
                matches.put(key, existing);
            }else throw new IllegalArgumentException("Key not found");
        }catch (IllegalArgumentException message){
            throw new IllegalArgumentException("Match not found");
        }
    }

    /**
     * Finishes a match and removes it from the scoreboard.
     *
     * @throws IllegalArgumentException if the match does not exist
     */
    public void finishGame(String home, String away) {
        String key = key(home, away);
        if (matches.remove(key) == null) {
            throw new IllegalArgumentException("Match not found");
        }
    }
}
