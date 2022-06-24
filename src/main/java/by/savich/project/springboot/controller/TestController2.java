package by.savich.project.springboot.controller;

import by.savich.project.springboot.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test2")
public class TestController2 {
    private final TestService ts;

    public TestController2(TestService ts) {
        this.ts = ts;
    }

    @GetMapping("/reverse")
    public String revers() {
        return ts.reverse("dog");
    }
}