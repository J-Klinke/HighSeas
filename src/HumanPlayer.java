import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class HumanPlayer extends Player{
    Scanner inputScanner = new Scanner(System.in);
    private ArrayList<HumanGuess> guessList = new ArrayList<>();

    public HumanPlayer(int size) {
        super(size);
    }

    @Override
    public boolean checkIfHit(int[] guess, Player other) {
        if (other.getHiddenMap()[guess[0]][guess[1]]) {
            System.out.println("Hit!");
            other.getVisibleMap()[guess[0]][ guess[1]] = true;
            other.setHiddenMapAt(guess[0], guess[1], false);
            return true;
        } else {
            System.out.println("You missed!");
            return false;
        }
    }


    /**
     * this method is the most important step in the beginning of the game: here the player sets their ships. in relation
     * to HighSeas.size more or less ship will be placed. Calls the makeMove() method.
     */
    @Override
    public void generateShips() {

        board.drawBoard();
        System.out.println();
        System.out.println("You can now choose where to place your ships.");
        switch (size) {
            case 5 -> System.out.println("You must place three ships: two two-segment ships and one three-segment ship.");
            case 9 -> System.out.println("You must place five ships: three two-segment ships and two three-segment ships.");
            case 13 -> System.out.println("You must place seven ships: three two-segment ships, two three-segment ship, " +
                    "one one-segment ship and one four-segment ship");
        }

        System.out.println("Each time choose a starting coordinate and the direction the ship should point.");
        System.out.println();
        restart :
        for (int i = 0; i < getShips().length; i++) {
            switch (i) { // initial printout
                case 0 -> System.out.println("For the first two-segment ship:");
                case 1 -> System.out.println("Now the" + (size==5?" ":" first ") + "two-segment ship:");
                case 2 -> System.out.println("Now the first three-segment ship");
                case 3 -> System.out.println("Now the third two-segment ship:");
                case 4 -> System.out.println("Now the second three-segment ship:"); 
                case 5 -> System.out.println("Now the one-segment ship:");
                case 6 -> System.out.println("Now the four-segment ship:");
            }

            int[] move = makeMove(true);
            while (getHiddenMap()[move[0]][move[1]]) { // here the player enters their ships coordinate.
                System.out.println("You already placed a ship at this location.");
                move = makeMove(true); // if there is already a ship in this location the player must enter a new coordinate
            }

            if (i == 5) { // the one-segment ship
                setHiddenMapAt(move[0], move[1], true);
                getShip(i+1)[0] = move[0]; getShip(i+1)[1] = move[1];
                board.drawBoard();
                System.out.println();
                continue;
            }

            System.out.println("Now enter the direction of the boat");
            System.out.println("Enter 'up', 'down', 'left' or 'right'");
            boolean directionAcceptable = false;
            String direction;

            while (!directionAcceptable) { // while the entered direction causes range problems
                                           // or a space to be filled with a ships part, this loop restarts
                System.out.print("Enter: ");
                direction = inputScanner.next();
                switch (direction) {
                    // for each direction the possible range and occupancy problems get checked
                    // & the chosen coordinates are written to the map.
                    // Additionally, the chosen fields get written to the ship arrays
                    case "up", "u" -> {
                        if ((i < 2 || i == 3) && move[0] != 0) { // two-segment ship
                            if (getHiddenMap()[move[0] - 1][move[1]]) {
                                System.out.println("You already placed a ship at this location. Try again.");
                                i--;
                                continue restart;
                            } else {
                                setHiddenMapAt(move[0],move[1], true);
                                setHiddenMapAt(move[0] - 1,move[1], true);

                                getShip(i+1)[0] = move[0]; getShip(i+1)[1] = move[1];
                                getShip(i+1)[2] = move[0]-1; getShip(i+1)[3] = move[1];

                                directionAcceptable = true;
                            }
                        } else if ((i == 2||i == 4) && !(move[0] < 2)) { // three-segment ship
                            if (getHiddenMap()[move[0] - 1][move[1]] || getHiddenMap()[move[0] - 2][move[1]]) {
                                System.out.println("You already placed a ship at this location. Try again.");
                                i--;
                                continue restart;
                            } else {
                                setHiddenMapAt(move[0], move[1], true);
                                setHiddenMapAt(move[0] - 1, move[1], true);
                                setHiddenMapAt(move[0] - 2, move[1], true);
                                getShip(i + 1)[0] = move[0]; getShip(i + 1)[1] = move[1];
                                getShip(i + 1)[2] = move[0] - 1; getShip(i + 1)[3] = move[1];
                                getShip(i + 1)[4] = move[0] - 2; getShip(i + 1)[5] = move[1];
                                directionAcceptable = true;
                            }
                        } else if (i == 6 && move[0] > 2){ // four-segment ship
                            if (getHiddenMap()[move[0] - 1][move[1]] || getHiddenMap()[move[0] - 2][move[1]]
                                    || getHiddenMap()[move[0] - 3][move[1]]) {
                                System.out.println("You already placed a ship at this location. Try again.");
                                i--;
                                continue restart;
                            } else {
                                setHiddenMapAt(move[0], move[1], true);
                                setHiddenMapAt(move[0] - 1, move[1],true);
                                setHiddenMapAt(move[0] - 2, move[1],true);
                                setHiddenMapAt(move[0] - 3, move[1],true);
                                getShip(i + 1)[0] = move[0]; getShip(i + 1)[1] = move[1];
                                getShip(i + 1)[2] = move[0] - 1; getShip(i + 1)[3] = move[1];
                                getShip(i + 1)[4] = move[0] - 2; getShip(i + 1)[5] = move[1];
                                getShip(i + 1)[6] = move[0] - 3; getShip(i + 1)[7] = move[1];
                                directionAcceptable = true;
                            }

                        } else {
                            System.out.println("Your entry doesn't fit inside the field. Please choose again.");
                            System.out.println("If you want to restart placing this ship enter 'restart'");
                        }
                    }
                    case "down", "d" -> {
                        if ((i < 2 || i == 3) && move[0] != size-1) { // two-segment ship
                            if (getHiddenMap()[move[0] + 1][move[1]]) {
                                System.out.println("You already placed a ship at this location. Try again.");
                                i--;
                                continue restart;
                            } else {
                                setHiddenMapAt(move[0],move[1],true);
                                setHiddenMapAt(move[0] + 1,move[1],true);

                                getShip(i+1)[0] = move[0]; getShip(i+1)[1] = move[1];
                                getShip(i+1)[2] = move[0]+1; getShip(i+1)[3] = move[1];

                                directionAcceptable = true;
                            }
                        } else if ((i == 2||i == 4) && !(move[0] > size-3)) { // three-segment ship
                            if (getHiddenMap()[move[0] + 1][move[1]] || getHiddenMap()[move[0] + 2][move[1]]) {
                                System.out.println("You already placed a ship at this location. Try again.");
                                i--;
                                continue restart;
                            } else {
                                setHiddenMapAt(move[0], move[1],true);
                                setHiddenMapAt(move[0] + 1, move[1], true);
                                setHiddenMapAt(move[0] + 2,move[1], true);
                                getShip(i+1)[0] = move[0]; getShip(i+1)[1] = move[1];
                                getShip(i+1)[2] = move[0]+1; getShip(i+1)[3] = move[1];
                                getShip(i+1)[4] = move[0]+2; getShip(i+1)[5] = move[1];
                                directionAcceptable = true;
                            }
                        } else if (i == 6 && move[0] < 10){ // four-segment ship
                            if (getHiddenMap()[move[0] + 1][move[1]] || getHiddenMap()[move[0] + 2][move[1]]
                                    || getHiddenMap()[move[0] + 3][move[1]]) {
                                System.out.println("You already placed a ship at this location. Try again.");
                                i--;
                                continue restart;
                            } else {
                                setHiddenMapAt(move[0], move[1], true);
                                setHiddenMapAt(move[0] + 1, move[1],true);
                                setHiddenMapAt(move[0] + 2, move[1],true);
                                setHiddenMapAt(move[0] + 3, move[1],true);
                                getShip(i + 1)[0] = move[0]; getShip(i + 1)[1] = move[1];
                                getShip(i + 1)[2] = move[0] + 1; getShip(i + 1)[3] = move[1];
                                getShip(i + 1)[4] = move[0] + 2; getShip(i + 1)[5] = move[1];
                                getShip(i + 1)[6] = move[0] + 3; getShip(i + 1)[7] = move[1];
                                directionAcceptable = true;
                            }

                        } else {
                            System.out.println("Your entry doesn't fit inside the field. Please choose again.");
                            System.out.println("If you want to restart placing this ship enter 'restart'");
                        }
                    }
                    case "left", "l" -> {
                        if ((i < 2 || i == 3) && move[1] != 0) { // two-segment ship
                            if (getHiddenMap()[move[0]][move[1] - 1]) {
                                System.out.println("You already placed a ship at this location. Try again.");
                                i--;
                                continue restart;
                            } else {
                                setHiddenMapAt(move[0], move[1], true);
                                setHiddenMapAt(move[0], move[1] - 1, true);

                                getShip(i+1)[0] = move[0]; getShip(i+1)[1] = move[1];
                                getShip(i+1)[2] = move[0]; getShip(i+1)[3] = move[1]-1;

                                directionAcceptable = true;
                            }
                        } else if ((i == 2||i == 4) && !(move[1] < 2)) { // three-segment ship
                            if (getHiddenMap()[move[0]][move[1]-1] || getHiddenMap()[move[0]][move[1]-2]) {
                                System.out.println("You already placed a ship at this location. Try again.");
                                i--;
                                continue restart;
                            } else {
                                setHiddenMapAt(move[0], move[1], true);
                                setHiddenMapAt(move[0], move[1]-1, true);
                                setHiddenMapAt(move[0], move[1]-2, true);
                                getShip(i+1)[0] = move[0]; getShip(i+1)[1] = move[1];
                                getShip(i+1)[2] = move[0]; getShip(i+1)[3] = move[1]-1;
                                getShip(i+1)[4] = move[0]; getShip(i+1)[5] = move[1]-2;
                                directionAcceptable = true;
                            }
                        } else if (i == 6 && move[1] > 2){ // four-segment ship
                            if (getHiddenMap()[move[0]][move[1]-1] || getHiddenMap()[move[0]][move[1]-2]
                                    || getHiddenMap()[move[0]][move[1]-3]) {
                                System.out.println("You already placed a ship at this location. Try again.");
                                i--;
                                continue restart;
                            } else {
                                setHiddenMapAt(move[0], move[1], true);
                                setHiddenMapAt(move[0], move[1]-1,true);
                                setHiddenMapAt(move[0], move[1]-2,true);
                                setHiddenMapAt(move[0], move[1]-3,true);
                                getShip(i + 1)[0] = move[0]; getShip(i + 1)[1] = move[1];
                                getShip(i + 1)[2] = move[0]; getShip(i + 1)[3] = move[1]-1;
                                getShip(i + 1)[4] = move[0]; getShip(i + 1)[5] = move[1]-2;
                                getShip(i + 1)[6] = move[0]; getShip(i + 1)[7] = move[1]-3;
                                directionAcceptable = true;
                            }

                        } else {
                            System.out.println("Your entry doesn't fit inside the field. Please choose again.");
                            System.out.println("If you want to restart placing this ship enter 'restart'");
                        }
                    }
                    case "right", "r" -> {
                        if ((i < 2 || i == 3) && move[1] != size-1) { // two-segment ship
                            if (getHiddenMap()[move[0]][move[1] + 1]) {
                                System.out.println("You already placed a ship at this location. Try again.");
                                i--;
                                continue restart;
                            } else {
                                setHiddenMapAt(move[0], move[1], true);
                                setHiddenMapAt(move[0], move[1] + 1, true);

                                getShip(i+1)[0] = move[0]; getShip(i+1)[1] = move[1];
                                getShip(i+1)[2] = move[0]; getShip(i+1)[3] = move[1]+1;

                                directionAcceptable = true;
                            }
                        } else if ((i == 2||i == 4) && !(move[1] > size-3)) { // three-segment ship
                            if (getHiddenMap()[move[0]][move[1]+1] || getHiddenMap()[move[0]][move[1]+2]) {
                                System.out.println("You already placed a ship at this location. Try again.");
                                i--;
                                continue restart;
                            } else {
                                setHiddenMapAt(move[0], move[1],true);
                                setHiddenMapAt(move[0], move[1]+1, true);
                                setHiddenMapAt(move[0], move[1]+2,  true);
                                getShip(i+1)[0] = move[0]; getShip(i+1)[1] = move[1];
                                getShip(i+1)[2] = move[0]; getShip(i+1)[3] = move[1]+1;
                                getShip(i+1)[4] = move[0]; getShip(i+1)[5] = move[1]+2;
                                directionAcceptable = true;
                            }
                        } else if (i == 6 && move[1] < 10){ // four-segment ship
                            if (getHiddenMap()[move[0]][move[1]+1] || getHiddenMap()[move[0]][move[1]+2]
                                    || getHiddenMap()[move[0]][move[1]+3]) {
                                System.out.println("You already placed a ship at this location. Try again.");
                                i--;
                                continue restart;
                            } else {
                                setHiddenMapAt(move[0], move[1], true);
                                setHiddenMapAt(move[0], move[1]+1,true);
                                setHiddenMapAt(move[0], move[1]+2,true);
                                setHiddenMapAt(move[0], move[1]+3,true);
                                getShip(i + 1)[0] = move[0]; getShip(i + 1)[1] = move[1];
                                getShip(i + 1)[2] = move[0]; getShip(i + 1)[3] = move[1]+1;
                                getShip(i + 1)[4] = move[0]; getShip(i + 1)[5] = move[1]+2;
                                getShip(i + 1)[6] = move[0]; getShip(i + 1)[7] = move[1]+3;
                                directionAcceptable = true;
                            }

                        } else {
                            System.out.println("Your entry doesn't fit inside the field. Please choose again.");
                            System.out.println("If you want to restart placing this ship enter 'restart'");
                        }

                    }
                    case "restart" -> {
                        i--;
                        continue restart;
                    }
                    default -> System.out.println("Please enter one of the above.");
                }
            }
            board.drawBoard();
            System.out.println();
        }

    }

    /**
     * allows the Player to make a move. This method reads an input from the console and checks for possible errors in
     * the input
     * @return a two-digit int array, playerGuess[0] is the row coordinate of the guess, playerGuess[1] the column
     * coordinate.
     */
    @Override
    public int[] makeMove(boolean startUp) {
        int[] playerGuess = new int[2];
        StringBuilder guessString = new StringBuilder();


        String row = "";
        boolean guessRowAcceptable = false;
        while (!guessRowAcceptable) { // for the row; the letters get converted to the coordinates
            System.out.print("Enter row: ");
            row = inputScanner.next().toLowerCase();
            if (row.equals("a") ||  row.equals("b") || row.equals("c") || row.equals("d") || row.equals("e")
                    || row.equals("f")&&size>5 || row.equals("g")&&size>5
                    || row.equals("h")&&size>5 || row.equals("i")&&size>5
                    || row.equals("j")&&size>9 || row.equals("k")&&size>9
                    || row.equals("l")&&size>9 || row.equals("m")&&size>9) {
                if (!startUp) {guessString.append(row.toUpperCase()).append("-");}
                switch (row) {
                    case "a" -> row = "0";
                    case "b" -> row = "1";
                    case "c" -> row = "2";
                    case "d" -> row = "3";
                    case "e" -> row = "4";
                    case "f" -> row = "5";
                    case "g" -> row = "6";
                    case "h" -> row = "7";
                    case "i" -> row = "8";
                    case "j" -> row = "9";
                    case "k" -> row = "10";
                    case "l" -> row = "11";
                    case "m" -> row = "12";
                }
                guessRowAcceptable = true;
            } else {
                System.out.print("You must enter either 'a', 'b', 'c', 'd'");
                switch (size) {
                    case 5 -> System.out.println(" or e.");
                    case 9 -> System.out.println(" 'e', 'f', 'g', 'h', or 'i'.");
                    case 13 -> System.out.println(" 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l' or 'm'.");
                }
            }
        }
        String column = "";
        boolean guessColumnAcceptable = false;
        while (!guessColumnAcceptable) { // for the columns
            System.out.print("Enter column: ");
            boolean isColumnInt = inputScanner.hasNextInt();
            column = inputScanner.next();
            if (!isColumnInt) {
                System.out.println("You must enter a number.");
            } else if (Integer.parseInt(column) < 1 || Integer.parseInt(column) > size) {
                System.out.println("You must enter a number between 1 and " + size + ".");
            } else {
                guessColumnAcceptable = true;
                if (!startUp) {guessString.append(column);}
            }
        }
        System.out.println();
        playerGuess[0] = Integer.parseInt(row);
        playerGuess[1] = (Integer.parseInt(column)-1); // the column coordinate is the entered coordinate - 1
        if (!startUp) {getGuessList().add(new HumanGuess(guessString.toString()));}
        return playerGuess;
    }

    /**
     * prints out the sorted guessList
     */
    public void printGuessList() {

        if (!getGuessList().isEmpty()) {
            System.out.println("Your previous moves:");
        }
        Collections.sort(getGuessList());
        for (int i = 0; i < getGuessList().size(); i++){
            System.out.print(getGuessList().get(i) + ", ");
            if (i%(size==5?7:(size==9?10:14)) == 0 && i > 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    public ArrayList<HumanGuess> getGuessList() {
        return guessList;
    }
}
