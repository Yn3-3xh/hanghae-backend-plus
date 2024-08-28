package org.example.junitinaction3.chapter07;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClientSkeleton {

    @BeforeAll
    public static void setUp() {
        // Jetty 서버를 시작하고
        // http://127.0.0.1:8081/testGetContentOk 경로로
        // 접근했을 때 "It works"를 반환하도록 설정한다.
    }

    @AfterAll
    public static void tearDown() {
        // Jetty 서버를 중지한다.
    }

    @Test
    @Disabled(value = "단순한 테스트 스켈레톤이므로 현재 이 테스트를 실행하면 실패한다.")
    public void testGetContentOk() throws MalformedURLException, URISyntaxException {
        WebClient client = new WebClient();
        String workingContent = client.getContent(new URI("http://127.0.0.1:8081/testGetContentOk").toURL());
        assertEquals("It works", workingContent);
    }
}
