package at.rote.klaue;

import java.time.LocalTime;
import java.util.Random;

public class ThreadedAccount implements Runnable {
    /***
     *    ___________            __         .__  __
     *    \_   _____/___________/  |_  ____ |__|/  |_  ____
     *     |    __)/  _ \_  __ \   __\/    \|  \   __\/ __ \
     *     |     \(  <_> )  | \/|  | |   |  \  ||  | \  ___/
     *     \___  / \____/|__|   |__| |___|  /__||__|  \___  >
     *         \/                         \/              \/
     */
    private static final Random RANDOM = new Random();
    private static int iterations = 20;
    private final String name;
    private final BankAccount bankAccount;

    public ThreadedAccount(String name, BankAccount bankAccount) {
        this.name = name;
        this.bankAccount = bankAccount;
    }
    public static void setIterations(int iterations) {
        ThreadedAccount.iterations = iterations;
    }

    @Override
    public final void run() {
        for (int i = iterations; i > 0; i--) {
            synchronized (bankAccount) {
                int value = RANDOM.nextInt(100)-50;
                System.out.printf("%s wants to withdraw %d€\n", name, value);
                LocalTime time = LocalTime.now();
                while (bankAccount.isNegative(value)) {
                    try {
                        if ((LocalTime.now().getSecond() - time.getSecond()) > 2) break;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                if (!bankAccount.isNegative(value)) {
                    bankAccount.withdraw(value);
                    System.out.printf("Total balance: %d€\n", bankAccount.getBalance());
                    bankAccount.notify();
                }
            }
            try {
                Thread.sleep(RANDOM.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Simulation finished for %s.\s", name);
    }
}

