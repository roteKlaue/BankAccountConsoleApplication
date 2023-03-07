package at.rote.klaue.gui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GUI {
    /***
     *    ___________            __         .__  __
     *    \_   _____/___________/  |_  ____ |__|/  |_  ____
     *     |    __)/  _ \_  __ \   __\/    \|  \   __\/ __ \
     *     |     \(  <_> )  | \/|  | |   |  \  ||  | \  ___/
     *     \___  / \____/|__|   |__| |___|  /__||__|  \___  >
     *         \/                         \/              \/
     */
    private static final Scanner SC = new Scanner(System.in);

    private static void clearConsole()
    {
        for (int i = 0; i < 50; ++i) System.out.println();
    }

    private static void print(String construct, String prompt, int length, Option ...options) {
        System.out.println(prompt);
        System.out.println("+" + "-".repeat(length + 11) + "+");

        for (int i = 0; i < options.length; i++) {
            System.out.printf(construct, options[i].color().getCode(), options[i].displayName(), Color.ANSI_RESET.getCode(), i + 1);
        }

        System.out.println("+" + "-".repeat(length + 11) + "+");
    }
    public static void printMenu(String prompt, Option ...options) {
        int longest = 0;
        for (Option option: options) {
            if(option.displayName().length() > longest)
                longest = option.displayName().length();
        }

        final String construct = "| %s%-" + longest + "s%s  ... (%" + (options.length > 9? "02": "") + "d) |\n";

        clearConsole();
        print(construct, prompt, longest, options);

        int selected;

        do {
            try {
                selected = SC.nextInt();
            } catch (InputMismatchException ex) {
                SC.next();
                selected = -2147483648;
            }

            if(selected < 1 || selected > options.length) {
                clearConsole();
                System.out.println("Invalid selection!");
                print(construct, prompt, longest, options);
            }
        } while (selected < 1 || selected > options.length);

        options[selected - 1].run().run();
    }

    public static void printNumberedList(String ...args) {
        int length = 0;

        for (String arg:args) {
            if (arg.length() > length) length = arg.length();
        }

        String construct = " %d. %-" + length + "s\n";

        for (int i = 0; i < args.length; i++) {
            System.out.printf(construct, i, args[i]);
        }
    }

    public static void printList(String ...args) {
        for (String arg : args) {
            System.out.printf(" • %s\n", arg);
        }
    }
}
