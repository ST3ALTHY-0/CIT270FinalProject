package src;

import java.sql.Timestamp;

/*
 * This class is meant to wrap score, a given name, gameType and
 *  the time this object was created into one type for saving in
 *  the Database.
 */

public class Data {
    private int score;
    private String gameType;
    private String name;
    private Timestamp time;

    //for sending data to db
    public Data(int score, String gameType, String name) {
        this.score = score;
        this.gameType = gameType;
        this.name = name;
        time = new Timestamp(System.currentTimeMillis());

    }

    //for retriving data from db
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

    public String getGameType() {
        return gameType;
    }

    public static String getGameTypeToString(GameBoardInitialization init) {//might not need this method
        if (init instanceof EasyGame) {
            return "Easy";
        } else if (init instanceof MediumGame) {
            return "Medium";
        } else {
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
