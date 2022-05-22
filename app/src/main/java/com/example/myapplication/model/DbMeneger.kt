package com.example.myapplication.database

import com.example.myapplication.viewmodel.Ad
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class DbMeneger  {
    val  db= Firebase.database.getReference("main")
    val auth = Firebase.auth

        //Указываем узел
    fun publishAd(ad: Ad){
        if (auth.uid!=null) {
        db.child(ad.key  ?: "empty").child(auth.uid!!).child("ad"). setValue(ad)
        }
    }
    //Считываем объявления из бд
    fun readDataFromDb(readerCallback: ReadDataCallBar?){
        db.addListenerForSingleValueEvent(object :ValueEventListener{

             override fun onDataChange(snapshot: DataSnapshot) {
                 val adArray = ArrayList<Ad>( )
             for ( item in snapshot.children){
                 val ad= item.children.iterator().next().child("ad").getValue(Ad::class.java)
                    if (ad!=null)adArray.add(ad)
             }
                 readerCallback?.readData(adArray)
            }

            override fun onCancelled(error: DatabaseError) {


            }

        })
    }
    // получаем данные из класса Ad
    interface ReadDataCallBar {
        fun readData(list:ArrayList<Ad>)
    }

}