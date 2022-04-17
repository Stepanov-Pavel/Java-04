package counter;

import converter.IpAddressConverter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;

public class UniqueIpAddressCounter {

    private final BitSet bitSetPartOne = new BitSet(Integer.MAX_VALUE);
    private final BitSet bitSetPartTwo = new BitSet(Integer.MAX_VALUE);
    private final BitSet bitSetPartThree = new BitSet(Integer.MAX_VALUE);

    IpAddressConverter converter = new IpAddressConverter();

    /**
     * Считает количество уникальных IP-адресов в файле, переданного аргументом
     *
     * @param filePathName Файл с IP-адресами
     * @return Возвращает число от 0 до 2 ^ 32 включительно
     */
    public long countUniqueIp(String filePathName) {
        final long START_VALUE_SET_PART_TWO = 2147483647L;
        final long END_VALUE_SET_PART_TWO = 4294967293L;

        try (BufferedReader lines = new BufferedReader(new FileReader(filePathName))) {
            String line;
            while ((line = lines.readLine()) != null) {
                long number = converter.convertIpAddressToNumber(line);
                int intValue;
                if (number > END_VALUE_SET_PART_TWO) {
                    intValue = (int) (number - (long) Integer.MAX_VALUE - (long) Integer.MAX_VALUE + 1);
                    bitSetPartThree.set(intValue);
                } else if (number >= START_VALUE_SET_PART_TWO) {
                    intValue = (int) (number - (long) Integer.MAX_VALUE);
                    bitSetPartTwo.set(intValue);
                } else {
                    intValue = (int) number;
                    bitSetPartOne.set(intValue);
                }
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Ошибка: Файл не найден: " + e, e);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + e, e);
        }

        return bitSetPartOne.cardinality() + bitSetPartTwo.cardinality() + bitSetPartThree.cardinality();
    }
}
