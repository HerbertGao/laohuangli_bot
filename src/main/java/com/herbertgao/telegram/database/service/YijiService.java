package com.herbertgao.telegram.database.service;

import com.herbertgao.telegram.database.entity.Yiji;
import com.herbertgao.telegram.database.entity.YijiExample;
import com.herbertgao.telegram.database.mapper.YijiMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class YijiService {

    private final String[] catalogList = {"衣", "食", "住", "行"};

    @Resource
    private YijiMapper mapper;

    public List<Yiji> getDailyYijiRandom(int seed) {
        List<Yiji> result = new ArrayList<>();

        // 宜
        for (String s : catalogList) {
            YijiExample example = new YijiExample();
            example.createCriteria()
                    .andCatalogEqualTo(s)
                    .andYiIsNotNull();
            example.setOrderByClause(" rand('" + seed + "') limit 1 ");
            List<Yiji> yijiList = mapper.selectByExample(example);
            result.addAll(yijiList);
        }

        // 忌
        List<Integer> existIdList = new ArrayList<>();
        result.forEach(yiji -> existIdList.add(yiji.getId()));
        YijiExample example = new YijiExample();
        example.createCriteria()
                .andIdNotIn(existIdList)
                .andJiIsNotNull();
        example.setOrderByClause(" rand('" + seed + "') limit 2 ");
        List<Yiji> yijiList = mapper.selectByExample(example);
        result.addAll(yijiList);

        return result;
    }

    public List<Yiji> getYijiRandom(int seed, Integer count) {
        List<Yiji> yijiList = new ArrayList<>();
        int n = 0;
        while (n < count) {
            YijiExample example = new YijiExample();
            YijiExample.Criteria c = example.createCriteria();
            if (n % 2 == 0) {
                c.andYiIsNotNull();
            } else {
                c.andJiIsNotNull();
            }
            example.setOrderByClause(" rand('" + seed + n + "') limit " + 1 + " ");
            yijiList.addAll(mapper.selectByExample(example));
            n++;
        }
        return yijiList;
    }

}
