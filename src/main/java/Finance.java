import com.h2.BestLoanRates;
import com.h2.MortgageCalculator;
import com.h2.SavingsCalculator;

import java.util.Arrays;
import java.util.Map;

public class Finance {
    public static final String BEST_LOAN_RATES = "bestLoanRates";
    public static final String SAVINGS_CALCULATOR = "savingsCalculator";
    public static final String MORTGAGE_CALCULATOR = "mortgageCalculator";
    public static final Map<String, String> commandsToUsage = Map.of(BEST_LOAN_RATES, "usage: bestLoanRates",
            SAVINGS_CALCULATOR, "usage: savingsCalculator <credits separated by ','> <debits separated by ','>",
            MORTGAGE_CALCULATOR, "usage: mortgageCalculator <loanAmount> <termInYears> <annualRate>");

    public static void main(String[] args) {
        String command = args[0];
        if (!commandsToUsage.containsKey(command)) {
            System.out.print(command + ": command not found\n");
        } else {
            boolean isValidCommand = validateCommandArguments(args);
            if (!isValidCommand) {
                System.out.print(commandsToUsage.get(command) + "\n");
            } else {
                executeCommand(command, Arrays.copyOfRange(args, 1, args.length));
            }
        }
    }

    private static void executeCommand(String command, String[] arguments) {
        switch (command) {
            case BEST_LOAN_RATES: System.out.print("Finding best loan rates ...\n"); BestLoanRates.main(arguments); break;
            case SAVINGS_CALCULATOR: System.out.print("Finding your net savings ...\n"); SavingsCalculator.main(arguments); break;
            case MORTGAGE_CALCULATOR: System.out.print("Finding your monthly payment ...\n"); MortgageCalculator.main(arguments); break;
        }
    }

    private static boolean validateCommandArguments(String[] args) {
        switch (args[0]) {
            case BEST_LOAN_RATES: return args.length == 1;
            case SAVINGS_CALCULATOR: return args.length == 3;
            case MORTGAGE_CALCULATOR: return args.length == 4;
            default: return false;
        }
    }
}
