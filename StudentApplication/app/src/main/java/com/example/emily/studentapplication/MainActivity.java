package com.example.emily.studentapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    /*@BindView(R.id.textViewRecordCount) TextView mTextViewRecordCount;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Button buttonCreateStudent = (Button) findViewById(R.id.buttonCreateStudent);
        buttonCreateStudent.setOnClickListener(new OnClickListenerCreateStudent());

        countRecords();
        readRecords();
    }

    public void countRecords() {
         //recordCount = 0;
        int recordCount = new TableControllerStudent(this).count();

        TextView mTextViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        mTextViewRecordCount.setText(recordCount + " records found.");

    }

    public void readRecords() {
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<ObjectStudent> students = new TableControllerStudent(this).read();
        if(students.size() > 0 ){
            for(ObjectStudent obj: students){
                int id = obj.id;
                String studentFirstname = obj.firstName;
                String studentEmail = obj.email;

                String textViewContents = studentFirstname + " - " + studentEmail;

                TextView textViewStudentItem = new TextView(this);
                textViewStudentItem.setPadding(0,10,0,10);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));

                textViewStudentItem.setOnLongClickListener(new OnLongClickListenerStudentRecord());

                linearLayoutRecords.addView(textViewStudentItem);
            }
        }

        else {
            TextView locationItem = new TextView(this);
            locationItem.setPadding(8,8,8,8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
