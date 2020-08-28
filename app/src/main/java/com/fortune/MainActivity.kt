package com.fortune

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Coroutine
        this.lifecycleScope.launch {
            // call API
            withContext(Dispatchers.Default) {
                val url = "http://api.jugemkey.jp/api/horoscope/free/2020/08/28"
                RestApiClient().requestGet(url)
            }.let {
                // mapping json to bean
                val fortuneResult =
                    ObjectMapper().readValue(
                        it,
                        HoroscopeBean::class.java
                    )
                // set TextView
                findViewById<TextView>(R.id.fortune_text).text =
                    fortuneResult.horoscope?.today?.get(0)?.content
            }
        }
    }
}