package hello.springmvc2.basic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
@Controller 애노테이션은
스프링의 리턴값이 String일시에 뷰 논리이름으로 인식한다.
@RestController 애노테이션이 붙으면
문자가 반환되면 스트링이 그대로 출력된다.
 */
@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name ="Spring";
        System.out.println("name = "+name);

        //로그 상태 레벨
        //모든 로그를 보고싶다면?
        log.trace("trace log={}", name);
        log.debug("debug log={}", name); // 개발 디버깅 정도
        log.info("info log={}", name); // 운영 서버 정도
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }


}
