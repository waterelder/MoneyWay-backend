package xyz.trackbuck;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lex on 26.09.16.
 */
@RestController
@EnableAutoConfiguration
public class Hello {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
}
