# Card Matching game for CIT 270

## Description
Our group project for CIT270 is a memory match card game where the user can start a game and select two cards from a set of 16 face-down cards on the screen. The chosen cards will be flipped over, revealing the images underneath. If the images match, the cards will remain face-up, and the user will earn points. If they don't match, the cards will flip back to the blank side, and the user can pick another pair of cards to try again.

The game will offer three difficulty levels: easy, medium, and hard. In the easy mode, there will be no time limit and fewer cards on the screen, with no special cards. As the difficulty increases, a time limit will be introduced, and the user will have a limited number of attempts to match all the cards correctly. There may also be additional card types, such as decoy cards without matches, and the time limit to view the picture side of the flipped cards before they revert to the blank side.



## Installation

```bash
## Example command
git clone https://github.com/ST3ALTHY-0/CIT270FinalProject.git
cd CIT270FinalProject
```


## How to Compile and Run
 You need to be in parent directory of finalProject to run the code correctly because some of the code relies on relative paths

```bash
### Compile
javac -cp "finalProject/lib/*" -d finalProject/bin finalProject/src/*.java

### Run
java -cp ".;finalProject/bin;finalProject/lib/*" finalProject.src.GameGUI
```

## Project authors
Luke Monroe, Jerin Gamagama, Cody CyberBatMan
