package com.acme.dbo.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {
    private Retrofit retrofit;
    private ClientService service;

    @BeforeEach
    public void prepare() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));

        retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("http://localhost:8080/dbo/api/")
                .client(httpClient.build())
                .build();

        service = retrofit.create(ClientService.class);
    }

    @Test
    @DisplayName("Test for GET all clients")
    public void shouldGetAllClients() throws IOException {
        service.getClients().execute().body().forEach(System.out::println);
    }

    @Test
    @DisplayName("Проверка создания клиента через POST")
    public void shouldPostClient() throws IOException {
        String newLogin = "test_" + randomInt(1_000, 10_000) + "@test.com";
        String salt = "some-salt";
        String secret = UUID.randomUUID().toString();

        Client actualClient = service.createClient(new Client(newLogin, salt, secret))
                .execute().body();

        assertNotNull(actualClient, "В ответе вернулся пустой массив данных по клиенту");
        String actualLogin = actualClient.getLogin();

        assertEquals(newLogin, actualLogin);
    }

    public static int randomInt(int min, int max) {
        Random rn = new Random();
        return rn.nextInt(max - min + 1) + min;
    }
}
