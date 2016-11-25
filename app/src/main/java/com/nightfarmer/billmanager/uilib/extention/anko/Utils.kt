package com.jqyd.yuerduo.extention.anko

import android.content.Context
import okhttp3.Call
import okhttp3.Callback
import java.io.File
import java.util.*

/**
 * 基于anko的全局工具
 * Created by zhangfan on 16-7-1.
 */

/**
 * 提交单据
 * @param url 请求地址
 * @param billData 包含单据数据在内的单据定义
 * @param exParameters 其余请求参数，可为null
 * @param callback 请求回调，推荐使用GsonDialogHttpCallback
 */
//fun commitBill(context: Context, url: String, billData: MutableList<BillItem>, exParameters: HashMap<String, String>?, callback: Callback, progress: ((Long, Long) -> Unit)? = null): Call {
//
//    val params = exParameters ?: hashMapOf();
//    val fileMap = HashMap<String, List<File>>()
//
//    for (billItem in billData) {
//        if (billItem.type == 3) {
//            val fileListId = UUID.randomUUID().toString();
//            fileMap.put(fileListId, (billItem.data as? List<File>).orEmpty())
//            billItem.data = fileListId;
//        }
//        val data = billItem.data
//        if (!billItem.id.isNullOrEmpty() && data is String) {
//            params.put(billItem.id, data)
//        }
//    }
//
//
//    return HttpCall.post(context, url, params, fileMap, callback, progress)
//}
//
//
///**
// * 新的提交单据
// * @param url 请求地址
// * @param billData 包含单据数据在内的单据定义
// * @param exParameters 其余请求参数，可为null
// * @param callback 请求回调，推荐使用GsonDialogHttpCallback
// */
//fun commitBillX(context: Context, url: String, billData: BillDefineX, exParameters: HashMap<String, String>?, callback: Callback, progress: ((Long, Long) -> Unit)? = null): Call {
//
//    val params = exParameters ?: hashMapOf()
//    val fileMap = HashMap<String, List<File>>()
//
//    for (billItem in billData.itemList) {
//        if (billItem.type == 4) {
//            val fileListId = UUID.randomUUID().toString();
//            fileMap.put(fileListId, (billItem.itemDefine["data"] as? List<File>).orEmpty())
//            val toHashMap = billItem.itemDefine.toHashMap()
//            toHashMap["data"] = fileListId;
//            billItem.itemDefine = toHashMap
//        }
//        val data = billItem.itemDefine["data"]
//        val id = billItem.itemDefine["id"]?.toString()
//        if (id != null && !id.isNullOrEmpty() && data is String) {
//            params.put(id, data)
//        }
//    }
//
//    return HttpCall.post(context, url, params, fileMap, callback, progress)
//}
