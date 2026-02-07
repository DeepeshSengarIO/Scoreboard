# Overview

---

This project implements a simple, in-memory Football World Cup Score Board as a Java library.
It supports starting matches, updating scores, finishing matches, and retrieving a summary of ongoing games ordered by total score and recency.

The implementation intentionally keeps the design minimal, readable, and focused on correctness and encapsulation, in line with the problem requirements.
# Functional Requirements

---
 - Start a game with initial score of 0-0
 - Update the score of an ongoing game
 - Finish a game and remove it from the scoreboard
 - Retrieve a summary of games ordered by:
   - Total score (descending)
   - Most recently started match (when total-scores are equal)

# Considerations

---
## 1. Explicit Time is passed when starting a game
In order to support deterministic ordering, I have put the match start time in ```startGame()``` explicitly. In a production scenario, this could be provided via a TimeProvider/Clock abstraction or injection.
## 2. Encapsulation and immutable summaries
Internal domain objects (Match) are never exposed outside the scoreboard.
The getSummary() method returns immutable MatchSummary records, detached from internal state.
This prevents external mutation and ensures all state changes go through the Scoreboard API, preserving domain invariants.
## 3. Use of Java records for summaries
MatchSummary is implemented as a Java record.
Records provide a concise, immutable data carrier suitable for read-only views, reduce boilerplate, and clearly communicate intent.
## 4. Defensive API behavior
Invalid operations result in explicit exceptions such as:
- starting a duplicate match
- updating a non-existing match
- finishing a non-existing match

# Assumptions

---
## 1. Single-Threaded
The scoreboard is assumed to be used in a single-threaded context.
Concurrency is not required by the problem statement, and avoiding synchronization keeps the implementation simple and readable.
If thread safety were required, concurrent data structures or synchronization mechanisms could be introduced.
## 2. One active match per team pair
A match is uniquely identified by its home and away team pair.
If Spain-Portugal match is going on then it is assumed it will not be added as a new entity until the match in ended, it can only be updated with scores or removed from scoreboard when already inside the scoreboard. 

# Final Notes

---

This solution prioritizes:
- Simplicity
- Correctness
- Clear domain boundaries
- Explicit API contracts

While minimal, the design leaves clear extension points for future enhancements such as time providers, persistence, or concurrency support if required.

Thank you :)
- by Deepesh Sengar
