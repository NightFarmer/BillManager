package com.nightfarmer.billmanager

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.nightfarmer.billmanager.bean.BillDefine
import com.nightfarmer.billmanager.bill.BillListAdapter
import com.nightfarmer.billmanager.component.Component
import com.nightfarmer.billmanager.component.ComponentConstants
import com.nightfarmer.billmanager.component.ComponentListAdapter
import com.nightfarmer.billmanager.mainui.BillContentListAdapter
import com.nightfarmer.billmanager.param.ParamSettingListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.onClick

class MainActivity : AppCompatActivity() {

    val billListAdapter = BillListAdapter(this)
    val componentListAdapter = ComponentListAdapter(this)
    val billContentListAdapter = BillContentListAdapter(this)
    val paramSettingListAdapter = ParamSettingListAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
        initListener()

    }

    private fun initData() {
        billDefineList.layoutManager = LinearLayoutManager(this)
        billDefineList.adapter = billListAdapter

        componentList.layoutManager = LinearLayoutManager(this)
        componentList.adapter = componentListAdapter

        billContentList.layoutManager = LinearLayoutManager(this)
        billContentList.adapter = billContentListAdapter
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0) {
            override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean {
                billContentListAdapter.onItemMove(viewHolder?.adapterPosition, target?.adapterPosition)
                componentListAdapter.onItemMove(viewHolder?.adapterPosition, target?.adapterPosition)
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {

            }
        }).attachToRecyclerView(billContentList)

        paramsSettingList.layoutManager = LinearLayoutManager(this)
        paramsSettingList.adapter = paramSettingListAdapter
    }

    private fun initListener() {
        billAddBtn.onClick {
            addBill()
        }

        billDelBtn.onClick {
            delBill()
        }

        billPreviewBtn.onClick {
            previewBill()
        }

        comAddBtn.onClick {
            addComp()
        }

        comDelBtn.onClick {
            delComp()
        }

        et_title.onInput {
            currentBill?.title = it
            tv_bill_title.text = it
            billListAdapter.notifyDataSetChanged()
        }
    }

    private fun previewBill() {
        //todo 解析单据定义，调用供应链OA客户端渲染界面

    }

    private fun delComp() {
        componentListAdapter.deleteCurrentComp()
    }

    private fun delBill() {
        billListAdapter.deleteCurrentBill()

    }

    fun addBill() {
        billListAdapter.billList.add(BillDefine())
        billListAdapter.notifyDataSetChanged()
    }

    var currentBill: BillDefine? = null
        set(value) {
            field = value
            componentListAdapter.billDefine = currentBill
            componentListAdapter.notifyDataSetChanged()
            billContentListAdapter.billDefine = value
            billContentListAdapter.notifyDataSetChanged()
            resetMainUI()
        }

    var currentComponent: Component? = null
        set(value) {
            field = value
            componentListAdapter.notifyDataSetChanged()
            billContentListAdapter.notifyDataSetChanged()
            paramSettingListAdapter.params = value?.define!!
            paramSettingListAdapter.notifyDataSetChanged()
        }

    fun onItemChoose(billDefine: BillDefine) {
        currentBill = billDefine
    }

    fun onAllBillDelete() {
        currentBill = null
        et_title.setText("")
    }


    private fun addComp() {
        currentBill?.let {
            var index = 0
            AlertDialog.Builder(this).setSingleChoiceItems(ComponentConstants.compList.map { it.name }.toTypedArray(), 0) {
                dialog, a ->
                index = a
            }.setNegativeButton("确认", {
                dialog, bt ->
                it.componentList.add(ComponentConstants.compList[index].copy())
                componentListAdapter.notifyDataSetChanged()
                billContentListAdapter.notifyDataSetChanged()
                resetMainUI()
                dialog.dismiss()
            }).setTitle("字段类型")
                    .show()
        }
    }

    fun onComponentChoose(component: Component) {
        currentComponent = component
    }

    fun resetMainUI() {
        currentBill?.let {
            tv_bill_title.text = it.title.toString()
            et_title.setText(it.title.toString())
//            billContentList.
        }
    }

    fun onComponentDelete() {
        currentComponent = null
    }

    fun onParamSet() {
        componentListAdapter.notifyDataSetChanged()
        billContentListAdapter.notifyDataSetChanged()
    }
}
