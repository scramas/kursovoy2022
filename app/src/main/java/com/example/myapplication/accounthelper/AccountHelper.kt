package com.example.myapplication.accounthelper

import android.widget.Toast
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseUser

class AccountHelper (act:MainActivity)  {
    private val act=act
    //Функция регистрации c отправкой ссылки для подтверждения
    fun singUpWithEmail(email:String,password:String)
    {
        if (email.isNotEmpty() && password.isNotEmpty())
        {
            act.mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{task->
                if (task.isSuccessful){
                    sendEmailVerification(task.result?.user!!) //
                    act.uiUpdate(task.result?.user )
                }
                else{
                    Toast.makeText(act, act.resources.getString(R.string.sing_up_error),Toast.LENGTH_LONG).show()
                }
            }
        }

    }
    //Функция регистрации

    fun singInWithEmail(email:String,password:String)
    {
        if (email.isNotEmpty() && password.isNotEmpty())
        {
            act.mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{task->
                if (task.isSuccessful){
                    sendEmailVerification(task.result?.user!!)
                    act.uiUpdate(task.result?.user )
                }
                else{
                    Toast.makeText(act, act.resources.getString(R.string.sing_in_error),Toast.LENGTH_LONG).show()
                }
            }
        }

    }
    private fun sendEmailVerification(user:FirebaseUser) {
        user.sendEmailVerification().addOnCompleteListener { task ->
            if (task.isSuccessful) {

                Toast.makeText(
                    act,
                    act.resources.getString(R.string.send_varification_emal_done),
                    Toast.LENGTH_LONG
                ).show()

            } else {
                Toast.makeText(
                    act,
                    act.resources.getString(R.string.send_varification_emal_error),
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }}
