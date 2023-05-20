package Code;

import java.sql.*;

public class Database {

    private final Statement statement;

    /**
     * This class can create a database connection with already existing database
     */
    public Database(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/MST",
                    "root",
                    ""
            );

            this.statement = connection.createStatement();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // --------------------------- This is the code to add data to different tables ----------------------------------

    /**
     * This will save a winner in the winner table in the database
     * @param player_name: name of the player
     * @param root_city: root city of the problem player solved
     */
    public void saveWinner(String player_name, String root_city){
        String sqlQuery = String.format("INSERT INTO `MST`.`winner` (`name`, `root`) " +
                "VALUES ('%s','%s')", player_name, root_city);

        try {
            this.statement.execute(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * This will save the shortest distances winner entered
     * @param winner_id: ID of the winner
     * @param connectionInputs: Connections entered by the user
     * @param total_min_distance: Total distance of the MST
     */
    public void saveUserInputs(int winner_id, String[] connectionInputs, int total_min_distance){
        String sql_query = String.format("INSERT INTO `MST`.`connections`(`winner_id`, `total_minimum_distance`," +
                        " `link_1`, `link_2`, `link_3`, `link_4`, `link_5`, `link_6`, `link_7`, `link_8`, `link_9`)" +
                        " VALUES ('%d', '%d', '%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                winner_id, total_min_distance, connectionInputs[0], connectionInputs[1], connectionInputs[2],
                connectionInputs[3], connectionInputs[4], connectionInputs[5], connectionInputs[6],
                connectionInputs[7], connectionInputs[8]);

        try {
            this.statement.execute(sql_query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * This will add the game play data to the database(distance between cities)
     * @param data: Matrix containing distance of the cities
     */
    public void saveCurrentGamePlayValues(int winner_id, String[][] data){
        String sqlQuery = String.format("INSERT INTO `MST`.`distance_between_cities`(`winner_id`, " +
                        "`ab`, `ac`, `ad`, `ae`, `af`, `ag`, `ah`, `ai`, `aj`, " +
                        "`bc`, `bd`, `be`, `bf`, `bg`, `bh`, `bi`, `bj`, " +
                        "`cd`, `ce`, `cf`, `cg`, `ch`, `ci`, `cj`, " +
                        "`de`, `df`, `dg`, `dh`, `di`, `dj`, " +
                        "`ef`, `eg`, `eh`, `ei`, `ej`, " +
                        "`fg`, `fh`, `fi`, `fj`, " +
                        "`gh`, `gi`, `gj`, " +
                        "`hi`, `hj`, " +
                        "`ij`) VALUES ('%d', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', " +
                        "'%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', " +
                        "'%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', " +
                        "'%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', " +
                        "'%s', '%s', '%s', '%s', '%s', '%s')", winner_id,
                data[0][0], data[1][0], data[2][0], data[3][0], data[4][0], data[5][0], data[6][0], data[7][0], data[8][0],
                data[1][1], data[2][1], data[3][1], data[4][1], data[5][1], data[6][1], data[7][1], data[8][1],
                data[2][2], data[3][2], data[4][2], data[5][2], data[6][2], data[7][2], data[8][2],
                data[3][3], data[4][3], data[5][3], data[6][3], data[7][3], data[8][3],
                data[4][4], data[5][4], data[6][4], data[7][4], data[8][4],
                data[5][5], data[6][5], data[7][5], data[8][5],
                data[6][6], data[7][6], data[8][6],
                data[7][7], data[8][7],
                data[8][8]);

        try {
            this.statement.execute(sqlQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // --------------------------- data retrieving from the database -------------------------------------------------

    /**
     * use to get the maximum winner id in the table
     * @return: maximum winner id, if no id is available -1 will be returned
     */
    public int getLatestWinnerID(){
        String sqlQuery = "SELECT MAX(`winner_id`) FROM `MST`.`winner`";

        try {
            ResultSet resultSet = this.statement.executeQuery(sqlQuery);
            if(resultSet.next()){
                return resultSet.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
