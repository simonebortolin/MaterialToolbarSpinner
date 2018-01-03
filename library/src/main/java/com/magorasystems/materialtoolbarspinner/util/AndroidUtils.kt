package com.magorasystems.materialtoolbarspinner.util

import android.content.Context
import android.support.annotation.DrawableRes
import android.util.TypedValue
import android.view.View
import com.magorasystems.materialtoolbarspinner.R


/**
 * Created at Magora Systems (http://magora-systems.com) on 20.07.16
 *
 * @author Stanislav S. Borzenko
 */
object AndroidUtils {
    @DrawableRes
    fun getSelectableItemBackground(context: Context): Int {
        val attrs = intArrayOf(R.attr.selectableItemBackground)
        val typedArray = context.obtainStyledAttributes(attrs)
        val backgroundResource = typedArray.getResourceId(0, 0)
        typedArray.recycle()

        return backgroundResource
    }

    fun setViewWidth(view: View, width: Int) {
        val layoutParams = view.layoutParams
        layoutParams.width = width
        view.layoutParams = layoutParams
    }

    fun convertDpToPx(context: Context, dp: Int): Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), context.resources.displayMetrics).toInt()
}
