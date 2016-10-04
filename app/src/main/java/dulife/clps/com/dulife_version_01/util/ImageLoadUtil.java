package dulife.clps.com.dulife_version_01.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

import dulife.clps.com.dulife_version_01.bean.ImageBean;


/**
 * Created by ${zhangyanfu} on 2016/9/13.
 * Email : zhangyanfu66@gmail.com
 */
public class ImageLoadUtil {

    /**
     * 将获取到的json转化成图片列表对象
     * @param res
     * @return
     */
    public static List<ImageBean> readJsonImageBeans(String res)
    {
        List<ImageBean> beans = new ArrayList<>();
        try{
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(res).getAsJsonArray();
            for(int i=1;i<jsonArray.size();i++)
            {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                ImageBean bean = JsonUtil.deserialize(jo,ImageBean.class);
                beans.add(bean);
            }

            return beans;
        }catch (Exception e)
        {

        }
        return null;
    }
}
