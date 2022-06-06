package com.example.cpstone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cpstone.R
import com.example.cpstone.data.TrashClass
import com.example.cpstone.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
private lateinit var rv_trash : RecyclerView
private val list = ArrayList<Any>()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv_trash = requireView().findViewById(R.id.rv_classTrash)
        rv_trash.setHasFixedSize(true)
        list.addAll(listTrash)
        showRecyclerList()
    }

    private val listTrash: ArrayList<Any>
        get() {
            val dataName = resources.getStringArray(R.array.data_name)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
            val dataDes = resources.getStringArray(R.array.data_desc)
            val dataProcess = resources.getStringArray(R.array.data_process)
            val listtrash = ArrayList<Any>()
            listtrash.add("Cara Mengidentifikasi Sampah dengan Mudah mengguakan TrashCon")
            for (i in dataName.indices) {
                val trash = TrashClass(dataName[i], dataPhoto.getResourceId(i, -1),dataDes[i],dataProcess[i])
                listtrash.add(trash)
            }
            return listtrash
        }

    private fun showRecyclerList() {
        rv_trash.layoutManager = GridLayoutManager(requireActivity(),2)
        val listHeroAdapter = TrashAdapter(list)
        rv_trash.adapter = listHeroAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}