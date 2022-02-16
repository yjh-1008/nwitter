package Spring.Spring.app;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
public class TimeTraceAop {
    @Around("execution(* Spring.Spring..*(..))")
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start=System.currentTimeMillis();
        System.out.println("START : "+joinPoint.toString());
        try{
            Object result=joinPoint.proceed();
            return result;
        }finally{
            long finish=System.currentTimeMillis();
            long timeMs=finish-start;
            System.out.println("END : "+joinPoint.toString()+" "+timeMs+"ms");
        }

    }
}
