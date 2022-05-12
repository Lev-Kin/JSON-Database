interface Flying {
    // returns height of flying in meters
    int getHeight();

    default int getHeightInKm() {
        return getHeight() / 1000;
    }

    // implements a default method getHeightInKm that returns height of flying in km as int type
}