package com.example.a9onhud.listviewapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Person> people;
    PersonAdapter adapter;
    GestureDetector gestureDetector;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                final View dialog = getLayoutInflater().inflate(R.layout.add_item_dialog, null);

                Log.i("MyLog", people.get(index).getName());
                final EditText nameEt = dialog.findViewById(R.id.namePt);
                final EditText nicknameEt = dialog.findViewById(R.id.nicknamePt);
                final EditText surnameEt = dialog.findViewById(R.id.surnamePt);

                String name = people.get(index).getName();
                String nickname = people.get(index).getNickname();
                String surname = people.get(index).getSurname();

                nameEt.setText(name);
                nicknameEt.setText(nickname);
                surnameEt.setText(surname);

                new AlertDialog.Builder(MainActivity.this).setView(dialog).setCancelable(false)
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                View v = listView.getChildAt(index-listView.getFirstVisiblePosition());
                                TextView nameTw = v.findViewById(R.id.nameTw);
                                TextView nicknameTw = v.findViewById(R.id.nicknameTw);
                                TextView surnameTw = v.findViewById(R.id.surnameTw);


                                nameTw.setText(nameEt.getText().toString());
                                nicknameTw.setText(nicknameEt.getText().toString());
                                surnameTw.setText(surnameEt.getText().toString());

                                people.get(index).setName(nameEt.getText().toString());
                                people.get(index).setNickname(nicknameEt.getText().toString());
                                people.get(index).setSurname(surnameEt.getText().toString());
                                Log.i("MyLog", people.get(index).getName());
                            }
                        }).create().show();

                adapter.notifyDataSetChanged();
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });

        people = new ArrayList<>();

        people.add(new Person("Techasit", "Toto", "Anukrua"));
        people.add(new Person("Techasit2", "Toto2", "Anukrua2"));

        adapter = new PersonAdapter(this, 0, people);

        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                index = listView.pointToPosition(0, (int) motionEvent.getY());

                if (index >=0 && index<people.size()) {
                    Log.i("MyLog", Integer.toString(index));
                    gestureDetector.onTouchEvent(motionEvent);
                }
                return false;
            }
        });
    }

    public void addItem(View view) {
        final View dialog = getLayoutInflater().inflate(R.layout.add_item_dialog, null);

        new AlertDialog.Builder(MainActivity.this).setView(dialog).setCancelable(false)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        EditText nameEt = dialog.findViewById(R.id.namePt);
                        EditText nicknameEt = dialog.findViewById(R.id.nicknamePt);
                        EditText surnameEt = dialog.findViewById(R.id.surnamePt);

                        people.add(new Person(nameEt.getText().toString(), nicknameEt.getText().toString(), surnameEt.getText().toString()));
                    }
                }).create().show();

        adapter.notifyDataSetChanged();
    }
}
