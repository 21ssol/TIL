package com.study.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.study.model.MemoDTO;
import com.study.model.MemoService;
import com.study.utility.Utility;

@Controller
public class MemoController {
  @Autowired
  @Qualifier("com.study.model.MemoServiceImpl")  //명확하게 지정
  private MemoService dao;
  
  
  @PostMapping("/memo/delete")
  public String delete(@RequestParam Map<String, String> map) {
    
    int memono = Integer.parseInt(map.get("memono"));
    int pflag = dao.passCheck(map);
    int flag = 0;
    if(pflag == 1) flag = dao.delete(memono);
    
    if(pflag != 1) return "passwdError";
    else if(flag != 1) return "error";
    else return "redirect:list";
    
  }
  
  @GetMapping("/memo/delete/{memono}")
  public String delete(@PathVariable int memono, Model model) {
    model.addAttribute("memono", memono);
    
    return "/delete";
  }
  
  //오류
  @PostMapping("/memo/reply")
  public String reply(MemoDTO dto) {
    Map map = new HashMap();
    map.put("grpno", dto.getGrpno());
    map.put("ansnum", dto.getAnsnum());
    dao.upAnsnum(map);
    if(dao.createReply(dto)==1) {
      return "redirect:list";
    } else {
      return "error";
    }
    
  }
  
  @GetMapping("/memo/reply/{memono}")
  public String reply(@PathVariable int memono, Model model) {
    model.addAttribute("dto", dao.readReply(memono));
    
    return "/reply";
  }
  
  @PostMapping("/memo/update")
  public String update(MemoDTO dto) {
    
    //비밀번호 체크
    Map map = new HashMap();
    map.put("memono", String.valueOf(dto.getMemono()));   // 정수를 문자형으로 바꿈
    map.put("passwd", dto.getPasswd());
    int pflag = dao.passCheck(map);
    int flag = 0;
    
    if(pflag == 1) flag = dao.update(dto);
    
    if(pflag != 1) return "passwdError";
    else if(flag != 1) return "error";
    else {
      return "redirect:list";
    }
  }
  
  @GetMapping("/memo/update/{memono}")
  public String update(@PathVariable int memono, Model model) {
    model.addAttribute("dto", dao.read(memono));
    
    return "/update";
  }
  
  @PostMapping("/memo/create")
  public String create(MemoDTO dto) {
    int cnt = dao.create(dto);
    if(cnt != 1) return "error";
    
    return "redirect:list";
  }
  
  @GetMapping("/memo/create")
  public String create() {
    
    return "/create";
  }
  
  @GetMapping("/memo/read/{memono}")
  public String read(@PathVariable int memono, Model model) {
    dao.upViewcnt(memono);
    model.addAttribute("dto", dao.read(memono));
    
    return "/read";
  }
  
  @GetMapping("/memo/list")
  public String list(HttpServletRequest request) {
    
    //검색관련
    String col = Utility.checkNull(request.getParameter("col"));
    String word = Utility.checkNull(request.getParameter("word"));
    
    if(col.equals("total")) word = "";
    
    //페이징관련
    int nowPage = 1;
    if(request.getParameter("nowPage") != null) {
      nowPage = Integer.parseInt(request.getParameter("nowPage"));
    }
    int recordPerPage = 5;
    int sno = ((nowPage - 1) * recordPerPage); //페이징할 시작(처음에는 0이와야함)
    int eno = recordPerPage; //한 페이지당 갯수
    
    //1. 모델 사용, 객체를 생성해서 사용한다.
    Map map = new HashMap();  //여기에 sno, eno, col, word가 들어간다.
    map.put("col", col);    //put을 통해 넣어준다.
    map.put("word", word);
    map.put("sno", sno);
    map.put("eno", eno);
    
    List<MemoDTO> list = dao.list(map);
    int total = dao.total(map);
    String paging = Utility.paging(total, nowPage, recordPerPage, col, word);
    
    request.setAttribute("list", list);
    request.setAttribute("paging", paging);
    request.setAttribute("col", col);
    request.setAttribute("word", word);
    request.setAttribute("nowPage", nowPage);
   
    return "/list";
  }
  
  @GetMapping
  public String home() {
    
    return "/home";
  }
}
