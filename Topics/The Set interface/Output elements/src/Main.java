import java.util.*;

public class Main {

    public static void main(String[] args) {
        Set<String> nameSet = new TreeSet<>(Arrays.asList("Mr.Green", "Mr.Yellow", "Mr.Red"));

        nameSet.forEach(System.out::println);

        int[] v = {1, 2, 3};
        int p = 3;
        int[] h = new int[4];

        h[0] = 0;
        h[1] = h[0] * p + v[0];
        h[2] = h[1] * p + v[1];
        h[3] = h[2] * p + v[2];

        System.out.println(h[3]);



    }
}
