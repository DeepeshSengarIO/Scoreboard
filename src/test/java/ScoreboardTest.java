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
        scoreboard.startGame("Mexico", "Canada", 900); // I assumed time to 900 as it started at 9:00 AM (24-hour-clock) for easiness but a time producer can be injected.
        List<MatchSummary> summary = scoreboard.getSummary();
        assertEquals(1, summary.size());
        assertEquals("Mexico", summary.get(0).homeTeam());
        assertEquals("Canada", summary.get(0).awayTeam());
        assertEquals(0, summary.get(0).homeScore());
        assertEquals(0, summary.get(0).awayScore());
    }

    @Test
    void updatingScoreUpdatesSummary() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startGame("Spain", "Brazil", 900);
        scoreboard.updateScore("Spain", "Brazil", 2, 1);
        MatchSummary match = scoreboard.getSummary().get(0);
        assertEquals(2, match.homeScore());
        assertEquals(1, match.awayScore());
    }

    @Test
    void summaryOrderedByTotalScoreDescending() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startGame("Spain", "Brazil", 900);
        scoreboard.startGame("Mexico", "Canada", 915);
        scoreboard.updateScore("Spain", "Brazil", 4, 1);
        scoreboard.updateScore("Mexico", "Canada", 3, 0);

        List<MatchSummary> summary = scoreboard.getSummary();
        assertEquals("Spain", summary.get(0).homeTeam());
        assertEquals("Mexico", summary.get(1).homeTeam());
    }
}
