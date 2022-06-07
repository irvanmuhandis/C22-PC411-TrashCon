package com.example.cpstone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cpstone.R
import com.example.cpstone.data.HeaderTutor
import com.example.cpstone.data.TrashClass
import com.example.cpstone.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null


    private val binding get() = _binding!!
    private lateinit var rv_trash: RecyclerView
    private val list = ArrayList<TrashClass>()
    private val header = ArrayList<HeaderTutor>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.ivTutor
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        return root
    }

    private val trashAdapter: TrashAdapter
        get() {
            val name = resources.getStringArray(R.array.data_name)
            val desc = resources.getStringArray(R.array.data_desc)
            val photo = resources.obtainTypedArray(R.array.data_photo)
            val process = resources.getStringArray(R.array.data_process)
            val data: List<TrashClass>
            for (i in name.indices) {
                list.add(TrashClass(name[i], photo.getResourceId(i, -1), desc[i], process[i]))
            }
            data = list.toList()
            return TrashAdapter { onOneColumnItemClicked(it) }.apply {
                updateData(data)
            }
        }


    private val headerAdapter: HeaderAdapter
        get() {
            val data = listOf(resources.getStringArray(R.array.header_text)[0])
            return HeaderAdapter { onTwoColumnItemClicked(it) }.apply {
                updateData(data)
            }
        }


    private val concatAdapter: ConcatAdapter by lazy {
        val config = ConcatAdapter.Config.Builder().apply {
            setIsolateViewTypes(false)
        }.build()
        ConcatAdapter(config, headerAdapter, trashAdapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layoutManager = GridLayoutManager(requireContext(), 12)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (concatAdapter.getItemViewType(position)) {
                    TrashAdapter.VIEW_TYPE -> 6
                    HeaderAdapter.VIEW_TYPE -> 12
                    else -> 12
                }
            }
        }
        binding.rvClassTrash.layoutManager = layoutManager
        binding.rvClassTrash.adapter = concatAdapter
    }

    private fun onOneColumnItemClicked(name: String) {
        Toast.makeText(
            requireContext(),
            "$name from one column adapter was clicked",
            Toast.LENGTH_SHORT
        ).show()
    }


    private fun onTwoColumnItemClicked(name: String) {
        Toast.makeText(requireContext(), "$name from two column was clicked", Toast.LENGTH_SHORT)
            .show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}