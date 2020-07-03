package com.example.ttokttok_v20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_nutrient.*

class NutrientActivity : AppCompatActivity() {

    private lateinit  var adapter: NutrientAdapter
    private var firestore : FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutrient)

        // DB에서 영양소만 가져오기
        getNutrient()

        rec_nutrient.layoutManager = LinearLayoutManager(this)
        adapter = NutrientAdapter(this) {
            val nutrientnameIntent = Intent(this, ProductActivity::class.java)
            startActivity(nutrientnameIntent)

        }
        rec_nutrient.adapter = adapter
    }

    private fun getNutrient() {
        //progressBar3.visibility = View.VISIBLE
        firestore = FirebaseFirestore.getInstance()
        firestore?.collection("Product")?.whereGreaterThanOrEqualTo("brandName", "" )
            ?.get()?.addOnSuccessListener { task ->
                // 성공
                for (dc in task.documents) {
                    var detailDTO = dc.toObject(Detail::class.java)
                    var nutrientDTO = Nutrient(detailDTO?.nutrient)
                    adapter.addItem(nutrientDTO)
                }
                runOnUiThread {
                    //progressBar3.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                }
                Log.d("Nutri", "Success.")
            }?.addOnFailureListener {
                Log.d("Nutri", "Fail.")
            }
    }

}