import java.util.Arrays;

public class Map {
    private boolean[][] map;

    public Map(int size) {
        this.map = new boolean[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(map[i], false);
        }
    }

    public boolean[][] getMap() {
        return map;
    }

    /**
     * this method sets the value of a specific point in the two-dimensional array to true or false
     * @param row the row coordinate to be changed
     * @param column the column coordinate to be changed
     * @param value the value the certain point is to be set to
     */
    public void setMapAt(int row, int column, boolean value) {
        getMap()[row][column] = value;
    }
}