package com.example.pictureview.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.pictureview.R;

public class ListViewActivirty extends AppCompatActivity implements View.OnClickListener {

    private Button normal_list , viewpager_list , multi_holder_list , recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_activirty);
        initView();
        assignment();
        monitorInitialization();
    }

    private void initView() {
        normal_list = findViewById(R.id.normal_list);
        viewpager_list = findViewById(R.id.viewpager_list);
        multi_holder_list = findViewById(R.id.multi_holder_list);
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void assignment() {
    }

    private void monitorInitialization() {
        normal_list.setOnClickListener(this);
        viewpager_list.setOnClickListener(this);
        multi_holder_list.setOnClickListener(this);
        recyclerView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.normal_list:
                startActivity(new Intent(this , ListViewNormalActivity.class));
                break;
            case R.id.viewpager_list:
                startActivity(new Intent(this , ListViewViewpagerActivity.class));
                break;
            case R.id.multi_holder_list:
                startActivity(new Intent(this , ListViewMultiHolderActivity.class));
                break;
            case R.id.recyclerView:
                startActivity(new Intent(this , RecyclerViewNormalActivity.class));
                break;
        }
    }
}
