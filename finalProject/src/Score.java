package finalProject.src;

//import java.util.Timer;

public class Score {

    private int score;
    private byte gameDifficultyScoreMultiplier;
    private long startTime;
    private long endTime;
    private static final int BASE_SCORE = 1000;
    private static final int TIME_FACTOR = 10000;


    public Score(GameManager game){
        gameDifficultyScoreMultiplier = setGameDifficultyScoreMultiplier(game);
    }

    public void startGame() {
        startTime = System.currentTimeMillis();
    }

    public void endGame() {
        endTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        return endTime - startTime;
    }
    //stop the timer after game is over
    //do some math
    //have score

    public void doMathForScore(){
        System.out.println(getElapsedTime());
        long elapsedTime = getElapsedTime();
        //getElapsedTime();
        //do some math with the time it took them to finish and game difficulty.
        double timeFactor = (double) elapsedTime / TIME_FACTOR;
        int calculatedScore = (int) (BASE_SCORE / (1 + timeFactor));
        System.out.println("Score is: " + calculatedScore * gameDifficultyScoreMultiplier);
        score = calculatedScore * gameDifficultyScoreMultiplier;
        }

    public byte setGameDifficultyScoreMultiplier(GameManager game){
        if (game instanceof EasyGame){
            return 1;
        }else if (game instanceof MediumGame){
            return 2;
        }else{
            return 3;
        }

    }

    public int getScore(){
        return score;
    }
}
