package com.qomunal.opensource.androidresearch.common.ext

import android.content.Context
import android.widget.Toast
import java.util.Locale

/**
 * Created by faisalamircs on 13/01/2024
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * -----------------------------------------
 */

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun getLocaleExt(): Locale {
    return Locale.getDefault()
}