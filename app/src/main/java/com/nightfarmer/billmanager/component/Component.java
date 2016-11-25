package com.nightfarmer.billmanager.component;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by zhangfan on 2016/11/24 0024.
 */

public class Component {

    public String recid = UUID.randomUUID().toString();
    public int index;

    public String name = "未知控件";


    public int type;
    public HashMap<String, Object> define = new HashMap<>();

    public Component copy() {
        Component component = new Component();
        component.recid = recid;
        component.index = index;
        component.name = name;
        component.type = type;
        component.define = new HashMap<>(define);
        return component;
    }
}
