package com.clearsky77.librarypractice_20210926

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageURL = "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA5MDZfMTA2%2FMDAxNjMwOTA0NjcwNTc4.1chhOoEtwNiz4DSvxnZ6ZpZMe6Csav1jXe32imo---Qg.IkgzaG9ceHks_6x9fQUMlo5AIjQLk0NoU23OI_jm2MMg.JPEG.kaiba1004%2F%25C6%25C4%25C6%25C714_%25BF%25A9%25B8%25ED%25C0%25C7_%25BB%25E7%25C5%25F5_%25BF%25AC%25B4%25EB%25B1%25E2_%25BD%25C5%25B1%25D4%25BC%25AD%25B9%25F6.JPG"
        Glide.with(this).load(imageURL).into(actionImg)

        profileImg.setOnClickListener {
            val myIntent = Intent(this, ViewPhotoActivity::class.java)
            startActivity(myIntent)
        }

//        전화 걸기 버튼
        callBtn.setOnClickListener {
            //  권한 획득 여부에 따른 행동 방안 정리.
            val pl = object : PermissionListener {
                // 권한이 허용 되었을 때. 실제 전화 걸기.
                override fun onPermissionGranted() {
                    val myUri = Uri.parse("tel:010-1919-2121")
                    val myIntent = Intent(Intent.ACTION_CALL, myUri)
                    startActivity(myIntent)
                }
                // 권한 거절 되었을 때.
                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    Toast.makeText(this@MainActivity, "권환이 거절되어 전화 연결이 불가합니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            // pl에 적힌 가이드를 근거로 실제 권한 확인
            TedPermission.create()
                .setPermissionListener(pl)
                .setPermissions(Manifest.permission.CALL_PHONE) //android의 Manifest선책하자!
                .check()


        }
    }
}