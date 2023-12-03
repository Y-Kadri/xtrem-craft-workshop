package money_problem.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class Bank {
    private final ArrayList<ExchangeRate> exchangeRates;

    private Bank(Map<String, Double> exchangeRates) {
        this.exchangeRates = new ArrayList<>();
    }

    public static Bank createBank(Currency currency1, Currency currency2, double rate) {
        var bank = new Bank(new HashMap<>());
        bank.addExchangeRate(currency1, currency2, rate);
        return bank;
    }

    public void addExchangeRate(Currency currency1, Currency currency2, double rate) {
        exchangeRates.add(this.createStringForExchange(currency1, currency2), rate);
    }

    public Money convert(Money money, Currency to) throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        if (this.canConvert(money.getCurrency(), to)) {
            throw new MissingExchangeRateException(money.getCurrency(), to);
        }
        return money.getCurrency() == to
                ? money
                : money.convert(getExangeRate(money.getCurrency(), to), to);
    }

    private Double getExangeRate(Currency from, Currency to) {
        return exchangeRates.get(this.createStringForExchange(from, to));
    }

    private boolean canConvert(Currency currency1, Currency currency2) {
       return !(currency1 == currency2 || exchangeRates.containsKey(this.createStringForExchange(currency1, currency2)));
    }

    private String createStringForExchange(Currency currency1, Currency currency2) {
        return currency1 + "->" + currency2;
    }
}