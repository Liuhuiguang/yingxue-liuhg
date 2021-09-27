package com.liuhg;

import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.liuhg.util.AliyunOSSUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AliyunTest {
    private static String endpoint = "oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    private static String accessKeyId = "LTAI5tES2Rjahs3KhwxEfbaZ";
    private static String accessKeySecret = "XtYFlM8UJQPW2Uo2pXUqGgUQVAsF6e";
    String bucketName = "yingxue-liu";

    @Test
    public void test2() throws FileNotFoundException {
// yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
        InputStream inputStream = new FileInputStream("D:\\Java\\第三阶段2\\LateProject\\Day5 类别实现，视频设计\\资料\\视频管理效果图.jpg");
// 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(bucketName, null, inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void test3() throws IOException {

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 填写网络流地址。
        InputStream inputStream = new URL("https://www.baidu.com/img/flexible/logo/pc/result.png").openStream();
// 填写Bucket名称和Object完整路径。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(bucketName, "result.png", inputStream);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void test4() throws IOException {

        String objectName = "1.jpg";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 下载Object到本地文件，并保存到指定的本地路径中。如果指定的本地文件存在会覆盖，不存在则新建。
// 如果未指定本地路径，则下载后的文件默认保存到示例程序所属项目对应本地路径中。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File("D:\\1.jpg"));

// 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void test5() throws IOException {
    }
}
