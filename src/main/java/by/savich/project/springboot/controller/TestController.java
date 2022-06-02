package by.savich.project.springboot.controller;

import by.savich.project.springboot.service.TestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    private final TestService ts;

    public TestController(TestService ts) {
        this.ts = ts;
    }

    @GetMapping("/revers")
    public StringBuilder revers() {
        return ts.revers(new StringBuilder("cat"));
    }


}