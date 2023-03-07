package at.rote.klaue.gui;

public class Option {
    /***
     *    ___________            __         .__  __
     *    \_   _____/___________/  |_  ____ |__|/  |_  ____
     *     |    __)/  _ \_  __ \   __\/    \|  \   __\/ __ \
     *     |     \(  <_> )  | \/|  | |   |  \  ||  | \  ___/
     *     \___  / \____/|__|   |__| |___|  /__||__|  \___  >
     *         \/                         \/              \/
     */
    private final Callback cb;
    private final String displayName;
    private Color clr = Color.ANSI_RESET;

    public Option(String displayName, Callback run) {
        this.displayName = displayName;
        cb = run;
    }

    public String displayName() {
        return this.displayName;
    }

    public void setColor(Color clr) {
        this.clr = clr;
    }

    public Color color() {
        return clr;
    }

    public Callback run() {
        return cb;
    }
}