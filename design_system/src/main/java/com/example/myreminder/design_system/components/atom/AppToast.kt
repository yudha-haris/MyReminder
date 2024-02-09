package com.example.myreminder.design_system.components.atom

import android.content.Context
import android.widget.Toast

object AppToast {
    fun showDefaultToast(message: String, context: Context) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}