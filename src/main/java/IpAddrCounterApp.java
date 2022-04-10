public class IpAddrCounterApp {

    public static void main(String[] args) {
        String file = Util.getFile(args);
        if (file == null) {
            throw new RuntimeException("Аргумент с файлом не найден! Используйте ключ '-file file_path' в аргументах.");
        }

        UniqueIpAddrCounter counter = new UniqueIpAddrCounter();
        long numberUniqueIp = counter.countUniqueIp(file);
        System.out.println("Количество уникальных айпишников: " + numberUniqueIp);
    }
}
