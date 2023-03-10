package at.rote.klaue.gui;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class GUI {
    /**
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

        int selected;

        do {
            clearConsole();
            print(construct, prompt, longest, options);
            selected = promptInt("");
        } while (selected < 1 || selected > options.length);

        options[selected - 1].getCb().run();
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

    public static void printNumberList(List<String> args) {
        printNumberedList(args.toArray(new String[0]));
    }

    public static void printList(String ...args) {
        for (String arg : args) {
            System.out.printf(" • %s\n", arg);
        }
    }

    public static void printList(List<String> args) {
        printList(args.toArray(new String[0]));
    }

    /***
     * @unfinished
     * @since 2023-03-07
     * */
    public static void printTable(String[][] table) {
        int[] temp = new int[table[0].length];

        for (int i = 0; i < table[0].length; i++) {
            for (int i1 = 0; i1 < table[i].length; i1++) {
                if(table[i][i1].length() > temp[i1]) {
                    temp[i1] = table[i][i1].length();
                }
            }
        }

        StringBuilder startEnd = new StringBuilder("+");
        for (int length:temp) {
            startEnd.append("-".repeat(length + 2)).append("+");
        }

        String[] constructs = new String[temp.length];
        for (int i = 0; i < constructs.length; i++) {
            constructs[i] = "%s %-" + temp[i] +  "s |";
        }


        for (String[] strings : table) {
            System.out.println(startEnd);
            String outPut = "|";
            for (int i1 = 0; i1 < strings.length; i1++) {
                outPut = String.format(constructs[i1], outPut, strings[i1]);
            }
            System.out.println(outPut);
        }
        System.out.println(startEnd);
    }

    public static int promptInt(String prompt) {
        Integer res = null;
        do {
            try {
                System.out.print(prompt);
                res = SC.nextInt();
            } catch (InputMismatchException ex) {
                SC.next();
            }
        } while (res == null);
        return res;
    }

    public static String promptString(String prompt){
        System.out.print(prompt);
        String res;
        do {
            res = SC.next();
        } while (res == null || res.equals(""));
        return res;
    }

    public static void main(String[] args) {
    }
}
