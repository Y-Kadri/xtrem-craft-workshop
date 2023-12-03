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

    @Test
    void convertEurToUsdWithSameCurrency() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
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
    void convertWithSameExchangeRate() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
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
    void convertWithDifferentExchangeRatesReturnsDifferentFloatsSecondRate() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
//        Exemple:
//
//        - Banque : devise EUR
//        - Taux de change : KRW -> 3 => 10 -> 30KRW
//                -> Modifie Taux de change -> 4000€
//                * 10€ -> 40 000 KRW

        // Arrange
        Bank createBank = BankBuilder.aBank()
                .witPivotCurrency(EUR)
                .withExchangeRate(KRW, 3.0)  // Initial exchange rate KRW to EUR
                .build();

        // Modifie Taux de change KRW -> EUR -> 4000€
        createBank.addExchangeRate(EUR, KRW, 4000.0);

        // Act
        Money convert = createBank.convert(Money.create(10, EUR), KRW);

        // Assert
        assertThat(convert).isEqualTo(Money.create(40000, KRW));
    }

    @Test
    void createBankWithoutPivotCurrencyThrowsError() {
        // -Pas de devise
        //-> créer une banque
        //* Erreur: devise Pivot obligatoire

        // Arrange & Act & Assert
        assertThatThrownBy(() -> {
            Bank.createBank(null, USD, 1.0);  // Tente de créer une banque sans devise pivot
        })
                .isInstanceOf(MissingPivotCurrencyException.class)
                .hasMessage("Pivot currency is required");
    }

    @Test
    void convertEurToUsdWithRoundedUpExchangeRate() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        //- Banque : EUR (devise pivot)
        //- TC : USD -> 1.2 -> 1.08
        //                  -> 1.32
        //-> convert 10€ -> USD
        //* 12 USD

        // Arrange
        Bank createBank = BankBuilder.aBank()
                .witPivotCurrency(EUR)
                .withExchangeRate(USD, 1.2)
                .build();

        // Act
        Money convert = createBank.convert(Money.create(10, EUR), USD);

        // Assert
        assertThat(convert.getAmount()).isEqualTo(12.0);
        assertThat(convert.getCurrency()).isEqualTo(USD);
    }

    @Test
    void convertEurToUsdWithRoundedBetweenExchangeRate() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        //Rule : Round Tapping
        //- Banque : EUR
        //- TC : USD -> 1.2
        //-> couvert: 10€ -> USD -> €
        //* 9€ <= resultat <= 11€

        // Arrange
        Bank createBank = BankBuilder.aBank()
                .witPivotCurrency(EUR)
                .withExchangeRate(USD, 1.2)
                .build();

        // Act
        Money convert = createBank.convert(Money.create(10, EUR), USD);
        Money convert2 = createBank.convert(convert, EUR);

        // Assert
        assertThat(convert2.getAmount()).isBetween(9.0, 11.0);
    }

    @Test
    void convertSameCurrencyWithOneExchangeRate() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        //Banque : EUR
        //                -> 10€ -> 10€ -> 10€

        // Arrange
        Bank createBank = BankBuilder.aBank()
                .witPivotCurrency(EUR)
                .withExchangeRate(EUR, 1)
                .build();

        // Act
        Money convert = createBank.convert(Money.create(10, EUR), EUR);

        // Assert
        assertThat(convert.getAmount()).isEqualTo(10.0);

        // Act
        Money convert2 = createBank.convert(convert, EUR);

        // Assert
        assertThat(convert2.getAmount()).isEqualTo(10.0);
    }

    @Test
    void definePivotCurrencyChangesExistingPivotCurrency() throws NegativeNumberException, InvalidNumberException, MissingExchangeRateException {
        //- Banque : USD (devise pivot)
        //- TC : USD -> 1.6
        //
        //-> convert 10 USD -> 16 EUR
        //* 16 EUR

        // Arrange
        Bank createBank = BankBuilder.aBank()
                .witPivotCurrency(USD)
                .withExchangeRate(EUR, 1.6)
                .build();

        // Act
        Money convert = createBank.convert(Money.create(10, USD), EUR);

        // Assert
        assertThat(convert.getAmount()).isEqualTo(16.0);
    }
}