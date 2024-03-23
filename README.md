# Snake and Ladder

## Input Format
Input has to be added in input.txt file available in dao folder in following format:
- [ ] S - Total number of snakes
- [ ] snakes[][2] - Following S lines contains pair (Snake’s Head and Snake’s Tail)
- [ ] L - Total number of ladders
- [ ] ladders[][2] - Following L lines contains pair (Ladder bottom and Ladder top)
- [ ] N - Total number of players
- [ ] List<Players> - Following N lines contains names & starting locations of each Player
- [ ] D - No. of dice (An override to manually enter the D die values that each player rolled in each turn.
  (If absent, Default value will be taken as 1))
- [ ] Movement Strategy - Movement strategy is either SUM (sum of numbers on dies), MAX (max of numbers on
  dies), MIN (min of number on dies)

## Rules
- [ ] Snake always takes you to the cell where its tail is, and has to be a number less than
where you are at currently. 
- [ ] Ladder takes you up (strictly). 
- [ ] If a player (A) comes to a cell where another player (B) is placed already, the previously
placed player (B) has to start again from 1.

## Optional Extensions
- [ ] Using the configuration like no. of snakes and ladders, Generate a random valid board 
  - Logic's already inplace. Can be acheived by uncommenting code at few places.