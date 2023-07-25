package com.herbertgao.telegram.bot.laohuangli.business.common.util;

import cn.hutool.core.lang.Snowflake;

/**
 * ID Util
 *
 * @author HerbertGao
 * @date 2021-12-14
 */
public class IdUtil {

    static Snowflake snowflake = cn.hutool.core.util.IdUtil.getSnowflake(1, 1);

    public static Long getId() {
        return snowflake.nextId();
    }

}
