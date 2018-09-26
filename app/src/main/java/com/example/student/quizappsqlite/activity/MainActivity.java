package com.example.student.quizappsqlite.activity;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student.quizappsqlite.R;
import com.example.student.quizappsqlite.model.Question;
import com.example.student.quizappsqlite.sqlite.MyDB;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Question> questionlist = new ArrayList<>();
private  int totalQuestion;
    private TextView question;
    private RadioGroup radioGroup;
    private RadioButton ans1,ans2,ans3,ans4;
    private int position=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        question= findViewById(R.id.tvQuestionId);
        radioGroup=findViewById(R.id.radioGroupId);
        ans1=findViewById(R.id.ansOneId);
        ans2=findViewById(R.id.ansTwoId);
        ans3=findViewById(R.id.ansThreeId);
        ans4=findViewById(R.id.ansFourId);
    }

    public void submit(View view) {
        int id = radioGroup.getCheckedRadioButtonId();
        if(id == -1){
            Toast.makeText(this, "please select an option", Toast.LENGTH_SHORT).show();

        }
        else {
            RadioButton radioButton = findViewById(id);
            String correct = radioButton.getText().toString();
            if(correct.equalsIgnoreCase(questionlist.get(position).getCurrectAns())){
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
                position++;
                viewData();
            }
            else {
                Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_resource_file,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_question){
            startActivity(new Intent(this,AddQuestionActivity.class));
            return true;
        }
        if(item.getItemId() == R.id.exit){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadData();
    }

    private void loadData() {
        MyDB myDB = new MyDB(this);
        Cursor cursor = myDB.getAllQuestion();

        totalQuestion= cursor.getCount();
        if(totalQuestion>0){
            while(cursor.moveToNext()){
                questionlist.add(new Question(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
            }

            viewData();
        }
        else {
            Toast.makeText(this, "no question found", Toast.LENGTH_SHORT).show();
        }

    }

    private void viewData() {
        try {
            question.setText(questionlist.get(position).getQuestion());
            ans1.setText(questionlist.get(position).getOption1());
            ans2.setText(questionlist.get(position).getOption2());
            ans3.setText(questionlist.get(position).getOption3());
            ans4.setText(questionlist.get(position).getOption4());
        }
        catch (Exception e){
            
        }
    }
}
