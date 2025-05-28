package dk.itu.moapd.copenhagenbuzz.buoe.model
data class Event(
    val eventName: String = "",
    val eventLocation: String = "",
    val eventStartDate: Long? = null,
    val eventEndDate: Long? = null,
    val eventType: String = "",
    val description: String = "",
    val photoUrl: String = "",
    val userId: String = "",
    val isFavorite: Boolean = false
) {
    constructor() : this(null.toString(), null.toString(), null, null,
        null.toString(), null.toString(), null.toString(), null.toString(), false
    )
}

