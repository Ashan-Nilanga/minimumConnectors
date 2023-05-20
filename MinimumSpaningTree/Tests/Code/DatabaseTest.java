package Code;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseTest {

    @Test
    void getLatestWinnerID() {
        Database database = new Database();

        int previous_winner_id = database.getLatestWinnerID();

        // adding a new winner
        database.saveWinner("TEST", "TEST_CITY");

        int expected = previous_winner_id + 1;
        int actual = database.getLatestWinnerID();

        Assertions.assertEquals(expected, actual);
    }
}