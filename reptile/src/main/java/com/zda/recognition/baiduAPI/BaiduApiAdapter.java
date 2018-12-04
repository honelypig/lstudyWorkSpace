package com.zda.recognition.baiduAPI;

import com.zda.utils.Assert;
import com.zda.utils.BaseImg64;
import com.zda.utils.HttpRequestUtils;
import com.zda.utils.TokenUtils;

import java.io.File;

public class BaiduApiAdapter {
    public  static final String baseUrl=BaiduApiConst.urlPrefix;

    private static final String baiduAuth ="?access_token=" + TokenUtils.getBaiduAuth();

    private static final String fullUrl="https://aip.baidubce.com/rest/2.0/ocr/v1/%s?access_token=" + TokenUtils.getBaiduAuth();

    private static final String exampleUrl = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic?access_token=" + TokenUtils.getBaiduAuth();

    /**
     *  用户向服务请求识别某张图中的所有文字，相对于通用文字识别该产品精度更高，但是识别耗时会稍长。
     *  请求参数 image 图像数据，base64编码后进行urlencode，要求base64编码和urlencode后大小不超过4M，最短边至少15px，最长边最大4096px,支持jpg/png/bmp格式
     *          detect_direction 是否检测图像朝向，默认不检测，即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:
     *          probability 是否返回识别结果中每一行的置信度  true、false
     * @param imgUrl
     * @return  识别结果，为json格式
     */
    public static String accurate(String imgUrl)  {
        File file = new File(imgUrl);
        Assert.checkIllegalArgument(file.exists(),"图片不存在");
        String image = BaseImg64.getImageStrFromPath(imgUrl);
        String param = "image=" + image;
        // return  HttpPostadapter(param,baseUrl+BaiduApiConst.ACCURATE_BASIC+baiduAuth);
      return HttpPostadapter(param,String.format(fullUrl,BaiduApiConst.ACCURATE_BASIC));
    }
//....

    public static  String HttpPostadapter(String param,String url){
        try {
            return HttpRequestUtils.HttpPost(param,url);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
