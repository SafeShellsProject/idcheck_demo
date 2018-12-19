package com.chainshells.idcheck_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.chainshells.idcheck_sdk.authSDK;
import com.chainshells.idcheck_sdk.initClient;

import org.json.JSONObject;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView tv_info;
    private static final String KEY_NAME = "MainActivity";
    private authSDK authSDK=new authSDK();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        tv_info=findViewById(R.id.tv_info);
        tv_info.setMovementMethod(new ScrollingMovementMethod());
    }
    public void btn_1(View view) {
        String ip="192.168.0.101";
        String port="20880";
        String appID="5DCEB05A057C8668";
        String appKey="3059301306072A8648CE3D020106082A8648CE3D03010703420004468A3655DE28BD634EB150C81605FAEC371640FA31EA6BC5DF88BB3AB6432AE4ADEDC33B977A0355E39875DA79DBBBDA5B607A0768E739C6252E61F4F908BADD";
        String info =  "{\"ip\": \""+ip+"\",\"port\": \""+port+"\",\"appID\": \""+appID+"\",\"appKey\": \""+appKey+"\"}";
        initClient.authInit(this , info,new initClient.Callback(){
            @Override
            public void onInitResult(int result, String message,  authSDK sdk) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                authSDK = sdk;
                print("init","result:"+result+";message:"+message);
            }

        });

    }
    public void btn_2(View view) {
        authSDK.authConfirm("" , new authSDK.Callback(){

            @Override
            public void onAuthResult(int result, String message, String data) {
                switch (result){
                    case 0:
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        break;
                    case 401://服务器返回数据解析失败，支持扩展数据。（认证页面关闭）
                        break;
                    case 402://服务器初始化报错，支持扩展数据。（认证页面关闭）
                        break;
                    case 403://无
                        break;
                    case 404://认证返回。（认证页面关闭）
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        break;
                    case 405://认证失败，支持扩展数据。（认证页面不会关闭）
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        break;
                    case 406://短信平台报错，支持扩展数据。（认证页面不会关闭）
                        break;
                    case 407://初始化网络异常。（认证页面关闭）
                        break;
                    case 408://发送验证码网络异常。（认证页面关闭）
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        break;
                    case 409://mobileVerify网络异常。（认证页面关闭）
                        break;
                    case 410://bankCardVerify网络异常。（认证页面关闭）
                        break;
                }
                print("authConfirm","result:"+result+";message:"+message+";data:"+data);
            }

        });
    }

    public void btn_5(View view) {

    }
    private void print(String title,String msg){
        if(tv_info.getText()!="")
            tv_info.append("\r\n");
        tv_info.append(Html.fromHtml("<font color='#0084ff'> "+title+": </font>"));
        tv_info.append("\r\n"+msg);
        Log.d(title,msg);
    }
    private void print(String msg){
        if(tv_info.getText()!="")
            tv_info.append("\r\n");
        tv_info.append("\r\n"+msg);
    }
}
