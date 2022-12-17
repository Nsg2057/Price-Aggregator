package edu.nyu.nsg2057.webscraper.configration;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionPoolConfiguration {
    private static final int MAX_TOTAL_CONNECTIONS = 100;
    private static final int MAX_CONNECTIONS_PER_ROUTE = 20;
    private static final int CONNECTION_TIMEOUT = (int) TimeUnit.SECONDS.toMillis(30);
    private static final int SOCKET_TIMEOUT = (int) TimeUnit.SECONDS.toMillis(30);

    @Bean
    public HttpClient httpClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(MAX_CONNECTIONS_PER_ROUTE);

        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setConnectionManager(connectionManager);
        builder.setConnectionTimeToLive(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);

        return builder.build();
    }
}
