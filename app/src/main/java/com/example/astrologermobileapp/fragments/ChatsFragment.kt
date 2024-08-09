package com.example.astrologermobileapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astrologermobileapp.activities.ChatScreenActivity
import com.example.astrologermobileapp.adapters.AstrologersDetailsAdapter
import com.example.astrologermobileapp.databinding.FragmentChatsBinding
import com.example.astrologermobileapp.models.AstrologerDetails


class ChatsFragment : Fragment() {

    private var _binding: FragmentChatsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatsBinding.inflate(inflater, container, false)


        setupAstrologers()

        return binding.root
    }

    private fun setupAstrologers() {
        val recyclerView: RecyclerView = binding.recyclerViewAstro

        // Sample Data
        val astrologers = listOf(
            AstrologerDetails(
                "Sitanjali",
                "Vedic, Vastu",
                "English, Hindi",
                "Exp: 10 Years",
                "https://cdn.astrochakra.com/profile-photos/YyFe8hB0d5tKhUptQzjU3AVx3HZqARlaPTwVg3xY.jpg"
            ),
            AstrologerDetails(
                "Vedant",
                "Vedic, Vastu",
                "English, Hindi",
                "Exp: 5 Years",
                "https://cdn.astrochakra.com/profile-photos/llWQi35RDqSeiRgxA1NXtTvOsxz6dSd531Kd0e9c.jpg"
            ),
            AstrologerDetails(
                "Amar",
                "Vedic, Vastu",
                "Marathi, Hindi",
                "Exp: 14 Years",
                "https://cdn.astrochakra.com/profile-photos/Hepxw3B3s3QhY3l5n7qGfSwUGhNI2stebrwK3My6.jpg"
            ),
            AstrologerDetails(
                "Harish",
                "Vedic, Vastu",
                "English, Hindi",
                "Exp: 6 Years",
                "https://cdn.astrochakra.com/profile-photos/YyFe8hB0d5tKhUptQzjU3AVx3HZqARlaPTwVg3xY.jpg"
            ),
            // Add more astrologers
        )

        val adapter = AstrologersDetailsAdapter(astrologers) { selectedAstrologer ->
            // Handle item click here
            val intent = Intent(requireContext(), ChatScreenActivity::class.java)
            intent.putExtra("name", selectedAstrologer.name)
            intent.putExtra("description", selectedAstrologer.description)
            intent.putExtra("languages", selectedAstrologer.languages)
            intent.putExtra("experience", selectedAstrologer.experience)
            intent.putExtra("image", selectedAstrologer.profileImage)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

}













