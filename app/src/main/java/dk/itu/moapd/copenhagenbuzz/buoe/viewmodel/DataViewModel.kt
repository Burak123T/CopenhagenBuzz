package dk.itu.moapd.copenhagenbuzz.buoe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.javafaker.Faker
import dk.itu.moapd.copenhagenbuzz.buoe.model.Event
import kotlinx.coroutines.launch

class DataViewModel : ViewModel() {
    private val listMutableLiveData = MutableLiveData<List<Event>>(emptyList())
    val events: LiveData<List<Event>> = listMutableLiveData
    init {
        mockEventsAsync()
    }

    private fun mockEventsAsync() {
        viewModelScope.launch { // launch the computational heavy operation on another thread
            listMutableLiveData.value = generateMockEvents(5)
        }
    }

    private fun generateMockEvents(amount : Int): List<Event> {
        val faker = Faker()
        return List(amount) {
            Event(
                eventName = faker.rockBand().name(),
                eventLocation = faker.address().fullAddress(),
                eventDate = faker.date().birthday().toString(),
                eventType = faker.gameOfThrones().character(),
                description = faker.lorem().paragraph(),
                photoUrl = faker.internet().image(),
                isFavorite = faker.bool().bool()
            )
        }
    }
}