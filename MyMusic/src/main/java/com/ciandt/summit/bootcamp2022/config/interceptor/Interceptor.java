package com.ciandt.summit.bootcamp2022.config.interceptor;

import com.ciandt.summit.bootcamp2022.exception.UnauthorizedRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Component
public class Interceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(Interceptor.class);

    private final String TOKENURL = "http://localhost:8081/api/v1/token/authorizer";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       String name = request.getHeader("name");
       String token = request.getHeader("token");

       if (name.isBlank() || token.isBlank()) {
        logger.warn("Credenciais inválidas, lançando exceção");
        throw new UnauthorizedRequestException("nome ou token de autenticação inválidos");
       }

        URI url = new URI(TOKENURL);

        InterceptorReqBody interceptorReqBody = new InterceptorReqBody(name, token);
        HttpEntity<InterceptorReqBody> httpEntity = new HttpEntity<>(interceptorReqBody);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, httpEntity, String.class);
            if (responseEntity.getStatusCodeValue() == 201) {
                logger.info("Autenticado com sucesso!");
                return true;
            }
        } catch (Exception e) {
            logger.info("Autenticação não autorizada pelo token provider");
            throw new UnauthorizedRequestException("Autenticação do token provider não autorizada");
        }

       return false;
    }
}
