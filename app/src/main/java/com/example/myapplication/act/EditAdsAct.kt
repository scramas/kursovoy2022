package com.example.myapplication.act

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.myapplication.R
import com.example.myapplication.viewmodel.Ad
import com.example.myapplication.database.DbMeneger

import com.example.myapplication.databinding.ActivityEditAdsBinding
import com.example.myapplication.dialogs_all.DialogSpinerHelper
import com.example.myapplication.utils.CityHelper

class EditAdsAct : AppCompatActivity() {
    private  val dbMeneger= DbMeneger()
     lateinit var rootElement: ActivityEditAdsBinding
    private  var dialog=DialogSpinerHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rootElement = ActivityEditAdsBinding.inflate(layoutInflater)
        setContentView(rootElement.root)
    }

    //Onclick

    //Вызываем список с регионами
    fun onClickeSelectRegion(view:View){
        val listRegion=CityHelper.getAllRegions(this)
        dialog.showSpinerDialog(this,listRegion,rootElement.tvRegion)
        if  (rootElement.tvCity.text.toString()!= getString(R.string.select_city)){
            rootElement.tvCity.text=getString(R.string.select_city)
        }
    }
    //Вызываем список с городами
    fun onClickeSelectCity(view:View){
        val selectedRegion=rootElement.tvRegion.text.toString()
        if (selectedRegion!=getString(R.string.select_region)){
        val listCity=CityHelper.getAllCity(selectedRegion,this)
        dialog.showSpinerDialog(this,listCity,rootElement.tvCity )
    }
        else{
            Toast.makeText(this,"Выберите Регион", Toast.LENGTH_LONG).show()
        }
    }

    //Вызываем список  категорий
    fun onClickeSelectCat(view:View){

            val listCat=resources.getStringArray (R.array.category).toMutableList() as ArrayList
        dialog.showSpinerDialog(this,listCat,rootElement.tvCat )

        }

    //Вызываем список с категорий
    fun onClickePublish(view:View){

        dbMeneger.publishAd(fillAd())
    }

    private fun fillAd( ):Ad{
        val ad: Ad
        rootElement.apply {
            ad=Ad(
                tvRegion.text.toString(),
                tvCity.text.toString(),
                edTel.text.toString(),
                edIndex.text.toString(),
                chSend.isChecked.toString(),
                tvCat.text.toString(),
                edTitle.text.toString(),
                edPrice.text.toString(),
                edDesc.text.toString(),dbMeneger.db.push().key,
            dbMeneger.auth.uid)
        }
        return ad
    }

}
