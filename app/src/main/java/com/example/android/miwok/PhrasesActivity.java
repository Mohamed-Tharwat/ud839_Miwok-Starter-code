package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    MediaPlayer mMediaPlayer;
    AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phrases);

        final ArrayList<Word> words = new ArrayList<Word> ();
        words.add(new Word("Where are you going?", "minto wuksus",1,R.raw.phrase_where_are_you_going));
        words.add(new Word("What is your name?", "tinnә oyaase'nә",1,R.raw.phrase_what_is_your_name));
        words.add(new Word("My name is...", "oyaaset",1,R.raw.phrase_my_name_is));
        words.add(new Word("How are you feeling?", "michәksәs?",1,R.raw.phrase_how_are_you_feeling));
        words.add(new Word("I’m feeling good.", "kuchi achit",1,R.raw.phrase_im_feeling_good));
        words.add(new Word("Are you coming?", "әәnәs'aa?",1,R.raw.phrase_are_you_coming));
        words.add(new Word("Yes, I’m coming.", "hәә’ әәnәm",1,R.raw.phrase_yes_im_coming));
        words.add(new Word("I’m coming.", "әәnәm",1,R.raw.phrase_im_coming));
        words.add(new Word("Let’s go.", "yoowutis",1,R.raw.phrase_lets_go));
        words.add(new Word("Come here.", "әnni'nem",1,R.raw.phrase_come_here));

        wordAdapter itemsAdapter = new wordAdapter(this, words, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list_Phrases);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (mMediaPlayer != null){

                    mMediaPlayer.release();
                    mMediaPlayer =null;
                }
                audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                int result = audioManager.requestAudioFocus(monAudioFocusListener,AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, words.get(position).getAudio());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mMediaPlayer.release();
                    }
                });}
            }
        });

                    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mMediaPlayer != null){

            mMediaPlayer.release();
            mMediaPlayer =null;
        };
        audioManager.abandonAudioFocus(monAudioFocusListener);
    }
    AudioManager.OnAudioFocusChangeListener monAudioFocusListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        mMediaPlayer.pause();
                    }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        if (mMediaPlayer != null){

                            mMediaPlayer.start();
                            mMediaPlayer =null;
                        }
                    }


                }   };
}
