package dk.itu.moapd.copenhagenbuzz.buoe

import android.app.Application
import com.google.android.material.color.DynamicColors
import io.github.cdimascio.dotenv.dotenv

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)

        try {
            val dotenv = dotenv {
                filename = "env"
                ignoreIfMissing = false
            }
        } catch (e: Exception) {
            android.util.Log.e("MyApplication", "dotenv error : ${e.message}", e)

        }
    }
    }
