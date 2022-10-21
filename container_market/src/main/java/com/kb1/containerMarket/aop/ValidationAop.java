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

    @Pointcut("@annotation(com.kb1.containerMarket.aop.annotation.ValidAspect)")
    private void annotationPointCut() {}

    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        BeanPropertyBindingResult bindingResult = searchBindingResult(joinPoint);

        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = errorBinding(bindingResult);
            throw new CustomValidationException("VALIDATION_ERR", errorMap);
        }
        return joinPoint.proceed();
    }

    private static Map<String, String> errorBinding(BeanPropertyBindingResult bindingResult) {
        Map<String, String> errorMap = new HashMap<String, String>();

        bindingResult.getFieldErrors().forEach(error -> {
            errorMap.put("errMsg", error.getDefaultMessage());
        });
        return errorMap;
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