package com.kb1.containerMarket.aop;

import com.kb1.containerMarket.exception.CustomValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Aspect
@Component
public class ValidationAop {

    @Pointcut("execution(* com.kb1.containerMarket..*Api.*(..))")
    private void executionPointCut() {}

    @Around("executionPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        BeanPropertyBindingResult bindingResult = searchBindingResult(joinPoint);

        searchError(bindingResult);
        //joinPoint 원래 내가 실행해야하는 메소드
        Object result = null;
        result = joinPoint.proceed();

        return result;
    }

    private static void searchError(BeanPropertyBindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }

            throw new CustomValidationException("Validation Error", errorMap);
        }
    }

    private static BeanPropertyBindingResult searchBindingResult(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs(); //매개변수는 매번 달라질 수 있음

        BeanPropertyBindingResult bindingResult = null;

        for (Object arg : args) {
            if(arg.getClass() == BeanPropertyBindingResult.class) {
                bindingResult = (BeanPropertyBindingResult) arg;
                break;
            }
        }
        return bindingResult;
    }


}