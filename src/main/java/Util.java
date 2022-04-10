public final class Util {

    private Util() {
    }

    public static String getFile(String[] args) {
        String file = null;
        if (args.length == 2 && "-file".equals(args[0])) {
            file= args[1];
        }

        return file;
    }
}
