package dulife.clps.com.dulife_version_01.main;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import dulife.clps.com.dulife_version_01.R;
import dulife.clps.com.dulife_version_01.bean.User;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ${zhangyanfu} on 2016/10/8.
 * Email : zhangyanfu66@gmail.com
 */

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    EditText _nameText;
    EditText _emailText;
    EditText _passwordText;
    Button _signupButton;
    TextView _loginLink;
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
        setContentView(R.layout.activity_signup);
        initViews();
        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initViews() {
        _nameText = (EditText) findViewById(R.id.input_name);
        _emailText = (EditText) findViewById(R.id.input_email);
        _passwordText = (EditText) findViewById(R.id.input_password);
        _signupButton = (Button) findViewById(R.id.btn_signup);
        _loginLink = (TextView) findViewById(R.id.link_login);
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.CardView_Light);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("帐号创建中..");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.
        final User user = new User();
        user.setPassword(password);
        user.setUsername(name);
        user.setMobilePhoneNumber(email);
        testSignUp(user);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        Log.e("run", "------------");

                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        Log.e("success", "success");
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String nameId = _nameText.getText().toString();
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (nameId.isEmpty() || nameId.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (email.isEmpty() || !Patterns.PHONE.matcher(email).matches()) {
            _emailText.setError("请输入一个有效的手机号");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 7) {
            _passwordText.setError("密码大于6位");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
    @SuppressLint("UseValueOf")
    private void testSignUp(User myUser) {
        addSubscription(myUser.signUp(new SaveListener<User>() {
            @Override
            public void done(User s, BmobException e) {
                if(e==null){
                    toast("注册成功:" +s.toString());
                }else{
                    loge(e);
                }
            }
        }));
    }
}

