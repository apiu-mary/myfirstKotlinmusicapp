//package com.example.myfirstmusicproject
//
//import android.animation.AnimatorSet
//import android.animation.ObjectAnimator
//import android.media.MediaPlayer
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.ImageView
//import com.example.myfirstmusicproject.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//
//    lateinit var music: MediaPlayer
//    lateinit var imageView: ImageView
//    lateinit var set: AnimatorSet
//    lateinit var ivplaybutton: ImageView
//    var isPlaying = false
//    val musicFiles = listOf(
//        R.raw.jay_melody_nakupenda_official_lyrics_video_mp3_67143,
//        R.raw.christianaudio,
//        R.raw.yicthiek
//    )
//    var currentSongIndex=0
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContentView(R.layout.activity_main)
//        imageView = findViewById(R.id.ivimage)
//        ivplaybutton = findViewById(R.id.ivplaybutton)
//        val test1 = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
//        test1.duration = 1000
//        val test2 = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
//        test1.duration = 500
//        test1.repeatCount = 1000
//        test2.repeatCount = 1000
//        test1.duration = 1000
//        test2.duration = 1000
//        set = AnimatorSet()
//        set.playSequentially(test1, test2)
//        music = MediaPlayer.create(this, R.raw.jay_melody_nakupenda_official_lyrics_video_mp3_67143)
//        ivplaybutton.setOnClickListener { playmusic() }
//        ivplaybutton.setOnClickListener { switchTonextSong() }
//        ivplaybutton.setOnClickListener {swithtoPreviouSong () }
//
//
//    }
//
//    fun playmusic() {
//        if (isPlaying == false) {
//            music.start()
//            set.start()
//            ivplaybutton.setImageResource(R.drawable.baseline_pause_24)
//            isPlaying = true
//        } else {
//            music.pause()
//            set.pause()
//            ivplaybutton.setImageResource(R.drawable.baseline_play_arrow_24)
//            isPlaying = false
//
//        }
//        fun switchTonextSong(){
//            currentSongIndex=(currentSongIndex+1)%musicFiles.size
//            if (isPlaying){
//                playmusic()
//            }
//
//            fun swithtoPreviouSong()=(currentSongIndex-1+musicFiles.size)%musicFiles.size
//            if (isPlaying){
//                playmusic()
//            }
//        }
//    }
//}
//
package com.example.myfirstmusicproject

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AbsSeekBar
import android.widget.ImageView
import android.widget.SeekBar
import com.example.myfirstmusicproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var ivprevious:ImageView
    lateinit var ivnext:ImageView
    lateinit var seekBar: SeekBar

    lateinit var music: MediaPlayer
    lateinit var imageView: ImageView
    lateinit var set: AnimatorSet
    lateinit var ivplaybutton: ImageView

    var isPlaying = false
    val musicFiles = listOf(
        R.raw.jay_melody_nakupenda_official_lyrics_video_mp3_67143,
        R.raw.christianaudio,R.raw.forgiveme,R.raw.coldwater,R.raw.anitta_envolver_official_music_video_mp3_21766,R.raw.nf_lie_audio_mp3_25577,
        R.raw.yicthiek
    )
    var currentSongIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.ivimage)
        seekBar=findViewById(R.id.seekBar)
        ivplaybutton = findViewById(R.id.ivplaybutton)
        ivnext=findViewById(R.id.ivnext)
        ivprevious=findViewById(R.id.ivprevious)
        val test1 = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
        test1.duration = 1000
        val test2 = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f)
        test1.duration = 500
        test1.repeatCount = 1000
        test2.repeatCount = 1000
        test1.duration = 1000
        test2.duration = 1000
        set = AnimatorSet()
        set.playSequentially(test1, test2)
        music = MediaPlayer.create(this, R.raw.jay_melody_nakupenda_official_lyrics_video_mp3_67143)
        ivplaybutton.setOnClickListener { togglePlayback() }
        ivprevious.setOnClickListener { switchToPreviousSong() }
        ivnext.setOnClickListener { switchToNextSong() }


    }

    private fun togglePlayback() {
        if (isPlaying) {
            pauseMusic()
        } else {
            playMusic()
        }
    }

    private fun playMusic() {
        music.release() // Release the current MediaPlayer instance
        music = MediaPlayer.create(this, musicFiles[currentSongIndex])
        music.setOnCompletionListener {
            switchToNextSong()
        }
        music.start()
        set.start()
        ivplaybutton.setImageResource(R.drawable.baseline_pause_24)
        isPlaying = true
    }

    private fun pauseMusic() {
        music.pause()
        set.pause()
        ivplaybutton.setImageResource(R.drawable.baseline_play_arrow_24)
        isPlaying = false
    }

    private fun switchToNextSong() {
        currentSongIndex = (currentSongIndex + 1) % musicFiles.size
        if (isPlaying) {
            music.release()
            music=MediaPlayer.create(this,musicFiles[currentSongIndex])
            music.start()
        }
    }

    private fun switchToPreviousSong() {
        currentSongIndex = (currentSongIndex - 1 + musicFiles.size) % musicFiles.size
        if (isPlaying) {
            music.release()
            music=MediaPlayer.create(this,musicFiles[currentSongIndex])
            music.start()
        }
    }
}

