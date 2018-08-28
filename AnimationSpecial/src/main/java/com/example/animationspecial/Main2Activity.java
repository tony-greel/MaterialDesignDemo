package com.example.animationspecial;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;

public class Main2Activity extends AppCompatActivity {

    public static final String EXPLODE_TYPE = "explode type";
    public static final String EXPLODE_CODE = "explode code";
    public static final String EXPLODE_XML = "explode xml";
    private String mExplodeType;
    private Transition transition = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mExplodeType = getIntent().getExtras().getString(EXPLODE_TYPE);

        setupWindowAnimations();

        initToolbar();
    }

    private void initToolbar() {
        Toolbar toolbar = ((Toolbar) findViewById(R.id.toolbar));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @SuppressLint("NewApi")
    private void setupWindowAnimations() {
        switch (mExplodeType) {
            case EXPLODE_CODE:
                transition = buildEnterTransitionByCode();
                break;
            case EXPLODE_XML:
                transition = buildEnterTransitionByXml();
                break;
            default:
                break;
        }

        getWindow().setEnterTransition(transition);
    }

    @SuppressLint("NewApi")
    private Transition buildEnterTransitionByCode() {
        Explode enterTransition = new Explode();
        enterTransition.setDuration(500);
        return enterTransition;
    }

    @SuppressLint("NewApi")
    private Transition buildEnterTransitionByXml() {
        return TransitionInflater.from(this).inflateTransition(R.transition.explode);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            //If you use 'finish();' you will not get the animation effect,
            //you can use the following methods instead.
            supportFinishAfterTransition();
        }
        return super.onOptionsItemSelected(item);
    }
}
