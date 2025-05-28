package dk.itu.moapd.copenhagenbuzz.buoe.viewmodel

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import dk.itu.moapd.copenhagenbuzz.buoe.databinding.FavoriteRowItemBinding
import dk.itu.moapd.copenhagenbuzz.buoe.model.Event

class FavoriteAdapter(options: FirebaseRecyclerOptions<Event>
) : FirebaseRecyclerAdapter<Event, FavoriteAdapter.ViewHolder>(options) {

    inner class ViewHolder(private val binding: FavoriteRowItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(e: Event){
            binding.textViewEventName.text = e.eventName
        }
        }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        return ViewHolder(FavoriteRowItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }
    override fun onBindViewHolder(holder: FavoriteAdapter.ViewHolder, position: Int, model: Event) {
        holder.bind(model)
    }
}
