package com.example.test2.util

import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

inline fun <T> T?.ifNull(block: (T?) -> Unit): T? {
    if (this == null) {
        block(this)
    }
    return this
}

fun RecyclerView.addLinearDividerDecoration(@DrawableRes drawableId: Int) {
    (layoutManager as? LinearLayoutManager)?.let { lm ->
        AppCompatResources.getDrawable(context, drawableId)?.let { drawable ->
            DividerItemDecoration(context, lm.orientation).let { decoration ->
                decoration.setDrawable(drawable)
                addItemDecoration(decoration)
            }
        }
    }
}