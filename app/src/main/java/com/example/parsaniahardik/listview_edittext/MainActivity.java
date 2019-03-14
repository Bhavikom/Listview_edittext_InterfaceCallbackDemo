package com.example.parsaniahardik.listview_edittext;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.content.Intent;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListenerFromAdapterToActivity,ListenFromFragment{

    private Button btn,btnStartFragment,btnDoSomethingInFragment;
    private ListView lv;
    private CustomeAdapter customeAdapter;
    public ArrayList<EditModel> editModelArrayList;
    FrameLayout frameLayoutContainer;
    public ListenFromActivity activityListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDoSomethingInFragment = (Button)findViewById(R.id.btn_fragment_listener);
        btnStartFragment = (Button)findViewById(R.id.btn_fragment);
        lv = (ListView) findViewById(R.id.listView);
        btn = (Button) findViewById(R.id.btn);

        final EditText editText;

        editModelArrayList = populateList();
        customeAdapter = new CustomeAdapter(this,editModelArrayList,this);
        lv.setAdapter(customeAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NextActivity.class);
                startActivity(intent);
            }
        });
        btnStartFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new BlankFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.frame_container,fragment,"");
                transaction.addToBackStack("blank");
                transaction.commit();
            }
        });
        btnDoSomethingInFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(activityListener != null) {
                    activityListener.doSomethingInFragment();
                }
            }
        });

    }

    private ArrayList<EditModel> populateList(){

        ArrayList<EditModel> list = new ArrayList<>();

        for(int i = 0; i < 8; i++){
            EditModel editModel = new EditModel();
            editModel.setEditTextValue(String.valueOf(i));
            list.add(editModel);
        }

        return list;
    }

    @Override
    public void detectClick() {
        Toast.makeText(MainActivity.this," clicked on lable ",Toast.LENGTH_SHORT).show();
    }

    public void yourDesiredMethod() {
        Toast.makeText(MainActivity.this," method call ",Toast.LENGTH_SHORT).show();
    }
    public void setActivityListener(ListenFromActivity activityListener) {
        this.activityListener = activityListener;
    }

    @Override
    public void doSomethingInActivity() {
        Toast.makeText(MainActivity.this," something happen in fragment",Toast.LENGTH_SHORT).show();
    }
}
