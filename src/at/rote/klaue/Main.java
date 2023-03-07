package at.rote.klaue;

import at.rote.klaue.gui.GUI;
import at.rote.klaue.gui.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    /***
     *    ___________            __         .__  __
     *    \_   _____/___________/  |_  ____ |__|/  |_  ____
     *     |    __)/  _ \_  __ \   __\/    \|  \   __\/ __ \
     *     |     \(  <_> )  | \/|  | |   |  \  ||  | \  ___/
     *     \___  / \____/|__|   |__| |___|  /__||__|  \___  >
     *         \/                         \/              \/
     */
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        Account[] accounts = new Account[4];
        Option[] options = new Option[accounts.length + 1];

        accounts[0] = new Account("Jan", options);
        accounts[1] = new Account("Jonas", options);
        accounts[2] = new Account("Raphael", options);
        accounts[3] = new Account("Moritz", options);

        for (int i = 0; i < accounts.length; i++) {
            options[i] = new Option(accounts[i].getName(), accounts[i]);
            accounts[i].setMyOption(options[i]);
        }

        options[options.length - 1] = new Option("Done", () -> {
            BankAccount bankAccount = new BankAccount(50);
            List<ThreadedAccount> threadedAccounts = new ArrayList<>();
            for (Account ac:accounts) {
                if(ac.getSelected()) {
                    threadedAccounts.add(new ThreadedAccount(ac.getName(), bankAccount));
                }
            }


            int amount;
            do {
                System.out.println("Set the number of iterations: ");
                amount = SCANNER.nextInt();
            } while (amount < 1);

            ThreadedAccount.setIterations(amount);

            for (ThreadedAccount threadedAccount : threadedAccounts) {
                new Thread(threadedAccount).start();
            }
        });

        GUI.printMenu("Who should be involved?", options);
    }
}
