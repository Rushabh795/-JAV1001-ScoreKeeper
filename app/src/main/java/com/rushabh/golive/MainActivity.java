package com.rushabh.golive;

import androidx.appcompat.app.AppCompatActivity;

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
    Switch switchChange;
    RadioButton rbTwoGoal, rbThreeGoal, rbFourGoal, rbFiveGoal;
    RadioGroup rgScore;
    Button btDes, btAdd;
    int inGoal = 2;
    String stTeam="TEAMA";

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
        tvTeamName = (TextView) findViewById(R.id.tvTeamName);
        imgAddscoreTeamOne = (ImageView) findViewById(R.id.imgAddscoreTeamOne);
        imgSubscoreTeamOne = (ImageView) findViewById(R.id.imgSubscoreTeamOne);
        imgAddscoreTeamTwo = (ImageView) findViewById(R.id.imgAddscoreTeamTwo);
        imgSubscoreTeamTwo = (ImageView) findViewById(R.id.imgSubscoreTeamTwo);
        switchChange = (Switch) findViewById(R.id.switchChange);
        rbTwoGoal = (RadioButton) findViewById(R.id.rbTwoGoal);
        rbThreeGoal = (RadioButton) findViewById(R.id.rbThreeGoal);
        rbFourGoal = (RadioButton) findViewById(R.id.rbFourGoal);
        rbFiveGoal = (RadioButton) findViewById(R.id.rbFiveGoal);
        rgScore = (RadioGroup) findViewById(R.id.rgScore);
        btAdd = (Button) findViewById(R.id.btAdd);
        btDes = (Button) findViewById(R.id.btDes);
        setID();

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
            @Override
            public void onClick(View v) {
                String oldScore = tvScoreTeamOne.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) + 1;
                tvScoreTeamOne.setText(String.valueOf(intNewScore));
                tvScoreTeamOneFinal.setText(String.valueOf(intNewScore));
            }
        });
        imgSubscoreTeamOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldScore = tvScoreTeamOne.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) - 1;
                tvScoreTeamOne.setText(String.valueOf(intNewScore));
                tvScoreTeamOneFinal.setText(String.valueOf(intNewScore));

            }
        });

        imgAddscoreTeamTwo.setOnClickListener(new View.OnClickListener() {
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
                String oldScore = tvScoreTeamTwo.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) - 1;
                tvScoreTeamTwo.setText(String.valueOf(intNewScore));
                tvScoreTeamTwoFinal.setText(String.valueOf(intNewScore));

            }
        });
    }

    public void setID() {
        tvScoreTeamOne.setText("1");
        tvScoreTeamOneFinal.setText("1");
        tvScoreTeamTwo.setText("2");
        tvScoreTeamTwoFinal.setText("2");
//        tvTeamName.setText("INDIA");

    }

    public void setNewGoalScore(String stTeam, int i, String stButton) {
        if (stTeam == "TEAMA") {
            if (stButton == "add") {
                String oldScore = tvScoreTeamOne.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) + i;
                tvScoreTeamOne.setText(String.valueOf(intNewScore));
                tvScoreTeamOneFinal.setText(String.valueOf(intNewScore));
            } else {
                String oldScore = tvScoreTeamOne.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) - i;
                tvScoreTeamOne.setText(String.valueOf(intNewScore));
                tvScoreTeamOneFinal.setText(String.valueOf(intNewScore));
            }
        } else {
            if (stButton == "add") {
                String oldScore = tvScoreTeamTwo.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) + i;
                tvScoreTeamTwo.setText(String.valueOf(intNewScore));
                tvScoreTeamTwoFinal.setText(String.valueOf(intNewScore));

            } else {
                String oldScore = tvScoreTeamTwo.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) - i;
                tvScoreTeamTwo.setText(String.valueOf(intNewScore));
                tvScoreTeamTwoFinal.setText(String.valueOf(intNewScore));
            }
        }
    }
}