package dk.itu.moapd.copenhagenbuzz.buoe.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dk.itu.moapd.copenhagenbuzz.buoe.databinding.FragmentTimelineBinding
import dk.itu.moapd.copenhagenbuzz.buoe.model.Event
import dk.itu.moapd.copenhagenbuzz.buoe.viewmodel.DataViewModel
import dk.itu.moapd.copenhagenbuzz.buoe.viewmodel.EventAdapter

/**
 * A simple [Fragment] subclass for viewing the timeline of available events around CPH.
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