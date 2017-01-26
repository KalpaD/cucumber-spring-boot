package org.kds.cucumbertest.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by KDS on 1/26/2017.
 */
@Configuration
public class ConnectionManager {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(clientHttpRequestFactory());
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory reqFactory = new HttpComponentsClientHttpRequestFactory();
        reqFactory.setReadTimeout(3000);
        reqFactory.setConnectTimeout(3000);
        return reqFactory;
    }
}
