package src;

import java.util.List;
import javax.swing.SwingUtilities;

import src.DTOs.Data;
import src.Deprecated.Database;
import src.Helper.ImageCache;
import src.JFrame.GameGUI;

public class GameLauncher {

    private static ScoreDataClient sdc;
    private GameGUI gameGUIInstance;
    /*
     * Database logic has been offloaded to RestAPI and ScoreDataClient should
     * be used instead to connect to send/receive data
     */
    @Deprecated 
    static Database db;
    private static final boolean isUsingLocalDB = false;

    @Deprecated
    void setupDB(boolean isUsingLocalDB) {
        db = new Database(isUsingLocalDB);
        db.customQuery();
    }

    public static void main(String[] args) {
        setupImages();
        getSDCConnection();
        SwingUtilities.invokeLater(() -> new GameGUI());
    }

    public GameGUI getGameGUI(){
        return gameGUIInstance;
    }

    /*
     * Note: these methods are static but could be changed to not be but it would
     * require a instance of this class to be passed to a few classes like HighScore
     */
    private static void getSDCConnection() {
        sdc = new ScoreDataClient();
    }

    private static void setupImages() {
        new Thread(() -> ImageCache.preloadImages()).start();
        //ImageCache.preloadImages();
    }

    public static List<Data> getHighScoreData(){
        return sdc.getDataFromAPI();
    }

    public static void sendHighScoreData(Data data){
        sdc.sendDataToAPI(data);
    }
}