package io.kimmking.rpcfx.demo.consumer;

import com.alibaba.fastjson.JSON;
import io.kimmking.rpcfx.api.Filter;
import io.kimmking.rpcfx.api.RpcfxRequest;
import io.kimmking.rpcfx.api.RpcfxResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Aspect
@Component
public class UserServiceAOP {

    @Autowired
    HttpClient httpClient;

    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    // 0. 替换动态代理 -> AOP，异常处理
    @Around("execution(public * io.kimmking.rpcfx.demo.api.*.*(*))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            RpcfxRequest request = new RpcfxRequest();
            request.setServiceClass(joinPoint.getTarget().getClass().getName());
            request.setMethod(joinPoint.getSignature().getName());
            request.setParams(joinPoint.getArgs());

            RpcfxResponse response = post(request, "http://localhost:8080/");
            return response;
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        } finally {
            System.out.println("around");
        }
    }

    private RpcfxResponse post(RpcfxRequest req, String url) throws IOException {
        String reqJson = JSON.toJSONString(req);
        System.out.println("req json: "+reqJson);

        // 1.可以复用client
        // 2.尝试使用httpclient或者netty client
        final Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSONTYPE, reqJson))
                .build();
        // todo
        String respJson = httpClient.send(request);
        System.out.println("resp json: "+respJson);
        return JSON.parseObject(respJson, RpcfxResponse.class);
    }

}
