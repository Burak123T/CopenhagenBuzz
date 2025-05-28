package dk.itu.moapd.copenhagenbuzz.buoe.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dk.itu.moapd.copenhagenbuzz.buoe.databinding.FragmentTimelineBinding
import dk.itu.moapd.copenhagenbuzz.buoe.viewmodel.DataViewModel
import dk.itu.moapd.copenhagenbuzz.buoe.viewmodel.EventAdapter  // varsa adapter'ı doğru yerden import et


class TimelineFragment : Fragment() {

    private val vm: DataViewModel by activityViewModels()
    private lateinit var binding: FragmentTimelineBinding
    private lateinit var adapter: EventAdapter   // <-- TANIMI EKLE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentTimelineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = EventAdapter(requireContext(), mutableListOf())
        binding.listView.adapter = adapter          // listView id’si XML’de

        vm.events.observe(viewLifecycleOwner) { events ->
            adapter.update(events)
        }
    }
}
