package com.nightfarmer.billmanager.bean;

import com.nightfarmer.billmanager.component.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by zhangfan on 2016/11/24 0024.
 */

public class BillDefine {

    public String recid = UUID.randomUUID().toString();
    public String postUrl;
    public String title = "未命名单据";
    public List<ItemDefine> itemList = new ArrayList<>();


    public List<Component> componentList = new ArrayList<>();

    public int selectedItem = -1;
}
