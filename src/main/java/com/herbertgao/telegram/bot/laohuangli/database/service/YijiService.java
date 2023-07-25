package com.herbertgao.telegram.bot.laohuangli.database.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.herbertgao.telegram.bot.laohuangli.database.entity.Yiji;
import com.herbertgao.telegram.bot.laohuangli.database.mapper.YijiMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class YijiService extends ServiceImpl<YijiMapper, Yiji> {

    private static final String[] CATALOG_LIST = {"衣", "食", "住", "行"};

    public List<Yiji> getDailyYijiRandom(int seed) {
        List<Yiji> result = new ArrayList<>();

        // 宜
        for (String s : CATALOG_LIST) {
            QueryWrapper<Yiji> wrapper = new QueryWrapper<>();
            wrapper.lambda()
                    .eq(Yiji::getCatalog, s)
                    .isNotNull(Yiji::getYi);
            wrapper.orderByAsc(" rand('" + seed + "') ")
                    .last("limit 1");
            List<Yiji> yijiList = this.list(wrapper);
            result.addAll(yijiList);
        }

        // 忌
        List<Integer> existIdList = new ArrayList<>();
        result.forEach(yiji -> existIdList.add(yiji.getId()));
        QueryWrapper<Yiji> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .notIn(Yiji::getId, existIdList)
                .isNotNull(Yiji::getJi);
        wrapper.orderByAsc(" rand('" + seed + "') ")
                .last("limit 2");
        List<Yiji> yijiList = this.list(wrapper);
        result.addAll(yijiList);

        return result;
    }

    public List<Yiji> getYijiRandom(int seed, Integer count) {
        List<Yiji> yijiList = new ArrayList<>();
        int n = 0;
        while (n < count) {
            QueryWrapper<Yiji> wrapper = new QueryWrapper<>();
            if (n % 2 == 0) {
                wrapper.lambda().isNotNull(Yiji::getYi);
            } else {
                wrapper.lambda().isNotNull(Yiji::getJi);
            }
            wrapper.orderByAsc(" rand('" + seed + n + "') ")
                    .last("limit 1");
            yijiList.addAll(this.list(wrapper));
            n++;
        }
        return yijiList;
    }


}
