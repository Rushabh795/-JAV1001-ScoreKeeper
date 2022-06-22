package com.rushabh.golive;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvScoreTeamOne, tvScoreTeamOneFinal, tvScoreTeamTwoFinal, tvScoreTeamTwo;
    ImageView imgAddscoreTeamOne, imgSubscoreTeamOne, imgAddscoreTeamTwo, imgSubscoreTeamTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindID();
    }

    public void bindID() {
        tvScoreTeamOne = (TextView) findViewById(R.id.tvScoreTeamOne);
        tvScoreTeamOneFinal = (TextView) findViewById(R.id.tvScoreTeamOneFinal);
        tvScoreTeamTwoFinal = (TextView) findViewById(R.id.tvScoreTeamTwoFinal);
        tvScoreTeamTwo = (TextView) findViewById(R.id.tvScoreTeamTwo);

        imgAddscoreTeamOne = (TextView) findViewById(R.id.imgAddscoreTeamOne);
        imgSubscoreTeamOne = (TextView) findViewById(R.id.imgSubscoreTeamOne);
        imgAddscoreTeamTwo = (TextView) findViewById(R.id.imgAddscoreTeamTwo);
        imgSubscoreTeamTwo = (TextView) findViewById(R.id.imgSubscoreTeamTwo);
    }
}