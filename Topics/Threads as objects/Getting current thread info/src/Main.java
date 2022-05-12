import java.util.Scanner;

class Info {

    public static void printCurrentThreadInfo() {

        Thread thread = Thread.currentThread();
        System.out.println("name: " + thread.getName());
        System.out.println("priority: " + thread.getPriority());

        System.out.println(Thread.MIN_PRIORITY);
        System.out.println(Thread.MAX_PRIORITY);
    }

}
