package com.example.evg.tofi.models

import java.io.Serializable

data class UserModel(val login : String,
                     val email : String,
                     val name: String,
                     val surname: String,
                     val patronymic: String,
                     val balance : String,
                     val phone : String) : Serializable
