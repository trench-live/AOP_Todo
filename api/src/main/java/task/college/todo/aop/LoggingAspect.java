package task.college.todo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(* task.college.todo.services.*.*(..))")
    public void logMethodCall(JoinPoint jp) {
        logger.info("Вызов метода: {} с аргументами: {}",
                jp.getSignature().getName(),
                jp.getArgs());
    }

    @AfterThrowing(
            pointcut = "execution(* task.college.todo.services.*.*(..))",
            throwing = "ex"
    )
    public void logException(JoinPoint jp, Exception ex) {
        logger.error("Ошибка в методе {}: {}",
                jp.getSignature().getName(),
                ex.getMessage());
    }
}