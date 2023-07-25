package com.herbertgao.telegram.bot.laohuangli.business.common.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    public static String botUsername;
    public static String token;


    @Value("${telegram.bot.username}")
    public void setBotUsername(String botUsername) {
        Config.botUsername = botUsername;
    }

    @Value("${telegram.bot.token}")
    public void setToken(String token) {
        Config.token = token;
    }
}
