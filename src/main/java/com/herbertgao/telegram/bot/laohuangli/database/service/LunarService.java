package com.herbertgao.telegram.bot.laohuangli.database.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.herbertgao.telegram.bot.laohuangli.database.entity.Lunar;
import com.herbertgao.telegram.bot.laohuangli.database.mapper.LunarMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LunarService extends ServiceImpl<LunarMapper, Lunar> {

    public Lunar getLunarByDate(LocalDate date) {
        QueryWrapper<Lunar> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .eq(Lunar::getGregoriandatetime, date);
        return this.getOne(wrapper);
    }

}
