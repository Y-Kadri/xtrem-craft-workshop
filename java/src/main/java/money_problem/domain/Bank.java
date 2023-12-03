package money_problem.domain;

import java.util.*;

public final class Bank {
    private final Set<ExchangeRate> exchangeRates;

    private Bank(Set<ExchangeRate> exchangeRates) {
        this.exchangeRates = exchangeRates;
    }

    public static Bank createBank(Currency pivot, Currency currency2, double rate) {
        if (pivot == null) {
            throw new MissingPivotCurrencyException();
        }
        var bank = new Bank(new HashSet<>());
        bank.addExchangeRate(pivot, currency2, rate);
        return bank;
    }

    public void addExchangeRate(Currency currency1, Currency currency2, double rate) {
        ExchangeRate exist = this.getRateExchangeRate(currency1, currency2);
        if (exist != null) {
            exist.setRate(rate);
        } else {
            this.exchangeRates.add(new ExchangeRate(this.createStringForExchange(currency1, currency2), rate));
        }
    }

    public Money convert(Money money, Currency to) throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        if (this.canConvert(money.getCurrency(), to)) {
            throw new MissingExchangeRateException(money.getCurrency(), to);
        }
        return money.getCurrency() == to
                ? money
                : money.convert(getRateExchangeRate(money.getCurrency(), to).getRate(), to);
    }

    private Boolean containsExchangeRate(Currency from, Currency to) {
        for (ExchangeRate er : this.exchangeRates) {
            if (er.getStringForExchange().equals(this.createStringForExchange(from, to))) {
                return true;
            }
        }
        return false;
    }

    private ExchangeRate getRateExchangeRate(Currency from, Currency to) {
        for (ExchangeRate er : this.exchangeRates) {
            if (er.getStringForExchange().equals(this.createStringForExchange(from, to))) {
                return er;
            }
        }
        return null;
    }

    private boolean canConvert(Currency currency1, Currency currency2) {
        return !(currency1 == currency2 || this.containsExchangeRate(currency1, currency2));
    }

    private String createStringForExchange(Currency currency1, Currency currency2) {
        return currency1 + "->" + currency2;
    }
}