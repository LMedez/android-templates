package android.template.ui

import android.opengl.Visibility
import android.os.Bundle
import android.template.base.BaseFragment
import android.template.databinding.FragmentFirstBinding
import android.template.presentation.viewmodel.MyModelUiState
import android.template.presentation.viewmodel.MyModelViewModel
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FirstFragment: BaseFragment<FragmentFirstBinding>(FragmentFirstBinding::inflate) {
    private val myModelViewModel: MyModelViewModel by viewModel()
    private var name = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                myModelViewModel.myModelState.collect {
                    when(it) {
                        is MyModelUiState.Success -> {
                            binding.progressCircular.visibility = View.GONE
                            binding.contentContainer.visibility = View.VISIBLE
                            name = it.data.name
                            binding.textView.text = it.data.name
                        }
                        is MyModelUiState.Error -> {}

                        MyModelUiState.Loading -> binding.progressCircular.show()
                    }
                }
            }
        }

        binding.materialButton.setOnClickListener {
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment(name))
        }
    }
}