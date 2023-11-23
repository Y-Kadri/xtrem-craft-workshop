package money_problem.domain;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static money_problem.domain.Currency.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BankBuilder {
    private Currency pivotCurrency;
    private HashMap<Currency, Double> exchangeRate = new HashMap<>();

    static BankBuilder aBank() {
        return new BankBuilder();
    }

    static BankBuilder aEuropeanBank() {
        return BankBuilder.aBank().witPivotCurrency(USD);
    }

    BankBuilder witPivotCurrency(Currency currency) {
        this.pivotCurrency = currency;
        return this;
    }

    BankBuilder withExchangeRate(Currency currency, double rate) {
        this.exchangeRate.put(currency, rate);
        return this;
    }

    Bank build() {
        Map.Entry<Currency, Double> firstEntry = exchangeRate.entrySet().iterator().next();
        Currency firstCurrency = firstEntry.getKey();
        Double firstRate = firstEntry.getValue();

        Bank bank = Bank.createBank(this.pivotCurrency, firstCurrency, firstRate);

        for (Map.Entry<Currency, Double> m : this.exchangeRate.entrySet()) {
            bank.addExchangeRate(this.pivotCurrency, m.getKey(), m.getValue());
            bank.addExchangeRate(m.getKey(), this.pivotCurrency, 1 / m.getValue());
        }
        return bank;
    }
}

class BankTest {

    @Test
    void convertEurToUsdWithExchangeRate() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        // Arrange
        Bank createBank = BankBuilder.aBank()
                .witPivotCurrency(EUR)
                .withExchangeRate(USD, 1.2)
                .build();

        // Act
        Money convert = createBank.convert(Money.create(10, EUR), USD);
        // Assert
        assertThat(convert).isEqualTo(Money.create(12, USD));
    }

    @Test
    void convertEurToUsdWithExchangeRateThrowError() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        // Arrange
        Bank createBank = BankBuilder.aBank()
                .witPivotCurrency(EUR)
                .withExchangeRate(USD, 1.2)
                .build();
        // Act
        Money convert = createBank.convert(Money.create(10, EUR), EUR);
        // Assert
        assertThat(convert).isEqualTo(Money.create(10, EUR));
    }

    @Test
    void convertThrowsExceptionOnMissingExchangeRate() {
        // Arrange
        Bank createBank = BankBuilder.aBank()
                .witPivotCurrency(EUR)
                .withExchangeRate(USD, 1.2)
                .build();
        // Assert
        assertThatThrownBy(() -> {
            // Act
            createBank.convert(Money.create(10, EUR), KRW);
        })
                .isInstanceOf(MissingExchangeRateException.class)
                .hasMessage("EUR->KRW");
    }

    @Test
    void convertWithDifferentExchangeRatesReturnsDifferentFloats() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        // Arrange
        Bank createBank = BankBuilder.aBank()
                .witPivotCurrency(EUR)
                .withExchangeRate(USD, 1.2)
                .build();
        // Act
        Money convert = createBank.convert(Money.create(10, EUR), USD);
        // Assert
        assertThat(convert).isEqualTo(Money.create(12, USD));
        // Arrange
        createBank.addExchangeRate(EUR, USD, 1.3);
        // Act
        Money convert2 = createBank.convert(Money.create(10, EUR), USD);
        // Assert
        assertThat(convert2).isEqualTo(Money.create(13, USD));
    }
}