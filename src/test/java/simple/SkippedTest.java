package simple;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SkippedTest {
    @Test
    @Disabled("Some reason")
    void test1() {
        assertTrue(true);
    }

    @Test
    @Disabled
    void test2() {
        assertTrue(true);
    }
}