package com.example.cryptoapp.common

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.example.cryptoapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_fragment.*

class SampleBottomSheet : BottomSheetDialogFragment() {

    private var listener: ListenerBottom? = null

    override fun setupDialog(dialog: Dialog, style: Int) {
        val contentView =
            View.inflate(context, R.layout.dialog_fragment, null)
        dialog.setContentView(contentView)
        (contentView.parent as View).setBackgroundColor(
            ContextCompat.getColor(
                contentView.context,
                android.R.color.transparent
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.dialog_fragment, container, false)

        view.findViewById<Button>(R.id.btn_cancel).setOnClickListener { dialog?.cancel() }
        view.findViewById<Button>(R.id.btn_ok).setOnClickListener {
            try{
                val count = view.findViewById<AppCompatEditText>(R.id.edit_text_count_coins).text
                    .toString().trim().toInt()
            }catch (e : Exception){

            }
            listener?.positiveClick(count = count)
            dialog?.cancel()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? ListenerBottom
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface ListenerBottom {
        fun positiveClick(count: Int)
    }
}