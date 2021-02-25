package pure_Java_core.pure_core.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pure_Java_core.pure_core.common.MyLogger;

import javax.servlet.http.HttpServletRequest;
import java.security.Provider;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping(value = "log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURI().toString();
        myLogger.setRequestURL(requestURL);
        System.out.println("myLogger = " + myLogger.getClass());

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }
}
