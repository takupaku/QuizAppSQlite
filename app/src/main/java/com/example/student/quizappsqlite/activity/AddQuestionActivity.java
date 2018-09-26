package com.example.student.quizappsqlite.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.student.quizappsqlite.R;
import com.example.student.quizappsqlite.sqlite.MyDB;

public class AddQuestionActivity extends AppCompatActivity {
    private EditText etQuestion, etOp1, etOp2, etOp3, etOp4, etCor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);
        initView();
    }

    private void initView() {

        etQuestion=findViewById(R.id.et_questionId);
        etOp1=findViewById(R.id.et_op1);
        etOp2=findViewById(R.id.et_op2);
        etOp3=findViewById(R.id.et_op3);
        etOp4=findViewById(R.id.et_op4);
        etCor=findViewById(R.id.et_Cor);
    }

    public void save(View view) {
       if(!validDate()) return;

        MyDB myDB = new MyDB(this);
        String ques = etQuestion.getText().toString().trim();
        String op1 = etOp1.getText().toString().trim();
        String op2 = etOp2.getText().toString().trim();
        String op3 = etOp3.getText().toString().trim();
        String op4 = etOp4.getText().toString().trim();
        String cor = etCor.getText().toString().trim();
        if(myDB.insertQuestion(ques, op1,op2,op3,op4,cor)){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            empty();

        }
        else {
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }


    }

    private void empty() {
        etQuestion.setText("");
        etOp1.setText("");
        etOp2.setText("");
        etOp3.setText("");
        etOp4.setText("");
        etCor.setText("");
    }


    private boolean validDate() {
        if(etOp1.getText().toString().trim().isEmpty()){
            etOp1.setError("plz enter a op1");
            etOp1.requestFocus();
            return false;

        }
        if(etOp2.getText().toString().trim().isEmpty()){
            etOp2.setError("plz enter a op2");
            etOp2.requestFocus();
            return false;

        }
        if(etOp3.getText().toString().trim().isEmpty()){
            etOp3.setError("plz enter a op3");
            etOp3.requestFocus();
            return false;

        }
        if(etOp4.getText().toString().trim().isEmpty()){
            etOp4.setError("plz enter a op4");
            etOp4.requestFocus();
            return false;

        }
        if(etCor.getText().toString().trim().isEmpty()){
            etCor.setError("plz enter the correct option");
            etCor.requestFocus();
            return false;

        }
        return true;
    }
}
