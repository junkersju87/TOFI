package com.example.evg.tofi.eventpage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import com.example.evg.tofi.R
import kotlinx.android.synthetic.main.activity_event.*
import android.support.v4.app.NotificationCompat.getExtras
import com.example.evg.tofi.models.EventModel


class EventActivity : AppCompatActivity(),SeekBar.OnSeekBarChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event)
        seekBar!!.setOnSeekBarChangeListener(this)
        val extras = intent.extras
        val event = extras.getSerializable("f") as EventModel
        event_page_title.text = event.title
        event_page_descr.text = event.description
        event_page_state.text = "2/100"
    }

    override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                   fromUser: Boolean) {
        event_page_donate_btn.text = "Внести " + progress.toString()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar) {
        // called when tracking the seekbar is started
    }

    override fun onStopTrackingTouch(seekBar: SeekBar) {
        // called when tracking the seekbar is stopped
    }

}
