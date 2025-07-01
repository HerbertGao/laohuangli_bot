package com.herbertgao.telegram.bot.laohuangli.business.common.util;

import com.herbertgao.telegram.bot.laohuangli.business.common.constant.Command;
import com.herbertgao.telegram.bot.laohuangli.business.common.constant.Config;
import org.apache.commons.lang3.StringUtils;
import org.telegram.telegrambots.meta.api.objects.message.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: laohuangli_bot
 * @description:
 * @author: HerbertGao
 * @create: 2020/7/27
 **/
public class TelegramBotUtil {

    public static Boolean isMatchCommand(String text, String command) {
        if (StringUtils.isBlank(text) || StringUtils.isBlank(command)) {
            return false;
        }
        return text.matches("^" + command + ".*");
    }

    /**
     * 获取消息文字，删除命令、@信息
     *
     * @param message
     * @param command
     * @return
     */
    public static String getTextByMessage(Message message, String command) {
        String text = message.getText();
        if (StringUtils.isNotBlank(text)) {
            text = text.replaceFirst("@" + Config.botUsername, "");
            if (StringUtils.isNotBlank(command)) {
                text = text.replaceFirst(command, "").trim();
            }
            return text;
        } else {
            return null;
        }
    }

    /**
     * 删除给定字符1中出现的第一个给定字符2
     *
     * @param text
     * @param remove
     * @return
     */
    public static String removeFirst(String text, String remove) {
        return text.replaceFirst(remove, "").trim();
    }

    /**
     * 是用户对话
     *
     * @param message
     * @return
     */
    public static Boolean isUserChat(Message message) {
        return message.getChat().isUserChat();
    }

    /**
     * 获得用户完整姓名
     *
     * @param user
     * @return
     */
    public static String getUserFullName(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(firstName)) {
            sb.append(firstName).append(" ");
        }
        if (StringUtils.isNotBlank(lastName)) {
            sb.append(lastName);
        }
        return sb.toString();
    }
}
