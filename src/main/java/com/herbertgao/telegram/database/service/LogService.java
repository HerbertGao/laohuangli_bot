package com.herbertgao.telegram.database.service;

import com.herbertgao.telegram.database.entity.Log;
import com.herbertgao.telegram.database.entity.LogExample;
import com.herbertgao.telegram.database.mapper.LogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
public class LogService {

    @Resource
    private LogMapper mapper;

    public String getLogByDate(LocalDate date) {
        LogExample example = new LogExample();
        example.createCriteria()
                .andDateEqualTo(date);
        List<Log> logList = mapper.selectByExample(example);
        if (logList.size() > 0) {
            return logList.get(0).getContent();
        } else {
            return null;
        }
    }

    public void insertLog(LocalDate date, String content) {
        Log log = new Log();
        log.setDate(date);
        log.setContent(content);
        mapper.insert(log);
    }
}
