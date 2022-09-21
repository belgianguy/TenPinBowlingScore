import java.util.ArrayList;
import java.util.List;

public class Game {
    private final List<Frame> frameList = new ArrayList<>();
    private final String gameString;

    public Game(final String gameString) {
        this.gameString = gameString;
        parseGameString(gameString);
    }

    public int calculateScore() {
        int gameScore = 0;

        for(final Frame frame : frameList) {
            int frameScore = 0;
            final FrameState frameState = frame.getFrameState();

            if (FrameState.REGULAR.equals(frameState) || FrameState.TENTH_FRAME.equals(frameState)) {
                frameScore = frame.calculateScore();
            } else if (FrameState.STRIKE.equals(frameState)) {
                frameScore = calculateStrikeFrame(frame);
            } else if(FrameState.SPARE.equals(frameState)) {
                frameScore = calculateSpareFrame(frame);
            }
            gameScore += frameScore;
            System.out.printf(Constants.END_FRAME_OUTPUT, frameState, frameScore, gameScore);
        }
        System.out.printf((Constants.END_GAME_OUTPUT), gameString, gameScore);
        return gameScore;
    }

    private int calculateSpareFrame(final Frame frame) {
        int frameScore;
        frameScore = frame.calculateScore();
        int nextFrameNumber = frame.getFrameNumber() + 1;
        if (nextFrameNumber < frameList.size()) {
            final Frame firstFrameAfter = frameList.get(nextFrameNumber);
            frameScore += firstFrameAfter.getValueOfFirstRoll();
        }
        return frameScore;
    }

    private int calculateStrikeFrame(final Frame frame) {
        int frameScore;
        frameScore = frame.calculateScore();
        int nextFrameNumber = frame.getFrameNumber() + 1;
        if (nextFrameNumber < frameList.size()) {
            final Frame firstFrameAfter = frameList.get(nextFrameNumber);
            if(FrameState.SPARE.equals(firstFrameAfter.getFrameState())) {
                frameScore += firstFrameAfter.calculateScore();
            } else if (firstFrameAfter.getNumberOfRolls() >= 2) {
                frameScore += firstFrameAfter.getValueOfFirstRoll();
                frameScore += firstFrameAfter.getValueOfSecondRoll();
            } else if (firstFrameAfter.getNumberOfRolls() == 1) {
                nextFrameNumber = nextFrameNumber + 1;
                if (nextFrameNumber < frameList.size()) {
                    final Frame secondFrameAfter = frameList.get(nextFrameNumber);
                    frameScore += firstFrameAfter.getValueOfFirstRoll();
                    frameScore += secondFrameAfter.getValueOfFirstRoll();
                }
            }
        }
        return frameScore;
    }

    private void parseGameString(final String gameString) {
        final String[] framesArray = gameString.split(Constants.SEPARATOR);
        for (int frameCounter = 0; frameCounter < framesArray.length; frameCounter++) {
            final Frame frame = new Frame(frameCounter, framesArray[frameCounter]);
            frameList.add(frame);
        }
    }
}
