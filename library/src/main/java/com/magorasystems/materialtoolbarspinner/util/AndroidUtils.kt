package com.magorasystems.materialtoolbarspinner.util

import android.content.Context
import android.content.res.TypedArray
import android.support.annotation.DrawableRes
import android.view.View
import android.view.ViewGroup

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
}
