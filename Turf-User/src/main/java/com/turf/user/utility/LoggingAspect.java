package com.turf.user.utility;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.turf.user.exception.TurfUserException;

@Component
@Aspect
public class LoggingAspect {
	
	private static final Logger logger = LogManager.getLogger(LoggingAspect.class);
	
	@AfterThrowing(pointcut = "execution(* com.turf.user.service.*Impl.*(..))",throwing = "exception")
	public void logServiceException(TurfUserException exception) {
		logger.error(" in loggingAspect " + exception.getMessage(),exception);
	}

}
