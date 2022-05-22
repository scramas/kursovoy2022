 package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.act.EditAdsAct
import com.example.myapplication.adapters.AdsRcAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.dialogs.DialogConst
import com.example.myapplication.viewmodel.FireBaseViewModel
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


 class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener{
     private val DialogHelper=com.example.myapplication.dialogs.DialogHelper(this )
     private lateinit var tvAccount:TextView
     private lateinit var rootElement: ActivityMainBinding
     val mAuth= FirebaseAuth.getInstance()
     val adapter =AdsRcAdapter(mAuth)
     private val firebaseViewModel:FireBaseViewModel by viewModels()

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         rootElement= ActivityMainBinding.inflate(layoutInflater) //  Доступ к элементам ativity
        setContentView(R.layout.activity_main)
         val view = rootElement.root
         setContentView(view)
        init()
         initRecView()
         initViewModel()
         firebaseViewModel.loadAllAds()


    }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         if (item.itemId == R.id.id_new_ads) {
             val i = Intent(this, EditAdsAct::class.java)
             startActivity(i)
         }
         return super.onOptionsItemSelected(item)
     }
    //Создаем меню
     override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         menuInflater.inflate(R.menu.main_menu,menu)
         return super.onCreateOptionsMenu(menu)
     }

     override fun onStart(){
         super.onStart()
         uiUpdate(mAuth.currentUser)
     }
     private fun initViewModel(){
         firebaseViewModel.liveAdsData.observe(this,{
             adapter.updateAdapter(it)
         })

     }
     //Фукция ициализации меню
     private  fun init(){
          setSupportActionBar(rootElement.mainContent.toolbar)
         val toggle=ActionBarDrawerToggle(this,rootElement.drawerlayout,rootElement.mainContent.toolbar,R.string.open,R.string.close)
         rootElement.drawerlayout.addDrawerListener(toggle)
         toggle.syncState()
         rootElement.navView.setNavigationItemSelectedListener  (this)
         tvAccount =  rootElement.navView.getHeaderView(0).findViewById(R.id.tvAccountemail)
     }

     private fun  initRecView(){
         rootElement.apply {
             mainContent.rcView.layoutManager= LinearLayoutManager(this@MainActivity)
             mainContent.rcView.adapter = adapter

         }
     }
        // Обработчик пуктов меню
     override fun onNavigationItemSelected(item: MenuItem): Boolean {

         when(item.itemId)
         {
             R.id.id_my_ads->{
                 Toast.makeText(this,"hello my_ads", Toast.LENGTH_LONG).show()
             }
             R.id.id_car->{Toast.makeText(this,"hello m_ads", Toast.LENGTH_LONG).show()}
             R.id.id_pc->{Toast.makeText(this,"hello my_", Toast.LENGTH_LONG).show()}
             R.id.id_my_smp->{Toast.makeText(this,"hello my_ad", Toast.LENGTH_LONG).show()}
             R.id.id_in->{ DialogHelper.createSingDialog(DialogConst.SING_IN_STATE)}
             R.id.id_sing_up->{
                 DialogHelper.createSingDialog(DialogConst.SING_UP_STATE)
             }
             R.id.id_out->{
                 uiUpdate(null)
                 mAuth.signOut()
             }
         }
         rootElement.drawerlayout.closeDrawer(GravityCompat.START)
         return true
     }
     fun uiUpdate(user: FirebaseUser?){
        tvAccount.text = if (user==null)
        {
           resources.getString(R.string.not_reg)
        }
        else{
            user.email
        }
     }



 }