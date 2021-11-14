package com.droppyn.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.droppyn.MainActivity
import com.droppyn.R
import com.droppyn.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {


    private val viewModel: LoginViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, LoginViewModel.Factory(activity.application)).get(LoginViewModel::class.java)
    }
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        binding = DataBindingUtil.inflate(
//            inflater,R.layout.fragment_login, container,false
//        )

        binding = FragmentLoginBinding.inflate(inflater)

        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this



        viewModel.eventLogin.observe(viewLifecycleOwner, Observer { login ->
            if (login){
                navToMainActivity()
                Toast.makeText(context, viewModel.email.value?: "null", Toast.LENGTH_SHORT).show()
                viewModel.eventLoginFinished()
            }
        })

        viewModel.eventNavSignup.observe(viewLifecycleOwner, Observer { navSignup ->
            if(navSignup){
                navToSignup()
                Toast.makeText(context, "Navigation", Toast.LENGTH_SHORT).show()


                viewModel.eventNavSignupFinished()
            }
        })


        return binding.root
    }

    fun navToMainActivity(){
        val intent = Intent(context, MainActivity::class.java)
        activity?.finish()
        startActivity(intent)
    }

    fun navToSignup(){
        val navController = findNavController(this)
        navController.navigate(R.id.action_loginFragment_to_signupFragment)
    }

}