package ir.mjahanbazi.dateoftoday

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val date: TextView = findViewById(R.id.date)
        val pattern = "EEEE dd MMMM"
        val df: DateFormat = SimpleDateFormat(pattern)
        val today = Calendar.getInstance().time
        val strToday: String = df.format(today)
        date.text = strToday
        val threadDay = Thread(Runnable {
            while (true) {
                val today = Calendar.getInstance().time
                val strToday: String = df.format(today)
                date.post(Runnable {
                    date.text = strToday
                })
                val diffTime = diffTime() / 1000
                var sleepTime: Long
                if (diffTime < 30) {
                    sleepTime = 1
                } else {
                    sleepTime = (diffTime * 0.8).toLong()
                }
                Thread.sleep(sleepTime)
            }
        }).start()
    }
}

private fun diffTime(): Long {
    val midNight: Calendar = Calendar.getInstance()
    midNight.set(Calendar.HOUR_OF_DAY, 0)
    midNight.set(Calendar.MINUTE, 0)
    midNight.set(Calendar.SECOND, 0)
    val midNightTimeInMillis = midNight.timeInMillis
    val current: Calendar = Calendar.getInstance();
    val currentTimeInMillis = current.timeInMillis
    val diffTime = currentTimeInMillis - midNightTimeInMillis
    return diffTime
}
