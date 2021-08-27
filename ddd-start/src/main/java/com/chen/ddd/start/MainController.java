package com.chen.ddd.start;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Spring Mvc的根路径、健康检查等。
 *
 * @author chengxu
 */
@Controller
public class MainController {

    /**
     * 健康检查，系统部署需要
     * 请不要删除！！
     */
    @GetMapping("/checkpreload.htm")
    @ResponseBody
    public String checkPreload() {
        return "success";
    }
}
