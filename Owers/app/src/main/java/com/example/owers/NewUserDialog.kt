package com.example.owers


import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.owers.data.User
import com.example.owers.databinding.DialogNewUserBinding

class NewUserDialog : DialogFragment() {
    interface NewUserDialogListener {
        fun onUserCreated(newUser: User)
    }

    private lateinit var listener: NewUserDialogListener
    private lateinit var binding: DialogNewUserBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewUserDialogListener
            ?: throw RuntimeException("Activity must implement the interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogNewUserBinding.inflate(LayoutInflater.from(context))

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .setPositiveButton(R.string.button_ok) { dialogInterface, i ->
                if (binding.etName.text.isNotEmpty()) {
                    listener.onUserCreated(User(null, binding.etName.text.toString(), 0))
                }
            }
            .setNegativeButton(R.string.button_cancel, null)
            .create()
    }

    companion object {
        const val TAG = "NewUserDialogFragment"
    }

}
