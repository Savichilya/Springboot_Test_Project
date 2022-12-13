package by.savich.project.springboot.service;

import by.savich.project.springboot.exception.DividedByZeroException;
import by.savich.project.springboot.exception.WrongParameterException;
import org.springframework.stereotype.Service;

@Service
public class MathService {
    public Integer sum(Integer a, Integer b){
        if (a==null){
            throw new WrongParameterException("First parameter cannot be null");
        }
        if (b==null){
            throw new WrongParameterException("Second parameter cannot be null");
        }
        return a+b;
    }

    public Integer difference(Integer a, Integer b){
        return a-b;
    }

    public Integer  multiplication(Integer a, Integer b){
        return a*b;
    }

    public double  division(Integer a, Integer b){
        if (b==0){
            throw new DividedByZeroException("Can't divide by zero, enter another number");
        }
        return a/b;
    }

}
