package com.example.ttokttok_v20

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_alarmdetail.*
import java.util.*

class AlarmdetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarmdetail)

        btn_alarmMorning.setOnClickListener {
            //TimePickerDialog 이용
            val cal= Calendar.getInstance()
            val hour = cal[Calendar.HOUR_OF_DAY]
            val minute = cal[Calendar.MINUTE]

            val timeDialog = TimePickerDialog(this,
//                OnTimeSetListener{view, hourOfDay, minute -> showToast("$hourOfDay:$minute")},
                null,
                hour,
                minute,
                false
            )
            timeDialog.show()



            tv_alarmdetailMorning.setText("$hour : $minute")
        }
        btn_alarmLunch.setOnClickListener {
            //TimePickerDialog 이용
            val cal= Calendar.getInstance()
            val hour = cal[Calendar.HOUR_OF_DAY]
            val minute = cal[Calendar.MINUTE]

            val timeDialog = TimePickerDialog(this,
//                OnTimeSetListener{view, hourOfDay, minute -> showToast("$hourOfDay:$minute")},
                null,
                hour,
                minute,
                false
            )
            timeDialog.show()
        }
        btn_alarmEvening.setOnClickListener {
            //TimePickerDialog 이용
            val cal= Calendar.getInstance()
            val hour = cal[Calendar.HOUR_OF_DAY]
            val minute = cal[Calendar.MINUTE]

            val timeDialog = TimePickerDialog(this,
//                OnTimeSetListener{view, hourOfDay, minute -> showToast("$hourOfDay:$minute")},
                null,
                hour,
                minute,
                false
            )
            timeDialog.show()
        }
    }
}
