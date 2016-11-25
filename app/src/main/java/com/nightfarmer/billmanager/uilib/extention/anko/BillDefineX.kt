package com.jqyd.yuerduo.extention.anko

import java.io.Serializable
import java.util.*

/**
 * 单据定义
 * Created by zhangfan on 2016/11/2 0002.
 * @param editable 单据只读状态
 * @param itemList 单据字段定义列表
 */
class BillDefineX(var title: String = "", var postUrl: String = "", var editable: Boolean = true, var itemList: ArrayList<BillItemX> = arrayListOf()) : Serializable {
}