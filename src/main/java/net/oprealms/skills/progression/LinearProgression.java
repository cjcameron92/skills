package net.oprealms.skills.progression;

public class LinearProgression implements Progression {

    private final int a;
    private final int b;

    public LinearProgression(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override public long getRequired(int level) {
        return a + ((long) level * b);
    }
}
