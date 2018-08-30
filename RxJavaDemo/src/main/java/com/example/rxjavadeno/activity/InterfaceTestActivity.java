package com.example.rxjavadeno.activity;

import android.os.Bundle;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rxjavadeno.Interface.PostRequest;
import com.example.rxjavadeno.R;
import com.example.rxjavadeno.bean.UserLog;
import com.example.rxjavadeno.bean.UserRegister;
import com.example.rxjavadeno.model.UserHelper;
import com.example.rxjavadeno.util.RetrofitProvider;


import butterknife.BindView;
import butterknife.ButterKnife;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class InterfaceTestActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.et_ac)
    EditText etAc;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.but_log)
    Button butLog;
    @BindView(R.id.ll_log)
    LinearLayout llLog;
    @BindView(R.id.et_id)
    EditText etId;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_verCode)
    EditText etVerCode;
    @BindView(R.id.but_reg)
    Button butReg;
    @BindView(R.id.ll_reg)
    LinearLayout llReg;
    @BindView(R.id.et_mailbox)
    EditText etMailbox;
    @BindView(R.id.but_verification)
    Button butVerification;
    @BindView(R.id.ll_verification)
    LinearLayout llVerification;
    @BindView(R.id.tv_1)
    TextView tv1;

    private UserHelper userHandle;

    private String accountNumber , password , name , pwd , email , verCode , e;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interface_test);
        initView();
        initData();
    }

    private void initView() {
        ButterKnife.bind(this);
        butLog.setOnClickListener(this);
        butReg.setOnClickListener(this);
        butVerification.setOnClickListener(this);
    }

    private void initData() {
        accountNumber = etAc.getText().toString();
        password = etPwd.getText().toString();
        name = etId.getText().toString();
        pwd = etPassword.getText().toString();
        email = etEmail.getText().toString();
        verCode = etVerCode.getText().toString();
        e = etMailbox.getText().toString();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.but_log:
                initData();
                userHandle.logIn(this,accountNumber,password,tv1);
                break;
            case R.id.but_reg:
                initData();
                userHandle.register(this,name,pwd,email,verCode);
                break;
            case R.id.but_verification:
                initData();
                userHandle.verification(this,e);
                break;
        }
    }
}
