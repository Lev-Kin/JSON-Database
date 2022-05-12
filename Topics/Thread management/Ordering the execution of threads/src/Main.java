class Invoker extends Thread {

    private final String str;

    public Invoker(String str) {
        this.str = str;
    }

    public static void invokeMethods(Thread t1, Thread t2, Thread t3) throws InterruptedException {



        t3.start();
        t3.join();


        t2.start();
        t2.join();

        t1.start();
        t1.join();
    }

    @Override
    public void run() {
        System.out.print (str + " ");
    }

}
