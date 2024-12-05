<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/header.jsp" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var = "path" value="${pageContext.servletContext.contextPath}" scope="application"></c:set>
		
		
		
		
		
		
		<table class="table table-striped table-hover">
			<tr>
				<td>순서</td>
				<td>직원번호</td>
				<td>fname</td>
				<td>급여</td>
				<td>부서이름</td>
				<td>도시</td>
				<td>나라</td>
				<td>직책</td>
			</tr>
			<c:forEach items="${empDatas}" var="emp" varStatus="status">
				<tr>
				<td>
				${status.count}
			
				</td>
					<td><a href="${path}/emp/detail.do?empid=${emp.employee_id}">${emp.employee_id}</a>
					</td>
					<td><a href="${path}/emp/detail.do?empid=${emp.employee_id}">${emp.first_name}</a>
					</td>
					<td>${emp.salary}</td>
					<td>${emp.department_name}</td>
					<td>${emp.city}</td>
					<td>${emp.country_name}</td>
					<td>${emp.job_title}</td>
					
				</tr>
			</c:forEach>
			<!-- c:은 jsp 태그 -->
		</table>