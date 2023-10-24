package money_problem.domain;

import java.util.Objects;

public class Money {
    private double amount;
    private Currency currency;

    public static Money create(double amount, Currency currency) throws NegativeNumberException, InvalidNumberException {
        if (amount <= 0) {
            throw new NegativeNumberException();
        }
        if (Double.isInfinite(amount)) {
            throw new InvalidNumberException("Number infinite");
        }
        return new Money(amount, currency);
    }

    private Money(double amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money add(Money money) throws InvalidNumberException {
        if (money.currency != this.currency) {
            throw new InvalidNumberException("Can't add two different currencies");
        }
        return new Money(this.amount + money.amount, this.currency);
    }

    public Money multiply(Money money) throws InvalidNumberException {
        if (money.currency != this.currency) {
            throw new InvalidNumberException("Can't add two different currencies");
        }
        return new Money(this.amount * money.amount, this.currency);
    }

    public Money divide(Money money) throws InvalidNumberException {
        if (money.amount == 0) {
            throw new ArithmeticException();
        }
        if (money.currency != this.currency) {
            throw new InvalidNumberException("Can't add two different currencies");
        }
        return new Money(this.amount / money.amount, this.currency);
    }

    public Money convert(double rate, Currency currency) throws NegativeNumberException, InvalidNumberException {
        return  Money.create(this.amount * rate, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Double.compare(amount, money.amount) == 0 && currency == money.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currency);
    }

    public double getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
