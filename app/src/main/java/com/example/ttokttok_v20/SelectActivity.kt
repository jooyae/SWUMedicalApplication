package com.example.ttokttok_v20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_select.*

class SelectActivity : AppCompatActivity() {

    companion object {
        val PRODUCT = "product"
    }

    private lateinit  var adapter: SelectAdapter
    private var firestore : FirebaseFirestore? = null

    private var storage: FirebaseStorage? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        // 선택한 브랜드 이름을 가진 제품들만 DB에서 가져오기
        getProduct()

        rec_select.layoutManager = LinearLayoutManager(this)

        adapter = SelectAdapter(this) {
            var select = "${it.name}"
            val selectIntent = Intent(this, ProductActivity::class.java)
            selectIntent.putExtra(PRODUCT, select)
            startActivity(selectIntent)
        }
        rec_select.adapter = adapter
    }

    private fun getProduct() {
        //progressBar5.visibility = View.VISIBLE
        var select = intent.getStringExtra(BrandActivity.BRAND)

        firestore = FirebaseFirestore.getInstance()
        firestore?.collection("Product")?.whereEqualTo("brandName", select)
            ?.get()?.addOnSuccessListener { task ->
                // 성공
                for (dc in task.documents) {
                    var detailDTO = dc.toObject(Detail::class.java)
                    var selectDTO = Select(detailDTO?.productName, "")
                    adapter.addItem(selectDTO)
                }
                runOnUiThread {
                    //progressBar5.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                }
                Log.d("Selecti", "Success.")
            }?.addOnFailureListener {
                Log.d("Selecti", "Fail.")
            }
    }
}
