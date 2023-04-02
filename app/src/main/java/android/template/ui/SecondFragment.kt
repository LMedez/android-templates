package android.template.ui

import android.os.Bundle
import android.template.base.BaseFragment
import android.template.databinding.FragmentFirstBinding
import android.template.databinding.FragmentSecondBinding
import android.view.View
import androidx.navigation.fragment.navArgs

class SecondFragment: BaseFragment<FragmentSecondBinding>(FragmentSecondBinding::inflate) {
    private val args: SecondFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text = args.name
    }
}