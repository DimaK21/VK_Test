package ru.kryu.vktest.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.HeroCarouselStrategy
import ru.kryu.vktest.R
import ru.kryu.vktest.databinding.FragmentDetailsBinding
import ru.kryu.vktest.domain.model.Goods
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
        val goods = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(ARGS_GOODS, Goods::class.java)
        } else {
            arguments?.getParcelable(ARGS_GOODS)
        }
        if (goods != null) {
            imageAdapter?.imageList?.addAll(goods.images)
        }
        binding.recycler.layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
        binding.tvName.text = goods?.title
        binding.tvDescription.text = goods?.description
        binding.tvPrice.text = getString(R.string.price, goods?.price.toString())
        binding.tvDiscountPercentage.text =
            getString(R.string.discount, goods?.discountPercentage.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        imageAdapter = null
    }

    companion object {
        private const val ARGS_GOODS = "args_goods"
        const val TAG = "DetailsFragment"

        fun newInstance(goods: Goods): Fragment {
            return DetailsFragment().apply {
                arguments = bundleOf(ARGS_GOODS to goods)
            }
        }
    }
}