interface Movable {
    int getX();
    int getY();
    void setX(int newX);
    void setY(int newY);
}

interface Storable {
    int getInventoryLength();
    String getInventoryItem(int index);
    void setInventoryItem(int index, String item);
}

interface Command {
    void execute();
    void undo();
}

class CommandMove implements Command {
    Movable entity;
    int xMovement;
    int yMovement;
    Integer previousX = null;
    Integer previousY = null;

    @Override
    public void execute() {
        previousX = entity.getX();
        previousY = entity.getY();

        entity.setX(xMovement);
        entity.setY(yMovement);
    }

    @Override
    public void undo() {
        entity.setX(previousX);
        entity.setY(previousY);
    }
}

class CommandPutItem implements Command {
    Storable entity;
    String item;
    Integer usedSlot = null;

    @Override
    public void execute() {
        int inventoryLength = entity.getInventoryLength();
        for (int i = 0; i < inventoryLength; i++) {

            String currentItem = entity.getInventoryItem(i);
            if (currentItem == null) {
                entity.setInventoryItem(i, item);
                usedSlot = i;

                return;
            }
        }
    }

    @Override
    public void undo() {
        if (usedSlot != null) {
            entity.setInventoryItem(usedSlot, null);
            usedSlot = null;
        }
    }
}