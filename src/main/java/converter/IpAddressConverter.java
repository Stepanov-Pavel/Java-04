package converter;

public class IpAddressConverter {

    /**
     * Сопоставляет строку представляющую IP-адрес в формате 0-255.0-255.0-255.0-255 с номером
     * в диапазоне 0..2 ^ 32 - 1  включительно
     *
     * @param ipAddress Строка представляющая IP-адрес
     * @return Число от 0 до 2 ^ 32 - 1 включительно
     */
    public long convertIpAddressToNumber(String ipAddress) {
        final String SPLITERATOR = "\\.";
        long result = 0;

        String[] ipAddressParts = ipAddress.split(SPLITERATOR);
        for (int i = 0; i < ipAddressParts.length; i++) {
            result += Integer.parseInt(ipAddressParts[i]) * Math.pow(256, 3 - i);
        }

        return result;
    }
}
