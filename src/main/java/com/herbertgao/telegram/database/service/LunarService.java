package com.herbertgao.telegram.database.service;

import com.herbertgao.telegram.database.entity.Lunar;
import com.herbertgao.telegram.database.entity.LunarExample;
import com.herbertgao.telegram.database.mapper.LunarMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
public class LunarService {

    @Resource
    private LunarMapper mapper;

    public Lunar getLunarByDate(LocalDate date) {
        LunarExample example = new LunarExample();
        example.createCriteria()
                .andGregoriandatetimeEqualTo(date);
        List<Lunar> lunarList = mapper.selectByExample(example);
        if (lunarList.size() > 0) {
            return lunarList.get(0);
        } else {
            return null;
        }
    }
}
