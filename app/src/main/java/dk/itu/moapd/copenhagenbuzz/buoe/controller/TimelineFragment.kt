package dk.itu.moapd.copenhagenbuzz.buoe.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.javafaker.Faker
import dk.itu.moapd.copenhagenbuzz.buoe.R
import dk.itu.moapd.copenhagenbuzz.buoe.databinding.FragmentTimelineBinding
import dk.itu.moapd.copenhagenbuzz.buoe.model.Event
import dk.itu.moapd.copenhagenbuzz.buoe.viewmodel.DataViewModel
import dk.itu.moapd.copenhagenbuzz.buoe.viewmodel.EventAdapter
import java.util.Random

/**
 * A simple [Fragment] subclass.
 * Use the [TimelineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimelineFragment : Fragment() {

    private val dataViewModel: DataViewModel by activityViewModels()
    private lateinit var fragmentTimelineBinding: FragmentTimelineBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTimelineBinding = FragmentTimelineBinding.inflate(inflater, container, false)
        return fragmentTimelineBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Initialize the ListView and Adapter
        val adapter = EventAdapter(requireContext(),  mutableListOf<Event>())
        fragmentTimelineBinding.listView.adapter = adapter

        dataViewModel.events.observe(viewLifecycleOwner) { events ->
            adapter.clear()
            adapter.addAll(events)
        }
    }
}