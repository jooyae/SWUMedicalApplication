package com.example.ttokttok_v20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // 'BACK' 버튼 클릭 -> MainActivity로 이동
//        btn_back7.setOnClickListener {
//            val back7Intent = Intent(this, MainActivity::class.java)
//            startActivity(back7Intent)
//        }
    }
}
