package com.example.sortly.utils

import android.annotation.SuppressLint
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

const val ZERO_PRICE = "0.00"

@SuppressLint("DefaultLocale")
fun getTotalPrice(quantity: String?, price: String?): String {
    if (quantity.isNullOrEmpty() || price.isNullOrEmpty()) {
        return ZERO_PRICE
    }
    return String.format("%.2f", quantity.toDouble()*price.toDouble())
}

fun setSpannableText(textView: TextView, text1: String, color1: Int, text2: String, color2: Int) {
    val builder = SpannableStringBuilder()

    val spannableText1 = SpannableString(text1)
    spannableText1.setSpan(
        ForegroundColorSpan(color1),
        0,
        text1.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    builder.append(spannableText1)

    val spannableText2 = SpannableString(text2)
    spannableText2.setSpan(
        ForegroundColorSpan(color2),
        0,
        text2.length,
        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    builder.append(spannableText2)

    textView.text = builder
}

fun formatDateTime(timestamp: String): String {
    val zonedDateTime = ZonedDateTime.parse(timestamp)
    val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a")
    return zonedDateTime.format(formatter)
}