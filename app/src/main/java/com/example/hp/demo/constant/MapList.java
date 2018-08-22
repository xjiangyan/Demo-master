package com.example.hp.demo.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MapList {

    public static final Map<String, String> KK_ZA_MAP = new HashMap<>();

    static {
        KK_ZA_MAP.put("绑架", "101");
        KK_ZA_MAP.put("本地在逃", "102");
        KK_ZA_MAP.put("本地在逃（家属）", "103");
        KK_ZA_MAP.put("盗窃", "104");
        KK_ZA_MAP.put("盗窃（家属）", "105");
        KK_ZA_MAP.put("酒驾", "106");
        KK_ZA_MAP.put("酒驾（家属）", "107");
        KK_ZA_MAP.put("开设赌场", "108");
        KK_ZA_MAP.put("开设赌场（家属）", "109");
        KK_ZA_MAP.put("流动登记已过期", "110");
        KK_ZA_MAP.put("抢劫", "111");
        KK_ZA_MAP.put("抢劫（家属）", "112");
        KK_ZA_MAP.put("全国盗抢车辆", "113");
        KK_ZA_MAP.put("全国在逃", "114");
        KK_ZA_MAP.put("全国在逃（家属）", "115");
        KK_ZA_MAP.put("吸贩毒", "116");
        KK_ZA_MAP.put("吸贩毒（家属）", "117");
        KK_ZA_MAP.put("携带管制刀具", "118");
        KK_ZA_MAP.put("携带管制刀具（家属）", "119");
        KK_ZA_MAP.put("寻衅滋事", "120");
        KK_ZA_MAP.put("寻衅滋事（家属）", "121");
        KK_ZA_MAP.put("曾被用作作案工具", "122");
        KK_ZA_MAP.put("组织参与黑社会性质组织", "123");
    }

    public static final Map<String, String> KK_JJ_MAP = new HashMap<>();

    static {

        KK_JJ_MAP.put("违章未处理4次及以上", "201");
        KK_JJ_MAP.put("失格", "202");
        KK_JJ_MAP.put("失格（家属）", "203");
    }

    public static final Map<String, String> KK_QT_MAP = new HashMap<>();

    static {
        KK_QT_MAP.put("其他违法", "303");
        KK_QT_MAP.put("其他违法（家属）", "304");
    }

}
