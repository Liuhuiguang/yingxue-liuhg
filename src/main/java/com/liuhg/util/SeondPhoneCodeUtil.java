package com.liuhg.util;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.Config;

import java.util.Random;

public class SeondPhoneCodeUtil {
    public static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static String sendPhoneCode(String phone, String random) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = SeondPhoneCodeUtil.createClient("LTAI5tJskyFFdYmPg2gcUM2G", "3RvcU24SlnarluPo6rXs0VJiqqDSEN");
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phone)
                .setSignName("小蛋黄")
                .setTemplateCode("SMS_171112532")
                .setTemplateParam("{\"code\":\"" + random + "\"}");
        // 复制代码运行请自行打印 API 的返回值
        SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
        SendSmsResponseBody body = sendSmsResponse.getBody();
        String code = body.getCode();
        String message = null;
        if (code.equals("ok")) {
            message = "发送成功";
        }
        if (code.equals("isv.MOBILE_NUMBER_ILLEGAL")) {
            message = "非法的手机号";
        }
        return message;
    }

    public static StringBuilder getRandom(int n) {
        char[] chars = "0123456789".toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(new Random().nextInt(chars.length));

        }
        return stringBuilder;

    }
}
