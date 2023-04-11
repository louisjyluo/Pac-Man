# Pac-Man

What will the application do?

The application will simulate the game Pac-Man. 
The game will include the usual mechanics seen in 
regular Pac-Man, and a few added features to make the
game more interesting.

Who will use it?

Since I am designing a video game, the audience will be 
video game players.

Why is this project of interest to you? 

I've always wanted to work on a personal video game that 
has elements designed and implemented personally. Previously, 
I've designed Snake, and a 2D sand simulator in Java with 
help from either videos, or other people. Pac-Man will be
my first true solo project designed from scratch.

## User Stories
- As a user, I want a map that features Pac-Man
- As a user, the map needs to have walls, and pellets
- As a user, Pac-Man needs to be able to collide with the walls
- As a user, touching a ghost will kill me
- As a user, I want to be able to collect pellets
- As a user, these pellets need to give me points
- As a user, I want to be able to move as Pac-Man
- As a user, I want ghosts chasing after me
- As a user, I want items on the map that will power up Pac-Man
- As a user, I want my score added to the scoreboard
- As a user, I want multiple lives with Pac-Man
- As a user, the game will rerun once all the pellets are gone.
- As a user, the game requires a save and quit option
- As a user, the game will load back my previous save
 
## Instruction for Grader

- You can generate the first required action related to adding Xs to a Y by pressing 
the button "add Ghost", you will add another ghost into the game.
That ghost will behave like all the previous ghosts at the beginning of the game. 


- You can generate the second required action related to adding Xs to a Y by pressing the arrow keys to
move around in the game. It has the same rules as pac man. There's 
also added hotkeys for the three buttons above. pressing A will add Ghosts, S to save, and L to load.


- You can locate my visual component by looking in the Images file. Located above the lib folder 
and below the data folder.


- You can save the state of my application by either press S or click the Save button.


- You can reload the state of my application by either press L or click the Load button.

## Phase 4: Task 2

User adds ghost to game*

EventLog logs "added to the map"

User removes ghost to game*

EventLog logs "removed from the map"

To get the log to print in the end, press E to save and exit with the log, another way to exit is by pressing f4,
which will only exit and print the log without saving. Closing the window itself will not execute the print log function 
and nothing will happen.

## Phase 4: Task 3

The design of the models is a class on the very top with
associations to every other model. The PacManGame class initializes PacMan, 
Ghost, Pellets, Walls, and PowerUps. If there was time to 
refactor certain parts of the code, I would create an intermediate class that 
creates the board of PacMan instead of doing all that within the PacManGame class. Another 
possible refactor is creating a game board that is complied of walls, pellets, and powerUps classified by
numbers 1, 2, and 3. The point is to make a faster functioning code that can make walls detection a lot faster. The 
current code loops through every wall and pellet to see if PacMan can interact with it. For a smaller map, the 
code would be viable, but as the maps increase in size. Each tick significantly increase the memory usage. A refactor 
that can remove the loop through the implementation of an intermediate board class would reduce the
lag and memory usage for this PacManGame model. The Position class also could've had an override equal method, so instead
of calling the X and Y positions of Position to compare the 2 Positions I can just have a method that checks for equals.

