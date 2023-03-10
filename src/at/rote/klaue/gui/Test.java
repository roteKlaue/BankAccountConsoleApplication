package at.rote.klaue.gui;

public class Test {
    /**
     *    ___________            __         .__  __
     *    \_   _____/___________/  |_  ____ |__|/  |_  ____
     *     |    __)/  _ \_  __ \   __\/    \|  \   __\/ __ \
     *     |     \(  <_> )  | \/|  | |   |  \  ||  | \  ___/
     *     \___  / \____/|__|   |__| |___|  /__||__|  \___  >
     *         \/                         \/              \/
     */
    public static void main(String[] args) throws InterruptedException {
        GUI.printNumberedList("hello", "why", "java");
        GUI.printList("hello", "why", "java");

        System.out.println(GUI.promptInt("Test: "));

        Thread testing = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ended");
        });

        testing.start();
        System.out.println(testing.isAlive());

        Thread.sleep(500);
        System.out.println(testing.isAlive());

        Thread.sleep(700);
        System.out.println(testing.isAlive());
    }
}
