package at.rote.klaue;

import at.rote.klaue.gui.Callback;
import at.rote.klaue.gui.Color;
import at.rote.klaue.gui.GUI;
import at.rote.klaue.gui.Option;

public class Account implements Callback {
    /***
     *    ___________            __         .__  __
     *    \_   _____/___________/  |_  ____ |__|/  |_  ____
     *     |    __)/  _ \_  __ \   __\/    \|  \   __\/ __ \
     *     |     \(  <_> )  | \/|  | |   |  \  ||  | \  ___/
     *     \___  / \____/|__|   |__| |___|  /__||__|  \___  >
     *         \/                         \/              \/
     */
    private final String name;
    private final Option[] allOptions;
    private boolean selected = false;
    private Option myOption;

    public Account(String name, Option[] options) {
        this.name = name;
        allOptions = options;
    }

    public Account(String name, Option[] options, boolean selected) {
        this.name = name;
        this.selected = selected;
        allOptions = options;
    }

    public void setMyOption(Option myOption) {
        this.myOption = myOption;
    }

    public String getName() {
        return name;
    }

    public boolean getSelected() {
        return selected;
    }

    @Override
    public void run() {
        selected = !selected;
        myOption.setColor(selected? Color.ANSI_PURPLE_BACKGROUND: Color.ANSI_RESET);
        GUI.printMenu("Who should be involved?", allOptions);
    }
}
