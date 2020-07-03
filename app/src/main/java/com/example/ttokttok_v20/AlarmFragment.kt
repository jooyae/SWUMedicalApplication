package com.example.ttokttok_v20


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_function.*
import kotlinx.android.synthetic.main.fragment_alarm.*

/**
 * A simple [Fragment] subclass.
 */
class AlarmFragment : Fragment() {

    private val alarmList = arrayListOf(
        Alarm("1", R.drawable.function_icon1),
        Alarm( "2",R.drawable.function_icon2),
        Alarm( "3",R.drawable.function_icon3),
        Alarm( "4",R.drawable.function_icon4),
        Alarm("5", R.drawable.function_icon5),
        Alarm( "6",R.drawable.function_icon6),
        Alarm( "7",R.drawable.function_icon7),
        Alarm("8", R.drawable.function_icon8),
        Alarm("9", R.drawable.function_icon9),
        Alarm( "10",R.drawable.function_icon10),
        Alarm( "11",R.drawable.function_icon11),
        Alarm( "12",R.drawable.function_icon12),
        Alarm( "13",R.drawable.function_icon13),
        Alarm( "14",R.drawable.function_icon14),
        Alarm( "15",R.drawable.function_icon15),
        Alarm( "16",R.drawable.function_icon16)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alarm, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(context, 3)
        rec_alarm.layoutManager = layoutManager

        var adapter = AlarmAdapter() {
            Toast.makeText(context,
                "선택된제품 : ${it.name}",
                Toast.LENGTH_SHORT).show()

            val AlarmdetailIntent = Intent(context, AlarmdetailActivity::class.java)
            startActivity(AlarmdetailIntent)

            //val selectIntent = Intent(context, SelectActivity::class.java)
            //startActivity(selectIntent)
        }
        adapter.setItems(alarmList)
        rec_alarm.adapter = adapter
    }


}
