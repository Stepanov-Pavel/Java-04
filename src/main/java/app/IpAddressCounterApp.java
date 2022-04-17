package app;

import counter.UniqueIpAddressCounter;

public class IpAddressCounterApp {

    public static void main(String[] args) {
        String filePathName = getFile(args);

        UniqueIpAddressCounter counter = new UniqueIpAddressCounter();
        long numberUniqueIp = counter.countUniqueIp(filePathName);
        System.out.println("Количество уникальных айпишников: " + numberUniqueIp);
    }

    private static String getFile(String[] args) {
        String file;
        if (args.length == 2 && "-file".equals(args[0])) {
            file = args[1];
        } else {
            throw new IllegalArgumentException("Аргумент с файлом не найден! Используйте ключ '-file file_path' в аргументах.");
        }

        return file;
    }
}
