package dk.itu.moapd.copenhagenbuzz.buoe.viewmodel

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import dk.itu.moapd.copenhagenbuzz.buoe.R
import dk.itu.moapd.copenhagenbuzz.buoe.model.Event

class EventAdapter(
    context: Context,
    data: List<Event>,
) : ArrayAdapter<Event>(context, R.layout.event_row_item, data) {

    private fun populateViewHolder(viewHolder: ViewHolder, event: Event) {
        with(viewHolder) {

            Log.d("EventAdapter", "Attempting to load image URL: ${event.photoUrl}")

            titleTextView.text = event.eventName
            descriptionTextView.text = event.description
            dateTextView.text = event.eventDate

            // Testing to see if the mock image is loaded
            Picasso.get().load(event.photoUrl).into(imageView)

        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.event_row_item, parent, false)
        val viewHolder = (view.tag as? ViewHolder) ?: ViewHolder(view)

        getItem(position)?.let { event ->
            populateViewHolder(viewHolder, event)
        }

        view.tag = viewHolder
        return view
    }

    inner class ViewHolder(view: View) {
        val titleTextView: TextView = view.findViewById(R.id.eventNameTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.eventDescriptionTextView)
        val dateTextView: TextView = view.findViewById(R.id.eventDateTextView)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }
}