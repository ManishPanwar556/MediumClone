package com.example.mediumclone2.ui.signUp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.mediumclone2.R
import com.example.mediumclone2.retrofit.models.user.UserRegister
import com.example.mediumclone2.retrofit.models.user.UserX
import com.example.mediumclone2.ui.home.HomeActivity
import com.example.mediumclone2.ui.login.LogInFragment
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

private val viewModel:SignUpViewModel by requireActivity().viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val logIn=view.findViewById<TextView>(R.id.logIn)
        val signUpBtn=view.findViewById<MaterialButton>(R.id.signUpBtn)
        val name=view.findViewById<EditText>(R.id.userName)
        val email=view.findViewById<EditText>(R.id.email)
        val password=view.findViewById<EditText>(R.id.password)
        val transaction=activity?.supportFragmentManager?.beginTransaction()
        signUpBtn.setOnClickListener {
            if(email.text.isNotEmpty()&&name.text.isNotEmpty()&&password.text.isNotEmpty()){
                val userX= UserX(email.text.toString(),password.text.toString(),name.text.toString())
                val userRegister=UserRegister(userX)
                viewModel.signUp(userRegister)
            }

        }
        viewModel.signUpSuccess.observe(viewLifecycleOwner, Observer {
            if(it){
                val intent= Intent(requireActivity(), HomeActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        })
        logIn.setOnClickListener {
            transaction?.replace(R.id.fragmentContainer, LogInFragment())
            transaction?.commit()
        }
    }

}