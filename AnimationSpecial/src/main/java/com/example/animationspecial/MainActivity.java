package com.example.animationspecial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.animationspecial.sharedelement.GridSpacingItemDecoration;
import com.example.animationspecial.sharedelement.MyRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initTransitionsView();

        initRecyclerView();
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.rv_share_elements);
        recyclerView.setFocusable(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, 5, false));
        recyclerView.setAdapter(new MyRecyclerViewAdapter(this));
    }

    private void initTransitionsView() {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.viewgroup_transition);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setOnClickListener(this);
        }
    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {
        for (int i = 0; i < ((ViewGroup) view.getParent()).getChildCount(); i++){
            if (view == (((ViewGroup) view.getParent()).getChildAt(i))) {
                switch (i){
                    case 0:
                        Intent explodeByCodeIntent = new Intent(this, Main2Activity.class);
                        explodeByCodeIntent.putExtra(Main2Activity.EXPLODE_TYPE, Main2Activity.EXPLODE_CODE);
                        startActivity(explodeByCodeIntent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, null).toBundle());
                        break;
                }
            }
        }
    }
}
