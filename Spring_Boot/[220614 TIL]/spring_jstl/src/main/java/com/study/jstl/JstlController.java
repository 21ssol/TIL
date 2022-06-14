package com.study.jstl;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JstlController {

  @GetMapping
   public String home(Model model) {
    
    // ELDTO dto = new ELDTO("다니엘 레드클리프", "해리 포터와 불의 잔"); 
    // model.addAttribute("dto", dto);
    // model.addAttribute("movie", "해리포터");  >>Type3,4 에 나옴
    
    //access.jsp
//   model.addAttribute("name", "가길동"); 
//   model.addAttribute("tot", 190); 
//   model.addAttribute("avg", 95); 
    
    //viewToday.jsp
//    Date today = new Date();
//    model.addAttribute("today", today); 
    
    //forEachTag.jsp
//    HashMap mapData = new HashMap();
//    mapData.put("name", "아로미"); 
//    mapData.put("today", new java.util.Date()); 
//    
//    model.addAttribute("map", mapData);
     
     return "jstl/useFunctions";
   }
}
