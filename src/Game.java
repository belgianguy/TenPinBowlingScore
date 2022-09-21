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
            final FrameState frameState = frame.getFrameState();
            System.out.println(frameState.name());
            if(frame.getFrameNumber() < 9) {
                if (FrameState.REGULAR.equals(frameState)) {
                    gameScore += frame.calculateScore();
                    System.out.println("REGULAR FRAME " + frame.getFrameNumber() + " " + frame.calculateScore() + " " + gameScore);
                } else if (FrameState.STRIKE.equals(frameState)) {
                    int score = frame.calculateScore();
                    int nextFrameNumber = frame.getFrameNumber() + 1;
                    if (nextFrameNumber < frameList.size()) {
                        final Frame firstFrameAfter = frameList.get(nextFrameNumber);
                        if(FrameState.SPARE.equals(firstFrameAfter.getFrameState())) {
                            score += firstFrameAfter.calculateScore();
                        } else if (firstFrameAfter.getNumberOfRolls() >= 2) {
                            score += firstFrameAfter.getValueOfFirstRoll();
                            score += firstFrameAfter.getValueOfSecondRoll();
                        } else if (firstFrameAfter.getNumberOfRolls() == 1) {
                            nextFrameNumber = nextFrameNumber + 1;
                            if (nextFrameNumber < frameList.size()) {
                                final Frame secondFrameAfter = frameList.get(nextFrameNumber);
                                score += firstFrameAfter.getValueOfFirstRoll();
                                score += secondFrameAfter.getValueOfFirstRoll();
                            }
                        }
                        gameScore += score;
                        System.out.println("STRIKE FRAME " + frame.getFrameNumber() + " " + score + " " + gameScore);
                    }
                } else if(FrameState.SPARE.equals(frameState)) {
                    int score = frame.calculateScore();
                    int nextFrameNumber = frame.getFrameNumber() + 1;
                    if (nextFrameNumber < frameList.size()) {
                        final Frame firstFrameAfter = frameList.get(nextFrameNumber);
                        score += firstFrameAfter.getValueOfFirstRoll();
                    }
                    gameScore += score;
                    System.out.println("SPARE FRAME " + frame.getFrameNumber() + " " + score + " " + gameScore);
                }
            } else {
                gameScore += frame.calculateScore();
                System.out.println("10th FRAME " + frame.getFrameNumber() + " " + frame.calculateScore() + " " + gameScore);
            }

        }
        System.out.println("GAME " + gameString + " SCORE " + gameScore);
        return gameScore;
    }

    private void parseGameString(final String gameString) {
        final String[] framesArray = gameString.split(Constants.SEPARATOR);
        for (int frameCounter = 0; frameCounter < framesArray.length; frameCounter++) {
            final Frame frame = new Frame(frameCounter, framesArray[frameCounter]);
            frameList.add(frame);
        }
    }
}
