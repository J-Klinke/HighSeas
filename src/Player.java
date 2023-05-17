public abstract class Player{
    private Map hiddenMap;
    private Map visibleMap;

    public int size;
    public Board board = new Board();
    private Ship[] ships;
    private Ship ship1;
    private Ship ship2;
    private Ship ship3;
    private Ship ship4;
    private Ship ship5;
    private Ship ship6;
    private Ship ship7;



    public Player(int size) {
        this.size = size;
        this.hiddenMap = new Map(size);
        this.visibleMap = new Map(size);
        switch (size){
            case 5 -> ships = new Ship[3];
            case 9 -> ships = new Ship[5];
            case 13 -> ships = new Ship[7];
        }
        getShips()[0] = new Ship(2);
        ship1 = getShips()[0];
        getShips()[1] = new Ship(2);
        ship2 = getShips()[1];
        getShips()[2] = new Ship(3);
        ship3 = getShips()[2];
        if (size > 5) {
            getShips()[3] = new Ship(2);
            ship4 = getShips()[3];
            getShips()[4] = new Ship(3);
            ship5 = getShips()[4];
            if (size > 9) {
                getShips()[5] = new Ship(1);
                ship6 = getShips()[5];
                getShips()[6] = new Ship(4);
                ship7 = getShips()[6];
            }
        }
    }

    /**
     * this method checks if there is a part of a ship (a 'true' in the array) at the given point in the map array
     * of the other Player. If there is, the matching point on other.visibleMap will be set to true,
     * the one on other.hiddenMap to false
     * @param guess int array, the first int is the row coordinate of the guess, the second the column coordinate
     * @param other the non-active Player
     * @return true, if the guess was a hit (if the corresponding point in the Map.map was true)
     */
    public abstract boolean checkIfHit(int[] guess, Player other);

    public boolean checkIfSunk(int[] guess, Player other) {
        if (size >= 5) {
                // Ship 3
                if ((guess[0] == other.getShip(3)[0] && guess[1] == other.getShip(3)[1])
                        || (guess[0] == other.getShip(3)[2] && guess[1] == other.getShip(3)[3])
                        || (guess[0] == other.getShip(3)[4] && guess[1] == other.getShip(3)[5])) {
                    if (other.getVisibleMap()[other.getShip(3)[0]][other.getShip(3)[1]]
                            && other.getVisibleMap()[other.getShip(3)[2]][other.getShip(3)[3]]
                            && other.getVisibleMap()[other.getShip(3)[4]][other.getShip(3)[5]]) {
                        System.out.println("Three-segment ship sunk");
                        return true;
                    }
                // Ship 2
                } else if ((guess[0] == other.getShip(2)[0] && guess[1] == other.getShip(2)[1])
                        || (guess[0] == other.getShip(2)[2] && guess[1] == other.getShip(2)[3])) {
                    if (other.getVisibleMap()[other.getShip(2)[0]][other.getShip(2)[1]]
                            && other.getVisibleMap()[other.getShip(2)[2]][other.getShip(2)[3]]) {
                        System.out.println("Two-segment ship sunk");
                        return true;
                    }
                // Ship 1
                } else if ((guess[0] == other.getShip(1)[0] && guess[1] == other.getShip(1)[1])
                        || (guess[0] == other.getShip(1)[2] && guess[1] == other.getShip(1)[3])) {
                    if (other.getVisibleMap()[other.getShip(1)[0]][other.getShip(1)[1]]
                            && other.getVisibleMap()[other.getShip(1)[2]][other.getShip(1)[3]]) {
                        System.out.println("Two-segment ship sunk");
                        return true;
                    }
                }
            }

            if (size >= 9){
            // Ship 4
                 if ((guess[0] == other.getShip(4)[0] && guess[1] == other.getShip(4)[1])
                        || (guess[0] == other.getShip(4)[2] && guess[1] == other.getShip(4)[3])) {
                    if (other.getVisibleMap()[other.getShip(4)[0]][other.getShip(4)[1]]
                            && other.getVisibleMap()[other.getShip(4)[2]][other.getShip(4)[3]]) {
                        System.out.println("Two-segment ship sunk");
                        return true;
                    }
                // Ship 5
                } else if ((guess[0] == other.getShip(5)[0] && guess[1] == other.getShip(5)[1])
                        || (guess[0] == other.getShip(5)[2] && guess[1] == other.getShip(5)[3])
                        || (guess[0] == other.getShip(5)[4] && guess[1] == other.getShip(5)[5])) {
                    if (other.getVisibleMap()[other.getShip(5)[0]][other.getShip(5)[1]]
                            && other.getVisibleMap()[other.getShip(5)[2]][other.getShip(5)[3]]
                            && other.getVisibleMap()[other.getShip(5)[4]][other.getShip(5)[5]]) {
                        System.out.println("Three-segment ship sunk");
                        return true;
                    }
                }
            }

            if (size == 13){
            // Ship 6
                if ((guess[0] == other.getShip(6)[0] && guess[1] == other.getShip(6)[1])) {
                    System.out.println("One-segment ship sunk");
                    return true;
            // Ship 7
                } else if ((guess[0] == other.getShip(7)[0] && guess[1] == other.getShip(7)[1])
                        || (guess[0] == other.getShip(7)[2] && guess[1] == other.getShip(7)[3])
                        || (guess[0] == other.getShip(7)[4] && guess[1] == other.getShip(7)[5])
                        || (guess[0] == other.getShip(7)[6] && guess[1] == other.getShip(7)[7])) {
                    if (other.getVisibleMap()[other.getShip(7)[0]][other.getShip(7)[1]]
                            && other.getVisibleMap()[other.getShip(7)[2]][other.getShip(7)[3]]
                            && other.getVisibleMap()[other.getShip(7)[4]][other.getShip(7)[5]]
                            && other.getVisibleMap()[other.getShip(7)[6]][other.getShip(7)[7]]) {
                        System.out.println("Four-segment ship sunk");
                        return true;
                    }
                }
            }
        return false;
    }

    public abstract void generateShips();

    /**
     * allows a Player to make a move, more information in the ComputerPlayer and HumanPlayer classes
     * @return the move's coordinates as a 2-digit int array
     */
    public abstract int[] makeMove(boolean startUp);

    public boolean[][] getHiddenMap() {
        return hiddenMap.getMap();
    }

    public void setHiddenMapAt(int row, int column, boolean value) {
        this.hiddenMap.setMapAt(row, column, value);
    }

    public boolean[][] getVisibleMap() {
        return visibleMap.getMap();
    }

    public Ship[] getShips() {
        return ships;
    }

    public int[] getShip(int number) {
        switch (number){
            case 1 -> {
                return ship1.getLocation();
            }
            case 2 -> {
                return ship2.getLocation();
            }
            case 3 -> {
                return ship3.getLocation();
            }
            case 4 -> {
                return ship4.getLocation();
            }
            case 5 -> {
                return ship5.getLocation();
            }
            case 6 -> {
                return ship6.getLocation();
            }
            case 7 -> {
                return ship7.getLocation();
            }
        }
        return new int[] {-1,-1};
    }
}