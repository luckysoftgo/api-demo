package com.javabase.demo.web.mq;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/activemq")
public class WelcomeController {
    
    @RequestMapping(value="/welcome",method=RequestMethod.GET)
    public ModelAndView welcome(){
        System.out.println("------------welcome");        
        ModelAndView mv = new ModelAndView();
        mv.setViewName("welcome");
        return mv;
    }
}
