package com.example.myapplication.dialogs_all

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.act.EditAdsAct

class RcviewDialogSpenerAdapter(var tvSelection:TextView,var dialog:AlertDialog) : RecyclerView.Adapter<RcviewDialogSpenerAdapter.SpViewHolder>() {
   private val mainList= ArrayList<String>()

       //создает элемент списка
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RcviewDialogSpenerAdapter.SpViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.sp_llist_item,parent,false)


        return SpViewHolder(view,tvSelection,dialog)
    }
    //берем текст по позиции
    override fun onBindViewHolder(holder: RcviewDialogSpenerAdapter.SpViewHolder, position: Int) {
     holder.setData(mainList[position])
    }
    //определяем количество элементов в списке
    override fun getItemCount(): Int {
      return mainList.size
    }
            //Создаем ViewHolders
    class SpViewHolder(itemView: View, var tvSelection: TextView, var dialog: AlertDialog) : RecyclerView.ViewHolder(itemView),View.OnClickListener {
        private var itemText = ""
        fun setData(text:String){
            val tvSpItem = itemView.findViewById<TextView>(R.id.tvSpItem)
            tvSpItem.text =text
            itemText = text
            itemView.setOnClickListener (this)

        }
            //Выбираем нужным на элемент
        override fun onClick(p0: View?) {
          tvSelection.text=itemText
            dialog.dismiss()

        }

    }
    fun upData(list:ArrayList<String>){
        mainList.clear()
        mainList.addAll(list)
        notifyDataSetChanged()
    }


}

