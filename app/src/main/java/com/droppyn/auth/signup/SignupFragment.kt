package com.droppyn.auth.signup

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.droppyn.MainActivity
import com.droppyn.R
import com.droppyn.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {

    private lateinit var viewModel: SignupViewModel
    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignupBinding.inflate(
            inflater, container,false
        )

        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        binding.signupViewModel = viewModel

       viewModel.eventSignup.observe(viewLifecycleOwner, Observer { signup ->
           if(signup){
               navToMainActivity()
               viewModel.eventSignupFinished()
           }
       })
        viewModel.eventNavLogin.observe(viewLifecycleOwner, Observer { navLogin ->
            if(navLogin){
                navToLogin()
                viewModel.eventNavLoginFinished()
            }
        })


        return binding.root
    }

    fun navToLogin(){
        val navController = NavHostFragment.findNavController(this)
        navController.navigate(R.id.action_signupFragment_to_loginFragment)
    }

    fun navToMainActivity(){
        val intent = Intent(context, MainActivity::class.java)
        activity?.finish()
        startActivity(intent)
    }

}