package com.study.model;

import java.util.List;
import java.util.Map;

public interface MemoMapper {
  List<MemoDTO> list(Map map);
  int total(Map map);
  MemoDTO read(int memono);
  void upViewcnt(int memono);
  int create(MemoDTO dto);
  int update(MemoDTO dto);
  int passCheck(Map map);
  MemoDTO readReply(int memono);
  int createReply(MemoDTO dto);
  void upAnsnum(Map map);
  int delete(int memono);
  
}
