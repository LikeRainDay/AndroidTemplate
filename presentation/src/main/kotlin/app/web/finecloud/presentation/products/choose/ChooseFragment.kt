package app.web.finecloud.presentation.products.choose

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.web.finecloud.presentation.R
import app.web.finecloud.presentation.databinding.FragmentChooseBinding
import app.web.finecloud.presentation.extension.setOnReactiveClickListener
import app.web.finecloud.presentation.extension.viewBinding
import java.io.Serializable

class ChooseFragment : Fragment(R.layout.fragment_choose) {

    private val binding by viewBinding(FragmentChooseBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupListeners()
    }

    private fun setupListeners() {
        binding.fragmentChooseCoroutineBtn.setOnReactiveClickListener {
            val action =
                ChooseFragmentDirections.navigateToProductsListFragment(ChoosePathType.COROUTINE)
            findNavController().navigate(action)
        }
        binding.fragmentChooseRxBtn.setOnReactiveClickListener {
            val action =
                ChooseFragmentDirections.navigateToProductsListFragment(ChoosePathType.RX)
            findNavController().navigate(action)
        }
    }
}

enum class ChoosePathType : Serializable {
    COROUTINE,
    RX
}