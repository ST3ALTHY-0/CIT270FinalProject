package src;

// public interface MySQLStatements {
//     static final String SELECT_DATA_MYSQL_STATEMENT = "SELECT u.username, g.gameType, s.score, s.timeStamp FROM Scores AS s JOIN Users AS u ON s.userId = u.id JOIN Games AS g ON s.gameId = g.id LIMIT ?";
//     static final String INSERT_SCORES_MYSQL_STATEMENT = "INSERT INTO Scores(userId, gameId, score, timeStamp) VALUES(?, ?, ?, ?)";
//     static final String INSERT_USERS_MYSQL_STATEMENT = "INSERT IGNORE INTO Users(username) VALUES(?)";
// }
//replaced interface with enum. Enum is basically a type that specifically stores constants, so its really purpose made for this use case
public enum MySQLStatements{
    SELECT_DATA_FROM_SCORES("SELECT u.username, g.gameType, s.score, s.timeStamp FROM Scores AS s JOIN Users AS u ON s.userId = u.id JOIN Games AS g ON s.gameId = g.id LIMIT ?"),
    INSERT_INTO_SCORES("INSERT INTO Scores(userId, gameId, score, timeStamp) VALUES(?, ?, ?, ?)"),
    INSERT_INTO_USERS("INSERT IGNORE INTO Users(username) VALUES(?)");

    private final String sql;

    MySQLStatements(String sql){
        this.sql = sql;
    }
    public String getSql(){
        return sql;
    }
}
