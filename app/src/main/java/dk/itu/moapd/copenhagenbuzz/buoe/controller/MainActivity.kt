package dk.itu.moapd.copenhagenbuzz.buoe.controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.WindowCompat
import com.google.android.material.snackbar.Snackbar
import dk.itu.moapd.copenhagenbuzz.buoe.R
import dk.itu.moapd.copenhagenbuzz.buoe.databinding.ActivityMainBinding
import dk.itu.moapd.copenhagenbuzz.buoe.model.Event

/**
 * The main activity of the application.
 */
class MainActivity : AppCompatActivity() {

    // TODO: change to ContentMainBinding (?)
    private lateinit var contentBinding: ActivityMainBinding

    companion object {
        /**
         * A set of private constants used in this class.
         */
        private val TAG = MainActivity::class.qualifiedName
    }

    // An instance of the Event class
    private var event: Event = Event() // Initialize with empty strings

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        contentBinding = ActivityMainBinding.inflate(layoutInflater)

        /**
         * The root view of the activity.
         */
        val view = contentBinding.root

        setContentView(view)

        val getIsLoggedIn = intent.getBooleanExtra("isLoggedIn", false)

        if(getIsLoggedIn){
            contentBinding.toolAppBar.setNavigationIcon(R.drawable.baseline_arrow_circle_right_24)
            showMessage(view, "Logged in as USER")
            contentBinding.toolAppBar.setNavigationOnClickListener {
                showMessage(view, "Logging out...")
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        } else {
            contentBinding.toolAppBar.setNavigationIcon(R.drawable.baseline_add_24)
            showMessage(view, "Logged in as Guest")
        }

        // Listener for user interaction in the "Add Event" button
        contentBinding.fabAddEvent.setOnClickListener {
            // Only execute the following code when the user fills all EditText
            if (contentBinding.editTextEventName.text.toString().isNotEmpty() &&
                contentBinding.editTextEventLocation.text.toString().toString().isNotEmpty()) {

                // Update the object attributes.
                event = event.copy(eventName = contentBinding.editTextEventName.text.toString())
                event = event.copy(eventLocation = contentBinding.editTextEventLocation.text.toString())
            }

            showMessage(view)
        }
    }

    /**
     * Write in the Logcat system.
     */
    private fun showMessage(view: CoordinatorLayout) {
        Log.d(TAG, event.toString())
        Snackbar.make(view, event.toString(), Snackbar.LENGTH_SHORT)
            .show()
    }

    /**
     * Write custom message as SnackBar.
     */
    private fun showMessage(view: CoordinatorLayout, msg: String) {
        Log.d(TAG, event.toString())
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
            .show()
    }
} // The missing closing brace is added here