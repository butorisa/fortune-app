package com.fortune.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.fortune.R
import com.fortune.fragment.EntryFragment
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    var realm: Realm

    init {
        // Realm初期化
        Realm.init(this)
        this.realm = Realm.getDefaultInstance()
    }

    /**
     * Activity描画
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // actionbar
        val toolbar = findViewById<Toolbar>(R.id.toolbar_fortune)
        toolbar.title = "pretty chipper"
        setSupportActionBar(toolbar)

        // EntryFragment描画
        val transaction = supportFragmentManager.beginTransaction()
        transaction.addToBackStack(null)
        transaction.replace(R.id.container, EntryFragment())
        transaction.commit()
    }

    fun getRealmInstance(): Realm {
        return this.realm
    }
}