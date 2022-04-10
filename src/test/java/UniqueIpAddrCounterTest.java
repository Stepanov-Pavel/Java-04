import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniqueIpAddrCounterTest {

    @Test
    void countUniqueIp() {
        String file = "src/test/resources/IPv4_addr.txt";
        UniqueIpAddrCounter counter = new UniqueIpAddrCounter();
        long actual = counter.countUniqueIp(file);
        long expected = 4L;
        assertEquals(expected, actual);
    }
}
