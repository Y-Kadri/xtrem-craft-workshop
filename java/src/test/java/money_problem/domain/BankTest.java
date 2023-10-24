package money_problem.domain;

import org.junit.jupiter.api.Test;

import static money_problem.domain.Currency.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BankTest {

    @Test
    void convertEurToUsdWithExchangeRate() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        // Arrange
        Bank createBank = Bank.createBank(EUR, USD, 1.2);
        // Act
        Money convert = createBank.convert(Money.create(10, EUR), USD);
        // Assert
        assertThat(convert).isEqualTo(Money.create(12, USD));
    }

    @Test
    void convertEurToUsdWithExchangeRateThrowError() throws MissingExchangeRateException, NegativeNumberException, InvalidNumberException {
        // Arrange
        Bank createBank = Bank.createBank(EUR, USD, 1.2);
        // Act
        Money convert = createBank.convert(Money.create(10, EUR), EUR);
        // Assert
        assertThat(convert).isEqualTo(Money.create(10, EUR));
    }

    @Test
    void convertThrowsExceptionOnMissingExchangeRate() {
        // Arrange
        Bank createBank = Bank.createBank(EUR, USD, 1.2);
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
        Bank createBank = Bank.createBank(EUR, USD, 1.2);
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