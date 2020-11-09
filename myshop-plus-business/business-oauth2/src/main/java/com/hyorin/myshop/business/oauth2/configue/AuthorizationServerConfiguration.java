package com.hyorin.myshop.business.oauth2.configue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * 认证服务器配置
 *
 * @author hyorin
 * @version 1.0.0
 * @date 2020.10.14
 * @see com.hyorin.myshop.business.oauth2.configue
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * 注入用于支持password模式
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * tokenStore配置
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore());
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                //允许客户端访问/oauth/check_token检查token
                .checkTokenAccess("isAuthenticated")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //配置客户端
        clients
                //使用内存设置
                .inMemory()
                // client_id 客户端标识
                .withClient("client")
                // client_secret 客户端安全码 使用默认加密方式进行加密
                .secret(passwordEncoder.encode("secret"))
                // 客户端授权类型  密码模式和刷新令牌
                .authorizedGrantTypes("password", "refresh_token")
                //授权类型
                .scopes("backend")
                //这里可以设置对哪些资源有访问权限 不设置则全部资源可以访问
                .resourceIds("backend-resources")
                //设置令牌有效期限，这里设置时间为1天
                .accessTokenValiditySeconds(60 * 60 * 24)
                //设置刷新令牌有效期 这里为30天
                .refreshTokenValiditySeconds(60 * 60 * 24 * 30);
    }
}
