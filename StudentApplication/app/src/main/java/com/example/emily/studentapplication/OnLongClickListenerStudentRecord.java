package com.example.emily.studentapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Emily on 3/24/2017.
 */
public class OnLongClickListenerStudentRecord implements View.OnLongClickListener {

    Context context;
    String id;

    @Override
    public boolean onLongClick(View v) {

        context = v.getContext();
        id = v.getTag().toString();

        final CharSequence[] items = {"Edit", "Delete"};

        new AlertDialog.Builder(context)
                .setTitle("Student Record")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            editRecord(Integer.parseInt(id));
                        } else if (item == 1) {
                            boolean deleteSuccessful = new TableControllerStudent(context)
                                    .delete(Integer.parseInt(id));
                            if (deleteSuccessful) {
                                Toast.makeText(context, "Student record was deleted.",
                                        Toast
                                                .LENGTH_LONG).show();
                            } else {
                                Toast.makeText(context, "Unable to delete student record.", Toast
                                        .LENGTH_LONG).show();
                            }

                            ((MainActivity) context).countRecords();
                            ((MainActivity) context).readRecords();
                        }
                        dialog.cancel();


                    }
                }).show();
        return false;
    }

    private void editRecord(final int studentId) {
        final TableControllerStudent tableControllerStudent = new TableControllerStudent(context);
        ObjectStudent objectStudent = tableControllerStudent.readSingleRecord(studentId);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.student_input_form, null, false);

        final EditText editTextStudentFirstName = (EditText) formElementsView.findViewById(R.id
                .editTextStudentFirstName);
        final EditText editTextStudentEmail = (EditText) formElementsView.findViewById(R.id
                .editTextStudentEmail);

        //setting single record values to EditText form elements
        editTextStudentFirstName.setText(objectStudent.firstName);
        editTextStudentEmail.setText(objectStudent.email);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //creating the object with the update value
                                ObjectStudent objectStudent = new ObjectStudent();
                                objectStudent.id = studentId;
                                objectStudent.firstName = editTextStudentFirstName.getText()
                                        .toString();
                                objectStudent.email = editTextStudentEmail.getText().toString();

                                //updating the record and telling user if it was updated or not
                                boolean updateSuccessful = tableControllerStudent.update
                                        (objectStudent);
                                if (updateSuccessful) {
                                    Toast.makeText(context, "Student record was updated.", Toast
                                            .LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(context, "Unable to update student record.",
                                            Toast.LENGTH_LONG);
                                }

                                ((MainActivity) context).countRecords();
                                ((MainActivity) context).readRecords();
                                dialog.cancel();
                            }
                        }).show();
    }
}
