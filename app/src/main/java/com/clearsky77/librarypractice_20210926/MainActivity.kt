package com.clearsky77.librarypractice_20210926

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        profileImg.setOnClickListener {
            val myIntent = Intent(this, ViewPhotoActivity::class.java)
            startActivity(myIntent)
        }

        callBtn.setOnClickListener {
//            권한 획득 여부에 따른 행동 방안 정리.
            val pl = object : PermissionListener{
                // 권한이 허용 되었을 때. 실제 전화 걸기.
                override fun onPermissionGranted() {

                }
                // 권한 거절 되었을 때.
                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(this@MainActivity, "권환이 거절되어 전화 연결이 불가합니다.", Toast.LENGTH_SHORT).show()
                }

            }


            val myUri = Uri.parse("tel:010-1919-2121")
            val myIntent = Intent(Intent.ACTION_CALL, myUri)
            startActivity(myIntent)
        }
    }
}