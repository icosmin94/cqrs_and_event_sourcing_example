package com.springbank.oauth2_server.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@RequiredArgsConstructor
public class AuthServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    @Qualifier("authenticationManagerBean")
    public   AuthenticationManager authenticationManager;

    private final AuthServerProperties authServerProperties;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(authServerProperties.getSigningKey());
        return converter;
    }

    @Bean
    public JwtTokenStore jwtTokenStore(JwtAccessTokenConverter converter) {
        return new JwtTokenStore(converter);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpointsConfigurer) {
        endpointsConfigurer
                .authenticationManager(authenticationManager)
                .tokenStore(jwtTokenStore(jwtAccessTokenConverter()))
                .accessTokenConverter(jwtAccessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient(authServerProperties.getClientId())
                .secret(authServerProperties.getClientSecret())
                .scopes("read", "write")
                .authorizedGrantTypes("password", "refresh_token")
                .accessTokenValiditySeconds(authServerProperties.getTokenValidity())
                .refreshTokenValiditySeconds(authServerProperties.getRefreshTokenValidity());
    }


}
