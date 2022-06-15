package com.study.model;
 
import java.util.List;
import java.util.Map;
 
public interface ReplyMapper{
 
   // 댓글 생성
   int create(ReplyDTO replyDTO);
 
   List<ReplyDTO> list(Map map);
 
   // 댓글 하나의 내용보는거
   ReplyDTO read(int rnum);
  
   // 댓글의 content만 수정(자기것만)
   int update(ReplyDTO replyDTO); 
   
   // 댓글 레코드 삭제
   int delete(int rnum);
 
   // 페이징처리
   int total(int bbsno);
}