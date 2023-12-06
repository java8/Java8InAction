package lambdasinaction.chap2;

public enum Colour {
    GREEN("green"),
    RED("red");

    public final String label;

    private Colour(String label) {
        this.label = label;
    }
}
