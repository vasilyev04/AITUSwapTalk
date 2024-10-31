package com.vasilyev.core.resource

import android.content.Context
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes id: Int): String
}

class AndroidResourceProvider(
    private val context: Context
): ResourceProvider {

    override fun getString(id: Int): String {
        return context.getString(id)
    }
}