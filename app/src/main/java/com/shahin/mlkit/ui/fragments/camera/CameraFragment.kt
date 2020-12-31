package com.shahin.mlkit.ui.fragments.camera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.shahin.mlkit.databinding.FragmentCameraBinding
import com.shahin.mlkit.ui.fragments.BaseFragment
import com.shahin.mlkit.utils.Permission
import org.koin.androidx.viewmodel.ext.android.viewModel

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
//            binding.cameraView.takePicture()
        }
    }

}