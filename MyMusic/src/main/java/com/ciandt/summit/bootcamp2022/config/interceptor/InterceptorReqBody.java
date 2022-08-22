package com.ciandt.summit.bootcamp2022.config.interceptor;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class InterceptorReqBody {
    private Data data;

    public InterceptorReqBody(String name, String token) {
        this.data = new Data(name, token);
    }

    @Getter
    @AllArgsConstructor
    static class Data {
        private String name;
        private String token;
    }
}
