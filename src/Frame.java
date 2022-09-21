import java.util.ArrayList;
import java.util.List;

public class Frame {
    private final List<Roll> rollList = new ArrayList<>();
    private FrameState frameState;
    private int frameNumber;

    public Frame(final int frameNumber, final String frameText) {
        this.frameNumber = frameNumber;
        for(char frameChar : frameText.toCharArray()) {
            if('X' == frameChar) {
                frameState = FrameState.STRIKE;
            } else if ('/' == frameChar) {
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
            throw new IllegalStateException("No such roll index!");
        }
        final Roll roll = rollList.get(1);
        return roll.calculateScore();
    }
}
