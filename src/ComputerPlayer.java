import java.util.Arrays;
import java.util.Random;

public class ComputerPlayer extends Player{
    Random random = new Random();
    int difficulty;
    int[] guesses;
    int guessCounter;
    public ComputerPlayer(int size, int difficulty) {
        super(size);
        this.guesses = new int[(size*size)*2];
        Arrays.fill(guesses, -1);
        this.guessCounter = 0;
        this.difficulty = difficulty;
    }

    @Override
    public boolean checkIfHit(int[] guess, Player other) {
        String guessAsString = "";
        switch (guess[0]) {
            case 0 -> guessAsString = "A";
            case 1 -> guessAsString = "B";
            case 2 -> guessAsString = "C";
            case 3 -> guessAsString = "D";
            case 4 -> guessAsString = "E";
            case 5 -> guessAsString = "F";
            case 6 -> guessAsString = "G";
            case 7 -> guessAsString = "H";
            case 8 -> guessAsString = "I";
            case 9 -> guessAsString = "J";
            case 10 -> guessAsString = "K";
            case 11 -> guessAsString = "L";
            case 12 -> guessAsString = "M";
        }
        if (other.getHiddenMap()[guess[0]][guess[1]]) {
            System.out.println("Enemy hit on coordinate " + guessAsString + "-" + (guess[1]+1));
            other.getVisibleMap()[guess[0]][guess[1]] = true;
            other.setHiddenMapAt(guess[0], guess[1], false);
            return true;
        } else {
            System.out.println("Enemy missed! They shot on coordinate " + guessAsString + "-" + (guess[1]+1));
            return false;
        }
    }


    /**
     * in this method, the computerPlayer's hidden Map will be randomly filled
     * in relation to HighSeas.size more or less ship will be placed. Calls the makeMove() method.
     */
    @Override
    public void generateShips() {
        restart :
        for (int i = 0; i < getShips().length; i++) {

            int[] move = makeMove(true);
            while (getHiddenMap()[move[0]][move[1]]) { // here the player enters their ships coordinate.
                move = makeMove(true); // if there is already a ship in this location the player must enter a new coordinate
            }

            if (i == 5) { // the one-segment ship
                getHiddenMap()[move[0]][move[1]] = true;
                getShip(i+1)[0] = move[0]; getShip(i+1)[1] = move[1];
                continue;
            }

            boolean directionAcceptable = false;
            char direction;

            while (!directionAcceptable) { // while the entered direction causes range problems
                // or a space to be filled with a ships part, this loop restarts
                direction = chooseDirection();
                switch (direction) {
                    // for each direction the possible range and occupancy problems get checked
                    // & the chosen coordinates are written to the map.
                    // Additionally, the chosen fields get written to the ship arrays
                    case 'u' -> {
                        if ((i < 2 || i == 3) && move[0] != 0) { // two-segment ship
                            if (getHiddenMap()[move[0] - 1][move[1]]) {
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
                        }
                    }
                    case 'd' -> {
                        if ((i < 2 || i == 3) && move[0] != size-1) { // two-segment ship
                            if (getHiddenMap()[move[0] + 1][move[1]]) {
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
                        }
                    }
                    case 'l' -> {
                        if ((i < 2 || i == 3) && move[1] != 0) { // two-segment ship
                            if (getHiddenMap()[move[0]][move[1] - 1]) {
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
                        }
                    }
                    case 'r' -> {
                        if ((i < 2 || i == 3) && move[1] != size-1) { // two-segment ship
                            if (getHiddenMap()[move[0]][move[1] + 1]) {
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
                        }
                    }
                }
            }
        }

    }

    /**
     * allows the Player to make a move. This method generates a move for the ComputerPlayer. The different difficulties
     * lead to different ways of how the move is generated. At the moment only one difficulty is implemented.
     * @return a two-digit int array, computerGuess[0] is the row coordinate of the guess, computerGuess[1] the column
     * coordinate
     */
    @Override
    //TODO: bei der Implementierung weiterer Schwierigkeitsgrade startUp berücksichtigen
    public int[] makeMove(boolean startUp) {
        int[] computerGuess = new int[2];
        switch (difficulty) {

            case 1 -> { // at difficulty 1 it's just random guesses, but each guess can only be made once
                boolean guessAcceptable = false;
                restart:
                while (!guessAcceptable)  {

                    int rowGuess = random.nextInt(size);
                    int columnGuess = random.nextInt(size);
                    for (int i = 0; i < guesses.length; i += 2) { // if the guess has been made the loop will restart
                        if (guesses[i] == rowGuess && guesses[i + 1] == columnGuess) {
                            continue restart;
                        }
                    }
                    computerGuess[0] = rowGuess; // the resulting guess gets written...
                    computerGuess[1] = columnGuess;
                    guesses[guessCounter] = computerGuess[0]; // ... and added to the array of previous guesses
                    guessCounter++;
                    guesses[guessCounter] = computerGuess[1];
                    guessCounter++;
                    guessAcceptable = true;
                }
            }

            case 2 -> { // to be implemented

            }
        }
        return computerGuess;
    }


    /**
     * allows the computerPlayer to choose a direction in the generateShips() method.
     * @return the direction as a char: 'u','d','l' or 'r'
     */
    private char chooseDirection() {
        int rnd = random.nextInt(4);
        char direction = '0';
        switch (rnd) {
            case 0 -> direction = 'u';
            case 1 -> direction = 'd';
            case 2 -> direction = 'l';
            case 3 -> direction = 'r';
        }
        return direction;
    }
}
