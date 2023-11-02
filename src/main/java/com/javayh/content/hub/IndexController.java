package com.javayh.content.hub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-10-25
 */
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }


    @GetMapping("/external/box")
    public String share(@RequestParam(name = "i", required = false) String i,
                        @RequestParam(name = "p", required = false) String p) {
        return "share";
    }

}
