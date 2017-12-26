package com.example.evg.tofi.models

import java.io.Serializable

data class EventModel(val title : String,
                      val description: String,
                      val image : String,
                      val create_date: String,
                      val change_date: String): Serializable
