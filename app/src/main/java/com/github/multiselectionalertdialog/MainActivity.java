package com.github.multiselectionalertdialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView text_person_name,
            text_select_person;
    private String[] NameArray = {"Alice","Bob","Catherine","Daniel","Elizabeth"};
    private boolean[] checkedItems;
    private boolean[] actualCheckedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_person_name = findViewById(R.id.text_person_name);
        text_select_person = findViewById(R.id.text_select_person);

        checkedItems = new boolean[NameArray.length];
        actualCheckedItems = new boolean[NameArray.length];

//        text_select_person.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(NameArray != null && NameArray.length > 0){
//                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                    builder.setTitle("Select the Persons");
//                    int selectedIndex = -1;
//                    shared_pref_selectedPerson = shared_pref.getString("selectPerson", "");
//                    if (shared_pref_selectedPerson != null) {
//                        for (int i = 0; i < NameArray.length; i++) {
//                            if (NameArray[i].equals(shared_pref_selectedPerson)) {
//                                selectedIndex = i;
//                                break;
//                            }
//                        }
//                    }
//
//                    builder.setSingleChoiceItems(NameArray, selectedIndex, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            selectedItem = Arrays.asList(NameArray).get(which);
//                            //Getting the selected Person Index Position
//                            Position = which;
//                        }
//                    });
//                    builder.setCancelable(false);
//                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int which) {
//                            //here we are adding the selected Person to Shared Preference
//                            text_select_person.setText(selectedItem);
//                            shared_pref_edit = shared_pref.edit();
//                            shared_pref_edit.putString("selectPerson", selectedItem);
//                            shared_pref_edit.apply();
//                        }
//                    });
//
//                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int which) {
//                            dialogInterface.dismiss();
//                        }
//                    });
//
//                    AlertDialog mDialog = builder.create();
//                    mDialog.show();
//
//                } else {
//                    Toast.makeText(getApplicationContext(), "No Persons Found", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
        text_select_person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (NameArray != null && NameArray.length > 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Select Persons");

                    builder.setMultiChoiceItems(NameArray, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int position, boolean isChecked) {

                        }
                    });

                    builder.setCancelable(false);
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {

                            String selected_persons = "";
                            for (int i = 0; i < NameArray.length; i++) {
                                boolean check = checkedItems[i];
                                actualCheckedItems[i] = checkedItems[i];
                                if (check) {
                                    selected_persons = selected_persons + NameArray[i];
                                    selected_persons = selected_persons + ",";
                                }
                            }
                            if (!selected_persons.equals("")) {
                                if (selected_persons.length() > 0) {
                                    selected_persons = selected_persons.substring(0, selected_persons.length() - 1);
                                }
                                text_select_person.setText(selected_persons);
                                text_select_person.setTextColor(getResources().getColor(R.color.color_black));
                            }
                            else {
                                String data = "Select Resident";
                                text_select_person.setText(data);
                                text_select_person.setTextColor(getResources().getColor(R.color.cardView_shadow_start_color));
                            }
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            for (int i = 0; i < checkedItems.length; i++) {
                                checkedItems[i] = actualCheckedItems[i];
                            }
                            dialogInterface.dismiss();
                        }
                    });

                    AlertDialog mDialog = builder.create();
                    mDialog.show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"No Residents Found",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
