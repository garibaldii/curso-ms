package com.gft.book_service.environment;

import org.springframework.boot.web.server.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class InstanceInformationService implements ApplicationListener<WebServerInitializedEvent> {

    public String retrieveServerPort() {
        return port;
    }

    private String port;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.port = String.valueOf(event.getWebServer().getPort());
    }


}
