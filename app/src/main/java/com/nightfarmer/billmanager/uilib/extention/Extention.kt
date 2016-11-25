package com.jqyd.yuerduo.extention

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

/**
 * android扩展
 * Created by zhangfan on 2016/4/12 0012.
 */


//fun Activity.getMyApp(): MyApplication {
//    return this.application as MyApplication;
//}

fun View.find(id: Int): View {
    return this.findViewById(id)
}

//fun Context.getLogin(): UserBean? {
//    return SystemEnv.getLogin(this);
//}
//
//fun Context.saveLogin(user: UserBean) {
//    SystemEnv.saveLogin(this, user)
//}
//
//fun Context.getDevicesID(): String {
//    return DevicesUtil.getDevicesID(this)
//}

fun Context.getResColor(id: Int): Int {
    return ContextCompat.getColor(this, id)
}

fun Fragment.toast(msg: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, msg.toString(), duration).show();
}

fun Activity.openKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager;
    imm.showSoftInput(view, InputMethodManager.RESULT_SHOWN);
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
            InputMethodManager.HIDE_IMPLICIT_ONLY);
}

fun Activity.closeKeyboard(view: View) {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager;
    imm.hideSoftInputFromWindow(view.windowToken, 0);
}

fun Date.format(formatStr: String? = "yyyy-MM-dd HH:mm"): String {
    try {
        return SimpleDateFormat(formatStr, Locale.getDefault()).format(this)
    } catch(ignore: Exception) {
        return "时间数据异常"
    }
}

//fun Activity.getLocation(callBack: Activity.(LocationBean?) -> Unit) {
//    val position = SystemEnv.getLatestSuccessLocation(this)
//    var locTime: Long = 0
//    try {
//        locTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(position.createTime).time
//    } catch(ignore: Exception) {
//    }
//    if (System.currentTimeMillis() - locTime <= 5 * 60 * 1000) {
//        callBack(position)
//    } else {
//        val mSVProgressHUD = ProgressHUD(this)
//        var bcr: BroadcastReceiver? = null
//        bcr = object : BroadcastReceiver() {
//            override fun onReceive(context: Context?, intent: Intent?) {
//                unregisterReceiver(bcr)
//                callBack(intent?.getSerializableExtra("loc") as? LocationBean)
//                mSVProgressHUD.dismissImmediately()
//            }
//        }
//        val mif = IntentFilter()
//        mif.addAction(LocationService.BROAD_CAST)
//        registerReceiver(bcr, mif)
//        val intent1 = Intent(this, LocationService::class.java)
//        intent1.action = LocationService.ACTION_SEND_LOC
//        intent1.putExtra(LocationService.NEED_BROAD_CAST, true)
//        startService(intent1)
//        mSVProgressHUD.showWithStatus("正在获取当前位置", ProgressHUD.SVProgressHUDMaskType.Black)
//
//    }
//}
//
//fun Activity.alert(title: String, msg: String, buttonBlue: String?, buttonRed: String?, callBack: Activity.(Int, AlertView) -> Unit): AlertView {
//    return AlertView("$title", "$msg", if (buttonBlue.isNullOrBlank()) null else buttonBlue, if (buttonRed.isNullOrBlank()) null else arrayOf(buttonRed), null, this, AlertView.Style.Alert, {
//        view, action ->
//        callBack(action + 1, view as AlertView)
//    }).apply { show() }
//}