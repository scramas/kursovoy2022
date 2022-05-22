package com.example.myapplication.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.DbMeneger

class FireBaseViewModel :ViewModel(){

    private val dbMeneger = DbMeneger()

    val liveAdsData = MutableLiveData<ArrayList<Ad>>()
        public fun loadAllAds(){
    dbMeneger.readDataFromDb(object :DbMeneger.ReadDataCallBar{
        override fun readData(list: ArrayList<Ad>) {
           liveAdsData.value=list
        }

    })
}

}