package com.example.ttokttok_v20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_join.*
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE

class JoinActivity : AppCompatActivity() {

    private var auth : FirebaseAuth? = null

    var emailDupulicateFlag : Boolean = FALSE
    var nameDuplicateFlag : Boolean = FALSE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        auth = FirebaseAuth.getInstance()

        // 자동으로 익명 로그인 -> 중복확인 절차 처리
        signInAnonymously()

        // 가입 버튼
        btn_CompleteJoin.setOnClickListener {
            createAndLoginEmail()
        }

        // 이메일 중복확인 버튼
        btn_checkDuplicateEmail.setOnClickListener {
            duplicateEmail()
        }

        // 닉네임 중복확인 버튼
        btn_checkDuplicateNickname.setOnClickListener {
            duplicateNickname()
        }
    }

    private fun signInAnonymously() {
        auth?.signInAnonymously()
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // 자동 익명 로그인 성공
                    Toast.makeText(this, "익명 로그인 성공", Toast.LENGTH_SHORT).show()
                } else {
                    // 자동 익명 로그인 실패
                    Toast.makeText(this, "익명 로그인 실패", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun createAndLoginEmail() {
        var email = ed_imputEmail.text.toString()
        var pw = ed_inputPassword.text.toString()
        var pwCheck = ed_inputPasswordCheck.text.toString()
        var name = ed_inputNickname.text.toString()

        // 이메일, 비밀번호, 비밀번호 확인, 닉네임 중 공란이 있을 경우
        if (email.isNullOrEmpty() || pw.isNullOrEmpty() || pwCheck.isNullOrEmpty() || name.isNullOrEmpty()) {
            Toast.makeText(this, "공란 없이 입력", Toast.LENGTH_SHORT).show()

            // 비밀번호와 비밀번호 확인 불일치
        } else if (pw != pwCheck) {
            Toast.makeText(this, "비밀번호와 비밀번호 확인 불일치", Toast.LENGTH_SHORT).show()

            // 이메일 중복확인 버튼 안 눌렀을 경우
        } else if (emailDupulicateFlag == FALSE) {
            Toast.makeText(this, "이메일 중복확인 버튼 클릭", Toast.LENGTH_SHORT).show()

            // 닉네임 중복확인 버튼 안 눌렀을 경우
        } else if (nameDuplicateFlag == FALSE) {
            Toast.makeText(this, "닉네임 중복확인 버튼 클릭", Toast.LENGTH_SHORT).show()

            // 모든 조건 충족
        } else {
            progressBar2.visibility = View.VISIBLE
            auth?.createUserWithEmailAndPassword(email, pw)
                ?.addOnCompleteListener { task ->
                    progressBar2.visibility = View.GONE
                    if (task.isSuccessful) {
                        // 회원가입 성공
                        val user = FirebaseAuth.getInstance().currentUser
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(name) // 가입시 입력한 닉네임으로 프로필 업데이트
                            .build()

                        user?.updateProfile(profileUpdates)
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d("UPDATE", "User profile updated.")
                                    Log.d("UPDATE", user?.displayName.toString())
                                }
                            }
                        Log.d("SIGN", "Sign up success.")
                        Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                        auth?.signOut() // 익명으로 되어 있던 로그인 -> 로그아웃
                        finish()

                    } else {
                        // 회원가입 실패
                        Log.d("SIGN", task.exception?.message)
                        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }

    private fun duplicateEmail() {
        val userAll = FirebaseAuth.getInstance().currentUser

        // 가입된 모든 사용자 정보 중 이메일만 가져와서 비교
        if (userAll != null) {
            userAll.providerData.forEach { profile ->
                if (profile.email == ed_imputEmail.text.toString()) {
                    Toast.makeText(this, "이미 사용 중인 이메일", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "사용 가능한 이메일", Toast.LENGTH_SHORT).show()
                    emailDupulicateFlag = TRUE
                }
            }
        }
    }

    private fun duplicateNickname() {
        val userAll = FirebaseAuth.getInstance().currentUser

        // 가입된 모든 사용자 정보 중 닉네임만 가져와서 비교
        if (userAll != null) {
            userAll.providerData.forEach { profile ->
                if (profile.displayName == ed_inputNickname.text.toString()) {
                    Toast.makeText(this, "이미 사용 중인 닉네임", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "사용 가능한 닉네임", Toast.LENGTH_SHORT).show()
                    nameDuplicateFlag = TRUE
                }
            }
        }
    }
}
