package by.savich.project.springboot.service;

import by.savich.project.springboot.exception.DividedByZeroException;
import by.savich.project.springboot.exception.WrongParameterException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MathServiceTest {

    @Test
    void shouldReturnTheSumOfTwoNumbersWhenCallSumMethod() {
        MathService mathService = new MathService();
        Integer result = mathService.sum(3, 7);

        assertThat(result).isEqualTo(10);

    }

    @Test
    void shouldReturnTheDifferenceOfTwoNumbersWhenCallDifferenceMethod() {
        MathService mathService = new MathService();
        Integer result = mathService.difference(8, 4);

        assertThat(result).isEqualTo(4);
    }

    @Test
    void shouldThrowWrongParameterExceptionIfFirstArgumentIsNull() {
        MathService mathService = new MathService();
        WrongParameterException exception = assertThrows(WrongParameterException.class, () -> mathService.sum(null, 3));

        assertThat(exception.getMessage()).contains("First parameter cannot be null");
    }

    @Test
    void shouldThrowWrongParameterExceptionIfSecondArgumentIsNull() {
        MathService mathService = new MathService();
        WrongParameterException exception = assertThrows(WrongParameterException.class, () -> mathService.sum(5, null));

        assertThat(exception.getMessage()).contains("Second parameter cannot be null");
    }
    
    @Test
    void shouldReturnTheMultiplicationOfTwoNumbersWhenCallMultiplicationMethod() {
        MathService mathService = new MathService();
        Integer result = mathService.multiplication(3, 4);

        assertThat(result).isEqualTo(12);
    }

    @Test
    void shouldReturnTheDivisionOfTwoNumbersWhenCallDivisionMethod() {
        MathService mathService = new MathService();
        double result = mathService.division(10, 5);

        assertThat(result).isEqualTo(2);
    }

    @Test
    void shouldThrowWrongParameterExceptionIfSecondArgumentIsZeroWhenUserDivides() {
        MathService mathService = new MathService();
        DividedByZeroException dividedByZeroException = assertThrows(DividedByZeroException.class, () -> mathService.division(8, 0));

        assertThat(dividedByZeroException.getMessage()).isEqualTo("Can't divide by zero, enter another number");
    }

}