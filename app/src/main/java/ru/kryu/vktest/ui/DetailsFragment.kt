package ru.kryu.vktest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.kryu.vktest.databinding.FragmentDetailsBinding
import ru.kryu.vktest.ui.carousel.ImageAdapter

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var imageAdapter: ImageAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageAdapter = ImageAdapter()
        binding.recycler.adapter = imageAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        imageAdapter = null
    }
}