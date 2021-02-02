package com.example.mediumclone2.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mediumclone2.R
import com.example.mediumclone2.retrofit.models.user.UserLogIn
import com.example.mediumclone2.retrofit.models.user.UserXX
import com.example.mediumclone2.ui.home.HomeActivity
import com.example.mediumclone2.ui.signUp.SignUpFragment
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LogInFragment : Fragment() {
val TAG="TAGFRAGMENT"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email=view.findViewById<EditText>(R.id.email)
        val password=view.findViewById<EditText>(R.id.password)
        val viewModel=ViewModelProvider(requireActivity()).get(LogInViewModel::class.java)
        val logInBtn=view.findViewById<MaterialButton>(R.id.logInBtn)
        val signUp = view.findViewById<TextView>(R.id.signUp)

        val transaction = activity?.supportFragmentManager?.beginTransaction()
        signUp.setOnClickListener {
            transaction?.replace(R.id.fragmentContainer, SignUpFragment())
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }
        logInBtn.setOnClickListener {
            if(email.text.isNotEmpty()&&password.text.isNotEmpty()){
                val userXX= UserXX(email.text.toString(),password.text.toString())
                val userLogIn=UserLogIn(userXX)
                viewModel.loginUser(userLogIn)
            }
        }
        viewModel.loginSuccess.observe(viewLifecycleOwner, Observer {
            val intent= Intent(requireActivity(), HomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        })
        viewModel.loginFailure.observe(viewLifecycleOwner, Observer {
            if(it){
                Log.e(TAG,"LogIn Failure")
            }
        })


    }

}