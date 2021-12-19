package com.droppyn.ui.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.droppyn.R
import com.droppyn.databinding.FragmentProfileBinding
import com.droppyn.ui.myoffer.MyOfferViewModel

class ProfileFragment : Fragment() {

  private lateinit var binding: FragmentProfileBinding

  private val profileViewModel: ProfileViewModel by lazy {
    val activity = requireNotNull(this.activity) {
      "You can only access the viewModel after onViewCreated()"
    }
    ViewModelProvider(this, ProfileViewModel.Factory(activity.application)).get(ProfileViewModel::class.java)
  }


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = FragmentProfileBinding.inflate(inflater, container, false)
    binding.profileViewModel = profileViewModel
    binding.lifecycleOwner = this

    profileViewModel.changeProfilePicture.observe(viewLifecycleOwner, { changePhoto ->

      if (changePhoto){
        openActivityForResult()
        profileViewModel.changeProfilePictureFinished()
      }
    })



    profileViewModel.profile.observe(viewLifecycleOwner,{ user ->
      if(user != null){
          binding.user = user
      }
    })

    profileViewModel.navToSettings.observe(viewLifecycleOwner, { nav ->
      if(nav) {
        findNavController().navigate(R.id.action_navigation_profile_to_settingsFragment)
        profileViewModel.navToSettingsFinished()
      }
    })

    return binding.root
  }

  private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
    if (result.resultCode == Activity.RESULT_OK) {
      // There are no request codes
      val data: Intent? = result.data
      binding.picture.setImageURI(data?.data)

    }
  }

  fun openActivityForResult() {
    val intent = Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
    resultLauncher.launch(intent)
  }
}