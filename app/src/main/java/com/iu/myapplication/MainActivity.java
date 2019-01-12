package com.iu.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iu.myapplication.datas.User;
import com.iu.myapplication.utils.ConnectServer;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends BaseActivity {

    private android.widget.EditText emailEdt;
    private android.widget.EditText passwordEdt;
    private android.widget.Button logInBtn;
    private android.widget.TextView findPwTxt;
    private android.widget.Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //서버에 아이디/비번을 던져서 실제 회원인지 체크
                //okhttp 깃허브에서 다운받고 매니페시트 인터넷 허용체크를 먼저 해야함.
                ConnectServer.postRequestSignin(mContext,
                        emailEdt.getText().toString(),
                        passwordEdt.getText().toString(),
                        new ConnectServer.JsonResponseHandler() {
                            @Override
                            public void onResponse(JSONObject json) {
                                Log.d("로그인응답",json.toString());

                                try {

                                    final int code = json.getInt("code");
                                    final String message = json.getString("message");

                                    JSONObject data = json.getJSONObject("data");
                                    JSONObject userJson = data.getJSONObject("user");

                                    User user = User.getUserFromJson(userJson);

                                    Log.d("로그인응답","로그인한사람이름 : " + user.getName());

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            if(code == 200){
                                                Toast.makeText(mContext, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show();
                                            }
                                            else{
                                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.signUpBtn = (Button) findViewById(R.id.signUpBtn);
        this.findPwTxt = (TextView) findViewById(R.id.findPwTxt);
        this.logInBtn = (Button) findViewById(R.id.logInBtn);
        this.passwordEdt = (EditText) findViewById(R.id.passwordEdt);
        this.emailEdt = (EditText) findViewById(R.id.emailEdt);
    }
}
