package com.droppyn.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.droppyn.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

  private lateinit var profileViewModel: ProfileViewModel
  private lateinit var binding: FragmentProfileBinding


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)
    binding = FragmentProfileBinding.inflate(inflater, container, false)
    binding.profileViewModel = profileViewModel

    return binding.root
  }
}