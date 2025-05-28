package dk.itu.moapd.copenhagenbuzz.buoe.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dk.itu.moapd.copenhagenbuzz.buoe.databinding.FavoriteRowItemBinding
import dk.itu.moapd.copenhagenbuzz.buoe.model.Event

class FavoriteAdapter(
    private var data: List<Event>
) : RecyclerView.Adapter<FavoriteAdapter.Holder>() {

    inner class Holder(val binding: FavoriteRowItemBinding)
        : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = FavoriteRowItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val event = data[position]
        holder.binding.textViewEventName.text = event.eventName
        holder.binding.textViewEventType.text = event.eventType
    }

    override fun getItemCount() = data.size

    fun update(newData: List<Event>) {
        data = newData
        notifyDataSetChanged()
    }
}
