package com.rushabh.golive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    TextView tvScoreTeamOne, tvScoreTeamOneFinal, tvScoreTeamTwoFinal, tvScoreTeamTwo, tvTeamName;
    ImageView imgAddscoreTeamOne, imgSubscoreTeamOne, imgAddscoreTeamTwo, imgSubscoreTeamTwo;
    Switch switchChange, swNightMode;
    RadioButton rbTwoGoal, rbThreeGoal, rbFourGoal, rbFiveGoal;
    RadioGroup rgScore;
    Button btDes, btAdd;
    int inGoal = 2;
    String stTeam = "TEAMA";
    LinearLayout lvMain;

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
        lvMain = findViewById(R.id.lvMain);
        SharedPrefManager.init(MainActivity.this);
        setNightMode();
        //Enable or Disable night mode
        swNightMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    lvMain.setBackgroundColor(getResources().getColor(R.color.black));
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPrefManager.putInt("night_mode", 0);

                } else {

                    lvMain.setBackgroundColor(getResources().getColor(R.color.white));
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPrefManager.putInt("night_mode", 1);

                }
            }
        });
//Using switch decide which side of teams is selected
        switchChange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
SharedPrefManager.putInt("switchTeam",0);
                    stTeam = "TEAMB";

                } else {
                    stTeam = "TEAMA";
                    SharedPrefManager.putInt("switchTeam",1);

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
                        SharedPrefManager.putInt("radioGoal",2);
                        break;
                    case R.id.rbThreeGoal:
                        inGoal = 3;
                        SharedPrefManager.putInt("radioGoal",3);

                        break;
                    case R.id.rbFourGoal:
                        inGoal = 4;
                        SharedPrefManager.putInt("radioGoal",4);

                        break;
                    case R.id.rbFiveGoal:
                        inGoal = 5;
                        SharedPrefManager.putInt("radioGoal",5);
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
                SharedPrefManager.putString("TeamOneFinalScore",(String.valueOf(intNewScore)));
                SharedPrefManager.putString("TeamOneScore", (String.valueOf(intNewScore)));

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
                SharedPrefManager.putString("TeamOneFinalScore", (String.valueOf(intNewScore)));
                SharedPrefManager.putString("TeamOneScore", (String.valueOf(intNewScore)));


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
                SharedPrefManager.putString("TeamTwoFinalScore", (String.valueOf(intNewScore)));
                SharedPrefManager.putString("TeamTwoScore",  (String.valueOf(intNewScore)));

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
                SharedPrefManager.putString("TeamTwoFinalScore", (String.valueOf(intNewScore)));
                SharedPrefManager.putString("TeamTwoScore", (String.valueOf(intNewScore)));

            }
        });
    }


    private void setNightMode() {

        int isDavaSave = SharedPrefManager.getInt("night_mode", 1);
        if (isDavaSave == 0) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            SharedPrefManager.putInt("night_mode", 0);
            swNightMode.setChecked(true);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            SharedPrefManager.putInt("night_mode", 1);
            swNightMode.setChecked(false);
        }
        setData();

    }

    private void setData() {
        int itSaveData = SharedPrefManager.getInt("Data_save", 1);
        if (itSaveData == 0) {
           int switchTeam = SharedPrefManager.getInt("switchTeam",1);
            int radioGoal = SharedPrefManager.getInt("radioGoal",2);
            String strTeamOneFinalScore = SharedPrefManager.getString("TeamOneFinalScore", "0");
            String strTeamTwoFinalScore = SharedPrefManager.getString("TeamTwoFinalScore", "0");
            String strTeamOneScore = SharedPrefManager.getString("TeamOneScore", "0");
            String strTeamTwoScore = SharedPrefManager.getString("TeamTwoScore", "0");
          if(switchTeam == 1)
          {
              switchChange.setChecked(false);
          }else
          {
              switchChange.setChecked(true);
          }

          if (radioGoal == 2)
          {
              rgScore.check(R.id.rbTwoGoal);
          }else if (radioGoal == 3)
          {
              rgScore.check(R.id.rbThreeGoal);

          }else if (radioGoal == 4)
          {              rgScore.check(R.id.rbFourGoal);


          }else
          {
              rgScore.check(R.id.rbFiveGoal);

          }

            tvScoreTeamOne.setText(strTeamOneScore);
            tvScoreTeamOneFinal.setText(strTeamOneFinalScore);
            tvScoreTeamTwo.setText(strTeamTwoScore);
            tvScoreTeamTwoFinal.setText(strTeamTwoFinalScore);

        } else {
            SharedPrefManager.putString("TeamOneFinalScore", "0");
            SharedPrefManager.putString("TeamTwoFinalScore", "0");
            SharedPrefManager.putString("TeamOneScore", "0");
            SharedPrefManager.putString("TeamTwoScore", "0");
            String strTeamOneFinalScore = SharedPrefManager.getString("TeamOneFinalScore", "0");
            String strTeamTwoFinalScore = SharedPrefManager.getString("TeamTwoFinalScore", "0");
            String strTeamOneScore = SharedPrefManager.getString("TeamOneScore", "0");
            String strTeamTwoScore = SharedPrefManager.getString("TeamTwoScore", "0");
            tvScoreTeamOne.setText(strTeamOneScore);
            tvScoreTeamOneFinal.setText(strTeamOneFinalScore);
            tvScoreTeamTwo.setText(strTeamTwoScore);
            tvScoreTeamTwoFinal.setText(strTeamTwoFinalScore);
            rgScore.check(R.id.rbTwoGoal);
        }
    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        setNightMode();
//    }
//
//    @Override
//    protected void onPause() {
//        setNightMode();
//        super.onPause();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        setNightMode();
//
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        setNightMode();
//
//
//    }

    public void setNewGoalScore(String stTeam, int i, String stButton) {
        //this method is used for add and minus goals accordind to user selection
        if (stTeam == "TEAMA") {
            if (stButton == "add") {
                //add team A goals and set value into textview
                String oldScore = tvScoreTeamOne.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) + i;
                tvScoreTeamOne.setText(String.valueOf(intNewScore));
                tvScoreTeamOneFinal.setText(String.valueOf(intNewScore));
                SharedPrefManager.putString("TeamOneFinalScore",(String.valueOf(intNewScore)));
                SharedPrefManager.putString("TeamOneScore", (String.valueOf(intNewScore)));

            } else {
                //Minus Team A goals and set value into textview
                String oldScore = tvScoreTeamOne.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) - i;
                tvScoreTeamOne.setText(String.valueOf(intNewScore));
                tvScoreTeamOneFinal.setText(String.valueOf(intNewScore));
                SharedPrefManager.putString("TeamOneFinalScore",(String.valueOf(intNewScore)));
                SharedPrefManager.putString("TeamOneScore", (String.valueOf(intNewScore)));

            }
        } else {
            if (stButton == "add") {
                //Add team B goals and set value into textview
                String oldScore = tvScoreTeamTwo.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) + i;
                tvScoreTeamTwo.setText(String.valueOf(intNewScore));
                tvScoreTeamTwoFinal.setText(String.valueOf(intNewScore));
                SharedPrefManager.putString("TeamTwoFinalScore",(String.valueOf(intNewScore)));
                SharedPrefManager.putString("TeamTwoScore", (String.valueOf(intNewScore)));


            } else {
                //Minus Team B goals and set value into textview
                String oldScore = tvScoreTeamTwo.getText().toString();
                int intNewScore = Integer.parseInt(oldScore) - i;
                tvScoreTeamTwo.setText(String.valueOf(intNewScore));
                tvScoreTeamTwoFinal.setText(String.valueOf(intNewScore));
                SharedPrefManager.putString("TeamTwoFinalScore",(String.valueOf(intNewScore)));
                SharedPrefManager.putString("TeamTwoScore", (String.valueOf(intNewScore)));

            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            SpannableString spanString = new SpannableString(menu.getItem(i).getTitle().toString());
            spanString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.btn_gr_strat)), 0, spanString.length(), 0); //fix the color to white
            item.setTitle(spanString);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_about:
                View parentLayout = findViewById(android.R.id.content);
                Snackbar.make(MainActivity.this.findViewById(android.R.id.content), "Name :- Rushabh Shah \n Student ID :- A00240755", Snackbar.LENGTH_LONG).setAction("Get me a coffee", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "Thankyou , I appreciate that.", Toast.LENGTH_SHORT).show();
                            }
                        }).setActionTextColor(getResources().getColor(android.R.color.holo_red_light))
                        .show();
                return true;
            case R.id.nav_settings:
                startActivity(new Intent(this, SettingActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}