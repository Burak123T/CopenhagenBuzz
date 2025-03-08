package dk.itu.moapd.copenhagenbuzz.buoe.controller

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

import com.google.android.material.snackbar.Snackbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

import dk.itu.moapd.copenhagenbuzz.buoe.model.Event
import dk.itu.moapd.copenhagenbuzz.buoe.databinding.ActivityMainBinding
import dk.itu.moapd.copenhagenbuzz.buoe.databinding.ContentMainBinding

/**
 * The main activity of the application.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var contentBinding: ContentMainBinding

    /**
     * The root view of the activity.
     */
    val view = contentBinding.root

    companion object {
        /**
         * A set of private constants used in this class.
         */
        private val TAG = MainActivity::class.qualifiedName
    }

    // GUI variables.
    private lateinit var eventName: EditText
    private lateinit var eventLocation: EditText
    private lateinit var addEventButton: FloatingActionButton

    // TODO: Implement the missing CO1 variables

    // An instance of the Event class
    private var event: Event = Event() // Initialize with empty strings

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        contentBinding = ContentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // IMPORTANT:
        // This is an awful implementation. I only implemented it like that because the students need to learn more about Moshi.
        // This implementation is quite similar to a Java code. We will refactor this code in the next exercise session.

        // Link the UI components with the Kotlin source code.
        // TODO: turn into binding

        // Listener for user interaction in the "Add Event" button
        addEventButton.setOnClickListener {
            // Only execute the following code when the user fills all EditText
            if (contentBinding.editTextEventName.text.toString().isNotEmpty() &&
                contentBinding.editTextEventLocation.text.toString().toString().isNotEmpty()) {

                // Update the object attributes.
                event = event.copy(eventName = contentBinding.editTextEventName.text.toString())
                event = event.copy(eventLocation = contentBinding.editTextEventLocation.text.toString())

                showMessage()
            }
        }
    }

    /**
     * Write in the Logcat system.
     */
    private fun showMessage() {
        Log.d(TAG, event.toString())
        Snackbar.make(view, event.toString(), Snackbar.LENGTH_SHORT)
            .show()
    }
} // The missing closing brace is added here