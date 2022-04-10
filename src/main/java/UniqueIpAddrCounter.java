import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;

public class UniqueIpAddrCounter {

    /**
     * Существует 256 возможных значений для каждой из 4 частей IP-адреса, что дает 256 ^ 4 = 4_294_967_296
     */
    private final long NUMBER_OF_IP_ADDR = 256L * 256 * 256 * 256;
    private final BitSet BIT_SET_PART_ONE = new BitSet(Integer.MAX_VALUE);
    private final BitSet BIT_SET_PART_TWO = new BitSet(Integer.MAX_VALUE);

    private long counter = 0;

    /**
     * Считает количество уникальных IP-адресов в файле, переданного аргументом
     * @param file Файл с IP-адресами
     * @return Возвращает число от 0 до 2 ^ 32 включительно
     */
    public long countUniqueIp(String file) {
        try (BufferedReader lines = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = lines.readLine()) != null && counter <= NUMBER_OF_IP_ADDR) {
                countUniqueIpHelper(ipAddrMapHelper(line));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Ошибка: Файл не найден: " + e);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла: " + e);
        }
        return counter;
    }

    /**
     * Проверяет наличие сопоставленного значения IP-адресов с содержимым BitSet,
     * увеличивает счетчик в случае нахождения
     * @param mappedValue Значение полученное путем сопоставления строки IP-адреса с диапазоном
     */
    private void countUniqueIpHelper(long mappedValue) {
        int intValue = (int) mappedValue;

        BitSet workingSet = BIT_SET_PART_ONE;
        if (mappedValue > Integer.MAX_VALUE) {
            intValue = (int) (mappedValue - Integer.MAX_VALUE);
            workingSet = BIT_SET_PART_TWO;
        }

        if (!workingSet.get(intValue)) {
            counter++;
            workingSet.set(intValue);
        }
    }

    /**
     * Сопоставляет строку представляющую IP-адрес в формате 0-255.0-255.0-255.0-255 с номером
     * в диапазоне 0..2 ^ 32 - 1  включительно
     * @param ipString Строка представляющая IP-адрес
     * @return Число от 0 до 2 ^ 32 - 1 включительно
     */
    private long ipAddrMapHelper(String ipString) {
        StringBuilder field = new StringBuilder(3);
        int startIndex = 0;
        long result = 0;

        for (int i = 0; i < 3; i++) {
            int spacerPosition = ipString.indexOf('.', startIndex);
            field.append(ipString, startIndex, spacerPosition);
            int fieldValue = Integer.parseInt(field.toString());
            field.setLength(0);
            result += fieldValue * Math.pow(256, 3 - i);
            startIndex = spacerPosition + 1;
        }
        result += Integer.parseInt(ipString.substring(startIndex));

        return result;
    }
}
