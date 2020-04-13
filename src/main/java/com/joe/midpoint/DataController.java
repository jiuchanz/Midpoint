package com.joe.midpoint;

import com.joe.midpoint.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class DataController {
    private Map<Integer, Update> dataUpdates = new HashMap<>();
    private Map<Integer, Update> commandUpdates = new HashMap<>();
    private Update currentState = new Update(0,new HashMap<>());
    private final String SUCCESS_MSG = "发送成功";
    private final String FAIL_MSG = "发送失败";
    private Logger logger = LoggerFactory.getLogger(MidpointApplication.class);
    private final Long expireTime = (long) (10 * 1000);

    @GetMapping("/data")
    public Update sendData(@RequestParam @NotNull Integer deviceId) {
        clearOutdatedEntries();
        logger.info("get data, device id:", deviceId);
        if (!dataUpdates.containsKey(deviceId)) {
            throw new EntryNotFoundException(deviceId);
        }
        return dataUpdates.get(deviceId);
    }

    @PostMapping("/data")
    public String receiveData(@RequestBody @Valid Update data) {
        dataUpdates.put(data.getDeviceId(), data);
        logger.info("post data, device id: " + data.getDeviceId());
        return SUCCESS_MSG;
    }

    @GetMapping("/command")
    public Update sendCommand(@RequestParam @NotNull Integer deviceId) {
        clearOutdatedEntries();
        logger.info("get command, device id:", deviceId);
        if (!commandUpdates.containsKey(deviceId)) {
            throw new EntryNotFoundException(deviceId);
        }
        return commandUpdates.get(deviceId);
    }

    @PostMapping("/command")
    public String receiveCommand(@RequestBody @Valid Update command) {
        commandUpdates.put(command.getDeviceId(), command);
        logger.info("post command, device id: " + command.getDeviceId());
        return SUCCESS_MSG;
    }

    @GetMapping("/all")
    public Set<Integer> getActiveDeviceList() {
        clearOutdatedEntries();
        return dataUpdates.keySet();
    }

    private void clearOutdatedEntries() {
        Map<Integer, Update> newDataUpdates = dataUpdates.entrySet().stream().filter(entry -> entry.getValue().getCreationDate().getTime() + expireTime > new Date().getTime() )
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
        if (dataUpdates.size() != newDataUpdates.size()) {
            logger.info("removed" + String.valueOf(dataUpdates.size() - newDataUpdates.size()) + " entries");
        }
        dataUpdates = newDataUpdates;
        Map<Integer, Update> newCommandUpdates = commandUpdates.entrySet().stream().filter(entry -> entry.getValue().getCreationDate().getTime() + expireTime > new Date().getTime() )
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));
        if (commandUpdates.size() != newCommandUpdates.size()) {
            logger.info("removed" + String.valueOf(commandUpdates.size() - newCommandUpdates.size()) + " entries");
        }
        commandUpdates = newCommandUpdates;
    }
}
