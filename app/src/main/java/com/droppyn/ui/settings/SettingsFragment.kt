package com.droppyn.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.droppyn.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

   private lateinit var binding : FragmentSettingsBinding

    private val settingsViewModel: SettingsViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, SettingsViewModel.Factory(activity.application)).get(SettingsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.settingsViewModel = settingsViewModel

        settingsViewModel.profile.observe(viewLifecycleOwner,{ user ->
            if(user != null){
                binding.user = user
            }
        })

        settingsViewModel.navBack.observe(viewLifecycleOwner, { nav ->
            if(nav) {

                findNavController().popBackStack()
                settingsViewModel.navBackFinished()
            }
        })

        return binding.root
    }



}