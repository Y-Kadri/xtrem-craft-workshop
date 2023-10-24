package money_problem.domain;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static money_problem.domain.Currency.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

    @Test
    void shouldAddInUsdReturnsNumber() {
        // Assert
        assertThat(MoneyCalculator.add(5, USD, 10)).isPositive();
        assertThat(MoneyCalculator.add(5, USD, 10)).isNotNull();
    }

    @Test
    void shouldAddMoneysWhenCurrenciesAreTheSame() throws InvalidNumberException, NegativeNumberException {
        // Act
        Money money = Money.create(5, Currency.USD);
        Money sum = money.add(Money.create(10, Currency.USD));

        // Assert
        assertThat(sum).isEqualTo(Money.create(15, Currency.USD));
        assertThat(money).isEqualTo(Money.create(5, Currency.USD));
    }

    @Test
    void shouldAddMoneysWhenCurrenciesAreDifferent() throws NegativeNumberException, InvalidNumberException {
        // Act
        Money money = Money.create(5, Currency.USD);

        // Assert
        ThrowableAssert.ThrowingCallable invalidSum = () -> {
            // Act
            money.add(Money.create(10, KRW));
        };
        assertThatThrownBy(invalidSum)
                .isInstanceOf(InvalidNumberException.class)
                .hasMessage("Can't add two different currencies");
    }

    @Test
    void shouldMultiplyMoneysWhenCurrenciesAreTheSame() throws InvalidNumberException, NegativeNumberException {
        // Act
        Money money = Money.create(5, Currency.USD);
        Money multiply = money.multiply(Money.create(10, Currency.USD));

        // Assert
        assertThat(multiply).isEqualTo(Money.create(50, Currency.USD));
        assertThat(money).isEqualTo(Money.create(5, Currency.USD));
    }

    @Test
    void shouldMultiplyMoneysWhenCurrenciesAreDifferent() throws NegativeNumberException, InvalidNumberException {
        // Act
        Money money = Money.create(5, Currency.USD);

        // Assert
        ThrowableAssert.ThrowingCallable invalidMultiply = () -> {
            // Act
            money.multiply(Money.create(10, KRW));
        };
        assertThatThrownBy(invalidMultiply)
                .isInstanceOf(InvalidNumberException.class)
                .hasMessage("Can't add two different currencies");
    }

    @Test
    void shouldDivideMoneysWhenCurrenciesAreTheSame() throws InvalidNumberException, NegativeNumberException {
        // Act
        Money money = Money.create(10, Currency.USD);
        Money divide = money.divide(Money.create(2, Currency.USD));

        // Assert
        assertThat(divide).isEqualTo(Money.create(5, Currency.USD));
        assertThat(money).isEqualTo(Money.create(10, Currency.USD));
    }

    @Test
    void shouldDivideMoneysWhenCurrenciesAreDifferent() throws NegativeNumberException, InvalidNumberException {
        // Act
        Money money = Money.create(10, Currency.USD);

        // Assert
        ThrowableAssert.ThrowingCallable invalidMultiply = () -> {
            // Act
            money.divide(Money.create(2, KRW));
        };
        assertThatThrownBy(invalidMultiply)
                .isInstanceOf(InvalidNumberException.class)
                .hasMessage("Can't add two different currencies");
    }

    @Test
    void shouldDivideMoneysWhenCurrenciesAreSameWithZeroDivide() {
        // Assert
        ThrowableAssert.ThrowingCallable divisionNegatif = () -> {
            // Act
            Money.create(-5, Currency.USD);
        };
        assertThatThrownBy(divisionNegatif)
                .isInstanceOf(NegativeNumberException.class);
    }

    @Test
    void shouldMoneysWithInifiteNumber() {
        // Assert
        ThrowableAssert.ThrowingCallable infiniteNumber = () -> {
            // Act
            Money.create(Double.POSITIVE_INFINITY, Currency.USD);
        };
        assertThatThrownBy(infiniteNumber)
                .isInstanceOf(InvalidNumberException.class);
    }
}