/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package money_problem.domain;

import java.util.ArrayList;
import static money_problem.domain.Currency.EUR;
import static money_problem.domain.Currency.USD;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author benja
 */
class Portfolio {

    ArrayList<Currency> tab = new ArrayList<>();
    
    
    
    void add(int i, Currency currency) {
        tab.add(currency);
    }

    float evaluate(Currency currency, Bank b) {
        
        return 15;
    }
}


/**
 *
 * @author benja
 */
public class PortfolioTest {
    @Test
    public void addWithSameCurrency(){
        // 5 USD + 10 USD = 15 USD
        Bank createBank = Bank.createBank(EUR, USD, 1.2);
        Portfolio portfolio = new Portfolio();
        
        portfolio.add(5, USD);
        portfolio.add(10, USD);
        
        float evaluate = portfolio.evaluate(USD, createBank);
        
        assertThat(evaluate).isEqualTo(15);
    }
    
    @Test
    public void test1(){
        // 5 USD + 10 EUR = 17 USD
        Bank createBank = Bank.createBank(EUR, USD, 1.2);
        Portfolio portfolio = new Portfolio();
        
        portfolio.add(5, USD);
        portfolio.add(10, EUR);
        
        float evaluate = portfolio.evaluate(USD, createBank);
        
        assertThat(evaluate).isEqualTo(17);
    }
}
