package com.onpu.onpulab7.task1

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class AlertDialog(
    private val message: String
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Incoming broadcast!")
            .setMessage(message)
            .setPositiveButton("Ok", null)
            .create()
    }

}