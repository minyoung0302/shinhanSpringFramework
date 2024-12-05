<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>직원등록</title>
  <meta charset="utf-8">
  
  <style>
  input[required]{
  background-color : yellowgreen;}
  </style>
</head>
<body>

<div class="container mt-3">
<!-- include 디렉티브태그는 jsp를 합쳐서 컴파일한다 -->
<%@ include file="../common/header.jsp" %>
  <h2>신규 직원 등록</h2>
  
  
  <form action="insert.do" method="post">
    <div class="input-group mb-3">
      <span class="input-group-text">직원번호</span>
      <input type="number" required="required" class="form-control" placeholder="숫자입력" name="employee_id">
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">성</span>
      <input type="text" class="form-control" name="first_name">
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">이름</span>
      <input type="text" required="required" class="form-control" name="last_name">
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">이메일</span>
      <input type="text" required="required" class="form-control" name="email">
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">전화번호</span>
      <input type="text" class="form-control" name="phone_number">
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">입사일</span>
      <input type="date" value="2024-11-18" required="required" class="form-control" name="hire_date">
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">직책</span>
      <select required = "required" class="form-control" name = "job_id">
      	<c:forEach items="${joblist}" var="job">
      		<option ${job.job_id=='IT_PROG'?'selected':''} value="${job.job_id}">
      		${job.job_id}/${job.job_title}</option>
      	</c:forEach>
      </select>
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">급여</span>
      <input type="number" class="form-control" name="salary" value="10000">
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">커미션</span>
      <input type="text" pattern="[0]\.[0-9]{1,2}" class="form-control" name="commission_pct">
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">매니저번호</span>
      <select class="form-control" name="manager_id">
      <option value=-1>매니저없음</option>
      	<c:forEach items= "${managerlist}" var="emp">
      		<option value = "${emp.employee_id}">${emp.first_name}/${emp.last_name}</option>
      	</c:forEach>
      </select>
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">부서번호</span>
      
      <select class="form-control" name="department_id">
      <option value=-1>부서없음</option>
      	<c:forEach items="${deptlist}" var="dept">
      	<option value = "${dept.department_id}">
      	${dept.department_name}
      	</option>
      	
      	</c:forEach>
      </select>
     
    </div>
    <input type = "hidden" name="phone" value="010-9874-6521">

    <button type="submit" class="btn btn-primary">신규 직원 등록</button>
  </form>
</div>
<script>
	var d = new Date().toISOString().split("T")[0];
	document.querySelector("input[name='hire_date']").value = '2000-01-01';
</script>

</body>
</html>