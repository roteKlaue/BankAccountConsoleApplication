package at.rote.klaue;

public class Config {
    private static final Config instance = new Config();
    private int iteration = 10;

    private Config() {}

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public int getIteration() {
        return iteration;
    }

    public static Config getInstance() {
        return instance;
    }
}
