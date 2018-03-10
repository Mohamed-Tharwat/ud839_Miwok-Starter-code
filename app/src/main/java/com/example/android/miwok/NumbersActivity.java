package com.example.android.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by m.sarwat on 3/1/2018.
 */

public class NumbersActivity extends AppCompatActivity{
    MediaPlayer mMediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("two", "otiiko", R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine", "wo’e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten,R.raw.number_ten));

//        ArrayList<String> words =new ArrayList<String>();
//        words.add("one");
//        words.add("one");
//        words.add("one");
//        words.add("one");
//
//        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
//        int X=0;
//        while (X < EnglishNumberArray.size())
//        {
//            TextView word = new TextView(this);
//            word.setText(EnglishNumberArray.get(X));
//            rootView.addView(word);
//            X++;
//        }

//        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1, words);
//        ListView listView = (ListView) findViewById(R.id.list_View);
//        listView.setAdapter(itemsAdapter);

           wordAdapter itemsAdapter = new wordAdapter(this, words, R.color.category_numbers);
           ListView listView = (ListView) findViewById(R.id.list_numbers);
           listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                if (mMediaPlayer != null){

                    mMediaPlayer.release();
                    mMediaPlayer =null;
                }

                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, words.get(position).getAudio());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        mMediaPlayer.release();
                    }
                });
            }
        });

//        for ( int X=0; X < words.size(); X++) {
//            Log.v( "array check log", "the word number in array " + X + " " + words.get(X).getDefaultTranslation());
//            TextView word = new TextView(this);
//            word.setText(EnglishNumberArray.get(X));
//            rootView.addView(word);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if (mMediaPlayer != null){

            mMediaPlayer.release();
            mMediaPlayer =null;
        }
    }
}