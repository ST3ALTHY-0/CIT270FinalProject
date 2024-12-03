package src;

//import java.util.Timer;

public class Score {

    private int score;
    private int gameDifficultyScoreMultiplier;
    private long startTime;
    private long endTime;
    private static final int BASE_SCORE = 1000;
    private static final int TIME_FACTOR = 10000;


    public Score(GameBoardInitialization init){
        gameDifficultyScoreMultiplier = setGameDifficultyScoreMultiplier(init);
    }

    public void startGame() {
        startTime = System.currentTimeMillis();
    }

    public void endGame() {
        endTime = System.currentTimeMillis();
    }

    private long getElapsedTime() {
        return endTime - startTime;
    }

    public void doMathForScore(){
        long elapsedTime = getElapsedTime();
        //*math for calculations was looked up*
        double timeFactor = (double) elapsedTime / TIME_FACTOR;
        int calculatedScore = (int) (BASE_SCORE / (1 + timeFactor));
        score = calculatedScore * gameDifficultyScoreMultiplier;
        }

    private int setGameDifficultyScoreMultiplier(GameBoardInitialization init){
        if (init instanceof EasyGame){
            return 1;
        }else if (init instanceof MediumGame){
            return 2;
        }else{
            return 3;
        }
    }

    public String getGameType(){
        if(gameDifficultyScoreMultiplier == 1 ){
            return "Easy";
        }else if (gameDifficultyScoreMultiplier == 2){
            return "Medium";
        }else{
            return "Hard";
        }
    }

    public int getScore(){
        return score;
    }

}
