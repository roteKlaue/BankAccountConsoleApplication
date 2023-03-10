package at.rote.klaue;

import java.util.List;

public class Config {
    /**
     *    ___________            __         .__  __
     *    \_   _____/___________/  |_  ____ |__|/  |_  ____
     *     |    __)/  _ \_  __ \   __\/    \|  \   __\/ __ \
     *     |     \(  <_> )  | \/|  | |   |  \  ||  | \  ___/
     *     \___  / \____/|__|   |__| |___|  /__||__|  \___  >
     *         \/                         \/              \/
     */
    private static final Config instance = new Config();
    private int iteration = 10;
    private boolean running = false;
    private List<Thread> threads;
    private boolean exit = true;

    private Config() {}

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public int getIteration() {
        return iteration;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isExit() {
        return exit;
    }

    public void setExit(boolean exit) {
        this.exit = exit;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setThreads(List<Thread> threads) {
        this.threads = threads;
    }

    public List<Thread> getThreads() {
        return threads;
    }

    public static Config getInstance() {
        return instance;
    }
}
