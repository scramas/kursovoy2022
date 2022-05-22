package com.example.myapplication.dialogs

import android.app.AlertDialog
import android.opengl.Visibility
import android.view.View
import android.view.textclassifier.TextClassifier
import android.widget.Toast
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.accounthelper.AccountHelper
import com.example.myapplication.databinding.SingDialogBinding
import com.google.android.gms.tasks.Task

//Класс для работы с регистрацией и авторизацией
class DialogHelper (act:MainActivity) {
private val act=act
    private  val accHelper=AccountHelper(act)
    fun createSingDialog(index:Int){
        val builder =AlertDialog.Builder(act)
        val rootDialogElement =SingDialogBinding.inflate(act.layoutInflater)
        val view= rootDialogElement.root
        setDialogState(index,rootDialogElement)

        val dialog=builder.create()

        rootDialogElement.btSingupin.setOnClickListener {

            setOnClickSingupin(index,rootDialogElement)
        }
        rootDialogElement.btForgetP.setOnClickListener {

            setOnClickResetPassword(rootDialogElement)

        }


        builder.setView(view)
        builder.show()

    }
        //Функция отправки ссылки для смены пароля
    private fun setOnClickResetPassword(rootDialogElement: SingDialogBinding) {
        if (rootDialogElement.edSingEmail.text.isNotEmpty()){
            act.mAuth.sendPasswordResetEmail(rootDialogElement.edSingEmail.text.toString()).addOnCompleteListener{ task->
                if (task.isSuccessful){
                    Toast.makeText(act,R.string.email_reset_password,Toast.LENGTH_LONG).show()
                }
                else{
                   rootDialogElement.tvDialogMassage.visibility=View.VISIBLE
                }
            }
        }
    }
    //Функция выбора диалога если нажата Регистрация в меню то в диалоге будет один набор элементов
    //иначе другой а и менно для входа
    private fun setOnClickSingupin(index: Int, rootDialogElement: SingDialogBinding) {
        if ( index==DialogConst.SING_UP_STATE ){
            accHelper.singUpWithEmail(rootDialogElement.edSingEmail.text.toString(), rootDialogElement.edSingPassword.text.toString())

        }
        else
        {
            accHelper.singInWithEmail(rootDialogElement.edSingEmail.text.toString(), rootDialogElement.edSingPassword.text.toString())
        }

    }
    //Выбор состояния если 0 то диалог регистрации а если 1 то авторизации
    private fun setDialogState(index: Int, rootDialogElement: SingDialogBinding) {
        if (index==DialogConst.SING_UP_STATE){
            rootDialogElement.tvSignTitle.text =act.resources.getString( R.string.ad_sing_up)
            rootDialogElement.btSingupin .text =act.resources.getString( R.string.sing_up_action)
        }
        else
        {
            rootDialogElement.tvSignTitle.text ="Вход"
            rootDialogElement.btSingupin .text ="Войти"
            rootDialogElement.btForgetP.visibility= View.VISIBLE
        }


    }
}