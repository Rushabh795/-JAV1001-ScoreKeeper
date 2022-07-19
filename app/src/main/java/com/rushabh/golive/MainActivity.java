package com.rushabh.golive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvScoreTeamOne, tvScoreTeamOneFinal, tvScoreTeamTwoFinal, tvScoreTeamTwo, tvTeamName;
    ImageView imgAddscoreTeamOne, imgSubscoreTeamOne, imgAddscoreTeamTwo, imgSubscoreTeamTwo;
    Switch switchChange, swNightMode;
    RadioButton rbTwoGoal, rbThreeGoal, rbFourGoal, rbFiveGoal;
    RadioGroup rgScore;
    Button btDes, btAdd;
    int inGoal = 2;
    String stTeam = "TEAMA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindID();
    }

    public void bindID() {
        //bind all the ids
        tvScoreTeamOne = (TextView) findViewById(R.id.tvScoreTeamOne);
        tvScoreTeamOneFinal = (TextView) findViewById(R.id.tvScoreTeamOneFinal);
        tvScoreTeamTwoFinal = (TextView) findViewById(R.id.tvScoreTeamTwoFinal);
        tvScoreTeamTwo = (TextView) findViewById(R.id.tvScoreTeamTwo);
        tvTeamName = (TextView) findViewById(R.id.tvTeamName);
        imgAddscoreTeamOne = (ImageView) findViewById(R.id.imgAddscoreTeamOne);
        imgSubscoreTeamOne = (ImageView) findViewById(R.id.imgSubscoreTeamOne);
        imgAddscoreTeamTwo = (ImageView) findViewById(R.id.imgAddscoreTeamTwo);
        imgSubscoreTeamTwo = (ImageView) findViewById(R.id.imgSubscoreTeamTwo);
        switchChange = (Switch) findViewById(R.id.switchChange);
        swNightMode = findViewById(R.id.swNightMode);
        rbTwoGoal = (RadioButton) findViewById(R.id.rbTwoGoal);
        rbThreeGoal = (RadioButton) findViewById(R.id.rbThreeGoal);
        rbFourGoal = (RadioButton) findViewById(R.id.rbFourGoal);
        rbFiveGoal = (RadioButton) findViewById(R.id.rbFiveGoal);
        rgScore = (RadioGroup) findViewById(R.id.rgScore);
        btAdd = (Button) findViewById(R.id.btAdd);
        btDes = (Button) findViewById(R.id.btDes);
        setID();
        //Enable or Disable night mode
        swNightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }
        });
//Using switch decide which side of teams is selected
        switchChange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    stTeam = "TEAMB";

                } else {
                    stTeam = "TEAMA";
                }
            }
        });


        rgScore.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            //radiogroup will check which radio button is used
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                int checkedID = rgScore.getCheckedRadioButtonId();
                switch (checkedID) {
                    case R.id.rbTwoGoal:
                        inGoal = 2;
                        break;
                    case R.id.rbThreeGoal:
                        inGoal = 3;
                        break;
                    case R.id.rbFourGoal:
                        inGoal = 4;
                        break;
                    case R.id.rbFiveGoal:
                        inGoal = 5;
                        break;
                }
            }
        });

        btAdd.setOnClickListener(new View.OnClickListener() {
            //Add button going to add score
            @Override
            public void onClick(View v) {
                setNewGoalScore(stTeam, inGoal, "add");
            }
        });
        btDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setNewGoalScore(stTeam, inGoal, "sub");
            }
        });
        imgAddscoreTeamOne.setOnClickListener(new View.OnClickListener() {
            //add one goal into Team A
            @Override
            public void onClick(View v) {
                String oldScore = tvScoreTeamOne.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) + 1;
                tvScoreTeamOne.setText(String.valueOf(intNewScore));
                tvScoreTeamOneFinal.setText(String.valueOf(intNewScore));
            }
        });
        imgSubscoreTeamOne.setOnClickListener(new View.OnClickListener() {
            //minus one goal into Team A
            @Override
            public void onClick(View v) {
                String oldScore = tvScoreTeamOne.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) - 1;
                tvScoreTeamOne.setText(String.valueOf(intNewScore));
                tvScoreTeamOneFinal.setText(String.valueOf(intNewScore));

            }
        });

        imgAddscoreTeamTwo.setOnClickListener(new View.OnClickListener() {
            //add one goal into Team B
            @Override
            public void onClick(View v) {
                String oldScore = tvScoreTeamTwo.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) + 1;
                tvScoreTeamTwo.setText(String.valueOf(intNewScore));
                tvScoreTeamTwoFinal.setText(String.valueOf(intNewScore));

            }
        });


        imgSubscoreTeamTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //minus score of team 2
                String oldScore = tvScoreTeamTwo.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) - 1;
                tvScoreTeamTwo.setText(String.valueOf(intNewScore));
                tvScoreTeamTwoFinal.setText(String.valueOf(intNewScore));

            }
        });
    }

    public void setID() {
        //first time set default value into textbox (default goals)
        tvScoreTeamOne.setText("1");
        tvScoreTeamOneFinal.setText("1");
        tvScoreTeamTwo.setText("2");
        tvScoreTeamTwoFinal.setText("2");
//        tvTeamName.setText("INDIA");

    }

    public void setNewGoalScore(String stTeam, int i, String stButton) {
        //this method is used for add and minus goals accordind to user selection
        if (stTeam == "TEAMA") {
            if (stButton == "add") {
                //add team A goals and set value into textview
                String oldScore = tvScoreTeamOne.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) + i;
                tvScoreTeamOne.setText(String.valueOf(intNewScore));
                tvScoreTeamOneFinal.setText(String.valueOf(intNewScore));
            } else {
                //Minus Team A goals and set value into textview
                String oldScore = tvScoreTeamOne.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) - i;
                tvScoreTeamOne.setText(String.valueOf(intNewScore));
                tvScoreTeamOneFinal.setText(String.valueOf(intNewScore));
            }
        } else {
            if (stButton == "add") {
                //Add team B goals and set value into textview
                String oldScore = tvScoreTeamTwo.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) + i;
                tvScoreTeamTwo.setText(String.valueOf(intNewScore));
                tvScoreTeamTwoFinal.setText(String.valueOf(intNewScore));

            } else {
                //Minus Team B goals and set value into textview
                String oldScore = tvScoreTeamTwo.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) - i;
                tvScoreTeamTwo.setText(String.valueOf(intNewScore));
                tvScoreTeamTwoFinal.setText(String.valueOf(intNewScore));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }
}