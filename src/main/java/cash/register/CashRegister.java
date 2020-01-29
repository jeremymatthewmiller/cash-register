/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package cash.register;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CashRegister {

    public static final String SHOW = "show";
    public static final String PUT = "put";
    public static final String TAKE = "take";
    public static final String CHANGE = "change";
    public static final String QUIT = "quit";

    public static void main(String[] args) {
        CashRegister cashRegister = new CashRegister();
        Map<Currency, Integer> register = cashRegister.initializeRegister();
        String command = "";

        System.out.println("Ready");
        do {
            command = cashRegister.getCommand();
            cashRegister.performAction(command, register);
        } while (!command.equals(QUIT));

        System.out.println("Bye");
    }

    String getCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = "";

        do {
            System.out.println("Please enter a command, or \"quit\"");
            command = scanner.nextLine().toLowerCase();
        } while (isCommandValid(command));

        return command;
    }

    boolean isCommandValid(final String command) {
        if (command == null) {
            return false;
        }

        String [] commands = command.split(" ");

        if (commands.length == 0 || commands.length > 6) {
            return false;
        }

        boolean isValid = false;
        switch (commands[0]) {
        case SHOW:
            isValid = isShowCommandValid(commands);
            break;
        case PUT:
            isValid = isPutCommandValid(commands);
            break;
        case TAKE:
            isValid = isTakeCommandValid(commands);
            break;
        case CHANGE:
            isValid = isChangeCommandValid(commands);
            break;
        case QUIT:
        default:
            isValid = false;
            break;
        }
        return isValid;
    }

    boolean isShowCommandValid(final String[] commands) {
        return commands.length == 1;
    }

    boolean isPutCommandValid(final String[] commands) {
        if (commands.length != 6) {
            return false;
        }

        for (int i = 1; i < commands.length; i++) {
            if (!isInteger(commands[i])) {
                return false;
            }
        }
        return true;
    }

    boolean isTakeCommandValid(final String[] commands) {
        return isPutCommandValid(commands);
    }

    boolean isChangeCommandValid(final String[] commands) {
        if (commands.length != 2) {
            return false;
        }
        return isInteger(commands[1]);
    }

    boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    void performAction(final String command, Map<Currency, Integer> register) {
        String [] commands = command.split(" ");

        switch (commands[0]) {
        case SHOW:
            performShowAction(register);
            break;
        case PUT:
            performPutAction(commands, register);
            break;
        case TAKE:
            performTakeAction(commands, register);
            break;
        case CHANGE:
            performChangeAction(commands, register);
            break;
        }
    }

    void performShowAction(Map<Currency, Integer> register) {
        int registerTotal = calculateRegisterTotal(register);
        String countByDenomination = getCountByDenomination(register);

        System.out.println("$ " + registerTotal + countByDenomination);
    }

    int calculateRegisterTotal(Map<Currency, Integer> register) {
        int registerTotal = 0;

        for (Map.Entry<Currency, Integer> entry : register.entrySet()) {
            registerTotal += entry.getKey().value * entry.getValue();
        }
        return registerTotal;
    }

    String getCountByDenomination(Map<Currency, Integer> register) {
        String countDisplay = "";

        countDisplay += " " + register.get(Currency.TWENTY);
        countDisplay += " " + register.get(Currency.TEN);
        countDisplay += " " + register.get(Currency.FIVE);
        countDisplay += " " + register.get(Currency.TWO);
        countDisplay += " " + register.get(Currency.ONE);

        return countDisplay;
    }

    void performPutAction(final String[] commands, Map<Currency, Integer> register) {

        addToRegister(Integer.parseInt(commands[1]), Currency.TWENTY, register);
        addToRegister(Integer.parseInt(commands[2]), Currency.TEN, register);
        addToRegister(Integer.parseInt(commands[3]), Currency.FIVE, register);
        addToRegister(Integer.parseInt(commands[4]), Currency.TWO, register);
        addToRegister(Integer.parseInt(commands[5]), Currency.ONE, register);

        performShowAction(register);
    }

    void addToRegister(int amount, Currency currency, Map<Currency, Integer> register) {
        int currentAmount = register.get(currency);
        register.put(currency, currentAmount + amount);
    }

    void performTakeAction(final String[] commands, Map<Currency, Integer> register) {

        deductFromRegister(Integer.parseInt(commands[1]), Currency.TWENTY, register);
        deductFromRegister(Integer.parseInt(commands[2]), Currency.TEN, register);
        deductFromRegister(Integer.parseInt(commands[3]), Currency.FIVE, register);
        deductFromRegister(Integer.parseInt(commands[4]), Currency.TWO, register);
        deductFromRegister(Integer.parseInt(commands[5]), Currency.ONE, register);

        performShowAction(register);
    }

    void deductFromRegister(int amount, Currency currency, Map<Currency, Integer> register) {
        int currentAmount = register.get(currency);

        if (currentAmount - amount < 0) {
            register.put(currency, 0);
        } else {
            register.put(currency, currentAmount - amount);
        }
    }

    void performChangeAction(final String[] commands, Map<Currency, Integer> register) {
        int amount = Integer.parseInt(commands[1]);

        for (Currency currency : Currency.values()) {
            while (register.get(currency) > 0 && amount > 0) {
                if (amount - currency.value >= 0) {
                    deductFromRegister(currency.value, currency, register);
                } else {
                    break;
                }
            }
        }

        if (amount != 0) {
            System.out.println("sorry");
        } else {
            performShowAction(register);
        }
    }

    Map<Currency, Integer> initializeRegister() {
        Map<Currency, Integer> register = new HashMap<>();
        register.put(Currency.TWENTY, Integer.valueOf(0));
        register.put(Currency.TEN, Integer.valueOf(0));
        register.put(Currency.FIVE, Integer.valueOf(0));
        register.put(Currency.TWO, Integer.valueOf(0));
        register.put(Currency.ONE, Integer.valueOf(0));

        return register;
    }
}
