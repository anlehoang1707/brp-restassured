package commons;

import java.util.Random;

public class BaseTest {
    protected int generateRandomNumber() {
        return new Random().nextInt(9999);
    }
}
