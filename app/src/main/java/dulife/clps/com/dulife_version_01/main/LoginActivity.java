package dulife.clps.com.dulife_version_01.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;
import dulife.clps.com.dulife_version_01.R;
import dulife.clps.com.dulife_version_01.main.widget.MainActivity;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    EditText _emailText;
    EditText _passwordText;
    Button _loginButton;
    TextView _signupLink;
    AppCompatButton btnValid;
    private CompositeSubscription mCompositeSubscription;

    /**
     * 解决Subscription内存泄露问题
     *
     * @param s
     */
    protected void addSubscription(Subscription s) {
        if (this.mCompositeSubscription == null) {
            this.mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(s);
    }

    public void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public static void loge(Throwable e) {
        Log.i(TAG, "===============================================================================");
        if (e instanceof BmobException) {
            Log.e(TAG, "错误码：" + ((BmobException) e).getErrorCode() + ",错误描述：" + ((BmobException) e).getMessage());
        } else {
            Log.e(TAG, "错误描述：" + e.getMessage());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
        btnValid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestCode();
            }
        });
    }

    private void initViews() {
        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _loginButton = (Button) findViewById(R.id.btn_login);
        _signupLink = (TextView) findViewById(R.id.link_signup);
        btnValid = (AppCompatButton) findViewById(R.id.btn_valid);
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.Base_Theme_AppCompat);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("登陆中,请稍候..");
        progressDialog.show();

        String number = _emailText.getText().toString();
        String code = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.
        verifySmsCode();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        Intent intent = new Intent(this, MainActivity.class);
        String email = _emailText.getText().toString();
        intent.putExtra("name",email);
        startActivity(intent);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "登录失败", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();
        Log.e("msg", email + "-" + password);
        if (email.isEmpty() || email.length() != 11) {
            Log.e("email-msg", email.length() + "--");
            _emailText.setError("请输入有效收手机号");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty()) {
            _passwordText.setError("验证码不能为空");
            Log.e("password-msg", password.length() + "--");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }

    public void requestCode() {
        String number = _emailText.getText().toString();
        if (!TextUtils.isEmpty(number)) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sendTime = format.format(new Date());
            BmobSMS.requestSMSCode(number, "注册模板", new QueryListener<Integer>() {

                @Override
                public void done(Integer smsId, BmobException ex) {
                    if (ex == null) {//验证码发送成功
                        toast("验证码发送成功，短信id：" + smsId);//用于查询本次短信发送详情
                    } else {
                        toast("errorCode = " + ex.getErrorCode() + ",errorMsg = " + ex.getLocalizedMessage());
                    }
                }
            });
        } else {
            toast("请输入手机号码");
        }
    }

    private void verifySmsCode() {
        String number = _emailText.getText().toString();
        String code = _passwordText.getText().toString();
        if (!TextUtils.isEmpty(number) && !TextUtils.isEmpty(code)) {
            BmobSMS.verifySmsCode(number, code, new UpdateListener() {

                @Override
                public void done(BmobException ex) {
                    if (ex == null) {//短信验证码已验证成功
                        toast("验证通过");
                    } else {
                        toast("验证失败：code =" + ex.getErrorCode() + ",msg = " + ex.getLocalizedMessage());
                    }
                }
            });
        } else {
            toast("请输入手机号和验证码");
        }
    }
}