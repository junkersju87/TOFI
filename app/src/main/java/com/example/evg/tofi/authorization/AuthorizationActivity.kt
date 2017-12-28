package com.example.evg.tofi.authorization

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.evg.tofi.R
import com.example.evg.tofi.api.ServerApiService
import com.example.evg.tofi.models.ErrorBody
import com.example.evg.tofi.models.UserDataHolder
import kotlinx.android.synthetic.main.activity_authorization.*
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

class AuthorizationActivity : AppCompatActivity() {

    private val serverApiService by lazy {
        ServerApiService.create()
    }

    private fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
        enter_btn.setOnClickListener({get_token()
        enter_btn.isClickable = false
        })
    }

    private fun get_token(){
        serverApiService.getToken(editLogin.text.toString(),editPassword.text.toString())
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    enter_btn.isClickable = true
                    UserDataHolder.token = result.token
                    toast(UserDataHolder.token)
                }, { error ->
                    toast("Error")
                    //toast(Gson().fromJson<List<ErrorBody>>(((error as HttpException).response().errorBody()!!.string()),
                    //       object : TypeToken<List<ErrorBody>>() {}.type)[0].message)
                    enter_btn.isClickable = true
                })
    }
}
