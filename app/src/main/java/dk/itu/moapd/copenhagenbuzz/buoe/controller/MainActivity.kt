package dk.itu.moapd.copenhagenbuzz.buoe.controller

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.WindowCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import dk.itu.moapd.copenhagenbuzz.buoe.R
import dk.itu.moapd.copenhagenbuzz.buoe.databinding.ContentMainBinding
import dk.itu.moapd.copenhagenbuzz.buoe.model.Event


/**
 * The main activity of the application.
 * Acts as the main activity, where the user can login.
 * Upon logging in (USER or GUEST), the user can add new events, and view the timeline, calendar, and maps.
 * The Timeline Fragment will be the default fragment to be shown.
 *
 * @property contentBinding
 *
 */
class MainActivity : AppCompatActivity(), AddNewEventDialog.AddEventDialogListener {

    /**
     * The binding for the content of the activity.
     */
    private lateinit var contentBinding: ContentMainBinding

    /**
     * Get the Firebase authentication instance.
     */
    private lateinit var getFBAuthentication: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        /**
         * Inflate the layout using view binding.
         */
        contentBinding = ContentMainBinding.inflate(layoutInflater)

        setContentView(contentBinding.root)

        getFBAuthentication = FirebaseAuth.getInstance()

        /**
         * Initialize navigation controller for bottom navigation bar.
         */
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container_view) as NavHostFragment

        /**
         * Initialize the navigation controller.
         */
        val navController = navHostFragment.navController

        // Setup bottom navigation bar
        contentBinding.bottomNavigation.setupWithNavController(navController)

        /**
         * The root view of the activity.
         */
        val view = contentBinding.root

        /**
         * Check if the user is logged in.
         */
        val getIsLoggedIn = intent.getBooleanExtra("isLoggedIn", false)

        if(getIsLoggedIn){
            contentBinding.toolAppBar.setNavigationIcon(R.drawable.baseline_arrow_circle_right_24)
            showMessage(view, "Logged in as USER")
            contentBinding.toolAppBar.setNavigationOnClickListener {
                showMessage(view, "Logging out...")
                getFBAuthentication.signOut()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        } else {
            contentBinding.toolAppBar.setNavigationIcon(R.drawable.outline_photo_camera_front_24)
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
            if(getFBAuthentication.currentUser == null){
                showMessage(view, "Log in to add an event")
            } else {
                val dialog = AddNewEventDialog()
                dialog.show(supportFragmentManager, "AddNewEventDialog")
            }
        }
    }

    /**
     * Overridden callback function for when an event is added.
     *
     * @param event The event that was added.
     *
     * @return Unit
     */
    override fun onEventAdded(event: Event) {
        showMessage(contentBinding.root, "Event added successfully")
    }

    /**
     * Write custom message as SnackBar.
     *
     * @param view The root view of the activity.
     * @param msg The message to be displayed.
     *
     * @return Unit
     */
    private fun showMessage(view: CoordinatorLayout, msg: String) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
            .show()
    }
}