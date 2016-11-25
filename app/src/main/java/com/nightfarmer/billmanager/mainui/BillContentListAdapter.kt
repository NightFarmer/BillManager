package com.nightfarmer.billmanager.mainui

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nightfarmer.billmanager.MainActivity
import com.nightfarmer.billmanager.R
import com.nightfarmer.billmanager.bean.BillDefine
import com.nightfarmer.billmanager.component.Component
import kotlinx.android.synthetic.main.list_item_bill_content_item_1.view.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.wrapContent

/**
 * Created by zhangfan on 2016/11/24 0024.
 */
class BillContentListAdapter(val mainActivity: MainActivity) : RecyclerView.Adapter<BillContentListAdapter.ViewHolder>() {

    var billDefine: BillDefine? = null

    override fun getItemCount(): Int {
        return billDefine?.componentList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindData(position)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        if (viewType == 2) {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_bill_content_item_2, parent, false)
            return ViewHolder(view)
        }
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_bill_content_item_1, parent, false)
        return ViewHolder(view)
    }


    open inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView?.onClick {
                component?.let {
                    billDefine?.selectedItem = billDefine?.componentList?.indexOf(component)
                    mainActivity.onComponentChoose(it)
                }
            }
        }

        private var component: Component? = null

        open fun bindData(position: Int) {
            component = billDefine?.componentList?.get(position)
            val label = component?.define?.get("label")
            if (label == null || "" == label) {
                itemView.tv_label.text = ""
            } else {
                itemView.tv_label.text = "$label："
            }
            itemView.tv_input.hint = (component?.define?.get("hint") as? String).orEmpty()
            itemView.tv_input.text = (component?.define?.get("text") as? String).orEmpty()

            itemView.tv_input.setTextColor(Color.parseColor(component?.define?.get("textColor").toString()))
            itemView.tv_input.setHintTextColor(Color.parseColor(component?.define?.get("hintTextColor").toString()))
            itemView.tv_label.setTextColor(Color.parseColor(component?.define?.get("labelColor").toString()))

            when (component?.type) {
                1, 2 -> {
                    itemView.arrow_right?.visibility = View.VISIBLE
                }
                else -> {
                    itemView.arrow_right?.visibility = View.GONE
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val component = billDefine?.componentList?.get(position)
        if (component?.type == 0 && component?.define?.get("singleLine") == false) {
            return 2
        }
        return 1
    }

    fun onItemMove(start: Int?, target: Int?) {
        if (start == null || target == null) return
        val removeAt = billDefine?.componentList?.removeAt(start)
        billDefine?.componentList?.add(target, removeAt)
        notifyItemMoved(start, target)
    }


}
