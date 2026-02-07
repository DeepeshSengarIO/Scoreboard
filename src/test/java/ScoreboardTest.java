import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreboardTest {
    @Test
    void gameSummaryIsEmptyWhenNoGameStarted(){
        Scoreboard scoreboard = new Scoreboard();
        assertTrue(scoreboard.getSummary().isEmpty());
    }

    @Test
    void startedGameAppearsInSummaryWithZeroScore() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startGame("Mexico", "Canada", 900); // I assumed time to 900 as it started at 9:00 AM for easiness but a time producer can be injected.
        List<MatchSummary> summary = scoreboard.getSummary();
        assertEquals(1, summary.size());
        assertEquals(0, summary.get(0).homeScore());
        assertEquals(0, summary.get(0).awayScore());
    }
}
