package org.example.junitinaction3.chapter07;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWebClient {

    private WebClient client = new WebClient();

    @BeforeAll
    public static void setUp() throws Exception {
        Server server = new Server(8081);

        ContextHandler contentOkContextHandler = new ContextHandler("/testGetContentOk");
        contentOkContextHandler.setHandler(new TestGetContentOkHandler());

        ContextHandler contentErrorContextHandler = new ContextHandler("/testGetContentError");
        contentErrorContextHandler.setHandler(new TestGetContentServerErrorHandler());

        ContextHandler contentNotFoundContextHandler = new ContextHandler("/testGetContentNotFound");
        contentNotFoundContextHandler.setHandler(new TestGetContentNotFoundHandler());

        ContextHandlerCollection contexts = new ContextHandlerCollection();
        contexts.setHandlers(new Handler[]{contentOkContextHandler, contentErrorContextHandler, contentNotFoundContextHandler});

        server.setHandler(contexts);
        server.setStopAtShutdown(true);
        server.start();
    }

    @Test
    public void testGetContentOk() throws MalformedURLException, URISyntaxException {
        String workingContent = client.getContent(new URI("http://localhost:8081/testGetContentOk").toURL());
        assertEquals("It works", workingContent);
    }

    private static class TestGetContentOkHandler extends AbstractHandler {
        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setContentType("text/plain; charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            baseRequest.setHandled(true);

            try (OutputStream out = response.getOutputStream()) {
                String message = "It works";
                byte[] messageBytes = message.getBytes(StandardCharsets.ISO_8859_1);
                response.setContentLength(messageBytes.length);
                out.write(messageBytes);
                out.flush();
            }
        }
    }

    private static class TestGetContentServerErrorHandler extends AbstractHandler {
        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            baseRequest.setHandled(true);
        }
    }

    private static class TestGetContentNotFoundHandler extends AbstractHandler {
        @Override
        public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            baseRequest.setHandled(true);
        }
    }
}
