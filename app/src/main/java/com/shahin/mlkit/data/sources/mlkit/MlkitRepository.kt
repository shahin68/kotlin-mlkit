package com.shahin.mlkit.data.sources.mlkit

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.text.Text
import java.io.File

interface MlkitRepository {
    val text: LiveData<Pair<Uri, Text>?>
    fun runAnalyzer(context: Context, uri: Uri, block: (Pair<Boolean, String>) -> Unit)
}