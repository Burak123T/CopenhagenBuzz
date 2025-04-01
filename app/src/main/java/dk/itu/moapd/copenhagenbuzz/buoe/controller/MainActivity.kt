package dk.itu.moapd.copenhagenbuzz.buoe.controller

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import dk.itu.moapd.copenhagenbuzz.buoe.R
import dk.itu.moapd.copenhagenbuzz.buoe.databinding.ContentMainBinding
import dk.itu.moapd.copenhagenbuzz.buoe.model.Event


/**
 * The main activity of the application.
 */
class MainActivity : AppCompatActivity(), AddNewEventDialog.AddEventDialogListener {

    // TODO: change to ContentMainBinding (?)
    private lateinit var contentBinding: ContentMainBinding

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

        contentBinding = ContentMainBinding.inflate(layoutInflater)
        setContentView(contentBinding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container_view) as NavHostFragment

        val navController = navHostFragment.navController

        // Setup bottom navigation bar
        contentBinding.bottomNavigation.setupWithNavController(navController)

        /**
         * The root view of the activity.
         */
        val view = contentBinding.root

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
            contentBinding.toolAppBar.setNavigationOnClickListener {
                showMessage(view, "Logging out (guest)...")
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        // Listener for user interaction in the "Add Event" button
        contentBinding.fabAddEvent.setOnClickListener {
            val dialog = AddNewEventDialog()
            dialog.show(supportFragmentManager, "AddNewEventDialog")
        }
    }

    override fun onEventAdded(event: Event) {
        showMessage(contentBinding.root, "Event added successfully")
    }

    /**
     * Write custom message as SnackBar.
     */
    private fun showMessage(view: CoordinatorLayout, msg: String) {
        Log.d(TAG, event.toString())
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
            .show()
    }
}