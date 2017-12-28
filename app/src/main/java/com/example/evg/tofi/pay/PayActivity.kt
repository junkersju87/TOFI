package com.example.evg.tofi.pay

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.evg.tofi.R
import com.example.evg.tofi.api.ServerApiService
import com.example.evg.tofi.models.UserDataHolder
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_authorization.*
import kotlinx.android.synthetic.main.activity_pay.*
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class PayActivity: AppCompatActivity() {

    private val serverApiService by lazy {
        ServerApiService.create()
    }

    private fun Context.toast(message: CharSequence) =
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)
    }

    @Throws(Exception::class)
    private fun encrypt(raw: ByteArray, clear: ByteArray): ByteArray {
        val skeySpec = SecretKeySpec(raw, "AES")
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec)
        return cipher.doFinal(clear)
    }

    @Throws(Exception::class)
    private fun decrypt(raw: ByteArray, encrypted: ByteArray): ByteArray {
        val skeySpec = SecretKeySpec(raw, "AES")
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.DECRYPT_MODE, skeySpec)
        return cipher.doFinal(encrypted)
    }

    private fun pay(cost:Int,cardNo:String,cardCVV2 : String, token:String){
        serverApiService.pay(card_encrypted_data(),UserDataHolder.token)
                .observeOn(io.reactivex.android.schedulers.AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ result ->
                    toast(UserDataHolder.token)
                }, { error ->
                    toast("Pay approved")
                })
    }

    private fun card_encrypted_data() : String{
        var out = ""
        out += encrypt(edit1.text.toString().toByteArray(),UserDataHolder.token.toByteArray())
        out += "/"
        out += encrypt(edit2.text.toString().toByteArray(),UserDataHolder.token.toByteArray())
        out += "/"
        out += encrypt(edit3.text.toString().toByteArray(),UserDataHolder.token.toByteArray())
        out += "/"
        out += encrypt(edit4.text.toString().toByteArray(),UserDataHolder.token.toByteArray())
        out += "/"
        out += encrypt(edit0.text.toString().toByteArray(),UserDataHolder.token.toByteArray())
        out += "/"
        out += encrypt(edit5.text.toString().toByteArray(),UserDataHolder.token.toByteArray())
        out += "/"
        out += encrypt(edit6.text.toString().toByteArray(),UserDataHolder.token.toByteArray())
        return out
    }
}