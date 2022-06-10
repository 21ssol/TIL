package com.study.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

import com.study.model.BbsDTO;
import com.study.model.BbsService;
import com.study.utility.Utility;

@Controller
public class BbsController {
  @Autowired
  @Qualifier("com.study.model.BbsServiceImpl")
  private BbsService dao;
  
  
  @PostMapping("/bbs/reply")
  public String reply(BbsDTO dto) {
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
  
  @GetMapping("/bbs/reply/{bbsno}")
  //model 은 넘버를 가지고오고 viewPage로 가야되기 때문에 필요
  public String relpy(@PathVariable int bbsno, Model model) {
    model.addAttribute("dto", dao.readReply(bbsno));
    
    return "/reply";
  }
  
  @PostMapping("/bbs/delete")
  public String delete(int bbsno, String passwd) {
    Map map = new HashMap();
    map.put("bbsno", bbsno);   // 정수를 문지형으로 바꿈
    map.put("passwd", passwd);
    
    int pflag = dao.passCheck(map);
    int flag = 0;
    if(pflag == 1) flag = dao.delete(bbsno);
    
    if(pflag != 1) return "passwdError";
    else if(flag != 1) return "error";
    else return "redirect:list";
    
  }
  
  @GetMapping("/bbs/delete/{bbsno}")
  public String delete(@PathVariable int bbsno, Model model) {
    model.addAttribute("bbsno", bbsno);
    
    return "/delete";
  }
  
  
  @PostMapping("/bbs/update")
  public String update(BbsDTO dto) {
    
    //비밀번호 체크
    Map map = new HashMap();
    map.put("bbsno", dto.getBbsno());   // 정수를 문지형으로 바꿈
    map.put("passwd", dto.getPasswd());
    int pflag = dao.passCheck(map);
    int flag = 0;
    
    if(pflag==1) flag = dao.update(dto);
    
    if(pflag!=1) return "passwdError";
    else if(flag!=1) return "error";
    else {
      return "redirect:list";
    }
  }
  
  @GetMapping("/bbs/update/{bbsno}")
  public String update(@PathVariable int bbsno, Model model) {
    model.addAttribute("dto", dao.read(bbsno));   //모델은 저장만 한다.
    
    return "/update";
  }
  
  @GetMapping("/")
  public String home(Locale locale, Model model) {
    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
    String formattedDate = dateFormat.format(date);
    
    model.addAttribute("serverTime", formattedDate );
    
    return "/home";  //tiles.xml 에서 찾는다.
  }
  
  @GetMapping("/bbs/list")
  public String list(HttpServletRequest request) {
    
    //검색관련-------------
    String col = Utility.checkNull(request.getParameter("col"));
    String word = Utility.checkNull(request.getParameter("word"));

    if(col.equals("total")) word="";

    //페이징 관련 ---------------
    int nowPage = 1; //현재 보고있는 페이지
    if(request.getParameter("nowPage") != null) {
      nowPage = Integer.parseInt(request.getParameter("nowPage"));
    }
    int recordPerPage = 5;
    
    int sno = ((nowPage - 1) *recordPerPage); //페이징할 시작(처음에는 0이와야함)
    int eno = recordPerPage; //한 페이지당 갯수
    
 // 1. 모델 사용, 객체를 생성해서 사용한다.
    Map map = new HashMap();  //여기에 sno, eno, col, word가 들어간다.
    map.put("col", col);    //put을 통해 넣어준다.
    map.put("word", word);
    map.put("sno", sno);
    map.put("eno", eno);
    
    List<BbsDTO> list = dao.list(map);
    int total = dao.total(map);
    String paging = Utility.paging(total, nowPage, recordPerPage, col, word);
    
    // 2. request에 모델 사용한 결과를저장(view페이지에서 사용할 내용을 저장)
    request.setAttribute("list", list);
    request.setAttribute("paging", paging);
    request.setAttribute("col", col);
    request.setAttribute("word", word);
    request.setAttribute("nowPage", nowPage);
    
    return "/list";
  }
  
  @GetMapping("/bbs/create")
  public String create() {
    
    return "/create";
  }
  
  @PostMapping("/bbs/create")
  public String create(BbsDTO dto) {
    int cnt = dao.create(dto);
    if(cnt != 1) return "error";  //flag가 false라면
    
    return "redirect:list";  //같은 도메인 내에서의 경로
  }
  
  @GetMapping("/bbs/read/{bbsno}")
  //@PathVariable : Mapping url경로에 변수를 넣어주는 것
  public String read(@PathVariable int bbsno, Model model) {
    dao.upViewcnt(bbsno);
    //model은 request.setAttribute와 같음
    model.addAttribute("dto", dao.read(bbsno));
    
    return "/read";
  }
  
  
}
