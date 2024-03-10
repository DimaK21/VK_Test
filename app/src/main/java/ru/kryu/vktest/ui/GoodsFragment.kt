package ru.kryu.vktest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kryu.vktest.R
import ru.kryu.vktest.databinding.FragmentGoodsBinding
import ru.kryu.vktest.presentation.GoodsScreenState
import ru.kryu.vktest.presentation.GoodsViewModel
import ru.kryu.vktest.ui.recycler.GoodsAdapter
import ru.kryu.vktest.util.ErrorType

class GoodsFragment : Fragment() {

    private var _binding: FragmentGoodsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: GoodsViewModel by viewModel()
    private var goodsAdapter: GoodsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGoodsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        goodsAdapter = GoodsAdapter()
        binding.rvGoods.adapter = goodsAdapter
        binding.rvGoods.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        viewModel.stateLiveData.observe(viewLifecycleOwner) {
            renderState(it)
        }
        viewModel.toastLiveData.observe(viewLifecycleOwner) {
            showToast(it)
        }
        binding.btnNoContent.setOnClickListener { viewModel.getGoods() }
    }

    private fun renderState(state: GoodsScreenState) {
        when (state) {
            is GoodsScreenState.Content -> {
                binding.progressBar.visibility = View.GONE
                binding.ivNoContent.visibility = View.GONE
                binding.tvNoContent.visibility = View.GONE
                binding.btnNoContent.visibility = View.GONE
                binding.rvGoods.visibility = View.VISIBLE

                goodsAdapter?.addGoods(state.content)
            }

            GoodsScreenState.Empty -> {
                binding.progressBar.visibility = View.GONE
                binding.ivNoContent.visibility = View.VISIBLE
                binding.tvNoContent.visibility = View.VISIBLE
                binding.btnNoContent.visibility = View.VISIBLE
                binding.rvGoods.visibility = View.GONE
            }

            GoodsScreenState.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
                binding.ivNoContent.visibility = View.GONE
                binding.tvNoContent.visibility = View.GONE
                binding.btnNoContent.visibility = View.GONE
            }
        }
    }

    private fun showToast(errorType: ErrorType) {
        if (errorType == ErrorType.NO_INTERNET) {
            Toast.makeText(requireContext(), getString(R.string.no_internet), Toast.LENGTH_SHORT)
                .show()
        }
        if (errorType == ErrorType.REMOTE_ERROR) {
            Toast.makeText(requireContext(), getString(R.string.remote_error), Toast.LENGTH_SHORT)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        goodsAdapter = null
    }
}