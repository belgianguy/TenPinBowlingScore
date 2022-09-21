public class Solution {
    public static int bowling_score(final String frames) {
        final Game game = new Game(frames);
        return game.calculateScore();
    }
}
