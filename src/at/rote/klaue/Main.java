package at.rote.klaue;

import at.rote.klaue.gui.GUI;
import at.rote.klaue.gui.Option;

import java.util.ArrayList;
import java.util.List;

public class Main {
    /***
     *    ___________            __         .__  __
     *    \_   _____/___________/  |_  ____ |__|/  |_  ____
     *     |    __)/  _ \_  __ \   __\/    \|  \   __\/ __ \
     *     |     \(  <_> )  | \/|  | |   |  \  ||  | \  ___/
     *     \___  / \____/|__|   |__| |___|  /__||__|  \___  >
     *         \/                         \/              \/
     */
    public static void main(String[] args) {
        List<Option> options = new ArrayList<>();
        List<Account> accounts = new ArrayList<>(List.of(new Account("Jan", options),
                new Account("Jonas", options),
                new Account("Raphael", options),
                new Account("Moritz", options)));

        for (int i = 0; i < accounts.size(); i++) {
            options.add(i, new Option(accounts.get(i).getName(), accounts.get(i)));
            accounts.get(i).setMyOption(options.get(i));
        }

        options.add(options.size(), new Option("Add User", () -> {
            accounts.add(new Account(GUI.promptString("Users name: "), options));
            options.add(accounts.size() - 1, new Option(accounts.get(accounts.size() - 1).getName(), accounts.get(accounts.size() - 1)));
            accounts.get(accounts.size() - 1).setMyOption(options.get(accounts.size() - 1));
            GUI.printMenu("Who should be involved?", options.toArray(Option[]::new));
        }));

        options.add(options.size(), new Option("Remove User", () -> {
            if(accounts.size() == 0) {
                GUI.printMenu("Who should be involved?", options.toArray(Option[]::new));
                return;
            }

            int user;
            do {
                user = GUI.promptInt("Which user should be removed? ");
            } while (user < 1 || user > accounts.size());

            accounts.remove(user - 1);
            options.remove(user - 1);
            GUI.printMenu("Who should be involved?", options.toArray(Option[]::new));
        }));


        options.add(options.size(), new Option("Done", () -> {
            BankAccount bankAccount = new BankAccount(50);
            List<ThreadedAccount> threadedAccounts = accounts.stream()
                    .filter(Account::getSelected)
                    .map((acc) -> new ThreadedAccount(acc.getName(), bankAccount))
                    .toList();

            int amount;
            do {
                amount = GUI.promptInt("Set the number of iterations: ");
            } while (amount < 1);

            ThreadedAccount.setIterations(amount);
            threadedAccounts.forEach((acc) -> new Thread(acc).start());
        }));

        GUI.printMenu("Who should be involved?", options.toArray(Option[]::new));
    }
}
