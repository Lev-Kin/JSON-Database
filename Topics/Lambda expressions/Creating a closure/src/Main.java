
import java.util.Scanner;
import java.util.function.UnaryOperator;

class PrefixSuffixOperator {
    // static Scanner scanner = new Scanner(System.in);

    public static final String PREFIX = "__pref__";
    public static final String SUFFIX = "__suff__";
    //public static String inputString = scanner.nextLine().trim();

    public static UnaryOperator<String> operator = (inputString) -> {
        return PREFIX + inputString.trim() + SUFFIX;
    };


}