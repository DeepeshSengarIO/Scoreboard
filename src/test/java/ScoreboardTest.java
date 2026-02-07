import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreboardTest {
    @Test
    void gameSummaryIsEmptyWhenNoGameStarted(){
        Scoreboard scoreboard = new Scoreboard();
        assertTrue(scoreboard.getSummary().isEmpty());
    }
}
