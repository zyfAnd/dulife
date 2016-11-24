package dulife.clps.com.dulife_version_01.main;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by ${zhangyanfu} on 2016/10/8.
 * Email : zhangyanfu66@gmail.com
 */

public class MyApplication extends Application {
    final  String APPID = "e0361651af3c69b2e073dac78c524e9e";
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this,APPID);
    }
}
