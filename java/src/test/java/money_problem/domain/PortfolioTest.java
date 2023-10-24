/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package money_problem.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static money_problem.domain.Currency.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author benja
 */
class Portfolio {

    Map<Currency, Double> wallet = new HashMap<>();
    Portfolio() {
    }

    void add(Money money) {
        if (wallet.containsKey(money.getCurrency())){
            double actualValue = wallet.get(money.getCurrency());
            wallet.put(money.getCurrency(), actualValue + money.getAmount());
        } else {
            wallet.put(money.getCurrency(), money.getAmount());
        }
    }

    double evaluate(Currency currency, Bank bank) throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        double totalValue = 0;

        for (Map.Entry<Currency, Double> entry : wallet.entrySet()) {
            Currency holdingCurrency = entry.getKey();
            double amount = entry.getValue();
            Money convertedValue = bank.convert(Money.create(amount, holdingCurrency), currency);
            totalValue += convertedValue.getAmount();
        }

        return totalValue;
    }
}


/**
 *
 * @author benja
 */
public class PortfolioTest {
    @Test
    public void add_with_same_currency() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        // 5 USD + 10 USD = 15 USD
        Bank createBank = Bank.createBank(EUR, USD, 1.2);
        Portfolio portfolio = new Portfolio();
        
        portfolio.add(Money.create(5, USD));
        portfolio.add(Money.create(10, USD));
        
        double evaluate = portfolio.evaluate(USD, createBank);
        
        assertThat(evaluate).isEqualTo(15);
    }
    
    @Test
    public void add_with_EUR_to_USD_currency() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        // 5 USD + 10 EUR = 17 USD
        Bank createBank = Bank.createBank(EUR, USD, 1.2);
        Portfolio portfolio = new Portfolio();
        
        portfolio.add(Money.create(5, USD));
        portfolio.add(Money.create(10, EUR));
        
        double evaluate = portfolio.evaluate(USD, createBank);
        
        assertThat(evaluate).isEqualTo(17);
    }

    @Test
    public void add_with_USD_to_EUR_currency() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        // 5 USD + 10 EUR = 14.1 USD
        Bank createBank = Bank.createBank(USD, EUR, 0.8);
        Portfolio portfolio = new Portfolio();

        portfolio.add(Money.create(5, USD));
        portfolio.add(Money.create(10, EUR));

        double evaluate = portfolio.evaluate(EUR, createBank);

        assertThat(evaluate).isEqualTo(14);
    }

    @Test
    public void convert_currency_not_in_wallet() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        // 5 USD + 10 EUR = 35 KRW
        Bank createBank = Bank.createBank(EUR, KRW, 2);
        createBank.addExchangeRate(USD, KRW, 3);
        Portfolio portfolio = new Portfolio();

        portfolio.add(Money.create(5, USD));
        portfolio.add(Money.create(10, EUR));

        double evaluate = portfolio.evaluate(KRW, createBank);

        assertThat(evaluate).isEqualTo(35);
    }
}
