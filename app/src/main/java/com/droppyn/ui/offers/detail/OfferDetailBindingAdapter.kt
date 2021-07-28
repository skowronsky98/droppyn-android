package com.droppyn.ui.offers.detail

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.droppyn.domain.Size

@BindingAdapter("sizeText")
fun setSizeText(view: TextView, item: Size?) {
    item?.let {
        view.text = item.us.toString() + " US / " + item.eu + " EUR / " + item.uk.toString() + " UK"
    }
}