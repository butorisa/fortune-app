package com.fortune.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fortune.R
import com.fortune.api.RestApiClient
import com.fortune.bean.FortuneResultBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FortuneResultFragment: Fragment() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_fortune_result, container, false)

        // Coroutine
        this.lifecycleScope.launch {
            // call API
            withContext(Dispatchers.Default) {
                val url = "http://api.jugemkey.jp/api/horoscope/free/" + getCurrentDate()
                RestApiClient().requestGet(url)
            }.let {
                delay(2000)
                // mapping json to bean
                val fortuneResult = parseJson(getCurrentDate(), it)
                // set TextView
                view?.findViewById<TextView>(R.id.fortune_content)?.text =
                    fortuneResult.get((0..11).random())?.content // TODO ランダムな星座を表示

                // set clickListener
                view.findViewById<Button>(R.id.button_back_to_entry).setOnClickListener {
                    fragmentManager?.popBackStack()
                }
            }
        }
        return view
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