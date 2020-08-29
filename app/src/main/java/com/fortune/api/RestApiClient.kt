package com.fortune.api

import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

/**
 * REST API呼び出し
 */
class RestApiClient {

    /**
     * GETリクエスト
     * @param APIリクエストURL
     * @param リクエストパラメータ
     * @return APIレスポンス（String）
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    fun requestGet(url: String, vararg param: String?): String {
        val request = Request.Builder().url(url!!).build()
        val httpClient = OkHttpClient()
        try {
            val response = httpClient.newCall(request).execute()
            return response.body!!.string()

        } catch (e: IOException) {
            // TODO handle exception
            e.printStackTrace()
        }
        // TODO Handle Exception
        throw NullPointerException()
    }

    companion object {
        private val JSON: MediaType = "application/json; charset=utf-8".toMediaTypeOrNull() as MediaType
    }
}