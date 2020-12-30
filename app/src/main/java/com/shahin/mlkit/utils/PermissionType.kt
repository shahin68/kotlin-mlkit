package com.shahin.mlkit.utils

import android.Manifest
import androidx.annotation.Keep

@Keep
enum class PermissionType(val permission: String) {
    CAMERA(Manifest.permission.CAMERA)
}