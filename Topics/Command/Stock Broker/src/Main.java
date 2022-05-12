public class Main {

    public static void main(String[] args) {

        Broker broker = new Broker();
        /* write your code here */
        Stock stock = new Stock();

        Command buyCommand = new BuyCommand(stock);
        Command sellCommand = new SellCommand(stock);

        broker.setCommand(buyCommand);
        broker.executeCommand();

        broker.setCommand(sellCommand);
        broker.executeCommand();
    }
}


class Stock {

    public void buy() {
        System.out.println("Stock was bought");
    }

    public void sell() {
        System.out.println("Stock was sold");
    }
}

interface Command {
    void executeCommand();
}

class BuyCommand implements Command {
    private Stock stock;

    public BuyCommand(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void executeCommand() {
        stock.buy();
    }
    /* write your code here */
}

class SellCommand implements Command {
    private Stock stock;

    public SellCommand(Stock stock) {
        this.stock = stock;
    }

    @Override
    public void executeCommand() {
        stock.sell();
    }

    /* write your code here */
}

class Broker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    void executeCommand() {
        command.executeCommand();
    }
}
