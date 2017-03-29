package com.example.emily.studentapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Emily on 3/23/2017.
 */
public class OnClickListenerCreateStudent implements View.OnClickListener{

    /*@BindView(R.id.editTextStudentFirstName) EditText mEditTextStudentFirstName;
    @BindView(R.id.editTextStudentEmail) EditText mEditTextStudentEmail;*/

    @Override
    public void onClick(View v) {
        final Context context = v.getContext();

       /* LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);*/
        //final View formElementsView = inflater.inflate(R.layout.student_input_form, null, false);
        //this way worked too and only used 1 line instead of 2 :)
        final View formElementsView = LayoutInflater.from(context).inflate(R.layout
                .student_input_form, null);

        final EditText mEditTextStudentFirstName = (EditText) formElementsView.findViewById(R.id
                .editTextStudentFirstName);
        final EditText mEditTextStudentEmail = (EditText) formElementsView.findViewById(R.id
                .editTextStudentEmail);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Student")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String studentFirstname = mEditTextStudentFirstName.getText()
                                        .toString();
                                String studentEmail = mEditTextStudentEmail.getText().toString();

                                ObjectStudent objectStudent = new ObjectStudent();
                                objectStudent.firstName = studentFirstname;
                                objectStudent.email = studentEmail;

                                boolean createSuccessful = new TableControllerStudent(context)
                                        .create(objectStudent);

                                if (createSuccessful) {
                                    Toast.makeText(context, "Student information was saved.",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(context, "Unable to save student information" +
                                            ".", Toast.LENGTH_LONG).show();
                                }

                                ((MainActivity)context).countRecords();
                                ((MainActivity)context).readRecords();

                                dialog.cancel();
                            }
                        }).show();
    }
}
