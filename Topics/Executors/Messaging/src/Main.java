import java.util.concurrent.*;

/* Do not change this class */
class Message {
    final String text;
    final String from;
    final String to;

    Message(String from, String to, String text) {
        this.text = text;
        this.from = from;
        this.to = to;
    }
}

/* Do not change this interface */
interface AsyncMessageSender {
    void sendMessages(Message[] messages);

    void stop();
}

class AsyncMessageSenderImpl implements AsyncMessageSender {
    private ExecutorService executor = Executors.newSingleThreadExecutor(); // TODO initialize the executor
    private final int repeatFactor;

    public AsyncMessageSenderImpl(int repeatFactor) {
        this.repeatFactor = repeatFactor;
    }

    @Override
    public void sendMessages(Message[] messages) {

        for (Message msg : messages) {

            int repeats = repeatFactor;

//            try {
//                executor.awaitTermination(60, TimeUnit.MILLISECONDS);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }

            do {


                // TODO repeat messages
                executor.submit(() -> {
                    System.out.printf("(%s>%s): %s\n", msg.from, msg.to, msg.text); // do not change it
                });

                repeats--;
            } while (repeats > 0);


        }

    }

    @Override
    public void stop() {
        //System.out.println("Completed.");
        executor.shutdown();
        // TODO stop the executor and wait for it
    }
}