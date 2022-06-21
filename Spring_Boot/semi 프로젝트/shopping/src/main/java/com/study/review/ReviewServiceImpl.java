package com.study.review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service("com.study.review.ReviewServiceImpl")
public class ReviewServiceImpl implements ReviewService {
  @Autowired
  private ReviewMapper mapper;


}
