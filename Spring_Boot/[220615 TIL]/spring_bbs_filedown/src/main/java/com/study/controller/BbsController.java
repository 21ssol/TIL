package com.study.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.bbs.UploadBbs;
import com.study.model.BbsDTO;
import com.study.model.BbsService;
import com.study.model.ReplyService;
import com.study.utility.Utility;

@Controller
public class BbsController {
  @Autowired // new객체로 생성하지 않기 위해, 의존성 주입
  @Qualifier("com.study.model.BbsServiceImpl")
  private BbsService dao;
  
  @Autowired
  @Qualifier("com.study.model.ReplyServiceImpl")
  private ReplyService rservice;

  @PostMapping("/bbs/reply")
  public String reply(BbsDTO dto) {

    String upDir = UploadBbs.getUploadDir();
    if (dto.getFilenameMF().getSize() > 0) {
      dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), upDir));
      dto.setFilesize((int) dto.getFilenameMF().getSize());
    }

    Map map = new HashMap();
    map.put("grpno", dto.getGrpno());
    map.put("ansnum", dto.getAnsnum());
    dao.upAnsnum(map);
    if (dao.createReply(dto) == 1) {
      return "redirect:list";
    } else {
      return "error";
    }

  }

  @GetMapping("/bbs/reply/{bbsno}")
  // model 은 넘버를 가지고오고 viewPage로 가야되기 때문에 필요
  public String relpy(@PathVariable int bbsno, Model model) {
    model.addAttribute("dto", dao.readReply(bbsno));

    return "/reply";
  }

  @PostMapping("/bbs/delete")
  public String delete(int bbsno, String passwd, String oldfile) {
    // 패스워드 체크
    Map map = new HashMap();
    map.put("bbsno", bbsno); // 정수를 문지형으로 바꿈
    map.put("passwd", passwd);

    // upDir(경로) 받아오기
    String upDir = UploadBbs.getUploadDir();

    int pflag = dao.passCheck(map);
    int flag = 0;
    if (pflag == 1) {
      flag = dao.delete(bbsno);
      // 파일업로드 delete구현
      if (oldfile != null && !oldfile.equals(""))
        Utility.deleteFile(upDir, oldfile);
    }
    if (pflag != 1)
      return "passwdError";
    else if (flag != 1)
      return "error";
    else
      return "redirect:list";

  }

  @GetMapping("/bbs/delete/{bbsno}")
  public String delete(@PathVariable int bbsno, String oldfile, Model model) {

    int cnt = dao.checkRefnum(bbsno); // bbsno가 부모글인지 확인
    boolean flag = false;
    if (cnt > 0)
      flag = true;

    model.addAttribute("bbsno", bbsno);
    model.addAttribute("oldfile", oldfile);
    model.addAttribute("flag", flag); // true : 답변글이면 삭제X

    return "/delete";
  }

  @PostMapping("/bbs/update")
  public String update(BbsDTO dto, String oldfile) {

    // 파일업로드 수정코드
    String upDir = UploadBbs.getUploadDir();
    if (dto.getFilenameMF().getSize() > 0) { // 0보다 크면 업로드가 된거임
      // oldfile이라는 명이 dto에 없기 때문에 바꿔야 한다.
      if (oldfile != null && !oldfile.equals(""))
        Utility.deleteFile(upDir, oldfile); // null이 아닐경우에만 지우겠다.
      // 업로드 처리
      dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), upDir)); // 파일을 저장하고 dto에 순수파일명 저장
      dto.setFilesize((int) dto.getFilenameMF().getSize()); // long타입이니까 int로 다운캐스팅
    }

    // 비밀번호 체크
    Map map = new HashMap();
    map.put("bbsno", dto.getBbsno()); // 정수를 문지형으로 바꿈
    map.put("passwd", dto.getPasswd());
    int pflag = dao.passCheck(map);
    int flag = 0;

    if (pflag == 1)
      flag = dao.update(dto);

    if (pflag != 1)
      return "passwdError";
    else if (flag != 1)
      return "error";
    else {
      return "redirect:list";
    }
  }

  @GetMapping("/bbs/update/{bbsno}")
  public String update(@PathVariable int bbsno, Model model) {
    model.addAttribute("dto", dao.read(bbsno)); // 모델은 저장만 한다.

    return "/update";
  }

  @GetMapping("/")
  public String home(Locale locale, Model model) {
    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
    String formattedDate = dateFormat.format(date);

    model.addAttribute("serverTime", formattedDate);

    return "/home"; // tiles.xml 에서 찾는다.
  }

  @GetMapping("/bbs/list")
  public String list(HttpServletRequest request) {

    // 검색관련-------------
    String col = Utility.checkNull(request.getParameter("col"));
    String word = Utility.checkNull(request.getParameter("word"));

    if (col.equals("total"))
      word = "";

    // 페이징 관련 ---------------
    int nowPage = 1; // 현재 보고있는 페이지
    if (request.getParameter("nowPage") != null) {
      nowPage = Integer.parseInt(request.getParameter("nowPage"));
    }
    int recordPerPage = 5;

    int sno = ((nowPage - 1) * recordPerPage); // 페이징할 시작(처음에는 0이와야함)
    int eno = recordPerPage; // 한 페이지당 갯수

    // 1. 모델 사용, 객체를 생성해서 사용한다.
    Map map = new HashMap(); // 여기에 sno, eno, col, word가 들어간다.
    map.put("col", col); // put을 통해 넣어준다.
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
    
    // list.jsp에서 댓글 갯수 가져올 <util:rcount(num,rmapper)>에서 사용할 
    // rmapper(ReplyMapper)의 값을 request 객체에 담는다.
    request.setAttribute("rservice", rservice);

    return "/list";
  }

  @GetMapping("/bbs/create")
  public String create() {

    return "/create";
  }

  @PostMapping("/bbs/create")
  public String create(BbsDTO dto) {

    // 파일 업로드 코드
    String upDir = UploadBbs.getUploadDir();
    // 현재 업로드 되어진애가 파일이 존재한다면 0보다 클것
    if (dto.getFilenameMF().getSize() > 0) { // 브라우저에서 파일을 보냈다면
      dto.setFilename(Utility.saveFileSpring(dto.getFilenameMF(), upDir)); // 파일명을 dto에 저장
      dto.setFilesize((int) dto.getFilenameMF().getSize()); // dto에 파일 사이즈 저장
    }

    int cnt = dao.create(dto);
    if (cnt != 1)
      return "error"; // flag가 안되었다면

    return "redirect:list"; // 같은 도메인 내에서의 경로
  }

  @GetMapping("/bbs/read/{bbsno}")
  // @PathVariable : Mapping url경로에 변수를 넣어주는 것
  public String read(@PathVariable int bbsno, Model model, HttpServletRequest request) {
    dao.upViewcnt(bbsno); // 위에서 이제 dao는 Service를 호출함
    // model은 request.setAttribute와 같음
    model.addAttribute("dto", dao.read(bbsno)); // model에 데이터 담음,dto에 저장해서 한건의 레코드 저장
    /* 댓글 관련 시작, 댓글은 nPage */
    int nPage = 1;
    if (request.getParameter("nPage") != null) {
      nPage = Integer.parseInt(request.getParameter("nPage"));
    }
    int recordPerPage = 3;

    // mysql
    int sno = (nPage - 1) * recordPerPage;
    int eno = recordPerPage;

    Map map = new HashMap();
    map.put("sno", sno);
    map.put("eno", eno);
    map.put("nPage", nPage);

    model.addAllAttributes(map);

    /* 댓글 처리 끝 */

    return "/read";
  }

  // produce는 데이터가 변환되는 방식은 json형식이다.
  @PostMapping(value = "/bbs/delete_Ajax", produces = "application/json;charset=UTF-8")
  @ResponseBody // return(map2)할 때 데이터 반환
  // 리턴되면 데이터는 Map객체로 반환된다.
  public Map<String, String> delete_Ajax(@RequestBody BbsDTO dto, HttpServletRequest request) {
    boolean cflag = false;
    int cnt = dao.checkRefnum(dto.getBbsno());
    if (cnt > 0)
      cflag = true; // 부모글
    String upDir = UploadBbs.getUploadDir();
    Map map = new HashMap();
    map.put("bbsno", dto.getBbsno());
    map.put("passwd", dto.getPasswd());

    boolean pflag = false;
    boolean flag = false;

    // 패스워드 체크
    if (!cflag) {
      int cnt2 = dao.passCheck(map);
      if (cnt2 > 0)
        pflag = true;
    }
    if (pflag) {
      if (dto.getFilename() != null) // 이미 파일로 올라온게 있다면
        Utility.deleteFile(upDir, dto.getFilename()); // 먼저 파일 지우고 delete처리
      int cnt3 = dao.delete(dto.getBbsno());
      if (cnt3 > 0)
        flag = true;
    }

    // Map을 사용하면 json 형식으로 사용할 수 있기 때문
    Map<String, String> map2 = new HashMap<String, String>();

    if (cflag) {
      map2.put("str", "답변있는 글이므로 삭제할 수 없습니다");
      map2.put("color", "blue");
    } else if (!pflag) {
      map2.put("str", "패스워드가 잘못입력되었습니다");
      map2.put("color", "blue");
    } else if (flag) {
      map2.put("str", "삭제 처리되었습니다");
      map2.put("color", "blue");
    } else {
      map2.put("str", "삭제중 에러가 발생했습니다");
      map2.put("color", "blue");
    }

    return map2;
  }

  @GetMapping("/bbs/delete_Ajax")
  public String delete_Ajax() {

    return "/bbs/delete_Ajax";
  }

  @GetMapping("/bbs/fileDown")
  public void fileDown(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // 저장 폴더를 절대 경로로 변환
    String dir = UploadBbs.getUploadDir();
    // 파일명 받기
    String filename = request.getParameter("filename");
    byte[] files = FileUtils.readFileToByteArray(new File(dir, filename));
    response.setHeader("Content-disposition", "attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");
    // Content-Transfer-Encoding : 전송 데이타의 body를 인코딩한 방법을 표시함.
    response.setHeader("Content-Transfer-Encoding", "binary");
    /**
     * Content-Disposition가 attachment와 함게 설정되었다면 'Save As'로 파일을 제안하는지 여부에 따라 브라우저가
     * 실행한다.
     */
    response.setContentType("application/octet-stream");
    response.setContentLength(files.length);
    response.getOutputStream().write(files);
    response.getOutputStream().flush();
    response.getOutputStream().close();
  }

}
