import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HelloClient {
    // 开启netty服务线程
    public static void main(String[] args) throws IOException {
        //获得Http客户端
        CloseableHttpClient build = HttpClientBuilder.create().build();
        //创建get请求
        HttpGet httpGet = new HttpGet("http://localhost:8801");
        //执行请求
        CloseableHttpResponse execute = build.execute(httpGet);
        //解析返回值
        StatusLine statusLine = execute.getStatusLine();
        //获取到返回状态码
        System.out.println("状态码为：" + statusLine.getStatusCode());
        String s = EntityUtils.toString(execute.getEntity());
        System.out.println("响应内容为：" + s);
        build.close();
        execute.close();
    }
}
