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

    @Test
    void sameScoreOrderedByMostRecentStartTime() {
        Scoreboard scoreboard = new Scoreboard();

        scoreboard.startGame("Spain", "Brazil", 900);
        scoreboard.startGame("Mexico", "Canada", 915);
        scoreboard.startGame("Japan", "Uruguay", 930);

        scoreboard.updateScore("Spain", "Brazil", 3, 2);
        scoreboard.updateScore("Mexico", "Canada", 2, 3);
        scoreboard.updateScore("Japan", "Uruguay", 2, 3);

        List<MatchSummary> summary = scoreboard.getSummary();

        assertEquals("Japan", summary.get(0).homeTeam());
    }

    @Test
    void finishedGameIsRemoved() {
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startGame("Spain", "Brazil", 900);
        scoreboard.finishGame("Spain", "Brazil");
        assertTrue(scoreboard.getSummary().isEmpty());
    }

    @Test
    void testCompleteMatchSummary(){

        Scoreboard scoreboard = new Scoreboard();
        scoreboard.startGame("Mexico", "Canada", 900);
        scoreboard.startGame("Spain", "Brazil", 915);
        scoreboard.startGame("Germany", "France", 930);
        scoreboard.startGame("Uruguay", "Italy", 945);
        scoreboard.startGame("Argentina", "Australia", 950);

        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        List<MatchSummary> summary = scoreboard.getSummary();
        assertEquals("Uruguay", summary.get(0).homeTeam());
        assertEquals("Spain", summary.get(1).homeTeam());
        assertEquals("Mexico", summary.get(2).homeTeam());
        assertEquals("Argentina", summary.get(3).homeTeam());
        assertEquals("Germany", summary.get(4).homeTeam());
    }
}
