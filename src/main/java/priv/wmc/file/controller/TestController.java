package priv.wmc.file.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wang Mincong
 * @date 2020-08-26 12:14:25
 */
@Api(tags = "test")
@RequestMapping("/test")
@RestController
public class TestController {

    @GetMapping
    public String test() {
        return "456";
    }

}
