package com.example.owers


import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.owers.data.Transaction


class SettleDebtDialog(val transaction: Transaction) :
    DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(requireContext())
            .setView(parentFragment?.view)
            .setMessage(R.string.settle)
            .setPositiveButton(R.string.yes) { dialogInterface, i ->

            }
            .setNegativeButton(R.string.button_cancel, null)
            .create()
    }

    companion object {
        val TAG = "SettleDebtDialog"
    }

}
