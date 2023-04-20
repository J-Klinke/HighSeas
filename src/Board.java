public class Board {

    public int size = 0;
    private HumanPlayer player;
    private ComputerPlayer computer;

    /**
     * draws the whole board, containing the player's map and the opponent's map
     */
    void drawBoard() {
        drawTopSection(size);
        drawLowerSection(size);
        drawFillLine(size);
        drawFillLine(size);
    }

    /**
     * helper method: draws the top section of the board (the upper 5 lines)
     * @param size the size of the board
     */
    void drawTopSection(int size) {
        drawFillLine(size);
        switch (size) {
            case 5 -> {
                drawWave(15, false);
                System.out.print(" High Seas ");
                drawWave(14, true);
            }
            case 9 -> {
                drawWave(23, false);
                System.out.print(" High Seas ");
                drawWave(22,true);

            }
            case 13 -> {
                drawWave(35, false);
                System.out.print(" High Seas ");
                drawWave(34, true);
            }
        }
        drawFillLine(size);

        switch (size) {
            case 5 -> {
                drawWave(5, false);
                System.out.print(" Your Map ");
                drawWave(10, false);
                System.out.print(" Enemy Map ");
                drawWave(4, true);
            }
            case 9 -> {
                drawWave(9, false);
                System.out.print(" Your Map ");
                drawWave(18, false);
                System.out.print(" Enemy Map ");
                drawWave(8,true);

            }
            case 13 -> {
                drawWave(16, false);
                System.out.print(" Your Map ");
                drawWave(28, false);
                System.out.print(" Enemy Map ");
                drawWave(15, true);
            }
        }
        drawFillLine(size);

        drawWave(size==13?6:4, false);
        System.out.print(" ");
        for (int i = 1; i <= size; i++) {
            System.out.print("|" + (i>9?(1):"~"));
        }
        System.out.print("| ");
        drawWave(size==13?11:7, false);
        System.out.print(" ");
        for (int i = 1; i <= size; i++) {
            System.out.print("|" + (i>9?(1):"~"));
        }
        System.out.print("| ");
        drawWave(size==13?5:3, true);


        drawWave(size==13?6:4, false);
        System.out.print(" ");
        for (int i = 1; i <= size; i++) {
            System.out.print("|" + (i>9?(i-10):i));
        }
        System.out.print("| ");
        drawWave(size==13?11:7, false);
        System.out.print(" ");
        for (int i = 1; i <= size; i++) {
            System.out.print("|" + (i>9?(i-10):i));
        }
        System.out.print("| ");
        drawWave(size==13?5:3, true);
    }

    /**
     * helper method: draws the lower half of the board, it basically draws both the player's and the opponent's Map.map
     * @param size the size of the board
     */
    void drawLowerSection(int size) {
        for (int i = 1; i <= size; i++) {
            drawWave(size==13?6:4, false);
            System.out.print(
                    switch (i) {
                        case 1 -> "A";
                        case 2 -> "B";
                        case 3 -> "C";
                        case 4 -> "D";
                        case 5 -> "E";
                        case 6 -> "F";
                        case 7 -> "G";
                        case 8 -> "H";
                        case 9 -> "I";
                        case 10 -> "J";
                        case 11 -> "K";
                        case 12 -> "L";
                        case 13 -> "M";
                        default -> "Error: Board.size is bigger than 13.";
                    }
            );
            System.out.print("|");
            for (int j = 0; j < size; j++) {
                System.out.print((player.getHiddenMap()[i-1][j]?"x":"~") + "|");
            }
            drawWave(size==13?12:8, false);
            System.out.print(
                    switch (i) {
                        case 1 -> "A";
                        case 2 -> "B";
                        case 3 -> "C";
                        case 4 -> "D";
                        case 5 -> "E";
                        case 6 -> "F";
                        case 7 -> "G";
                        case 8 -> "H";
                        case 9 -> "I";
                        case 10 -> "J";
                        case 11 -> "K";
                        case 12 -> "L";
                        case 13 -> "M";
                        default -> "Error: Board.size is bigger than 13.";
                    }
            );
            System.out.print("|");
            for (int j = 0; j < size; j++) {
                System.out.print((computer.getVisibleMap()[i-1][j]?"x":"~") + "|");
            }
            drawWave(size==13?6:4, true);
        }
    }

    /**
     * helper method: draws a line containing '~'
     * @param size the size of the board
     */
    void drawFillLine(int size) {
        for (int i = 0; i < (2 * size +1)*2 + 18 + (size == 13 ? 8 : 0); i++) {
            System.out.print("~");
        }
        System.out.println();
    }

    /**
     * helper method: draws a specific amount of '~' in a line
     * @param amount the amount of characters to be printed
     * @param lineBreak if true, a line break will be made after the line, if false, not
     */
    void drawWave(int amount, boolean lineBreak) {
        for (int i = 0; i < amount; i++) {
            System.out.print("~");
        }
        if (lineBreak) {
            System.out.println();
        }
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPlayers(HumanPlayer humanPlayer, ComputerPlayer computer){
        this.computer = computer;
        this.player = humanPlayer;
    }
}
