package com.example.evg.tofi.mainpage

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.example.evg.tofi.R
import com.example.evg.tofi.authorization.AuthorizationActivity
import com.example.evg.tofi.eventpage.EventActivity
import com.example.evg.tofi.models.EventModel
import com.example.evg.tofi.registration.RegistrationActivity
import com.example.evg.tofi.user.UserActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val data : List<EventModel> = mutableListOf(EventModel("1","2","3","4","5"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registration_btn.setOnClickListener({startActivity(Intent(this,RegistrationActivity::class.java))})
        authorization_btn.setOnClickListener({startActivity(Intent(this,AuthorizationActivity::class.java))})
        user_btn.setOnClickListener({startActivity(Intent(this, UserActivity::class.java))})
        bindRecyclerView()
    }

    private fun bindRecyclerView() {
        var adapter = MainPageRwAdapter({
            val intent = Intent(this, EventActivity::class.java)
            intent.putExtra("f", it)
            startActivity(intent)
        }, data)
        main_pager_rw.adapter = adapter
        main_pager_rw.layoutManager = GridLayoutManager(this, 1)
        main_pager_rw.isNestedScrollingEnabled = false
    }

}
