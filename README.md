# Abstract-Strat-Connect-4
In this project I implemented a game of Connect 4 using MaxBook Air emojis 🔴 ⬜️ 🟡 to show the board. When working on implementing the state of my game I thought of how the game is played on a physical board. I thought of myself during an actual game, what am I seeing, how am I seeing, what changes etc. When initializing a game of connect 4 the default board is set with all "⬜️" emojis. I thought this was better than a white circle so players wouldn't possibly confuse it to be an actual piece. Every time the make move method is called, the state of the game changes and important variables are changed like the grid. My program works sort of like a chain, when make move is called, so are other methods, which call other methods that call other methods. The way I implemented the state of my game was so that just be calling the makeMove() method, multiple fields would change in order to determine things like ties, whos turn it is next, who won the game, and these things are determined by fields such as how many moves have been made so far, and what was the last piece played.
