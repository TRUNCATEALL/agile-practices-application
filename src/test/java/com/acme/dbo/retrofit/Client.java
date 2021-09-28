package com.acme.dbo.retrofit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import javax.annotation.processing.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)

@Generated("jsonschema2pojo")
public class Client {
    @JsonProperty("id")
    @JsonPropertyDescription("Client id")
    private int id;

    @JsonProperty("login")
    @JsonPropertyDescription("Client login")
    private String login;

    @JsonProperty("salt")
    @JsonPropertyDescription("Client salt")
    private String salt;

    @JsonProperty("secret")
    @JsonPropertyDescription("Client secret")
    private String secret;

    @JsonProperty("created")
    @JsonPropertyDescription("Client created")
    private String created;

    @JsonProperty("enabled")
    @JsonPropertyDescription("Client enabled")
    private Boolean enabled;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Client(@JsonProperty("login") String login, @JsonProperty("salt") String salt, @JsonProperty("secret") String secret) {
        this.login = login;
        this.salt = salt;
        this.secret = secret;
    }

    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    @JsonProperty("login")
    public void setLogin(String login) {
        this.login = login;
    }

    @JsonProperty("salt")
    public String getSalt() {
        return salt;
    }

    @JsonProperty("salt")
    public void setSalt(String salt) {
        this.salt = salt;
    }

    @JsonProperty("secret")
    public String getSecret() {
        return secret;
    }

    @JsonProperty("secret")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", salt='" + salt + '\'' +
                ", secret='" + secret + '\'' +
                ", created='" + created + '\'' +
                ", enabled=" + enabled +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
