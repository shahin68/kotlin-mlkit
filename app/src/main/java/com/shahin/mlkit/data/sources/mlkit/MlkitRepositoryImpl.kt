package com.shahin.mlkit.data.sources.mlkit

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.shahin.mlkit.R
import java.io.File

class MlkitRepositoryImpl: MlkitRepository {

    override suspend fun runAnalyzer(context: Context, file: File): Task<Text> {
        val photoURI: Uri = FileProvider.getUriForFile(
            context,
            context.getString(R.string.file_provider),
            file
        )
        val image = InputImage.fromFilePath(context, photoURI)
        val detector = TextRecognition.getClient()
        return detector.process(image)
    }

}