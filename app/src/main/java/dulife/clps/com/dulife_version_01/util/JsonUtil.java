package dulife.clps.com.dulife_version_01.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

/**
 * Created by ${zhangyanfu} on 2016/9/13.
 * Email : zhangyanfu66@gmail.com
 */
public class JsonUtil {
    private static Gson mGson = new Gson();

    /**
     * 反序列化 将json对象转化成bean对象
     * @param json
     * @param clz
     * @param <T>
     * @return
     * @throws JsonSyntaxException
     */
    public static <T> T deserialize(JsonObject json,Class<T>clz)throws JsonSyntaxException
    {
        return mGson.fromJson(json,clz);
    }
}
