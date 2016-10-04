package dulife.clps.com.dulife_version_01.news;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import dulife.clps.com.dulife_version_01.bean.NewsBean;
import dulife.clps.com.dulife_version_01.util.JsonUtil;

/**
 * Created by ${zhangyanfu} on 2016/9/19.
 * Email : zhangyanfu66@gmail.com
 */
public class NewsJsonUtil {
    private final static String TAG = "NewsJsonUtil";

    /**
     *
     * @param res
     * @param
     * @return
     */
    public static List<NewsBean> readJsonNewsBeans(String res,String value)
    {
        List<NewsBean> beans = new ArrayList<>();
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(res).getAsJsonObject();
            JsonElement jsonElement = jsonObject.get(value);
            if(jsonElement==null)
            {
                return null;
            }
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for(int i=1;i<jsonArray.size();i++)
            {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                NewsBean bean = JsonUtil.deserialize(jo,NewsBean.class);
                beans.add(bean);
            }
            Log.e("NewsJsonUtil",beans.size()+"---haha");
        }catch (Exception e)
        {

        }
        return beans;
    }
}
