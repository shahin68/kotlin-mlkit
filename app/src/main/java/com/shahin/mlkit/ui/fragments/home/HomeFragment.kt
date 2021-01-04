package com.shahin.mlkit.ui.fragments.home

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy
import com.shahin.mlkit.R
import com.shahin.mlkit.databinding.FragmentHomeBinding
import com.shahin.mlkit.extensions.*
import com.shahin.mlkit.ui.fragments.BaseFragment
import com.shahin.mlkit.utils.CreditCardType
import com.shahin.mlkit.utils.GENERIC_CAMERA_REQUEST_CODE
import com.shahin.mlkit.utils.Permission
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val viewModel: HomeViewModel by viewModel()
    private var currentFile: File? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.text.observe(viewLifecycleOwner) {
            if (it == null) {
                return@observe
            }
            Glide.with(this)
                .load(it.first)
                .placeholder(R.drawable.ic_placeholder)
                .downsample(DownsampleStrategy.AT_MOST)
                .into(binding.capturedIv)
            binding.capturedIv.visible()

            val resultText = it.second.text
            for (block in it.second.textBlocks) {
                val blockText = block.text
                val blockCornerPoints = block.cornerPoints
                val blockFrame = block.boundingBox
                for (line in block.lines) {
                    val lineText = line.text
                    val lineCornerPoints = line.cornerPoints
                    val lineFrame = line.boundingBox
                    for (element in line.elements) {
                        val elementText = element.text
                        val elementCornerPoints = element.cornerPoints
                        val elementFrame = element.boundingBox
                    }
                }
            }
            binding.cardNumberEt.setText("")
            binding.expiryDateEt.setText("")
            binding.cvvEt.setText("")
        }

        binding.captureView.setOnClickListener {
            navigateToCamera()
        }

        binding.captureTv.setOnClickListener {
            navigateToCamera()
        }

        binding.expiryDateL.setEndIconOnClickListener {
            openDatePicker()
        }

        binding.expiryDateEt.setDateValidator()
        binding.cardNumberEt.validateCard {
            when (it) {
                CreditCardType.VISA -> binding.cardNumberL.setEndIconDrawable(R.drawable.ic_visa)
                CreditCardType.MASTER_CARD -> binding.cardNumberL.setEndIconDrawable(R.drawable.ic_master)
                CreditCardType.AE -> binding.cardNumberL.setEndIconDrawable(R.drawable.ic_american_express)
                CreditCardType.NON -> binding.cardNumberL.endIconDrawable = null
            }
        }
    }

    private fun navigateToCamera() {
        Permission(this, Manifest.permission.CAMERA).runOnGrant {
            Permission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE).runOnGrant {
                findNavController().navigate(
                    HomeFragmentDirections.actionFragmentHomeToFragmentCamera()
                )
            }
        }
    }

    private fun openDatePicker() {
        val myCalendar: Calendar = Calendar.getInstance()
        val date =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateSeizureDate(myCalendar)
            }

        DatePickerDialog(
            requireContext(),
            date,
            myCalendar[Calendar.YEAR],
            myCalendar[Calendar.MONTH],
            myCalendar[Calendar.DAY_OF_MONTH]
        ).show()
    }

    private fun updateSeizureDate(calendar: Calendar) {
        val myFormat = "MM/yy"
        val sdf = SimpleDateFormat(myFormat, Locale.getDefault())
        binding.expiryDateEt.setText(sdf.format(calendar.time))
    }


}