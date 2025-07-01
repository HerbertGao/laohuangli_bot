package com.herbertgao.telegram.bot.laohuangli.database.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.herbertgao.telegram.bot.laohuangli.database.entity.Yiji;
import com.herbertgao.telegram.bot.laohuangli.database.mapper.YijiMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class YijiService extends ServiceImpl<YijiMapper, Yiji> {

    private static final String[] CATALOG_LIST = {"衣", "食", "住", "行"};

    public List<Yiji> getDailyYijiRandom(int seed) {
        List<Yiji> result = new ArrayList<>();
        Random random = new Random(seed);

        // 宜 - 按分类选择
        for (String catalog : CATALOG_LIST) {
            QueryWrapper<Yiji> wrapper = new QueryWrapper<>();
            wrapper.lambda()
                    .eq(Yiji::getCatalog, catalog)
                    .isNotNull(Yiji::getYi);
            List<Yiji> yijiList = this.list(wrapper);
            
            if (!yijiList.isEmpty()) {
                // 在应用层随机选择
                int randomIndex = random.nextInt(yijiList.size());
                result.add(yijiList.get(randomIndex));
            }
        }

        // 忌 - 排除已选择的宜项
        List<Integer> existIdList = new ArrayList<>();
        result.forEach(yiji -> existIdList.add(yiji.getId()));
        
        QueryWrapper<Yiji> wrapper = new QueryWrapper<>();
        wrapper.lambda()
                .notIn(Yiji::getId, existIdList)
                .isNotNull(Yiji::getJi);
        List<Yiji> jiList = this.list(wrapper);
        
        // 随机选择2个忌项
        for (int i = 0; i < 2 && !jiList.isEmpty(); i++) {
            int randomIndex = random.nextInt(jiList.size());
            result.add(jiList.get(randomIndex));
            jiList.remove(randomIndex); // 避免重复选择
        }

        return result;
    }

    public List<Yiji> getYijiRandom(int seed, Integer count) {
        List<Yiji> result = new ArrayList<>();
        Random random = new Random(seed);

        // 分别获取宜和忌的数据
        QueryWrapper<Yiji> yiWrapper = new QueryWrapper<>();
        yiWrapper.lambda().isNotNull(Yiji::getYi);
        List<Yiji> yiList = this.list(yiWrapper);

        QueryWrapper<Yiji> jiWrapper = new QueryWrapper<>();
        jiWrapper.lambda().isNotNull(Yiji::getJi);
        List<Yiji> jiList = this.list(jiWrapper);

        // 在应用层随机选择
        for (int i = 0; i < count; i++) {
            if (i % 2 == 0 && !yiList.isEmpty()) {
                // 选择宜项
                int randomIndex = random.nextInt(yiList.size());
                result.add(yiList.get(randomIndex));
                yiList.remove(randomIndex); // 避免重复选择
            } else if (!jiList.isEmpty()) {
                // 选择忌项
                int randomIndex = random.nextInt(jiList.size());
                result.add(jiList.get(randomIndex));
                jiList.remove(randomIndex); // 避免重复选择
            }
        }

        return result;
    }
}
