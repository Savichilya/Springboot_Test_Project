package by.savich.project.springboot.service;

import by.savich.project.springboot.controller.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TestService {
    private final static Logger log = LoggerFactory.getLogger(TestService.class);

    public String reverse(String word) {
        log.info("Class instance is: {}", this);
        return new StringBuilder(word).reverse().toString();
    }
}
