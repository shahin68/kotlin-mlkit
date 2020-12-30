package com.shahin.mlkit.data.sources.mlkit

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.text.Text
import java.io.File

interface MlkitRepository {
    suspend fun runAnalyzer(context: Context, file: File): Task<Text>
}