package dk.itu.moapd.copenhagenbuzz.buoe.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseListOptions
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dk.itu.moapd.copenhagenbuzz.buoe.R
import dk.itu.moapd.copenhagenbuzz.buoe.databinding.FragmentTimelineBinding
import dk.itu.moapd.copenhagenbuzz.buoe.model.Event
import dk.itu.moapd.copenhagenbuzz.buoe.viewmodel.DataViewModel
import dk.itu.moapd.copenhagenbuzz.buoe.viewmodel.EventAdapter  // varsa adapter'ı doğru yerden import et


class TimelineFragment : Fragment() {

    private lateinit var binding: FragmentTimelineBinding
    private lateinit var adapter: EventAdapter
    private lateinit var listView: ListView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimelineBinding.inflate(inflater, container, false)
        listView = binding.listView
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseAuth.getInstance().currentUser?.let { user ->
            val query = Firebase.database("https://copenhagenbuzz-dc42a-default-rtdb.europe-west1.firebasedatabase.app/").reference
                .child("events")
                .child(user.uid)
                .orderByChild("eventStartDate")

            val options = FirebaseListOptions.Builder<Event>()
                .setQuery(query, Event::class.java)
                .setLifecycleOwner(this)
                .setLayout(R.layout.event_row_item)
                .build()

            val adapter = EventAdapter(options)

            listView.adapter = adapter

        }
    }
}
