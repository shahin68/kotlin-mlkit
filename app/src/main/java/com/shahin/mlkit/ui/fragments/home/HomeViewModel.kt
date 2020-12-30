package com.shahin.mlkit.ui.fragments.home

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.text.Text
import com.shahin.mlkit.data.sources.mlkit.MlkitRepository
import java.io.File

class HomeViewModel(
    private val mlkitRepository: MlkitRepository
) : ViewModel() {

    suspend fun analyzeImage(context: Context, file: File): Task<Text> {
        return mlkitRepository.runAnalyzer(context, file)
    }

}