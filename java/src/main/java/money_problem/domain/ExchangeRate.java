package money_problem.domain;

import java.util.Objects;

public class ExchangeRate {


    String stringForExchange;
    Double rate;


    public ExchangeRate(String stringForExchange, Double rate) {
        this.rate = rate;
        this.stringForExchange = stringForExchange;
    }

    public String getStringForExchange() {
        return stringForExchange;
    }

    public void setStringForExchange(String stringForExchange) {
        this.stringForExchange = stringForExchange;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRate that = (ExchangeRate) o;
        return Objects.equals(stringForExchange, that.stringForExchange) && Objects.equals(rate, that.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringForExchange, rate);
    }
}
