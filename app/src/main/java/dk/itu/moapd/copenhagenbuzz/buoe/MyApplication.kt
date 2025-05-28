package dk.itu.moapd.copenhagenbuzz.buoe

import android.app.Application
import com.google.android.material.color.DynamicColors

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)

        val dotenv = io.github.cdimascio.dotenv.dotenv {
            directory = "/assets"  // This tells dotenv-kotlin to look in the root of the assets folder
            filename = "env"      // The name of your file
            ignoreIfMissing = false // Ensures you get an error if it's still not found for some reason
        }
    }
}
