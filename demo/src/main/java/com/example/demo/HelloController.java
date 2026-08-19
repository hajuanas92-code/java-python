
package com.example.demo;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @GetMapping("/")
    public String sayHello() {
        return "Hello World";
    }

    @GetMapping("/hello/{name}")
    public String sayHelloToName(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    @GetMapping("/greet")
    public String greetWithParam(@RequestParam(defaultValue = "Guest") String name) {
        return "Hello, " + name + "! (via query param)";
    }

    @PostMapping("/greet")
    public String greetWithPost(@RequestParam String name) {
        return "Hello, " + name + "! (received via POST)";
    }
}