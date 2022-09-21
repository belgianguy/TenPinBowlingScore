public class Solution {
    public static int bowling_score(String frames) {
        final Game game = new Game(frames);
        return game.calculateScore();
    }
}
