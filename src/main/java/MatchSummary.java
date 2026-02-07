/**
 * Immutable read-only representation of a football match summary.
 *
 * This record is used to expose match information to clients without
 * allowing modification of internal domain state.
 */
public record MatchSummary(
        String homeTeam,
        String awayTeam,
        int homeScore,
        int awayScore
) {}
