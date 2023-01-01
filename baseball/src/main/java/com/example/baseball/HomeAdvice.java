package com.example.baseball;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HomeAdvice {
    static int pageView=0;
    public static int getPageView(){
        return pageView;
    }

    @After("execution(* com.example.pet.PetApplication.*(..))")
    public void after(){
        System.out.println("--- page view : "+(++pageView));
    }
}
