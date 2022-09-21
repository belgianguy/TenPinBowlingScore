import java.util.ArrayList;
import java.util.List;

public class Frame {
    private final List<Roll> rollList = new ArrayList<>();
    private FrameState frameState;
    private final int frameNumber;

    public Frame(final int frameNumber, final String frameString) {
        this.frameNumber = frameNumber;
        parseFrameString(frameNumber, frameString);
    }

    private void parseFrameString(final int frameNumber, final String frameText) {
        for(char frameChar : frameText.toCharArray()) {
            if(9 == frameNumber) {
                frameState = FrameState.TENTH_FRAME;
            } else if(Constants.STRIKE_CHAR == frameChar) {
                frameState = FrameState.STRIKE;
            } else if (Constants.SPARE_CHAR == frameChar) {
                frameState = FrameState.SPARE;
            } else {
                frameState = FrameState.REGULAR;
            }
            final Roll roll = new Roll(frameChar);
            rollList.add(roll);
        }
    }

    public int calculateScore() {
        if(FrameState.SPARE.equals(frameState)) {
            return Constants.BONUS_POINTS;
        }

        int frameScore = 0;
        for(final Roll roll : rollList) {
            frameScore += roll.calculateScore();
        }
        return frameScore;
    }

    public FrameState getFrameState() {
        return frameState;
    }

    public int getNumberOfRolls() {
        return rollList.size();
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public int getValueOfFirstRoll() {
        final Roll roll = rollList.get(0);
        return roll.calculateScore();
    }

    public int getValueOfSecondRoll() {
        if(rollList.size() < 2) {
            throw new IllegalStateException(Constants.NO_SUCH_ROLL_INDEX);
        }
        final Roll roll = rollList.get(1);
        return roll.calculateScore();
    }
}
