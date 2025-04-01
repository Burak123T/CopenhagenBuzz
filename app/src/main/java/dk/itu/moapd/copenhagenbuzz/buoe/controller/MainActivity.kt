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
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.fragment.app.Fragment


/**
 * The main activity of the application.
 */
class MainActivity : AppCompatActivity(), AddNewEventDialog.AddEventDialogListener {

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

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null

        when (item.itemId) {
            R.id.navigation_timeline -> selectedFragment = TimelineFragment()
            R.id.navigation_favorites -> selectedFragment = FavoritesFragment()
            R.id.navigation_maps -> selectedFragment = MapsFragment()
            R.id.navigation_calendar -> selectedFragment = CalendarFragment()
        }

        // Replace the current fragment with the selected one
        selectedFragment?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, it)
                .commit()
        }

        true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        contentBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(contentBinding.root)


        // Bottom navigation listener
        contentBinding.bottomNavigation.setOnNavigationItemSelectedListener(navListener)

        // Load the default fragment (Timeline)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, TimelineFragment()) // Replace with TimelineFragment initially
                .commit()
        }

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