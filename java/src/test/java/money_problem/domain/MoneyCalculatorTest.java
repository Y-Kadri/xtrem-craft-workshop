package money_problem.domain;

import org.junit.jupiter.api.Test;

import static money_problem.domain.Currency.*;
import static org.assertj.core.api.Assertions.assertThat;

class MoneyCalculatorTest {
    @Test
    void shouldAddUSDNotNull() {
        // Act
        double add = MoneyCalculator.add(5, USD, 10);
        // Assert
        assertThat(add).isEqualTo(15);
    }

    @Test
    void shouldMultiplyInEuros() {
        // Act
        double multiply = MoneyCalculator.multiply(10, EUR, 2);
        // Assert
        assertThat(multiply).isEqualTo(20);
    }

    @Test
    void shouldDivideInKoreanWons() {
        // Act
        double divide = MoneyCalculator.divide(4002, KRW, 4);
        // Assert
        assertThat(divide).isEqualTo(1000.5);
    }
}