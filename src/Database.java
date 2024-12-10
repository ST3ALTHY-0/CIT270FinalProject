package src;
//TODO: create restful api with spring boot that will handle the direct DB transactions and connect to it to this class

//TODO: To make this class better A: split into multiple classes, like initialization and running db, B: replace the commented out System.out.println's with Log messages that go into Log file
// Looked up some syntax related to java.sql
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

//looked up syntax related to Dotenv
import io.github.cdimascio.dotenv.Dotenv;
import java.nio.file.Paths;

    // also note that the remote db uses MYSQL (because this was the first remote db
    // i found for free and it only offered MYSQL) and the local db uses SQLite
    // this is because sqlite is just a whole lot easier to work with in simple
    // java applications like this.
    // The two DBMS require slightly different SQL syntax, and also for the local db
    // we need to be able to create and setup the db, where as in the remote we just need
    // to be able to insert and select data only.

    //NOTE: There is a bug with remote db, where the remote db goes inactive after ~15min of no activity, and then you try to get the connection for the first time
    //since the db has gone inactive, the connection fails to establish. 
    //To get pass this, you have to fail to get the remote db by running the program once, and then once the remote db is activated by the 
    //failed connection you can run the program fine after that until once again the db goes to sleep.

    //I tried to fix this bug by trying to get the connection, waiting some seconds and then trying to get the connection again,
    //but this did not work, only completely stopping the program and running again ever fixed this. Im sure there is a fix to this, 
    //I just dont know that much about 'java.sql'.

public class Database {
    private Connection connection;
    private boolean soup = true;// controls if DB uses remote db or local db in the db directory(tr ue = local :
                                // false = remote)




    public Database(boolean isUsingLocalDB) {
        soup = isUsingLocalDB;
        try {
            if (soup) {
                getLocalDBConnection();
            } else {
                getRemoteDBConnection();
            }
            // System.out.println(connection.toString());
            // System.out.println("Here");
        } catch (Exception e) {
            // e.printStackTrace();
        }
    }

    void swapDBConnection() {
        closeConnection();
        soup = !soup;
        if (soup) {
            getLocalDBConnection();
        } else {
            getRemoteDBConnection();
        }
    }

    public void setSoup(boolean soup) {
        this.soup = soup;
    }

    public boolean getSoup() {
        return soup;
    }

    // private void getRemoteDBConnection() {
    //     Dotenv dotenv = Dotenv.configure()
    //             .directory(Paths.get("./private").toString())
    //             .filename("cred.env")
    //             .load();
    //     try {
    //         connection = DriverManager.getConnection(dotenv.get("DB_URL"), dotenv.get("DB_USER"),
    //                 dotenv.get("DB_PASSWORD"));
    //         connection.setAutoCommit(false); // had problems with autoCommit so turned it off
    //         System.out.println("Remote Database connection established.");
    //     } catch (Exception e) {
    //         System.out.println("Remote Database connection failed to established.");
    //         // getRemoteDBConnection();//maybe dont do this
    //     }

    // }
    private void getRemoteDBConnection() {
        Dotenv dotenv = Dotenv.configure()
                .directory(Paths.get("./private").toString())
                .filename("cred.env")
                .load();     
            try {
                connection = DriverManager.getConnection(dotenv.get("DB_URL"), dotenv.get("DB_USER"),
                        dotenv.get("DB_PASSWORD"));
                connection.setAutoCommit(false); // had problems with autoCommit so turned it off
                System.out.println("Remote Database connection established.");
            } catch (SQLException e) {
                System.out.println("Remote Database connection failed to establish.");
                e.printStackTrace();      
        }
    }

    private void getLocalDBConnection() {
        try {
            connection = DriverManager.getConnection(SQLiteStatements.LOCAL_DB_URL.getSql());
            connection.setAutoCommit(false); // had problems with autoCommit so turned it off
            if (!doesTablesExist()) {
                createTables();
                insertDataIntoGames();
                // System.out.println("tables created");
            }
            System.out.println("Local database connection established.");
        } catch (SQLException e) {
            System.out.println("Failed to establish local database connection.");
            // e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                // System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            // System.out.println("Failed to close database connection.");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    private void rollbackTransaction() {
        try {
            connection.rollback();
            // System.out.println("Transaction rolled back.");
        } catch (Exception e) {
            // e.printStackTrace();
            // System.out.println("Failed to rollback transaction.");
        }
    }

    // not used(only for testing)
    private boolean doesTablesExist() {
        String[] tables = { "Users", "Games", "Scores" };
        for (String table : tables) {
            try (PreparedStatement pstmt = connection
                    .prepareStatement(SQLiteStatements.SELECT_TABLE_FROM_DB.getSql())) {
                pstmt.setString(1, table);
                ResultSet rs = pstmt.executeQuery();
                connection.commit();
                if (!rs.next()) {
                    // System.out.println("Tables don't exist.");
                    return false;
                }
            } catch (SQLException e) {
                // e.printStackTrace();
                // System.out.println("Error checking table existence: " + e.getMessage());
                return false;
            }
        }
        // System.out.println("Tables exist.");
        return true;
    }

    // not used(only for testing)
    private void recreateScoresTable() {
        String dropTable = "DROP TABLE IF EXISTS Scores";
        String createTable = "CREATE TABLE Scores (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " userId INTEGER NOT NULL,\n"
                + " gameId INTEGER NOT NULL,\n"
                + " score INTEGER NOT NULL,\n"
                + " timeStamp TEXT NOT NULL,\n"
                + " FOREIGN KEY (userId) REFERENCES Users(id),\n"
                + " FOREIGN KEY (gameId) REFERENCES Games(id)\n"
                + ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(dropTable);
            stmt.execute(createTable);
            connection.commit(); // Commit transaction
            // System.out.println("Scores table has been recreated successfully.");
        } catch (SQLException e) {
            // e.printStackTrace();
            // System.out.println("Error recreating Scores table: " + e.getMessage());
        }
    }

    // not used(only for testing)
    private void dropAllTables() {
        String dropUsersTable = "DROP TABLE IF EXISTS Users";
        String dropGamesTable = "DROP TABLE IF EXISTS Games";
        String dropScoresTable = "DROP TABLE IF EXISTS Scores";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(dropUsersTable);
            stmt.execute(dropGamesTable);
            stmt.execute(dropScoresTable);
            connection.commit(); // Commit transaction
            // System.out.println("All tables have been dropped successfully.");
        } catch (SQLException e) {
            // e.printStackTrace();
            // System.out.println("Error dropping tables: " + e.getMessage());
            rollbackTransaction();
        }
    }

    // not used(only for testing)
    private void deleteAllDataFromTables() {
        String deleteUsersData = "DELETE FROM Users";
        String deleteGamesData = "DELETE FROM Games";
        String deleteScoresData = "DELETE FROM Scores";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(deleteUsersData);
            stmt.execute(deleteGamesData);
            stmt.execute(deleteScoresData);
            connection.commit();
            // System.out.println("All data has been deleted from tables successfully.");
        } catch (SQLException e) {
            // e.printStackTrace();
            // System.out.println("Error deleting data from tables: " + e.getMessage());
            rollbackTransaction();
        }
    }

    // not used(only for testing)
    private void createTables() {

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(SQLiteStatements.CREATE_USERS_TABLE.getSql());
            stmt.execute(SQLiteStatements.CREATE_GAMES_TABLE.getSql());
            stmt.execute(SQLiteStatements.CREATE_SCORES_TABLE.getSql());
            connection.commit();
            System.out.println("Tables have been created successfully.");
        } catch (SQLException e) {
            // e.printStackTrace();
            // System.out.println("Error creating tables: " + e.getMessage());
            rollbackTransaction();
        }
    }

    public void insertDataIntoUsers(Data data) {
        String sql = getSQLStatement(SQLiteStatements.INSERT_INTO_USERS.getSql(),
                MySQLStatements.INSERT_INTO_USERS.getSql());

        try (PreparedStatement sqlStatement = connection.prepareStatement(sql)) {
            sqlStatement.setString(1, data.getName());
            sqlStatement.executeUpdate();
            connection.commit();
            System.out.println("User inserted.");
        } catch (Exception e) {
            // e.printStackTrace();
            // System.out.println("Error inserting user: " + e.getMessage());
        }
    }

    private void insertDataIntoGames() {
        String[] gameTypes = { "Easy", "Medium", "Hard" };
        try (PreparedStatement pstmt = connection.prepareStatement(SQLiteStatements.INSERT_INTO_GAMES.getSql())) {
            for (String gameType : gameTypes) {
                pstmt.setString(1, gameType);
                pstmt.addBatch();
            }
            pstmt.executeBatch();
            // System.out.println("Games inserted.");
        } catch (SQLException e) {
            // System.out.println("Error inserting gameTypes: " + e.getMessage());
        }
    }

    public void insertDataIntoScores(Data data) {

        String sql = getSQLStatement(SQLiteStatements.INSERT_INTO_SCORES.getSql(),
                MySQLStatements.INSERT_INTO_SCORES.getSql());

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, getId(data.getName(), "Users", "username"));
            pstmt.setInt(2, getId(data.getGameType(), "Games", "gameType"));
            pstmt.setInt(3, data.getScore());
            // pstmt.setTimestamp(4, data.getTime());
            long timeStamp = data.getTime().getTime();
            pstmt.setLong(4, timeStamp);
            pstmt.executeUpdate();
            connection.commit();
            // System.out.println("Score inserted.");
        } catch (Exception e) {
            // System.out.println("Error inserting data into Scores: " + e.getMessage());
        }
    }

    private int getId(String query, String table, String column) {

        String SELECT_ID_SQL_STATEMENT = "SELECT id FROM " + table + " WHERE " + column + " = ?";
        // System.out.println(SELECT_ID_SQL_STATEMENT);
        try (PreparedStatement pstmt = connection.prepareStatement(SELECT_ID_SQL_STATEMENT)) {
            pstmt.setString(1, query);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                return result.getInt("id");
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            // System.out.println("Error selecting Id from table: " + e.getMessage());
        }
        return -1;
    }

    public ArrayList<Data> selectDataFromScores(int numOfRows) {
        ArrayList<Data> dataList = new ArrayList<>();
        String sql = getSQLStatement(SQLiteStatements.SELECT_DATA_FROM_SCORES.getSql(),
                MySQLStatements.SELECT_DATA_FROM_SCORES.getSql());
        // System.out.println(sql);

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            long timeStamp = 0;
            pstmt.setInt(1, numOfRows);
            ResultSet rs = pstmt.executeQuery();
            connection.commit();

            while (rs.next()) {
                try {
                    String username = rs.getString("username");
                    String gameType = rs.getString("gameType");
                    int score = rs.getInt("score");
                   // if (soup) {
                        timeStamp = rs.getLong("timeStamp"); // Slight difference in how data is stored remotely vs
                                                             // locally means we need to retrieve data slightly
                                                             // differently TODO: this is stupid and should be
                                                             // redesigned
                   // } else {
                   //     timeStamp = rs.getTimestamp("timeStamp").getTime();
                   // }
                    Data data = new Data(username, gameType, score, timeStamp);
                    dataList.add(data);
                } catch (SQLException e) {
                     System.out.println("Error parsing data row: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
             System.out.println("Error selecting data: " + e.getMessage());
        }

        // TODO: testing to show data in terminal/ remove later
        //System.out.println("hello");
        // for (Data data : dataList) {
        //     System.out.println(data.getName() + " : " + data.getScore());
        // }
        return dataList;
    }

    // returns the correct string of sql based on if soup is true or false (If we
    // are using remote[MYSQL] or local[SQLite] db)
    private String getSQLStatement(String SQLite, String MySQL) {
        if (soup) {
            return SQLite;
        } else {
            return MySQL;
        }
    }
}
