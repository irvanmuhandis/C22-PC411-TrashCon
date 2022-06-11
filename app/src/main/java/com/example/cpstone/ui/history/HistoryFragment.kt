package com.example.cpstone.ui.history

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cpstone.data.ImageUpload
import com.example.cpstone.databinding.HistoryFragmentBinding
import com.example.cpstone.ui.dashboard.ResultActivity
import com.example.cpstone.ui.home.DetailClassTrashActivity
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class HistoryFragment : Fragment() {

    lateinit var binding: HistoryFragmentBinding
    private val list = ArrayList<ImageUpload>()
    lateinit var viewModel: HistoryViewModel
    lateinit var adapter: HistoryAdapter
    var dataRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("myImages")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HistoryFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

        binding.rvList.layoutManager = LinearLayoutManager(requireContext())

        dataRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {
                    var model = data.getValue(ImageUpload::class.java)
                    list.add(model!!)
                }
                adapter = HistoryAdapter(list)
                binding.rvList.adapter = adapter
                adapter.setOnhistoryClick(object : HistoryAdapter.OnHistoryClick {
                    override fun onHistoryClicked(packet: ImageUpload) {
                        val intent = Intent(requireContext(), DetailHistoryActivity::class.java)
                        intent.putExtra("resultHistory",packet)
                        startActivity(intent)
                    }

                })
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


    }

}



