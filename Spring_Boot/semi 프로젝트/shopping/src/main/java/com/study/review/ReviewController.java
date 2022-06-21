package com.study.review;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
  private static final Logger log = LoggerFactory.getLogger(ReviewController.class); // 로그기록
  @Autowired
  @Qualifier("com.study.review.ReviewServiceImpl")
  private ReviewService service;
  
  
  
  
}
