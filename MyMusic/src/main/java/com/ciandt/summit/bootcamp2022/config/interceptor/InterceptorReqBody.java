package com.ciandt.summit.bootcamp2022.config.interceptor;

public class InterceptorReqBody {
    private Data data;

    public InterceptorReqBody(String name, String token) {
        this.data = new Data(name, token);
    }

    public Data getData() {
        return this.data;
    }

    static class Data {
        private String name;
        private String token;

        public Data(String name, String token) {
            this.name = name;
            this.token = token;
        }

        public String getName() {
            return this.name;
        }

        public String getToken() {
            return this.token;
        }
    }
}
