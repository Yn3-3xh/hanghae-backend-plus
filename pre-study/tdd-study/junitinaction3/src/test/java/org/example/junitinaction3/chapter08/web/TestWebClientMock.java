package org.example.junitinaction3.chapter08.web;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClientMock {

    @Test
    public void testGetContentOk() throws Exception {
        MockHttpURLConnection mockConnection = new MockHttpURLConnection();
        mockConnection.setExpectedInputStream(new ByteArrayInputStream("It works".getBytes()));

        TestableWebClient client = new TestableWebClient();
        client.setHttpURLConnection(mockConnection);

        String result = client.getContent(new URI("http://127.0.0.1").toURL());

        assertEquals("It works", result);
    }

    private static class TestableWebClient extends WebClient1 {

        private HttpURLConnection connection;

        public void setHttpURLConnection(HttpURLConnection connection) {
            this.connection = connection;
        }

        public HttpURLConnection createHttpURLConnection(URL url) throws IOException {
            return this.connection;
        }
    }
}