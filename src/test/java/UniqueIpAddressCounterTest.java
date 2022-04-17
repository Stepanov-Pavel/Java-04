import counter.UniqueIpAddressCounter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UniqueIpAddressCounterTest {

    @Test
    void correctCounter() {
        String file = "src/test/resources/IPv4_addr.txt";
        UniqueIpAddressCounter counter = new UniqueIpAddressCounter();
        long actual = counter.countUniqueIp(file);
        long expected = 6L;
        assertEquals(expected, actual);
    }

    @Test
    void emptyCounter() {
        String file = "src/test/resources/IPv4_addr_empty.txt";
        UniqueIpAddressCounter counter = new UniqueIpAddressCounter();
        long actual = counter.countUniqueIp(file);
        long expected = 0;
        assertEquals(expected, actual);
    }
}
