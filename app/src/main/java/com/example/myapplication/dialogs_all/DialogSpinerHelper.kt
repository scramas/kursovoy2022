package com.example.myapplication.dialogs_all

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.utils.CityHelper

class DialogSpinerHelper  {
    fun showSpinerDialog(context: Context,list: ArrayList<String> ,tvselection:TextView){
        val builder =AlertDialog.Builder(context)
        val dialog = builder.create()
        val rootView=LayoutInflater.from(context).inflate(R.layout.spiner_layaut,null)
         val adapter = RcviewDialogSpenerAdapter(tvselection,dialog)
        val rcView =rootView.findViewById<RecyclerView>(R.id.rcSpView)
        val sv =rootView.findViewById<SearchView>(R.id.svSpiner)
        rcView.layoutManager =LinearLayoutManager(context)
        rcView.adapter = adapter
        adapter.upData(list )
        setSearshViewListener(adapter,list,sv )
        dialog.setView(rootView)
        dialog.show()
    }

    private fun setSearshViewListener(adapter: RcviewDialogSpenerAdapter, list: ArrayList<String>, sv: SearchView?) {
    sv?.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
        override fun onQueryTextSubmit(p0: String?): Boolean {
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            val tempList=CityHelper.FilterListData(list, newText)
            adapter.upData(tempList)
            return true
        }
    })
    }
}



