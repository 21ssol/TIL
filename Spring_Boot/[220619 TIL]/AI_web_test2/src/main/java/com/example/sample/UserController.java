package com.example.sample;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
  @Autowired // new객체로 생성하지 않기 위해, 의존성 주입
  @Qualifier("com.example.sample.UserServiceImpl")
  private UserService service;
  private static final Logger log = LoggerFactory.getLogger(UserController.class);

  @GetMapping("/loginCk")
  @ResponseBody
  public UserDTO login(@RequestParam String id,
      @RequestParam String passwd) {
    
    Map map = new HashMap();
    map.put("id", id);
    map.put("passwd", passwd);

    UserDTO dto = service.userLogin(map);
    
    return dto;
  }
  
  @GetMapping("/")
  public String home(Locale locale, Model model) {
    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
    String formattedDate = dateFormat.format(date);

    model.addAttribute("serverTime", formattedDate);

    return "test1"; // tiles.xml 에서 찾는다.
  }
}
