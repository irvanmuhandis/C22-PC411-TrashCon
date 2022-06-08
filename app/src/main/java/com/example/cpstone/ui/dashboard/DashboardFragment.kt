package com.example.cpstone.ui.dashboard

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cpstone.R
import com.example.cpstone.databinding.FragmentDashboardBinding
import com.example.cpstone.helper.reduceFileImage
import com.example.cpstone.helper.rotateBitmap
import com.example.cpstone.helper.uriToFile
import com.example.cpstone.ml.Model
import com.example.cpstone.ui.camera.CameraActivity
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.nio.ByteBuffer
import java.nio.ByteOrder

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var dashboardViewModel: DashboardViewModel

    companion object {
        const val TAG = "MainsActivity"
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    private var getFile: File? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //   val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    requireContext(),
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                requireActivity().finish()
            }
        }


    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        activity?.let { it1 ->
            ContextCompat.checkSelfPermission(
                it1.baseContext,
                it
            )
        } == PackageManager.PERMISSION_GRANTED
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }
        binding.cameraXButton.setOnClickListener { startCameraX() }
        binding.galleryButton.setOnClickListener { startGallery() }
        binding.uploadButton.setOnClickListener { classifyImage() }

    }


    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private fun startCameraX() {
        val intent = Intent(requireContext(), CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CAMERA_X_RESULT) {
            val myFile = it.data?.getSerializableExtra("picture") as File
            val isBackCamera = it.data?.getBooleanExtra("isBackCamera", true) as Boolean

            getFile = myFile
            val result = rotateBitmap(
                BitmapFactory.decodeFile(getFile?.path),
                isBackCamera
            )

            binding.previewImageView.setImageBitmap(result)

        }
    }


    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri

            val myFile = uriToFile(selectedImg, requireContext())

            getFile = myFile

            binding.previewImageView.setImageURI(selectedImg)
        }
    }

    private fun loading(b: Boolean) {
        if (b) {
            binding.loadmain.visibility = View.VISIBLE
        } else {
            binding.loadmain.visibility = View.GONE
        }
    }

    private fun classifyImage() {
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)
            val bitmap: Bitmap = BitmapFactory.decodeFile(file.path)
            val image = Bitmap.createScaledBitmap(bitmap, 160, 160, false)
            val model = Model.newInstance(requireContext())

// Creates inputs for reference.
            val inputFeature0 =
                TensorBuffer.createFixedSize(intArrayOf(1, 160, 160, 3), DataType.FLOAT32)
            val byteBuffer: ByteBuffer = ByteBuffer.allocateDirect(4 * 160 * 160 * 3)
            byteBuffer.order(ByteOrder.nativeOrder())

            var intValue = IntArray(160 * 160)
            image.getPixels(intValue, 0, image.width, 0, 0, image.width, image.height)
            var pixel = 0

            for (i in 1..160) {
                for (j in 1..160) {
                    val value = intValue[pixel++]
                    byteBuffer.putFloat(((value shr 16) and 0xFF) * (1f / 1))
                    byteBuffer.putFloat(((value shr 8) and 0xFF) * (1f / 1))
                    byteBuffer.putFloat((value and 0xFF) * (1f / 1))
                }
            }

            inputFeature0.loadBuffer(byteBuffer)
            Log.d(TAG, "classifyImage input: $inputFeature0")
// Runs model inference and gets result.
            val outputs: Model.Outputs = model.process(inputFeature0)
            val outputFeature0: TensorBuffer = outputs.outputFeature0AsTensorBuffer
            Log.d(TAG, "classifyImage: ${outputFeature0.floatArray}")
            var confidence: FloatArray = outputFeature0.floatArray
            var maxPos = 0
            var max = 0f
            for (i in 0..confidence.size - 1) {
                if (confidence[i] > max) {
                    max = confidence[i]
                    maxPos = i
                }
            }

            val intent = Intent(requireContext(), ResultActivity::class.java)
            intent.putExtra("result",maxPos)
            startActivity(intent)

// Releases model resources if no longer used.
            model.close()
        } else {
            Toast.makeText(
                requireContext(),
                "Silakan masukkan berkas gambar terlebih dahulu.",
                Toast.LENGTH_SHORT
            ).show()
        }
        loading(false)
    }


}