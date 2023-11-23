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
    private ArrayList<Money> wallet2 = new ArrayList<>();

    Portfolio() {
    }

    void add(Money money) {
        if (wallet.containsKey(money.getCurrency())){
            double actualValue = wallet.get(money.getCurrency());
            wallet.put(money.getCurrency(), actualValue + money.getAmount());
        } else {
            wallet.put(money.getCurrency(), money.getAmount());
        }
        wallet2.add(money);
    }

    Money evaluate(Currency currency, Bank bank) throws NegativeNumberException, InvalidNumberException, MissingExchangeRateException {
        double totalValue = 0;
        Money totalMoneyValue = Money.create(totalValue, currency);
//        //Money reduce = wallet2.stream().reduce(Money.create(0, currency), (money, money2) -> money.add(bank.convert(money2, currency)));
//        return reduce;
        for (Map.Entry<Currency, Double> entry : wallet.entrySet()) {
            Currency holdingCurrency = entry.getKey();
            double amount = entry.getValue();
            Money convertedValue = bank.convert(Money.create(amount, holdingCurrency), currency);
            // Faire un reduce qui retourne directement le résultat : reduce(acc: X, current: X) -> X
            totalMoneyValue = totalMoneyValue.add(convertedValue);
        }

        return totalMoneyValue;
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
        Bank createBank = BankBuilder.aBank()
                .witPivotCurrency(EUR)
                .withExchangeRate(USD, 1.2)
                .build();
        Portfolio portfolio = new Portfolio();
        
        portfolio.add(Money.create(5, USD));
        portfolio.add(Money.create(10, USD));
        
        Money evaluate = portfolio.evaluate(USD, createBank);
        
        assertThat(evaluate).isEqualTo(Money.create(15, USD));
    }
    
    @Test
    public void add_with_EUR_to_USD_currency() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        // 5 USD + 10 EUR = 17 USD
        Bank createBank = BankBuilder.aBank()
                .witPivotCurrency(EUR)
                .withExchangeRate(USD, 1.2)
                .build();
        Portfolio portfolio = new Portfolio();
        
        portfolio.add(Money.create(5, USD));
        portfolio.add(Money.create(10, EUR));
        
        Money evaluate = portfolio.evaluate(USD, createBank);
        
        assertThat(evaluate).isEqualTo(Money.create(17, USD));
    }

    @Test
    public void add_with_USD_to_EUR_currency() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        // 5 USD + 10 EUR = 14.1 USD
        Bank createBank = BankBuilder.aBank()
                .witPivotCurrency(USD)
                .withExchangeRate(EUR, 0.8)
                .build();
        Portfolio portfolio = new Portfolio();

        portfolio.add(Money.create(5, USD));
        portfolio.add(Money.create(10, EUR));

        Money evaluate = portfolio.evaluate(EUR, createBank);

        assertThat(evaluate).isEqualTo(Money.create(14, EUR));
    }

    @Test
    public void convert_currency_not_in_wallet() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        // 5 USD + 10 EUR = 35 KRW
        Bank createBank = BankBuilder.aBank()
                .witPivotCurrency(EUR)
                .withExchangeRate(KRW, 2)
                .build();
        createBank.addExchangeRate(USD, KRW, 3);
        Portfolio portfolio = new Portfolio();

        portfolio.add(Money.create(5, USD));
        portfolio.add(Money.create(10, EUR));

        Money evaluate = portfolio.evaluate(KRW, createBank);

        assertThat(evaluate).isEqualTo(Money.create(35, KRW));
    }
}
