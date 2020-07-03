package com.example.ttokttok_v20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_brand.*
import java.util.HashSet

class BrandActivity : AppCompatActivity() {

    companion object {
        val BRAND = "brandName"
    }
    private lateinit  var adapter: BrandAdapter
    private var firestore: FirebaseFirestore? = null

    var hsItem = ArrayList<String>()
    var hs = HashSet<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_brand)

        // DB에서 브랜드이름만 가져오기
        getBrandName()

        // 'BACK' 버튼 클릭 -> MainActivity로 이동
//        btn_back4.setOnClickListener {
//            val back4Intent = Intent(this, MainActivity::class.java)
//            startActivity(back4Intent)
//        }
        rec_brand.layoutManager = LinearLayoutManager(this)

        adapter = BrandAdapter(this) {
            var select = "${it.name}"
            val brandNameIntent = Intent(this, SelectActivity::class.java)
            brandNameIntent.putExtra(BRAND, select)
            startActivity(brandNameIntent)
        }
        rec_brand.adapter = adapter
    }

    private fun getBrandName() {
       // progressBar4.visibility = View.VISIBLE
        firestore = FirebaseFirestore.getInstance()
        firestore?.collection("Product")?.whereGreaterThanOrEqualTo("brandName", "" )
            ?.get()?.addOnSuccessListener { task ->
                // 성공
                for (dc in task.documents) {
                    var detailDTO = dc.toObject(Detail::class.java)
                    var brandDTO = Brand(detailDTO?.brandName, "")
                    hs.add(brandDTO.name.toString()) // 중복 제거, hs에 계속 추가
                }
                hsItem.addAll(hs) // hsItem 리스트에 hs 전부 넣기
                for (n in 0 until hs.size) {
                    var brandDTO2 = Brand(hsItem[n]) // 중복 없는 브랜드명 DTO 생성
                    adapter.addItem(brandDTO2) // adapter에 1개씩 넣기
                }
                runOnUiThread {
                   // progressBar4.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                }
                Log.d("Brandi", "Success.")
            }?.addOnFailureListener {
                Log.d("Brandi", "Fail.")
            }
    }
}
