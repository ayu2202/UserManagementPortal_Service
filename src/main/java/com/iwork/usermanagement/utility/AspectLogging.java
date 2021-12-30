package com.iwork.usermanagement.utility;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectLogging {

    private static final Log LOGGER = LogFactory.getLog(AspectLogging.class);

    @AfterThrowing(pointcut = "execution(* com.iwork.usermanagement.service.impl.*Impl.*(..))", throwing = "exception")
    public void logServiceException(Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
    }

}
