package com.droppyn.auth.signup

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.droppyn.MainActivity
import com.droppyn.R
import com.droppyn.auth.login.LoginViewModel
import com.droppyn.databinding.FragmentSignupBinding


class SignupFragment : Fragment() {

    private val viewModel: SignupViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, SignupViewModel.Factory(activity.application)).get(SignupViewModel::class.java)
    }

    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSignupBinding.inflate(
            inflater, container,false
        )

        binding.lifecycleOwner = this
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

        viewModel.eventError.observe(viewLifecycleOwner, { error ->
            if (error){
                Toast.makeText(context, "Failed to login", Toast.LENGTH_LONG).show()
                viewModel.eventErrorFinished()
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