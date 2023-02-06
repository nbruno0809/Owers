package com.example.owers


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.owers.data.Transaction
import com.example.owers.data.User
import com.example.owers.databinding.DialogNewTransactionBinding


class NewTransactionDialog(
    private val listener: NewTransactionDialogListener,
    var users: List<User>
) : DialogFragment() {
    interface NewTransactionDialogListener {
        fun onTransactionCreated(newT: Transaction)
    }

    private lateinit var binding: DialogNewTransactionBinding
    private lateinit var spinnerPaidBy: Spinner
    private lateinit var spinnerPaidFor: Spinner

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogNewTransactionBinding.inflate(LayoutInflater.from(context))
        initSpinners()

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setPositiveButton(R.string.button_ok) { dialogInterface, i ->
                val uBy = users.find { u -> u.name == spinnerPaidBy.selectedItem.toString() }
                val uFor = users.find { u -> u.name == spinnerPaidFor.selectedItem.toString() }

                if (binding.etAmount.text.isNotEmpty() && uBy != null && uFor != null) {
                    val newT = Transaction(
                        null, uBy.id!!, uFor.id!!, binding.etAmount.text.toString().toInt()
                    )
                    TransactionCreator.createNewTransaction(newT)
                    listener.onTransactionCreated(newT)
                }
            }
            .setNegativeButton(R.string.button_cancel, null)
            .create()
    }

    private fun initSpinners() {
        spinnerPaidBy = binding.sPaidBy
        spinnerPaidFor = binding.sPaidFor
        val userNames: MutableList<String> = mutableListOf()
        for (u in users) {
            userNames.add(u.name)
        }
        val dataAdapter: ArrayAdapter<String> =
            ArrayAdapter<String>(requireContext(), R.layout.spinner_item, userNames)
        dataAdapter.setDropDownViewResource(R.layout.spinner_item)
        spinnerPaidFor.adapter = dataAdapter
        spinnerPaidBy.adapter = dataAdapter
    }

    companion object {
        const val TAG = "NewTransactionDialogFragment"
    }


}
