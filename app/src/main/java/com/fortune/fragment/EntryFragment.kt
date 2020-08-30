package com.fortune.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.fortune.R

class EntryFragment : Fragment() {

    /**
     * EntryFragment描画
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_entry, container, false)

        // ボタン押下でFortuneResultFragmentに移動
        view?.findViewById<Button>(R.id.button_telling_fortune)?.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.addToBackStack(null)
            transaction?.replace(R.id.container, FortuneResultFragment())
            transaction?.commit()
        }
        return view
    }
}