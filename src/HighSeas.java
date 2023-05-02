import java.util.Scanner;

public class HighSeas {
    Scanner in = new Scanner(System.in);

    public int size = 0;
    private char activePlayer = 'P';
    private int difficulty;

    ComputerPlayer computer;
    HumanPlayer player;

    private Board board = new Board();

    /**
     * allows the difficulty to be set. Sets difficulty to 1, if 'easy' is chosen, to 2, if 'hard' is chosen
     */
    public void setDifficulty() {
        int difficulty = 0;
        boolean acceptable = false;
        System.out.println("Now enter the difficulty level you want to play at. ");
        System.out.print("Type 'easy' or 'hard': ");
        while (!acceptable) {
            String difficultyInput = in.next();
            if (difficultyInput.equals("easy")) {
                difficulty = 1;
                acceptable = true;
            } else if (difficultyInput.equals("hard")) {
                difficulty = 2;
                acceptable = true;
            } else {
                System.out.println("Please enter 'easy' oder 'hard'.");
            }

        }
        this.difficulty = difficulty;
    }

    /**
     * in this method the player chooses how big the map should be. The possible options are 5x5, 9x9 or 13x13.
     * @return size of the map
     */
    public int initializeSize(){
        int enteredSize = 0;
        boolean acceptable = false;
        System.out.println("How big should the board be? You can choose between:");
        while (!acceptable) {
            System.out.println("small: 5x5, medium: 9x9 and big: 13x13");
            System.out.print("Enter 'small', medium' or 'big': ");
            String input = in.next();
            switch (input) {
                case "small", "s", "Small" -> {
                    enteredSize = 5;
                    acceptable = true;
                }
                case "medium", "m", "Medium" -> {
                    enteredSize = 9;
                    acceptable = true;
                }

                case "big", "b", "Big" -> {
                    enteredSize = 13;
                    acceptable = true;
                }
                default -> System.out.println("You entered something other than 'small', 'medium' or 'big', try again");
            }
        }
        System.out.println();
        return enteredSize;
    }

    /**
     * this method initializes both players. this isn't done in a constructor, since the size must be is not chosen at
     * this point in time
     */
    public void initializePlayers(){
        this.player = new HumanPlayer(size);
        this.computer = new ComputerPlayer(size, difficulty);
        player.board = board;
        computer.board = board;
    }

    /**
     * helper method to clean the screen whenever necessary
     */
    void clearScreen(){
        System.out.print("\u001b[2J");
        System.out.flush();
    }

    /**
     * this method toggles the active player. it will be called by game() every turn
     */
    void togglePlayer() {
        if (activePlayer == 'C'){
            activePlayer = 'P';
        } else {
            activePlayer = 'C';
        }
    }


    /**
     * this method checks if the game is over (if the map of the non-active player is only containing 'false')
     * it's called at the end of every turn in which the active player sunk a ship
     * @return boolean value whether game ist over or not.
     */
    public boolean gameOver() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (activePlayer == 'P' && computer.getHiddenMap()[i][j]) {
                    return false;
                } else if (activePlayer == 'C' && player.getHiddenMap()[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * this method executes the whole program and is the only method that needs to be in the main method
     */
    public void game(){
        setDifficulty();
        size = initializeSize();
        board.setSize(size);
        initializePlayers();
        board.setPlayers(player, computer);
        player.generateShips();
        computer.generateShips();
        clearScreen();
        System.out.println("Now make your first move:");
        while (true) {
            if (activePlayer == 'P') {
                board.drawBoard();
                player.printGuessList();
                System.out.println();
                int[] move = player.makeMove(false);
                if (player.checkIfHit(move, computer)) {
                    if (player.checkIfSunk(move, computer)) {
                        if (gameOver()) {
                            board.drawBoard();
                            System.out.println();
                            System.out.println("You won! Congratulations!");
                            return;
                        }
                    }
                }
            } else {
                int[] move = computer.makeMove(false);
                if (computer.checkIfHit(move, player)) {
                    if (computer.checkIfSunk(move, player)) {
                        if (gameOver()) {
                            board.drawBoard();
                            System.out.println();
                            System.out.println("You lost! Better luck next time!");
                            return;
                        }
                    }
                }
            }
            togglePlayer();
        }
    }
}
