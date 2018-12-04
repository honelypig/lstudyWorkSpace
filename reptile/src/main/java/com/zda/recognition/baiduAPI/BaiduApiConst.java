package com.zda.recognition.baiduAPI;

/**
 * 用来拼接百度文字识别的APi地址
 */
public class BaiduApiConst {

    public static String urlPrefix ="https://aip.baidubce.com/rest/2.0/ocr/v1/";
    /**
     * 通用文字识别
     */
    public static final String GENERAL_BASIC="general_basic";

    /**
     * 通用文字识别（含位置信息版）
     */
    public static final String GENERAL="general";

    /**
     * 通用文字识别（含生僻字版）
     */
    public static final String GENERAL_ENHANCED="general_enhanced";

    /**
     * 通用文字识别（高精度版）
     */
    public static final String ACCURATE_BASIC="accurate_basic";

    /**
     * 通用文字识别（高精度含位置版）
     */
    public static final String ACCURATE="accurate";

    /**
     * 网络图片文字识别（高精度版）
     */
    public static final String WEBIMAGE="webimage";

    /**
     * 身份证识别
     */
    public static final String IDCARD="idcard";

    /**
     * 银行卡识别
     */
    public static final String BANKCARD="bankcard";

    /**
     * 驾驶证识别
     */
    public static final String DRIVING_LICENSE="driving_license";

    /**
     * 行驶证识别
     */
    public static final String VEHICLE_LICENSE="vehicle_license";

    /**
     * 营业执照识别
     */
    public static final String BUSINESS_LICENSE="business_license";

    /**
     * 车牌识别
     */
    public static final String LICENSE_PLATE="license_plate";

    /**
     * 表格文字识别-提交请求
     */
    public static final String FORM_OCR_REQUEST="form_ocr/request";

    /**
     * 表格文字识别-获取结果
     */
    public static final String FORM_OCR_GET_REQUEST_RESULT="form_ocr/get_request_result";

    /**
     * 通用票据识别
     */
    public static final String RECEIPT="receipt";

    /**
     * 手写文字识别
     */
    public static final String HANDWRITING="handwriting";

    /**
     * 增值税发票识别
     */
    public static final String VAT_INVOICE="vat_invoice";

    /**
     * 数字识别
     */
    public static final String NUMBERS="numbers";

    /**
     * 火车票识别
     */
    public static final String TRAIN_TICKET="train_ticket";

    /**
     * 出租车票识别
     */
    public static final String TAXI_RECEIPT="taxi_receipt";
}
