package com.hyorin.myshop.plus.business.login.controller;

import com.google.common.collect.Maps;
import com.hyorin.myshop.plus.business.commons.util.MapperUtils;
import com.hyorin.myshop.plus.business.commons.util.OkHttpClientUtil;
import com.hyorin.myshop.plus.commons.domain.UmsAdmin;
import com.hyorin.myshop.plus.commons.dto.LoginInfo;
import com.hyorin.myshop.plus.commons.dto.LoginParams;
import com.hyorin.myshop.plus.commons.dto.ResponseResult;
import com.hyorin.myshop.plus.profile.feign.ProfileFeign;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 用户登录 前端控制器
 * </p>
 *
 * @author hyorin
 * @since 2020-10-15
 */
@RestController
public class UserLoginController {

    private static final String URL_OAUTH_TOKEN = "http://localhost:9902/oauth/token";

    @Value("business.oauth2.grant_type")
    public String oauth2GrantType;

    @Value("business.oauth2.client_id")
    public String oauth2ClientId;

    @Value("business.oauth2.client_secret")
    public String oauth2ClientSecret;

    @Autowired
    public UserDetailsService userDetailsService;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public TokenStore tokenStore;

    @Autowired
    public ProfileFeign profileFeign;

    /**
     * 登录
     *
     * @param loginParams 登录参数实体类 {@link LoginParams}
     * @return {@link ResponseResult}
     */
    @PostMapping("/user/login")
    public ResponseResult<Map<String, Object>> login(@RequestBody LoginParams loginParams) {
        Map<String, Object> result = Maps.newHashMap();

        //密码验证
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginParams.getUsername());
        if (userDetails == null || !passwordEncoder.matches(userDetails.getPassword(), loginParams.getPassword())) {
            return new ResponseResult<>(ResponseResult.Code.SUCCESS.getValue(), "用户名或密码错误", null);
        }

        //封装参数请求登录接口
        Map<String, String> param = Maps.newHashMap();
        param.put("password", loginParams.getPassword());
        param.put("username", loginParams.getUsername());
        param.put("client_id", oauth2ClientId);
        param.put("client_secret", oauth2ClientSecret);
        param.put("grant_type", oauth2GrantType);

        try {
            //解析并返回相应结果
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, param);
            String jsonString = Objects.requireNonNull(response.body().string());
            Map<String, Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token", token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseResult<>(ResponseResult.Code.SUCCESS.getValue(), "登录成功", result);
    }

    /**
     * 获取用户信息
     *
     * @param request
     * @return {@link ResponseResult}
     */
    @RequestMapping(value = "/user/info")
    public ResponseResult<LoginInfo> info(HttpServletRequest request) throws Exception {

        //获取token
        String token = request.getParameter("access_token");
        //获取个人信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String jsonString = profileFeign.info(authentication.getName());
        UmsAdmin umsAdmin = MapperUtils.json2pojoByTree(jsonString, "data", UmsAdmin.class);
        //封装结果并返回
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setName(umsAdmin.getNickname());
        loginInfo.setToken(umsAdmin.getEmail());
        return new ResponseResult<>(ResponseResult.Code.SUCCESS.getValue(), "获取用户信息成功", loginInfo);
    }

    /**
     * 注销
     *
     * @param request
     * @return {@link ResponseResult}
     */
    @RequestMapping(value = "/user/logout")
    public ResponseResult<Void> logout(HttpServletRequest request) {
        //获取token
        String token = request.getParameter("access_token");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
        //删除token以注销
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return new ResponseResult<>(ResponseResult.Code.SUCCESS.getValue(), "注销成功");
    }
}
