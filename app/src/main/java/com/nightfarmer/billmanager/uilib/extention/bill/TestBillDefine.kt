package com.jqyd.yuerduo.extention.bill

/**
 * Created by zhangfan on 16-11-4.
 */
class TestBillDefine {

    companion object {

        val json = """


        {
            "postUrl":"/xxx/xxx",
            "title":"我的单据1",
            "itemList":[
                {
                    "type":0,
                    "itemDefine":{
                        "label" : "名称",
                        "hint" : "请输入",
                        "hintTextColor" : "#00FF00",
                        "id" : "name",
                        "singleLine" : false,
                        "textColor" : "#FF0000",
                        "labelColor" : "#0000FF",
                        "text" : "默认内容\n内容"
                    }
                },
                {
                    "type":0,
                    "itemDefine":{
                        "label" : "文本1",
                        "hint" : "请输入。。。",
                        "hintTextColor" : "#00FF00",
                        "id" : "text1",
                        "textColor" : "#FF0000",
                        "labelColor" : "#0000FF"
                    }
                },
                {
                    "type":1,
                    "itemDefine":{
                        "id" : "testTime",
                        "label" : "全时间",
                        "hint" : "选个时间吧",
                        "type" : "ALL",
                        "format" : "yyyy-MM-dd HH-mm-ss"
                    }
                },
                {
                    "type":2,
                    "itemDefine":{
                        "textColor" : "#FF0000",
                        "id" : "myselect",
                        "label" : "单选",
                        "list" :[{"id":"11", "value":"A"},{"id":"22", "value":"B"}]
                    }
                },
                {
                    "type":3,
                    "itemDefine":{
                        "id" : "myselect2",
                        "label" : "列表选择",
                        "multiselect" : false,
                        "dataUrl": "/download/testlist.json"
                    }
                },
                {
                    "type":4,
                    "itemDefine":{
                        "id" : "myPic1",
                        "label" : "拍照测试"
                    }
                },
                {
                    "type": 5,
                    "itemDefine":{
                        "id": "func1",
                        "label":"打开某个功能",
                        "funcId":"Restock",
                        "jsonParam":{
                            "title":"自定义的标题"
                        }
                    }
                },
                {
                    "type":6,
                    "itemDefine":{
                        "id" : "mybill2",
                        "text" : "打开新单据2",
                        "billDefine" : {
                            "title":"单据标题2",
                            "itemList":[
                                {
                                    "type":0,
                                    "itemDefine":{
                                        "label" : "文本1",
                                        "hint" : "请输入。。。",
                                        "hintTextColor" : "#00FF00",
                                        "id" : "text1",
                                        "textColor" : "#FF0000",
                                        "labelColor" : "#0000FF"
                                    }
                                },
                                {
                                    "type":1,
                                    "itemDefine":{
                                        "id" : "testTime",
                                        "label" : "只有时间",
                                        "hint" : "选个时间吧",
                                        "type" : "HOURS_MINS",
                                        "format" : "HH-mm"
                                    }
                                }
                            ]
                        }
                    }
                }
            ]
        }



        """

    }
}