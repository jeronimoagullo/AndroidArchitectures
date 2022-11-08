package com.jeroagullo.androidarchitectures

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jeroagullo.androidarchitectures.databinding.ActivityArchitectureBinding
import com.jeroagullo.androidarchitectures.mvc.MVCActivity
import com.jeroagullo.androidarchitectures.mvp.MVPActivity
import com.jeroagullo.androidarchitectures.mvvm.MVVMActivity

class ArchitecturesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArchitectureBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArchitectureBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
        initButtons()
    }

    private fun initButtons() {
        binding.buttonMVC.setOnClickListener{
            startActivity(Intent(this, MVCActivity::class.java))
        }

        binding.buttonMVP.setOnClickListener{
            startActivity(Intent(this, MVPActivity::class.java))
        }

        binding.buttonMVVM.setOnClickListener{
            startActivity(Intent(this, MVVMActivity::class.java))
        }
    }


}