package com.shahin.mlkit.data.sources.mlkit

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.shahin.mlkit.R
import java.io.File

class MlkitRepositoryImpl: MlkitRepository {

    private val _text: MutableLiveData<Pair<Uri, Text>?> = MutableLiveData()
    override val text: LiveData<Pair<Uri, Text>?> = _text

    init {
        _text.postValue(null)
    }

    override fun runAnalyzer(context: Context, uri: Uri, block: (Pair<Boolean, String>) -> Unit) {
        val image = InputImage.fromFilePath(context, uri)
        val detector = TextRecognition.getClient()
        detector.process(image)
            .addOnSuccessListener {
                Log.d("text-analyzer", "----->" + it)
                _text.postValue(uri to it)
                block.invoke(true to "")
            }.addOnFailureListener { e ->
                Log.e("text-analyzer", "----->" + e)
                block.invoke(false to e.toString())
            }
    }

}