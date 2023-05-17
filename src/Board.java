public class Board {

    public int size = 0;
    private HumanPlayer player;
    private ComputerPlayer computer;

    /**
     * draws the whole board, containing the player's map and the opponent's map.
     */
    public void drawBoard() {
        drawTitleLine();
        drawPlayerIdentifierLine();
        drawUpperFieldNumberLine();
        drawLowerFieldNumberLine();
        drawLowerSection();
        drawFillLine();
        drawFillLine();
    }

    /**
     * draws the line where the number of the fields are displayed.
     */
    private void drawLowerFieldNumberLine() {
        drawWave(size == Size.BIG ? 6 : 4, false);
        System.out.print(" ");
        for (int i = 1; i <= size; i++) {
            System.out.print("|" + (i > Size.MEDIUM ? (i - 10) : i));
        }
        System.out.print("| ");
        drawWave(size == Size.BIG ? 11 : 7, false);
        System.out.print(" ");
        for (int i = 1; i <= size; i++) {
            System.out.print("|" + (i > Size.MEDIUM ? (i - 10) : i));
        }
        System.out.print("| ");
        drawWave(size == Size.BIG ? 5 : 3, true);
    }

    /**
     * draw the upper Field number line. In size small & medium it only exists of '~',
     * in large it also contains numbers.
     */
    private void drawUpperFieldNumberLine() {
        drawWave(size == Size.BIG ? 6 : 4, false);
        System.out.print(" ");
        for (int i = 1; i <= size; i++) {
            System.out.print("|" + (i > Size.MEDIUM ? (1) : "~"));
        }
        System.out.print("| ");
        drawWave(size == Size.BIG ? 11 : 7, false);
        System.out.print(" ");
        for (int i = 1; i <= size; i++) {
            System.out.print("|" + (i > Size.MEDIUM ? (1) : "~"));
        }
        System.out.print("| ");
        drawWave(size == Size.BIG ? 5 : 3, true);
    }

    /**
     * draws the line where the player's and the computer's fields are indicated.
     */
    private void drawPlayerIdentifierLine() {
        switch (size) {
            case Size.SMALL -> {
                drawWave(5, false);
                System.out.print(" Your Map ");
                drawWave(10, false);
                System.out.print(" Enemy Map ");
                drawWave(4, true);
            }
            case Size.MEDIUM -> {
                drawWave(9, false);
                System.out.print(" Your Map ");
                drawWave(18, false);
                System.out.print(" Enemy Map ");
                drawWave(8, true);

            }
            case Size.BIG -> {
                drawWave(16, false);
                System.out.print(" Your Map ");
                drawWave(28, false);
                System.out.print(" Enemy Map ");
                drawWave(15, true);
            }
        }
        drawFillLine();
    }

    /**
     * draws the title line in Board.
     */
    private void drawTitleLine() {
        drawFillLine();
        switch (size) {
            case Size.SMALL -> {
                drawWave(15, false);
                System.out.print(" High Seas ");
                drawWave(14, true);
            }
            case Size.MEDIUM -> {
                drawWave(23, false);
                System.out.print(" High Seas ");
                drawWave(22, true);

            }
            case Size.BIG -> {
                drawWave(35, false);
                System.out.print(" High Seas ");
                drawWave(34, true);
            }
        }
        drawFillLine();
    }


    /**
     * helper method: draws the lower half of the board, it basically draws both the player's
     * and the opponent's Map.map.
     */
    private void drawLowerSection() {
        for (int i = 1; i <= size; i++) {
            drawWave(size == Size.BIG ? 6 : 4, false);
            printFieldLetter(i);
            for (int j = 0; j < size; j++) {
                System.out.print((player.getHiddenMap()[i - 1][j] ? "x" : "~") + "|");
            }
            drawWave(size == Size.BIG ? 12 : 8, false);
            printFieldLetter(i);
            for (int j = 0; j < size; j++) {
                System.out.print((computer.getVisibleMap()[i - 1][j] ? "x" : "~") + "|");
            }
            drawWave(size == Size.BIG ? 6 : 4, true);
        }
    }

    /**
     * prints the row's letter indicator.
     * @param row the row
     */
    private static void printFieldLetter(int row) {
        System.out.print(
                switch (row) {
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
    }

    /**
     * helper method: draws a line containing '~'.
     */
    private void drawFillLine() {
        for (int i = 0; i < (2 * size + 1) * 2 + 18 + (size == Size.BIG ? 8 : 0); i++) {
            System.out.print("~");
        }
        System.out.println();
    }

    /**
     * helper method: draws a specific amount of '~' in a line.
     * @param amount the amount of characters to be printed
     * @param lineBreak if true, a line break will be made after the line, if false, not
     */
    private void drawWave(int amount, boolean lineBreak) {
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

    public void setPlayers(HumanPlayer humanPlayer, ComputerPlayer computer) {
        this.computer = computer;
        this.player = humanPlayer;
    }
}
