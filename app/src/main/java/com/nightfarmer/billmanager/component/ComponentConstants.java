package com.nightfarmer.billmanager.component;

import com.nightfarmer.billmanager.param.ParamSettingListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangfan on 2016/11/24 0024.
 */

public class ComponentConstants {

    public static final List<Component> compList = new ArrayList<>();

    public static class IdValue {
        public String id;
        public String value;

        public IdValue(String id, String value) {
            this.id = id;
            this.value = value;
        }
    }

    static {
        Component component = new Component();
        component.type = 0;
        component.name = "文本输入";
        initBaseParam(component, "文本输入", "请输入", "");
        component.define.put("singleLine", true);
        compList.add(component);

        component = new Component();
        component.type = 1;
        component.name = "日期时间";
        initBaseParam(component, "日期时间", "请选择", "");
        component.define.put("type", "ALL");
        component.define.put("format", "yyyy-MM-dd HH:mm:ss");
        compList.add(component);

        component = new Component();
        component.type = 2;
        component.name = "单项选择";
        initBaseParam(component, "单项选择", "请选择", "");
        component.define.put("list", new ArrayList<IdValue>());
        compList.add(component);
    }

    private static void initBaseParam(Component component, String label, String hint, String text) {
        component.define.put("label", label);
        component.define.put("hint", hint);
        component.define.put("text", text);
        component.define.put("labelColor", "#777777");
        component.define.put("hintTextColor", "#777777");
        component.define.put("textColor", "#333333");
    }

}
