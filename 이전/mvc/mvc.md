mvc : 

뷰 : 사용자가 보는 것, 프론트 같은 느낌

컨트롤러는 잘 안보임

MVC모델1 : 브라우저에서 요청을 받고나서 필요시 자바빈즈를 사용, 그후 데이터 처리를 한다음에 그 데이터를 다시 jsp가 브라우져에 응답한다.

MVC 모델 2 : 브라우저에서 요청받아서 컨트롤러가 분석하고 그 결과에 따라 모델을 선택하고 사용하고 그 결과를 답을 뷰를 선택하고 뷰에 대한 결과를 브라우져에 응답한다.

- 브라우져에서 컨트롤러가요청을 받는다. 그러면 컨트롤러가 분석을 하고 객체 사용한 모델을 선택한다. 그 선택한 요청을 jsp에 보내고 자바빈즈가 CRUD 처리를 한다. 그러면 jsp(뷰)는 다시 데이터 처리 결과를 담아서 브라우져에 응답한다. 

---



* Controller(front) -> 요청 받고, 요청 기능 분석, 백앤드 컨트롤러 호출, view 포워드
* Action(백앤드 컨트롤러) -> 모델사용, request저장, view 선택(리턴)