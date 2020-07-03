package com.example.ttokttok_v20


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A simple [Fragment] subclass.
 */
class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* 검색창 */

        // '검색' 버튼 클릭 -> SelectActivity로 이동
        btn_search.setOnClickListener {
            val searchIntent = Intent(context, SelectActivity::class.java)
            startActivity(searchIntent)
        }

        /* 주요 버튼 3개 */

        // '브랜드별' 버튼 클릭 -> BrandActivity로 이동
        btn_brand.setOnClickListener {
            val brandIntent = Intent(context, BrandActivity::class.java)
            startActivity(brandIntent)
        }

        // '기능별' 버튼 클릭 -> FunctionActivity로 이동
        btn_function.setOnClickListener {
            val functionIntent = Intent(context, FunctionActivity::class.java)
            startActivity(functionIntent)
        }

        // '영양소별' 버튼 클릭 -> NutrientActivity로 이동
        btn_nutrient.setOnClickListener {
            val nutrientIntent = Intent(context, NutrientActivity::class.java)
            startActivity(nutrientIntent)
        }
    }
}
