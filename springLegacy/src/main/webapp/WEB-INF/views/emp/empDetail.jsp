<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>직원정보수정</title>
  <meta charset="utf-8">

  <style>
  input[required]{
  color : yellowgreen;}
  h2{background-color:pink}
  [readonly]{background-color:lightgray; important}
  </style>
</head>
<body>

<div class="container mt-3">
<!-- include 디렉티브태그는 jsp를 합쳐서 컴파일한다 -->
<%@ include file="../common/header.jsp" %>
  <h2>직원 정보 수정</h2>
  
  
  <form action="detail.do" method="post">
    <div class="input-group mb-3">
      <span class="input-group-text">직원번호</span>
      <input type="number" required="required" readonly="readonly" class="form-control" value="${empInfo.employee_id}" name="employee_id">
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">성</span>
      <input type="text" class="form-control" value="${empInfo.first_name}" name="first_name">
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">이름</span>
      <input type="text" required="required" class="form-control" value="${empInfo.last_name}"name="last_name">
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">이메일</span>
      <input type="text" required="required" class="form-control" value="${empInfo.email}" name="email">
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">전화번호</span>
      <input type="text" class="form-control" value="${empInfo.phone_number}" name="phone_number">
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">입사일</span>
      <input type="date" value="2024-11-18" required="required" class="form-control" value="${empInfo.hire_date}" name="hire_date">
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">직책</span>
      <select required = "required" class="form-control" name = "job_id">
      	<c:forEach items="${joblist}" var="job">
      		<option ${job.job_id==empInfo.job_id?'selected':''} value="${job.job_id}">
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
      		<option ${empInfo.manager_id==emp.employee_id?'selected':''} value = "${emp.employee_id}">${emp.first_name}/${emp.last_name}</option>
      	</c:forEach>
      </select>
    </div>
    <div class="input-group mb-3">
      <span class="input-group-text">부서번호</span>
      
      <select class="form-control" name="department_id">
      <option value=-1>부서없음</option>
      	<c:forEach items="${deptlist}" var="dept">
      	<option ${empInfo.department_id==dept.department_id?'selected':'' } value = "${dept.department_id}">
      	${dept.department_name}
      	</option>
      	
      	</c:forEach>
      </select>
     
    </div>

    <button type="submit" class="btn btn-primary">직원 정보 수정</button>
  </form>
</div>


</body>
</html>