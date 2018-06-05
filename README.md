# "Guess 5" Word Guessing Game

In this game, the computer picks an arbitrary 5-letter word from the dictionary. You have a set number of turns (that you set for yourself) to guess which word the computer has chosen. For every guess, the computer will indicate which letters of your guess were correct: '\*' for in-place matches (correct letters in the correct position) and '+' for out-of-place matches (letters that do appear in the word, but not in the same position). **However, note that no information about specific positions is provided in the feedback.** The number of each type of match is revealed, but the user is not told which positions the matches correspond to.

## Input
You can only use 5-letter, lowercase character Strings to guess.

You can also input 'r' (for remaining words) into the guess field to bring up a list of all possible leftover words based on your previous guesses.

## Matches

An in-place match is a letter that is both the same, and in the same place, in a guess and a code word. For example, the words “tests” and “tasks” have three in-place matches between them: Both have a ‘t’ in position 0, an ‘s’ in position 2, and another ‘s’ in position 4.

An out-of-place match is a letter in a guess word that is not an in-place match, but is a letter in the code word at a position that is also not an in-place match. For example, “tests” and “treat” have two out-of-place matches between them: The ‘e’ in position 1 of “tests” is in position 2 of “treats,” and the second ‘t’ of “tests” in position 3 corresponds to the second ‘t’ of “treat,” which is in position 4. The ‘t’ in position 0 of both words is not counted because it is an in-place match.

## Example

Do you want to play Code Word? (y/n)? **_y_**

How many guesses do you want? **_10_**

10: Guess a 5-letter word (enter 'r' for remaining words): **_plane_**

	plane: ++

9: Guess a 5-letter word (enter 'r' for remaining words): **_lanes_**

	plane: ++

	lanes: ***

8: Guess a 5-letter word (enter 'r' for remaining words): **mones**

	plane: ++

	lanes: ***

	mones: **

7: Guess a 5-letter word (enter 'r' for remaining words): **_tanem_**

	plane: ++

	lanes: ***

	mones: **

	tanem: *

6: Guess a 5-letter word (enter 'r' for remaining words): **_linos_**

	plane: ++

	lanes: ***

	mones: **

	tanem: *

	linos: **

5: Guess a 5-letter word (enter 'r' for remaining words): **_latos_**

	plane: ++

	lanes: ***

	mones: **

	tanem: *

	linos: **

	latos: **

4: Guess a 5-letter word (enter 'r' for remaining words): **_lanem_**

	plane: ++

	lanes: ***

	mones: **

	tanem: *

	linos: **

	latos: **

	lanem: **

3: Guess a 5-letter word (enter 'r' for remaining words): **_lonts_**

	plane: ++

	lanes: ***

	mones: **

	tanem: *

	linos: **

	latos: **

	lanem: **

	lonts: **

2: Guess a 5-letter word (enter 'r' for remaining words): **_lantr_**

	plane: ++

	lanes: ***

	mones: **

	tanem: *

	linos: **

	latos: **

	lanem: **

	lonts: **

	lantr: *


1: Guess a 5-letter word (enter 'r' for remaining words): **_r_**

	Remaining words:
	
	leges
	
	lexes
	
	lezes
	
	lubes
	
	luces
	
	ludes
	
	luges
	
	luxes
	
	lyses
	
1: Guess a 5-letter word (enter 'r' for remaining words): **_leges_**

	plane: ++

	lanes: ***

	mones: **

	tanem: *

	linos: **

	latos: **

	lanem: **

	lonts: **

	lantr: *

	leges: ***

You are out of guesses!
