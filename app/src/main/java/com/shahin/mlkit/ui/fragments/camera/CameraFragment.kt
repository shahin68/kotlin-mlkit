package com.shahin.mlkit.ui.fragments.camera

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.shahin.mlkit.R
import com.shahin.mlkit.databinding.FragmentCameraBinding
import com.shahin.mlkit.ui.fragments.BaseFragment
import com.shahin.mlkit.utils.Permission
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class CameraFragment : BaseFragment<FragmentCameraBinding>() {

    private val viewModel: CameraViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCameraBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            findNavController().popBackStack()
        }

        binding.cameraView.bindToLifecycle(viewLifecycleOwner)

        binding.capture.setOnClickListener {
            val photoFile: File = createImageFile()
            val outputOptions = ImageCapture.OutputFileOptions
                .Builder(photoFile)
                .build()
            binding.cameraView.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(requireContext()),
                object : ImageCapture.OnImageSavedCallback {
                    override fun onError(exc: ImageCaptureException) {

                    }

                    override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                        viewModel.analyzeImage(
                            requireContext(),
                            output.savedUri ?: FileProvider.getUriForFile(
                                requireContext(),
                                getString(R.string.file_provider),
                                photoFile
                            )
                        ) {
                            if (it.first) {
                                findNavController().popBackStack()
                                showToast("Success")
                            } else {
                                showToast(it.second)
                            }
                        }
                    }
                })
        }
    }

    private fun showToast(string: String) {
        Toast.makeText(
            requireContext(),
            string,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun createImageFile(): File {
        val storageDir: File =
            requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile(
            "temp_image",
            ".jpg",
            storageDir
        )
    }
}