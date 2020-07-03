package com.example.ttokttok_v20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_function.*

class FunctionActivity : AppCompatActivity() {

    private val productList = arrayListOf(
        Function("간건강", R.drawable.function_icon1),
        Function("관절건강", R.drawable.function_icon2),
        Function("구강건강", R.drawable.function_icon3),
        Function("눈건강", R.drawable.function_icon4),
        Function("단백질보충제", R.drawable.function_icon5),
        Function("면역기능개선", R.drawable.function_icon6),
        Function("뼈건강", R.drawable.function_icon7),
        Function("소화기능개선", R.drawable.function_icon8),
        Function("영양보충", R.drawable.function_icon9),
        Function("장건강", R.drawable.function_icon10),
        Function("칼슘흡수촉진", R.drawable.function_icon11),
        Function("피로개선", R.drawable.function_icon12),
        Function("피부건강", R.drawable.function_icon13),
        Function("향산화", R.drawable.function_icon14),
        Function("혈당조절", R.drawable.function_icon15),
        Function("혈행개선", R.drawable.function_icon16)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_function)

        val layoutManager = GridLayoutManager(this, 4)
        rec_function.layoutManager = layoutManager

        var adapter = FunctionAdapter() {
            Toast.makeText(this,
                "선택된제품 : ${it.name}",
                Toast.LENGTH_SHORT).show()

            val selectIntent = Intent(this, SelectActivity::class.java)
            startActivity(selectIntent)
        }
        adapter.setItems(productList)
        rec_function.adapter = adapter


    }
}
