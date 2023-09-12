package money_problem.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyCalculatorTest {
    @Test
    void shouldAddInUsd() {
        assertThat(MoneyCalculator.add(5, 10))
                .isNotNull();
    }

    @Test
    void shouldMultiplyInEuros() {
        assertThat(MoneyCalculator.multiplicate(10, 2))
                .isEqualTo(20);
    }

    @Test
    void shouldDivideInKoreanWons() {
        assertThat(MoneyCalculator.divide(4002, 4))
                .isEqualTo(1000.5);
    }
}