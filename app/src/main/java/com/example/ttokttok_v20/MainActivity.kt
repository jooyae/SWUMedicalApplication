package com.example.ttokttok_v20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.w3c.dom.Element
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.xml.parsers.DocumentBuilderFactory

class MainActivity : AppCompatActivity() {

    private var firestore : FirebaseFirestore? = null

    var detailList = ArrayList<Detail>()

    //drawer
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 공공 API 호출
        callAPI()

        //drawer
        navController = Navigation.findNavController(this, R.id.nav_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph, drawer_layout)

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        navigation_view.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun callAPI() {
        //progressBar6.visibility = View.VISIBLE
        val request = getRequestUrl()
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {

                val body = response.body?.string()?.byteInputStream()
                val buildFactory = DocumentBuilderFactory.newInstance()
                val docBuilder = buildFactory.newDocumentBuilder()
                val doc = docBuilder.parse(body, null)
                val nList = doc.getElementsByTagName("row")


                for (n in 0 until nList.length) {
                    val element = nList.item(n) as Element
                    val detailDTO = Detail( // API로부터 데이터 가져오기
                        getValueFromKey(element, "BSSH_NM"),
                        getValueFromKey(element, "PRDLST_NM"),
                        getValueFromKey(element, "PRIMARY_FNCLTY"),
                        getValueFromKey(element, "IFTKN_ATNT_MATR_CN"),
                        getValueFromKey(element, "STDR_STND"),
                        getValueFromKey(element, "RAWMTRL_NM")
                    )
                    // detailDTO를 담은 리스트
                    detailList.add(detailDTO)
                }
                // detailList를 DB에 추가
                //progressBar6.visibility = View.VISIBLE
                firestore = FirebaseFirestore.getInstance()
                for (n in 0 until detailList.size) {
                    val document = detailList[n].productName
                    firestore?.collection("Product")?.document(document!!)
                        ?.set(detailList[n])?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // 성공
                                //progressBar6.visibility = View.GONE
                            } else {
                                // 실패
                            }
                        }
                }

                runOnUiThread {
                    // 검색창 입력한 부분
                   // progressBar6.visibility = View.GONE
                    Log.d("ITEM", "Success.")
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                val body = e.message
                runOnUiThread {
                   // progressBar6.visibility = View.GONE
                    Log.d("ITEM", "fail: $body")
                }
            }
        })
    }

    private fun getRequestUrl() : Request {

        val keyId = "3a597790ea664c9f8361"
        val serviceId = "C003"
        val dataType = "xml"
        val startIdx = "1"
        val endIdx = "5"

        var url = "http://openapi.foodsafetykorea.go.kr/api/" +
                keyId + "/" +
                serviceId + "/" +
                dataType + "/" +
                startIdx + "/" +
                endIdx

        var httpUrl = url.toHttpUrlOrNull()

        return Request.Builder()
            .url(httpUrl!!)
            .build()
    }

    private fun getValueFromKey(element: Element, key: String) : String {
        return element.getElementsByTagName(key).item(0).firstChild.nodeValue
    }
}
