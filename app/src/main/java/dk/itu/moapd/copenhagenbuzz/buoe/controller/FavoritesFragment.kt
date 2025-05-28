package dk.itu.moapd.copenhagenbuzz.buoe.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dk.itu.moapd.copenhagenbuzz.buoe.R
import dk.itu.moapd.copenhagenbuzz.buoe.databinding.FragmentFavoritesBinding
import dk.itu.moapd.copenhagenbuzz.buoe.model.Event
import dk.itu.moapd.copenhagenbuzz.buoe.viewmodel.FavoriteAdapter

/**
 * A simple [Fragment] subclass for viewing the favorite events, as chosen by the user.
 */
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val DATABASE_URL = "https://copenhagenbuzz-dc42a-default-rtdb.europe-west1.firebasedatabase.app/"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FirebaseAuth.getInstance().currentUser?.let { user ->
            val query =
                Firebase.database(DATABASE_URL).reference
                    .child("user-favorites")
                    .child(user.uid)
                    .orderByChild("eventStartDate")

            val options = FirebaseRecyclerOptions.Builder<Event>()
                .setQuery(query, Event::class.java)
                .setLifecycleOwner(this)
                .build()
            val adapter = FavoriteAdapter(options)
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                this.adapter = adapter
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}