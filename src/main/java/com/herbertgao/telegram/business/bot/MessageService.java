package com.herbertgao.telegram.business.bot;

import com.herbertgao.telegram.business.service.LaohuangliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: laohuangli_bot
 * @description: MessageService
 * @author: HerbertGao
 * @create: 2020/11/28
 **/
@Service
public class MessageService {

    @Autowired
    private LaohuangliService laohuangliService;

    public String getLaohuangli() {
        return laohuangliService.getLaohuangli();
    }
}
