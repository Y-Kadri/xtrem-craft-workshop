package money_problem.domain;

import org.junit.jupiter.api.Test;

import static money_problem.domain.Currency.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BankTest {

    @Test
    void convertEurToUsdWithExchangeRate() throws MissingExchangeRateException {
        // Arrange
        Bank createBank = Bank.createBank(EUR, USD, 1.2);
        // Act
        double convert = createBank.convert(10, EUR, USD);
        // Assert
        assertThat(convert).isEqualTo(12);
    }

    @Test
    void convertEurToUsdWithExchangeRateThrowError() throws MissingExchangeRateException {
        // Arrange
        Bank createBank = Bank.createBank(EUR, USD, 1.2);
        // Act
        double convert = createBank.convert(10, EUR, EUR);
        // Assert
        assertThat(convert).isEqualTo(10);
    }

    @Test
    void convertThrowsExceptionOnMissingExchangeRate() {
        // Arrange
        Bank createBank = Bank.createBank(EUR, USD, 1.2);
        // Assert
        assertThatThrownBy(() -> {
            // Act
            createBank.convert(10, EUR, KRW);
        })
                .isInstanceOf(MissingExchangeRateException.class)
                .hasMessage("EUR->KRW");
    }

    @Test
    void convertWithDifferentExchangeRatesReturnsDifferentFloats() throws MissingExchangeRateException {
        // Arrange
        Bank createBank = Bank.createBank(EUR, USD, 1.2);
        // Act
        double convert = createBank.convert(10, EUR, USD);
        // Assert
        assertThat(convert).isEqualTo(12);
        // Arrange
        createBank.addExchangeRate(EUR, USD, 1.3);
        // Act
        double convert2 = createBank.convert(10, EUR, USD);
        // Assert
        assertThat(convert2).isEqualTo(13);
    }
}