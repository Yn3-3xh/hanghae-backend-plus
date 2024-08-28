package org.example.junitinaction3.chapter07;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.server.handler.ContextHandler;

public class JettySample {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8081);

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase("./build.gradle");
        resourceHandler.setDirectoriesListed(true);

        ContextHandler contextHandler = new ContextHandler("/");
        contextHandler.setHandler(resourceHandler);

        server.setHandler(contextHandler);

        server.setStopAtShutdown(true);

        server.start();
    }
}
