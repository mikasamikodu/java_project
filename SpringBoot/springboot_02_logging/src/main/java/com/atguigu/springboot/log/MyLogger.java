package com.atguigu.springboot.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyLogger {

    private Logger logger= LoggerFactory.getLogger(MyLogger.class);

    public void contextLoads(){
        logger.trace("trace...");
        logger.debug("debugger...");
        logger.info("info...");
        logger.warn("warn...");
        logger.error("error...");
    }
}
