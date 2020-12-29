package com.shahin.mlkit.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding>: AppCompatActivity() {

    protected var _binding: T? = null
    protected val binding: T get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}