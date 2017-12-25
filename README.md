# "Guess 5" Word Guessing Game

In this game, the computer picks an arbitrary 5-letter word from the dictionary. You have a set number of turns (that you set for yourself) to guess which word the computer has chosen. For every guess, the computer will indicate which letters of your guess were correct: '\*' for in-place matches (correct letters in the correct position) and '+' for out-of-place matches (letters that do appear in the word, but not in the same position). **However, note that no information about specific positions is provided in the feedback.** The number of each type of match is revealed, but the user is not told which positions the matches correspond to.

### Input
You can only use 5-letter, lowercase character Strings to guess.

You can also input 'r' (for remaining words) into the guess field to bring up a list of all possible leftover words based on your previous guesses.

### Matches

An in-place match is a letter that is both the same, and in the same place, in a guess and a code word. For example, the words “tests” and “tasks” have three in-place matches between them: Both have a ‘t’ in position 0, an ‘s’ in position 2, and another ‘s’ in position 4.

An out-of-place match is a letter in a guess word that is not an in-place match, but is a letter in the code word at a position that is also not an in-place match. For example, “tests” and “treat” have two out-of-place matches between them: The ‘e’ in position 1 of “tests” is in position 2 of “treats,” and the second ‘t’ of “tests” in position 3 corresponds to the second ‘t’ of “treat,” which is in position 4. The ‘t’ in position 0 of both words is not counted because it is an in-place match.


	
