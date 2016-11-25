package com.nightfarmer.billmanager.param

import android.content.DialogInterface
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.OnColorSelectedListener
import com.flask.colorpicker.builder.ColorPickerClickListener
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import com.nightfarmer.billmanager.MainActivity
import com.nightfarmer.billmanager.R
import com.nightfarmer.billmanager.onInput
import kotlinx.android.synthetic.main.list_item_param_input.view.*

import kotlinx.android.synthetic.main.list_item_param_color.view.v_color_show
import kotlinx.android.synthetic.main.list_item_param_boolean.view.cb_boolean
import org.jetbrains.anko.onClick
import java.util.*

/**
 * Created by zhangfan on 2016/11/25 0025.
 */
class ParamSettingListAdapter(val mainActivity: MainActivity) : RecyclerView.Adapter<ParamSettingListAdapter.ViewHolder>() {

    class KeyValue(var key: String, var value: Any)

    var params: HashMap<String, Any> = hashMapOf()
        set(value) {
            field = value
            val arrayOf = arrayListOf<KeyValue>()
            for ((k, v) in value) {
                arrayOf.add(KeyValue(k, v))
            }
            arrayOf.sortBy { it.key }
            paramList = arrayOf
        }

    var paramList: List<KeyValue> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        when (viewType) {
            0 -> {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_param_input, parent, false)
                return InputParamViewHolder(view)
            }
            1 -> {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_param_color, parent, false)
                return ColorParamViewHolder(view)
            }
            2 -> {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_param_boolean, parent, false)
                return BooleanParamViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_param_input, parent, false)
                return InputParamViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindData(position)
    }

    override fun getItemCount(): Int {
        return paramList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (paramList[position].key) {
            "label", "hint", "text" -> 0
            "labelColor", "hintTextColor", "textColor" -> 1
            "singleLine" -> 2
            else -> {
                0
            }
        }
    }

    inner abstract class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var data: KeyValue? = null
        abstract fun bindData(position: Int)
    }

    inner class InputParamViewHolder(itemView: View?) : ViewHolder(itemView) {

        init {
            itemView?.et_param_input?.onInput {
                data?.value = it
                params[data?.key.orEmpty()] = it
                mainActivity.onParamSet()
            }
        }

        override fun bindData(position: Int) {
            val keyValue = paramList[position]
            data = keyValue
            itemView.tv_label.text = "${keyValue.key}:"
            itemView.et_param_input.setText(keyValue.value.toString())
        }

    }

    inner class ColorParamViewHolder(itemView: View?) : ViewHolder(itemView) {
        init {
            itemView?.et_param_input?.onInput { input ->
                try {
                    val color = Color.parseColor(input)
                    data?.value = input
                    params[data?.key.orEmpty()] = input
                    itemView.v_color_show.setBackgroundColor(color)
                    mainActivity.onParamSet()
                } catch(e: Exception) {
                }
            }
            itemView?.v_color_show?.onClick {
                ColorPickerDialogBuilder.with(it?.context)
                        .setTitle("选择颜色")
                        .initialColor(Color.parseColor(data?.value.toString()))
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12).setOnColorSelectedListener { selectedColor ->
                    Log.i("xxx", "11 " + Integer.toHexString(selectedColor))
                }.setPositiveButton("ok") { dialog, selectedColor, allColors ->
                    data?.value = "#${Integer.toHexString(selectedColor)}"
                    params[data?.key.orEmpty()] = "#${Integer.toHexString(selectedColor)}"
                    itemView.et_param_input.setText(data?.value.toString())
                    itemView.v_color_show.setBackgroundColor(Color.parseColor(data?.value.toString()))
                    mainActivity.onParamSet()
                }.setNegativeButton("cancel") { dialog, which ->
                }.build().show()
            }
        }

        override fun bindData(position: Int) {
            val keyValue = paramList[position]
            data = keyValue
            itemView.tv_label.text = "${keyValue.key}:"
            itemView.et_param_input.setText(keyValue.value.toString())
            itemView.v_color_show.setBackgroundColor(Color.parseColor(data?.value.toString()))
        }
    }

    inner class BooleanParamViewHolder(itemView: View?) : ViewHolder(itemView) {

        init {
            itemView?.cb_boolean?.setOnCheckedChangeListener { compoundButton, b ->
                data?.value = b
                params[data?.key.orEmpty()] = b
                mainActivity.onParamSet()
            }
        }

        override fun bindData(position: Int) {
            val keyValue = paramList[position]
            data = keyValue
            itemView.tv_label.text = "${keyValue.key}:"
            itemView.cb_boolean.isChecked = (keyValue.value as? Boolean) ?: false
        }
    }
}