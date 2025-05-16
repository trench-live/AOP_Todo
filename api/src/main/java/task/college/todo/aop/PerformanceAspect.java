package task.college.todo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("@annotation(task.college.todo.aop.TrackTime)")
    public Object trackTime(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        logger.info("Метод {} выполнен за {} мс",
                pjp.getSignature().getName(),
                System.currentTimeMillis() - start);
        return result;
    }
}