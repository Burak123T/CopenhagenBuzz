package dk.itu.moapd.copenhagenbuzz.buoe.controller

import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import dk.itu.moapd.copenhagenbuzz.buoe.databinding.DialogAddNewEventBinding
import dk.itu.moapd.copenhagenbuzz.buoe.model.Event

/**
 * A modular dialog fragment for adding a new event.
 */
class AddNewEventDialog: DialogFragment() {

    private lateinit var dialogBinding: DialogAddNewEventBinding

    // An instance of the Event class
    private var event: Event = Event() // Initialize with empty strings

    interface AddEventDialogListener {
        fun onEventAdded(event: Event)
    }

    private var listener: AddEventDialogListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout using view binding
        dialogBinding = DialogAddNewEventBinding.inflate(inflater, container, false)
        return dialogBinding.root // Return the root view
    }
    override fun onStart() {
        // The dialog appeared very small, so we need to adjust its size.
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listener = activity as? AddEventDialogListener

        dialogBinding.editTextEventDate.setOnClickListener {
            val datePickerBuilder = MaterialDatePicker.Builder.dateRangePicker()
            datePickerBuilder.setTitleText("Select start/end date")
            val datePicker = datePickerBuilder.build()

            datePicker.addOnPositiveButtonClickListener {
                dialogBinding.editTextEventDate.setText(datePicker.headerText)

                // Clear prev errors if they have appeared (not adding this have caused trouble before)
                dialogBinding.editTextEventDate.error = null
            }

            datePicker.show(parentFragmentManager, "DATE_PICKER")
        }

        dialogBinding.dialogButtonSave.setOnClickListener {

        // If the user has provided new event info and presses the "save" button.
        if (dialogBinding.editTextEventName.text.toString().isNotEmpty() &&
            dialogBinding.editTextEventLocation.text.toString().isNotEmpty() &&
            dialogBinding.editTextEventDate.text.toString().isNotEmpty() &&
            dialogBinding.editTextEventDescription.text.toString().isNotEmpty()) {
            // Update the new event object attributes.
            event = event.copy(eventName = dialogBinding.editTextEventName.text.toString())
            event = event.copy(eventLocation = dialogBinding.editTextEventLocation.text.toString())
            event = event.copy(eventDate = dialogBinding.editTextEventDate.text.toString())
            event = event.copy(description = dialogBinding.editTextEventDescription.text.toString())

            listener?.onEventAdded(event)
            dismiss()
        } else {
            Snackbar.make(view, "Please fill in all fields", Snackbar.LENGTH_SHORT)
                .show()
        }
    }
    }
}