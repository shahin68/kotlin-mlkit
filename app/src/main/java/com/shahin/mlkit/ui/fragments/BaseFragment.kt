package com.shahin.mlkit.ui.fragments

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.shahin.mlkit.utils.Permission

abstract class BaseFragment<T : ViewBinding> : Fragment() {
    protected var _binding: T? = null
    protected val binding: T get() = _binding!!

    override fun onDetach() {
        super.onDetach()
        _binding = null
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Permission.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}