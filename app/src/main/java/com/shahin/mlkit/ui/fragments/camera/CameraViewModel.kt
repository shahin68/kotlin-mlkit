package com.shahin.mlkit.ui.fragments.camera

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.text.Text
import com.shahin.mlkit.data.sources.mlkit.MlkitRepository
import kotlinx.coroutines.launch
import java.io.File

class CameraViewModel(
    private val mlkitRepository: MlkitRepository
) : ViewModel() {

    val text = mlkitRepository.text

    fun analyzeImage(context: Context, uri: Uri, block: (Pair<Boolean, String>) -> Unit) {
        mlkitRepository.runAnalyzer(context, uri, block)
    }

}