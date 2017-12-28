package com.example.evg.tofi.user

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.evg.tofi.R
import com.example.evg.tofi.models.UserModel
import kotlinx.android.synthetic.main.activity_user.*

class ChangeActivity : AppCompatActivity() {
    val default_user : UserModel = UserModel("DragonSlayer1337","evgen@gmail.com","Evgeni","Kushner","Sergeevich","940","+375333279940")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change)
        //show_user(default_user)
    }

    private fun show_user(user : UserModel){
        user_login.text = user.login
        user_email.text = user.email
        user_fio.text = user.surname + " " + user.name + " " + user.patronymic
        user_balance.text = user.balance
        user_bd.text  = "21/12/1996"
        user_phone.text = user.phone
    }
}