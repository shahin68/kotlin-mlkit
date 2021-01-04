package com.shahin.mlkit.ui.fragments.home

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.text.Text
import com.shahin.mlkit.data.sources.mlkit.MlkitRepository
import kotlinx.coroutines.launch
import java.io.File

class HomeViewModel(
    private val mlkitRepository: MlkitRepository
) : ViewModel() {

    val text = mlkitRepository.text


}