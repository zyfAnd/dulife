package dulife.clps.com.dulife_version_01.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by ${zhangyanfu} on 2016/9/13.
 * Email : zhangyanfu66@gmail.com
 */
public class OkHttpUtil {
    private Handler mDelivery = new Handler(Looper.getMainLooper());
    private OkHttpClient mOkHttpClient = new OkHttpClient();
    private Gson mGson = new Gson();

    /**
     * 异步的get请求
     *
     * @param url
     * @param callback
     */
    public void _getAsyn(String url, final ResultCallback callback) {
        final Request request = new Request.Builder().url(url).build();
        deliverResult(callback, request);
    }

    public void deliverResult(final ResultCallback callback, Request request) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String str = response.body().string();
                if (callback.mType == String.class) {
                    sendSuccessResultCallback(str, callback);
                } else {
                    Object object = mGson.fromJson(str, callback.mType);
                    sendSuccessResultCallback(object, callback);
                }
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final ResultCallback callback) {
        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.onSuccess(object);
                }
            }
        });
    }

    public static abstract class ResultCallback<T> {
        Type mType;

        public ResultCallback() {
            mType = getSuperclassTypeParamter(getClass());
        }

        static Type getSuperclassTypeParamter(Class<?> subclass) {
            Type superclass = subclass.getGenericSuperclass();
            if (superclass instanceof Class) {
                throw new RuntimeException("Missing type paramter");
            }
            ParameterizedType parameterizedType = (ParameterizedType) superclass;
            return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
        }

        public abstract void onError(Request request, Exception e);

        public abstract void onSuccess(T response);
    }
    public void getJson()
    {
        String url = "http://c.m.163.com/nc/article/list/T1348654060988/0-20.html";
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {

                Log.e("getJson",response.body().string());
            }
        });
    }

}
