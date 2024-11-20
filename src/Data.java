package src;


import java.sql.Timestamp;

public class Data {
    private int score;
    private String gameType;
    private String name;
    private Timestamp time;


    public Data(int score, String gameType, String name) {
        this.score = score;
        this.gameType = gameType;
        this.name = name;
        time = new Timestamp(System.currentTimeMillis());
     
    }
   // public Data(){};

    public Data(String name, String gameTypeString, int score, long timeStampMS) {
        this.name = name;
        this.gameType = gameTypeString;
        this.score = score;
        time = new Timestamp(timeStampMS); 
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }
    public String getGameType(){
        return gameType;
    }

    public static String getGameTypeToString(GameManager game){
        if (game instanceof EasyGame){
            return "Easy";
        }else if (game instanceof MediumGame){
            return "Medium";
        }else{
            return "Hard";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp timestamp) {
        time = timestamp;
    }

}
