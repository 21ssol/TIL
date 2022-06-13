package com.study.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service("com.study.model.MemoServiceImpl")
public class MemoServiceImpl implements MemoService {
  @Autowired
  private MemoMapper mapper;

  @Override
  public List<MemoDTO> list(Map map) {
    // TODO Auto-generated method stub
    return mapper.list(map);
  }
  
  //Test
  @GetMapping
  public String home() {
    
    return "/home";
  }

  @Override
  public int total(Map map) {
    // TODO Auto-generated method stub
    return mapper.total(map);
  }

  @Override
  public MemoDTO read(int memono) {
    // TODO Auto-generated method stub
    return mapper.read(memono);
  }

  @Override
  public void upViewcnt(int memono) {
    mapper.upViewcnt(memono);
  }

  @Override
  public int create(MemoDTO dto) {
    // TODO Auto-generated method stub
    return mapper.create(dto);
  }

  @Override
  public int update(MemoDTO dto) {
    // TODO Auto-generated method stub
    return mapper.update(dto);
  }

  @Override
  public int passCheck(Map map) {
    // TODO Auto-generated method stub
    return mapper.passCheck(map);
  }

  @Override
  public MemoDTO readReply(int memono) {
    // TODO Auto-generated method stub
    return mapper.readReply(memono);
  }

  @Override
  public int createReply(MemoDTO dto) {
    // TODO Auto-generated method stub
    return mapper.createReply(dto);
  }

  @Override
  public void upAnsnum(Map map) {
    mapper.upAnsnum(map);
  }

  @Override
  public int delete(int memono) {
    // TODO Auto-generated method stub
    return mapper.delete(memono);
  }
}
