package set1.signle_byte_xor_chiper;

public class ScoreCharText {
    private final long  score;
    private final char c;
    private final String text;

    public ScoreCharText(long score, char c, String text) {
        this.score = score;
        this.c = c;
        this.text = text;
    }

    public long getScore() {
        return score;
    }

    public char getC() {
        return c;
    }

    public String getText() {
        return text;
    }
}
