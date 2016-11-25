package com.nightfarmer.billmanager.bill

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nightfarmer.billmanager.MainActivity
import com.nightfarmer.billmanager.R
import com.nightfarmer.billmanager.bean.BillDefine
import kotlinx.android.synthetic.main.list_item_bill.view.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.onClick

/**
 * Created by zhangfan on 2016/11/24 0024.
 */
class BillListAdapter(val mainActivity: MainActivity) : RecyclerView.Adapter<BillListAdapter.ViewHolder>() {

    var selectedItem = -1

    var billList: MutableList<BillDefine> = arrayListOf()

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindData(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_bill, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return billList.size
    }

    fun chooseItem(index: Int) {
        selectedItem = index
        notifyDataSetChanged()
        mainActivity.onItemChoose(billList[index])
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        private var billDefine: BillDefine? = null

        fun bindData(position: Int) {
            val define = billList[position]
            billDefine = define
            itemView.tv_title.text = define.title.toString()
            if (position == selectedItem) {
                itemView.backgroundColor = Color.parseColor("#D0d0d0")
            } else {
                itemView.backgroundColor = Color.parseColor("#00FFFFFF")
            }
            itemView.onClick {
                chooseItem(position)
            }
        }

    }

    fun deleteCurrentBill() {
        if (selectedItem != -1) {
            billList.removeAt(selectedItem)
            notifyItemRemoved(selectedItem)
            if (selectedItem >= billList.size) {
                selectedItem -= 1
            }
            if (selectedItem != -1) {
                chooseItem(selectedItem)
            } else {
                mainActivity.onAllBillDelete()
            }
        }
    }
}