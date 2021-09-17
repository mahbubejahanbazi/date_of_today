## A Simple Calendar & Clock

The application shows the current date and time and updates them automatically

The date is displayed in "EEEE dd MMMM" format and the data will update at midnight.

## Tech Stack

Kotlin

<!-- ![Logo](https://github.com/peymanjahanbazi/DateOfToday/blob/main/images/screenshot2.jpg)  -->

<p align="center">
  <img src="https://github.com/mahbubejahanbazi/date_of_today/blob/main/images/screenshot2.jpg" />
</p>


## Source code

MainActivity.kt
```kotlin
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
}

```

activity_main.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/date"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:gravity="center|bottom"
        android:paddingTop="4dp"
        android:textAlignment="gravity"
        android:textColor="@color/date"
        android:textSize="@dimen/date_size"
        app:layout_constraintHeight_percent="0.35"
        app:layout_constraintTop_toTopOf="parent" />

    <DigitalClock
        android:id="@+id/time"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_marginBottom="7dp"
        android:fontFamily="@font/calibri_regular"
        android:textAlignment="center"
        android:textColor="@color/time"
        android:textSize="@dimen/time_size"
        android:textStyle="bold"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintHeight_percent="0.65"
        app:layout_constraintTop_toBottomOf="@+id/date" />


</androidx.constraintlayout.widget.ConstraintLayout>
```
## Contact


mjahanbazi@protonmail.com