package com.study.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.study.model.AddrDAO;
import com.study.model.AddrDTO;
import com.study.utility.Utility;

@Controller
public class AddrController {

  @Autowired
  private AddrDAO dao;
  //Action 사용하기!! 실제 구현 코드 작성
  
  
  @GetMapping("/addr/delete/{addressnum}")
  public String delete(@PathVariable int addressnum) {
    
    boolean flag = dao.delete(addressnum);
    if(!flag) return "error";
    
    return "redirect:/addr/list";
  }
  
  @PostMapping("/addr/update")
  public String update(AddrDTO dto) {
    boolean flag = dao.update(dto);
    if(!flag) return "error";
    
    return "redirect:list";
  }
  
  @GetMapping("/addr/update/{addressnum}")
  public String update(@PathVariable int addressnum, Model model) {
    model.addAttribute("dto", dao.read(addressnum));
    
    return "/update";
  }
  
  @PostMapping("/addr/create")
  public String create(AddrDTO dto) {
    boolean flag = dao.create(dto);
    if(!flag) return "error";
    
    return "redirect:list";
  }
  
  @GetMapping("/addr/create")
  public String create() {
    
    return "/create";
  }
  
  @GetMapping("/addr/read/{addressnum}")
  public String read(@PathVariable int addressnum, Model model) {
    model.addAttribute("dto", dao.read(addressnum));
    
    return "/read";
  }
  
  @GetMapping("/addr/list")
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
    
    List<AddrDTO> list = dao.list(map);
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
  
  @GetMapping
  public String home() {
    
    return "/home";
  }
}
