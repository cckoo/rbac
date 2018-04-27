package cn.cckoo.rbac;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping("/")
    public String dashboard() {
        return "index";
    }

}
