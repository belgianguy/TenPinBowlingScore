public class Roll {
    private final char value;

    public Roll(final char value) {
        this.value = value;
    }

    public int calculateScore() {
        if(Constants.STRIKE_CHAR == value) {
            return Constants.BONUS_POINTS;
        }

        return Character.getNumericValue(value);
    }
}
