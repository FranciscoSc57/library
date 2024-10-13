package fsr.exame.library.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@Aspect
public class TiempoEjecucionAspect {
    
    @Around("@annotation(fsr.exame.library.service.LogAspect)")  
    public Object MedirTiempoEjecucion(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{  
        Long start = System.currentTimeMillis();
        Object resultado = proceedingJoinPoint.proceed();
        Long tiempoEjecucion = System.currentTimeMillis() - start;
        log.info("***********Tiempo de ejecucion del servicio***********: " + tiempoEjecucion);
        return resultado;
    }

}
