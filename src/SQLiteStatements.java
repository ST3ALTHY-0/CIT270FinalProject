// package finalProject.src;

// public interface SQLiteStatements {
//     static final String LOCAL_DB_URL = "jdbc:sqlite:finalProject\\db\\matchingGamedb.db";
//     static final String INSERT_GAMES_SQLITE_STATEMENT = "INSERT OR IGNORE INTO Games(gameType) VALUES(?)";
//     static final String CREATE_USERS_TABLE_SQLITE_STATEMENT = "CREATE TABLE IF NOT EXISTS Users (\n"
//             + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
//             + " username TEXT NOT NULL UNIQUE\n"
//             + ");";
//     static final String CREATE_GAMES_TABLE_SQLITE_STATEMENT = "CREATE TABLE IF NOT EXISTS Games (\n"
//             + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
//             + " gameType TEXT NOT NULL UNIQUE\n"
//             + ");";
//     static final String CREATE_SCORES_TABLE_SQLITE_STATEMENT = "CREATE TABLE IF NOT EXISTS Scores (\n"
//         + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
//         + " userId INTEGER NOT NULL,\n"
//         + " gameId INTEGER NOT NULL,\n"
//         + " score INTEGER NOT NULL,\n"
//         + " timeStamp INTEGER NOT NULL,\n"
//         + " FOREIGN KEY (userId) REFERENCES Users(id),\n"
//         + " FOREIGN KEY (gameId) REFERENCES Games(id)\n"
//         + ");";
//     static final String SELECT_TABLE_FROM_DB_SQLITE_STATEMENT = "SELECT name FROM sqlite_master WHERE type='table' AND name=?";
//     static final String SELECT_DATA_SQLITE_STATEMENT = "SELECT u.username, g.gameType, s.score, s.timeStamp FROM Scores AS s JOIN Users AS u ON s.userId = u.id JOIN Games AS g ON s.gameId = g.id LIMIT ?";
//     static final String INSERT_SCORES_SQLITE_STATEMENT = "INSERT INTO Scores(userId, gameId, score, timeStamp) VALUES(?, ?, ?, ?)";
//     static final String INSERT_USERS_SQLITE_STATEMENT = "INSERT OR IGNORE INTO Users(username) VALUES(?)";
// }

package src;

public enum SQLiteStatements {
    LOCAL_DB_URL("jdbc:sqlite:./db/matchingGamedb.db"),
    INSERT_INTO_GAMES("INSERT OR IGNORE INTO Games(gameType) VALUES(?)"),
    CREATE_USERS_TABLE("CREATE TABLE IF NOT EXISTS Users (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " username TEXT NOT NULL UNIQUE\n"
            + ");"),
    CREATE_GAMES_TABLE("CREATE TABLE IF NOT EXISTS Games (\n"
            + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
            + " gameType TEXT NOT NULL UNIQUE\n"
            + ");"),
    CREATE_SCORES_TABLE("CREATE TABLE IF NOT EXISTS Scores (\n"
        + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
        + " userId INTEGER NOT NULL,\n"
        + " gameId INTEGER NOT NULL,\n"
        + " score INTEGER NOT NULL,\n"
        + " timeStamp INTEGER NOT NULL,\n"
        + " FOREIGN KEY (userId) REFERENCES Users(id),\n"
        + " FOREIGN KEY (gameId) REFERENCES Games(id)\n"
        + ");"),
    SELECT_TABLE_FROM_DB("SELECT name FROM sqlite_master WHERE type='table' AND name=?"),
    SELECT_DATA_FROM_SCORES("SELECT u.username, g.gameType, s.score, s.timeStamp FROM Scores AS s JOIN Users AS u ON s.userId = u.id JOIN Games AS g ON s.gameId = g.id ORDER BY s.score DESC LIMIT ?"),
    INSERT_INTO_SCORES("INSERT INTO Scores(userId, gameId, score, timeStamp) VALUES(?, ?, ?, ?)"),
    INSERT_INTO_USERS("INSERT OR IGNORE INTO Users(username) VALUES(?)");

    private final String sql;

    SQLiteStatements(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

}

