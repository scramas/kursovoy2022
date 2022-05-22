package com.example.myapplication.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.viewmodel.Ad
import com.example.myapplication.databinding.AdListItemBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.ArrayList

class AdsRcAdapter (val auth:FirebaseAuth)  :RecyclerView.Adapter<AdsRcAdapter.AdHolder> (){

     val adArray=ArrayList<Ad>() //Достаем объявления из массива


    // Создает объявления
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdHolder {
     val binding = AdListItemBinding.inflate(LayoutInflater.from(parent.context ),parent,false)
        return AdHolder(binding,auth)
    }
    // заполняет разметку объявления
    override fun onBindViewHolder(holder: AdHolder, position: Int) {
        holder.setData(adArray[position])

    }
    //Следит за количеством элементов в массиве
    override fun getItemCount(): Int {
     return  adArray.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(newList:List<Ad>)
    {
        adArray.clear()
        adArray.addAll(newList)
        notifyDataSetChanged()

    }
    class AdHolder (val binding: AdListItemBinding,val auth: FirebaseAuth): RecyclerView.ViewHolder (binding.root ){

        //Считываем данные из бд
        fun setData(ad: Ad){
         binding.apply {
             tvDesc.text =ad.description
             tvPriceAd.text=ad.price
             tvTitle.text=ad.title
         }
             showEditPanel(isOwner(ad))

     }
        //проверяет uid пользователя
        private fun isOwner(ad:Ad): Boolean{


            return ad.uid==auth.uid

        }
        //показывае editpanel со значками отредоктировать
        // и удалить если у пользователя есть объявления
        private fun showEditPanel(isOwner:Boolean){
            if (isOwner){
                binding.editPanel.visibility=View.VISIBLE
            }
            else
            {
                binding.editPanel.visibility=View.GONE
            }



        }
    }
}