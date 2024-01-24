package hello.springmvc2.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {
    private Logger log = LoggerFactory.getLogger(getClass());
    @RequestMapping("/hello-basic")
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value="/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1(){
        return "mappingGetV1";
    }

    @GetMapping(value="/mapping-get-v2")
    public String mappingGetV2(){
        return "mappingGetV2";
    }
    /**
     * PahtVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String userId -> @PathVariable userId
     * /mapping/userA
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId={}",data);
        return "ok";
    }

    @GetMapping("/mapping2/{userId}")
    public String mappingPath2(@PathVariable String userId){
        log.info("mappingPath2 userId={}",userId);
        return "ok";
    }

    /**
     * PathVariable 다중 사용
     */
    @GetMapping("/mapping/users/{userId}/orders/{orerId}")
    public String mappingPath(@PathVariable String userId, @PathVariable Long orderId){
        log.info("mappingPath userId={}, orderId={}",userId,orderId);
        return "ok";
    }

    /**
     * 파라미터 추가 매핑
     * params = "mode"
     * params = "!mode"
     * params = "mode=debug"
     * params = "mode!=debug"
     * params = {"mode=debug", "data=good"}
     */
    @GetMapping(value="/mapping-param", params="mode=debug")
    public String mappingParam(){
        log.info("mapppingParam");
        return "ok";
    }

    /**
     * 특정 헤더 조건 매핑
     * headers="mode"
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=de
     */
    @GetMapping(value="/mapping-header",headers="mode=debug")
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * 미디어 타입 헤더기반 추가 매핑
     * consumes="application/json"
     * consumes="!apllication/json"
     * consumes="applicaion/*"
     * consumes="*\/*"
     * Media.Type.APPLICATION_JSON_VALUE
     */
    @PostMapping(value="/mapping-consume", consumes="application/json")
    public String mappingConsumes(){
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * 미디어 타입 조건 매핑
     * Accept 헤더 기반
     * produces = "text/html"
     * produces = "!text/html"
     * produces="text/*"
     * produces="*\/*"
     */
    @PostMapping(value="/mapping-produce", produces="text/html")
    public String mappingProduces(){
        log.info("mappingProduces");
        return "ok";
    }
}
