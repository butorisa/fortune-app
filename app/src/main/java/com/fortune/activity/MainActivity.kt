package com.fortune.activity

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fortune.R
import com.fortune.api.RestApiClient
import com.fortune.bean.FortuneResultBean
import kotlinx.coroutines.*
import org.json.JSONObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Coroutine
        this.lifecycleScope.launch {
            // call API
            withContext(Dispatchers.Default) {
                val url = "http://api.jugemkey.jp/api/horoscope/free/" + getCurrentDate()
                RestApiClient().requestGet(url)
            }.let {
                // mapping json to bean
                val fortuneResult = parseJson(getCurrentDate(), it)
                // set TextView
                findViewById<TextView>(R.id.fortune_text).text =
                    fortuneResult.get(0)?.content
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDate(): String {
        val today = LocalDate.now()
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")
        return dateFormatter.format(today)
    }

    private fun parseJson(today: String, strJson: String): List<FortuneResultBean> {
        val json = JSONObject(strJson)
        // remove "\" from json-property
        val strResult = json.getJSONObject("horoscope").getString(today.replace("\\", ""))
        return ObjectMapper().readValue(strResult, object: TypeReference<List<FortuneResultBean>>(){})
    }
}