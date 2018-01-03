package com.magorasystems.materialtoolbarspinner

import android.content.Context
import android.os.Build
import android.support.v7.widget.AppCompatSpinner
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.magorasystems.materialtoolbarspinner.util.AndroidUtils


/**
 * Created at Magora Systems (http://magora-systems.com) on 20.07.16
 *
 * @author Stanislav S. Borzenko
 */
class MaterialToolbarSpinner : LinearLayout {
    private var spinner: Spinner? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        orientation = LinearLayout.HORIZONTAL

        spinner = AppCompatSpinner(context, null,
                R.attr.toolbarSpinnerStyle)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val dropDownVerticalOffset = resources.getDimensionPixelSize(
                    R.dimen.popup_dropdown_v_offset_ge_21)
            spinner!!.dropDownVerticalOffset = dropDownVerticalOffset
        }

        addView(spinner, LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT))
    }

    var adapter : Adapter?
        get() = spinner?.adapter as Adapter
        set(value) { spinner!!.adapter = value }


    var onClickListener : AdapterView.OnItemSelectedListener?
        get() = spinner?.onItemSelectedListener
        set(value) {spinner!!.onItemSelectedListener = value}



    class ViewHolder(val view: View) {
        val textView: TextView = view.findViewById(R.id.text)

        init {
            textView.setSingleLine()
            textView.ellipsize = TextUtils.TruncateAt.END
        }

    }

    data class Item(val text: CharSequence)



    abstract class Adapter : BaseAdapter() {
        abstract fun getToolbarView(
                position: Int, convertView: View?, parent: ViewGroup): View

        abstract fun getDownView(
                position: Int, convertView: View?, parent: ViewGroup): View

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            val view: View
            if (convertView == null) {
                convertView = getToolbarView(position, null, parent)

                val itemContainer = LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.mts,
                                parent, false) as LinearLayout
                itemContainer.addView(convertView, 0,
                        LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.MATCH_PARENT))


                //AndroidUtils.setViewWidth(itemContainer, 200)
                view = itemContainer
            } else {
                val realConvertView = (convertView as LinearLayout).getChildAt(0)
                getToolbarView(position, realConvertView, parent)
                view = convertView
            }

            return view
        }

        override fun getDropDownView(position: Int,
                                     convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            if (convertView == null) {
                convertView = getDownView(position, null, parent)

                //AndroidUtils.setViewWidth(convertView, 200)


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    convertView.setBackgroundResource(
                            AndroidUtils.getSelectableItemBackground(
                                    convertView.context))
                }
            } else {
                convertView = getDownView(position, convertView, parent)
            }

            return convertView
        }
    }

    abstract class SimpleAdapter (val context: Context) : Adapter() {
        abstract override fun getItem(position: Int) : Item

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getDownView(position: Int,
                                 convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            val viewHolder: ViewHolder
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.mts_dropdown_item, parent, false)
                viewHolder = ViewHolder(convertView)
                convertView.tag = viewHolder


            } else {
                viewHolder = convertView.tag as ViewHolder
            }

            val item = getItem(position)
            viewHolder.textView.text = item.text

            return convertView!!
        }

        override fun getToolbarView(position: Int, convertView: View?, parent: ViewGroup): View {
            var convertView = convertView
            val viewHolder: ViewHolder
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.context).inflate(
                        R.layout.mts_tooltbar_item, parent, false)
                viewHolder = ViewHolder(convertView)
                convertView.tag = viewHolder
                convertView.post {
                    val parentWidth = parent.width
                    val width = convertView.width
                    if (width == parentWidth) AndroidUtils.setViewWidth(convertView, width - AndroidUtils.convertDpToPx(convertView.context, 30))
                }
            } else {
                viewHolder = convertView.tag as ViewHolder
            }

            val item = getItem(position)
            viewHolder.textView.text = item.text

            return convertView!!
        }
    }
}
