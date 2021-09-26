package com.acme.dbo;

public interface EnvVariables {
    String X_API_VERSION = "X-API-VERSION";
    String HOST = "http://localhost";
    int PORT = 8080;
    String HOME_PATH = "/dbo/api";
    String CLIENTS = "/client";
    String CLIENT_ID = "/client/{id}";
    String CLIENT_LOGIN = "/client/login/{clientLogin}";
}
