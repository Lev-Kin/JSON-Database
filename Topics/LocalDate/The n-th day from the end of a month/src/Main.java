import java.time.LocalDate;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] arr = line.split(" ");
        LocalDate localDate = LocalDate.of(parseInt(arr[0]), parseInt(arr[1]), 1);
        System.out.println(localDate.plusDays(localDate.lengthOfMonth() - parseInt(arr[2])));
    }
}