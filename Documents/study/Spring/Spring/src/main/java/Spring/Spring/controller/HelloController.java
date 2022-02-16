package Spring.Spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello"; //templeates에 있는 hello.html과 연결
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name")String name, Model model){
        model.addAttribute("name",name);
        //hello-template.html에 Model채로 넘겨줌 
        return "hello-template";
    }
    @GetMapping("hello-string")
    @ResponseBody
    //http body구역에 직접 넣어준다. 경로가 없다. 그렇기 때문에 웹 브라우저 상에서는 hello'+name'만 나오게 된다.
    public String helloSpring(@RequestParam("name")String name){
        return "hello"+name;
    }

    //JSON 방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;

    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
