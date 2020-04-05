package com.joe.midpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DataController {
    private Map<String, Object> currentState = new HashMap<>();
    private final String SUCCESS_MSG = "发送成功";
    private final String FAIL_MSG = "发送失败";
    private Logger logger = LoggerFactory.getLogger(MidpointApplication.class);

    @GetMapping("/data")
    public Map<String, Object> sendUpdate() {
        logger.info("get request");
        return currentState;
    }

    @PostMapping("/data")
    public String receiveUpdate(@RequestBody Map<String, Object> data) {
        currentState = data;
        logger.info("post request: " + data.toString());
        return SUCCESS_MSG;
    }
}
