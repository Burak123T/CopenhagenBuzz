package dk.itu.moapd.copenhagenbuzz.buoe.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.firebase.ui.database.FirebaseListAdapter
import com.firebase.ui.database.FirebaseListOptions
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import dk.itu.moapd.copenhagenbuzz.buoe.R
import dk.itu.moapd.copenhagenbuzz.buoe.model.Event
import java.util.Date

class EventAdapter(
    options: FirebaseListOptions<Event>,
) : FirebaseListAdapter<Event>(options){

    /**
     * Get the Firebase authentication instance.
     */
    private lateinit var getFBAuthentication: FirebaseAuth

    override fun populateView(view: View, model: Event, position: Int) {
        val titleTextView: TextView = view.findViewById(R.id.eventNameTextView)
        titleTextView.text = model.eventName
        val descriptionTextView: TextView = view.findViewById(R.id.eventDescriptionTextView)
        descriptionTextView.text = model.description
        val dateTextView: TextView = view.findViewById(R.id.eventDateTextView)
        dateTextView.text = model.eventStartDate.toString()
        val imageView: ImageView = view.findViewById(R.id.imageView)
        Picasso.get().load(model.photoUrl).into(imageView)
        val isFavorite: ImageView = view.findViewById(R.id.save_event_icon)
        if(getFBAuthentication.currentUser?.uid != model.userId){
            isFavorite.visibility = View.GONE
        } else {
            isFavorite.visibility = View.VISIBLE
            if (model.isFavorite) {
                isFavorite.setImageResource(R.drawable.baseline_add_circle_24)
            } else {
                isFavorite.setImageResource(R.drawable.outline_add_circle_outline_24)
            }
        }
    }

}