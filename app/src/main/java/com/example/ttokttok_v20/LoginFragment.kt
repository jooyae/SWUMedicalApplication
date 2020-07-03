package com.example.ttokttok_v20


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        return view
    }

    private var auth : FirebaseAuth? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        // '로그인' 버튼 클릭 -> 성공시 MainActivity로 돌아감
        btn_login.setOnClickListener {
            login()
        }

        // '회원가입' 버튼 클릭 -> JoinActivity로 이동
        btn_join.setOnClickListener {
            val joinIntent = Intent(context, JoinActivity::class.java)
            startActivity(joinIntent)
        }
    }

    private fun login() {
        if (ed_id.text.toString().isNullOrEmpty() || ed_password.text.toString().isNullOrEmpty()) {
            Toast.makeText(context, "이메일과 비밀번호를 입력해주세요", Toast.LENGTH_LONG).show()
            return
        }

        var email = ed_id.text.toString()
        var password = ed_password.text.toString()
        progressBar.visibility = View.VISIBLE
        auth?.signInWithEmailAndPassword(email, password)
            ?.addOnCompleteListener { task ->
                progressBar.visibility = View.GONE
                if (task.isSuccessful) {
                    Toast.makeText(context, "로그인 성공", Toast.LENGTH_LONG).show()
                    moveMainPage(auth?.currentUser) // 메인페이지로 이동
                } else {
                    Toast.makeText(context, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun moveMainPage(user: FirebaseUser?) {
        if (user != null) {
            startActivity(Intent(context, MainActivity::class.java))
            return
        }
    }


}
