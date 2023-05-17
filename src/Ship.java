public class Ship {final private int[] location;

    public int[] getLocation() {
        return this.location;
    }

    public Ship(int length) {
        this.location = new int[length*2];
    }
}
