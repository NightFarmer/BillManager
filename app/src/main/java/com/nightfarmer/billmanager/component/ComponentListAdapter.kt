package com.nightfarmer.billmanager.component

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nightfarmer.billmanager.MainActivity
import com.nightfarmer.billmanager.R
import com.nightfarmer.billmanager.bean.BillDefine
import com.nightfarmer.billmanager.bill.BillListAdapter
import kotlinx.android.synthetic.main.list_item_component.view.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.onClick
import java.util.*

/**
 * Created by zhangfan on 2016/11/24 0024.
 */
class ComponentListAdapter(val mainActivity: MainActivity) : RecyclerView.Adapter<ComponentListAdapter.ViewHolder>() {


    var billDefine: BillDefine? = null

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindData(position)
    }

    override fun getItemCount(): Int {
        return billDefine?.componentList?.size ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_component, parent, false)
        return ViewHolder(view)
    }

    fun chooseItem(index: Int) {
        billDefine?.let {
            it.selectedItem = index
            mainActivity.onComponentChoose(it.componentList[index])
        }
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var component: Component? = null
        fun bindData(position: Int) {
            billDefine?.let {
                val comp = it.componentList[position]
                component = comp
                comp.name = comp.define.get("label").toString()
                if (comp.name.isNullOrEmpty() || comp.name == "null") {
                    comp.name = comp.define.get("text").toString()
                }
                if (comp.name.isNullOrEmpty() || comp.name == "null") {
                    comp.name = comp.define.get("hint").toString()
                }
                itemView.tv_title.text = comp.name
                if (position == billDefine?.selectedItem) {
                    itemView.backgroundColor = Color.parseColor("#D0d0d0")
                } else {
                    itemView.backgroundColor = Color.parseColor("#00FFFFFF")
                }
                itemView.onClick {
                    chooseItem(position)
                }
            }
        }

    }

    fun onItemMove(start: Int?, target: Int?) {
        if (start == null || target == null) return
        notifyItemMoved(start, target)
    }

    fun deleteCurrentComp() {
        billDefine?.let {
            if (it.selectedItem == -1) return
            it.componentList.removeAt(it.selectedItem)
            if (it.selectedItem >= it.componentList.size) {
                it.selectedItem -= 1
            }
            if (it.selectedItem != -1) {
                mainActivity.onComponentChoose(it.componentList[it.selectedItem])
            } else {
                mainActivity.onComponentDelete()
            }
        }
    }


}
