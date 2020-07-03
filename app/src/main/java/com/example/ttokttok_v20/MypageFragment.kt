package com.example.ttokttok_v20


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.fragment_mypage.*

/**
 * A simple [Fragment] subclass.
 */
class MypageFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_mypage, container, false)

        return view
    }

    private var auth : FirebaseAuth? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        //닉네임 불러오기
        val user = FirebaseAuth.getInstance().currentUser
        ed_mypageNickname.setText(user?.displayName.toString())

        btn_mypageUpdate.setOnClickListener {
            val name = ed_mypageNickname.text.toString()

            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build()

            user?.updateProfile(profileUpdates)
                ?.addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("UPDATE", "User profile updated.")
                        Log.d("UPDATE", user?.displayName.toString())
                    }
                }

        }

    }


}
