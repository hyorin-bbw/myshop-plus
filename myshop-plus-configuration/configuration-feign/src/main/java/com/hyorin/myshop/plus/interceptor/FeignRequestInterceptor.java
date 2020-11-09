package com.hyorin.myshop.plus.interceptor;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;
import java.util.Enumeration;

/**
 * feign拦截器
 * <p>
 *
 * @author hyorin
 * @version 1.0.0
 * @Description 请求封装进feign 用于设置请求头 传递token
 * </p>
 * @date 2020.10.17
 * @see FeignRequestInterceptor
 */
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        //设置请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String value = request.getHeader(name);
                requestTemplate.header(name, value);
            }
        }

        //设置请求体 传递token
        Enumeration<String> parameterNames = request.getParameterNames();
        StringBuilder body = new StringBuilder();
        if (parameterNames != null) {
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                String value = request.getParameter(name);
                //讲access_token加入请求头
                if ("access_token".equals(name)) {
                    requestTemplate.header("authorization", "bearer" + value);
                }
                //其他参数加入请求体
                else {
                    body.append(name).append("=").append(value).append("&");
                }
            }
        }

        //设置请求体
        if (body.length() > 0) {
            //去掉最后一位的 &
            body.deleteCharAt(body.length() - 1);
            requestTemplate.body(Request.Body.bodyTemplate(body.toString(), Charset.defaultCharset()));
        }

    }
}
