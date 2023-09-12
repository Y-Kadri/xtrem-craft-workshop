package money_problem.domain;

public class MoneyCalculator {
    public static double add(double amount, Currency currency, double amount2) {
        // convert amount
        return amount + amount2;
    }

    public static double multiply(double amount, Currency currency, int value) {
        // convert amount
        // todo not multiplying by negative number
        return amount * value;
    }

    public static double divide(double amount, Currency currency, int value) {
        // convert amount (bank method)
        // TODO Division by zero
        return amount / value;
    }
}