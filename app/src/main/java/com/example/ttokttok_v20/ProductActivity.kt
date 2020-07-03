package com.example.ttokttok_v20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_product.*

class ProductActivity : AppCompatActivity() {

    private lateinit var adapter : ProductAdapter

    private val product = arrayListOf<Product>(
        Product("customer","king", "4", "쩔어"),
        Product("customer","yume", "3", "흠 별로 효과좋은지 모르겠네요,,"),
        Product("customer","hello", "5", "대박대박강추상품")
    )

    private var firestore : FirebaseFirestore? = null

    var list = ArrayList<Product2>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        // 선택한 제품만 DB에서 가져오기
        getProductDetail()

        rec_review.layoutManager = LinearLayoutManager(this)
        adapter = ProductAdapter(this) {
            // 이벤트
        }
        adapter.setItems(product)
        rec_review.adapter = adapter

    }

    private fun getProductDetail() {
        var select = intent.getStringExtra(SelectActivity.PRODUCT)

        firestore = FirebaseFirestore.getInstance()
        firestore?.collection("Product")?.whereEqualTo("productName", select)
            ?.get()?.addOnSuccessListener { task ->
                // 성공
                for (dc in task.documents) {
                    var detailDTO = dc.toObject(Detail::class.java)
                    var product2DTO = Product2(
                        detailDTO?.productName,
                        detailDTO?.function,
                        detailDTO?.nutrient,
                        detailDTO?.content
                    )
                    list.add(product2DTO)
                }
                tv_productName.text = list[0].productName
                tv_detailFunctionality.text = list[0].function
                tv_detailBaseMaterial.text = list[0].nutrient
                tv_detailContent.text = list[0].content

                runOnUiThread {
                    //
                }
                Log.d("Producti", "Success.")
            }?.addOnFailureListener {
                Log.d("Producti", "Fail.")
            }
    }

}