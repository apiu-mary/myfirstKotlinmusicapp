package com.example.myfirstmusicproject

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.myfirstmusicproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var music: MediaPlayer
    lateinit var imageView: ImageView
    lateinit var set: AnimatorSet
    lateinit var ivplaybutton: ImageView
    var isPlaying = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.ivimage)
        ivplaybutton = findViewById(R.id.ivplaybutton)
        val test1 = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
        test1.duration = 1000
        val test2 = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
        test1.duration = 500
        test1.repeatCount = 1000
        test2.repeatCount = 500
        test1.duration = 1000
        test2.duration = 1000
        set = AnimatorSet()
        set.playSequentially(test1, test2)
        music = MediaPlayer.create(this, R.raw.jay_melody_nakupenda_official_lyrics_video_mp3_67143)
        ivplaybutton.setOnClickListener { playmusic() }
    }

    fun playmusic() {
        if (isPlaying == false) {
            music.start()
            set.start()
            ivplaybutton.setImageResource(R.drawable.baseline_pause_24)
            isPlaying = true
        } else {
            music.pause()
            set.pause()
            ivplaybutton.setImageResource(R.drawable.baseline_play_arrow_24)
            isPlaying=false

        }
    }
}