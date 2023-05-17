public class HumanGuess implements Comparable<HumanGuess> {

    private final String guess;

    public HumanGuess(String guess) {
        this.guess = guess;
    }

    /**
     * compares instances of the HumanGuess class. The guess String are sorted first by the beginning letter, then by
     * the number. Example: A-3, A-5, B-1, A-1, D-4, A-6 -> A-1, A-3, A-5, A-6, B-1, D-4
     * @param other the object to be compared.
     * @return int values
     */
    @Override
    public int compareTo(HumanGuess other) {
        int guessInt;
        if (this.guess.charAt(0) == other.guess.charAt(0)) {
            String[] splitGuessThis = this.guess.split("-");
            String[] splitGuessOther = other.guess.split("-");
            guessInt = Integer.parseInt(splitGuessThis[1]) - Integer.parseInt(splitGuessOther[1]);
        } else {
            guessInt = this.guess.charAt(0) - other.guess.charAt(0);
        }
        return guessInt;
    }

    @Override
    public String toString() {
        return guess;
    }
}