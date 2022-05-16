<%@ page contentType="text/html; charset=UTF-8" %>
<%
String[] titles = {
"  급  여  명  세  서 ", 
"기 본 급: 2,500,000 원",
"야근수당:   100,000 원",
"주말수당:   200,000 원",
"세금합계:   100,000 원",
"실수령액: 2,900,000 원",
"입금 예정일: 2021-10-01일 수고하셨습니다.",
};
%> 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style type="text/css"> 
        *{ 
          font-family: gulim; 
          font-size: 20px; 
          color:green;
          font-weight: bold;
        } 
         
    </style>
</head>
<body>
    <ul>
<%
String id = request.getParameter("id");
String passwd = request.getParameter("passwd");
System.out.println(id);
if (id.equals("테스트") && passwd.equals("테스트")){
    for (int i = 0 ; i < titles.length ; i++) {
        out.println("<li>" + titles[i] + "<br/><br/>");
    }
}else{
    out.println("<li>인증 실패입니다.<br/><br/>");
    out.println("<li>다시 시도해 주세요.<br/><br/>");
}
%>    
</ul>
</body>
</html>