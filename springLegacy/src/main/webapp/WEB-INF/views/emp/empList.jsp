<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body>
	<div class="container">
		<!-- include 디렉티브태그는 jsp를 합쳐서 컴파일한다 -->
		<%@ include file="../common/header.jsp"%>
		<hr>
		<button id="btnSalary" class="btn btn-danger">조회(only 급여)</button>
		<button id="btnJob" class="btn btn-danger">조회(only 직책)</button>
		<button id="btnJobJoin" class="btn btn-danger">조회(only
			직책join)</button>
		<button id="btnJobJoin" class="btn btn-danger">조회(only
			직책join2)</button>
		<button id="btnDept" class="btn btn-danger">조회(only 부서)</button>
		<button id="btnArray" class="btn btn-danger">조회(부서배열)</button>
		<button id="btnTransfer" class="btn btn-danger">Transaction연습</button>
		<h3>Restful API 사용하기</h3>
		<button id="btnSelect" class="btn btn-secondary">전체조회</button>
		<button id="btnDetail" class="btn btn-secondary">상세보기</button>
		<button id="btnInsert" class="btn btn-secondary">입력</button>
		<button id="btnUpdate" class="btn btn-secondary">수정</button>
		<button id="btnDelete" class="btn btn-secondary">삭제</button>
		<hr>
		<fieldset>
			<div class="input-group mt-3 mb-3">
				<span class="input-group-text">부서번호</span> <select
					class="form-control" name="department_id">
					<option value=-1>선택안함</option>
					<c:forEach items="${deptlist}" var="dept">
						<option value="${dept.department_id}">
							${dept.department_name}</option>

					</c:forEach>
				</select> <span class="input-group-text">직책</span> <select
					required="required" class="form-control" name="job_id">
					<option value=-1>선택안함</option>
					<c:forEach items="${joblist}" var="job">
						<option ${job.job_id=='IT_PROG'?'selected':''}
							value="${job.job_id}">${job.job_id}/${job.job_title}</option>
					</c:forEach>
				</select> <span class="input-group-text">급여</span> <input type="number"
					class="form-control" name="salary" value="10000"> <span
					class="input-group-text">입사일</span> <input type="date"
					class="form-control" name="hire_date"> <span
					class="input-group-text"> <input type="checkbox"
					name="checkDate" value="1">모든일자
				</span>
				<button id="btn_condition" class="btn btn-primary">조 회</button>
			</div>
		</fieldset>
		<hr>

		<h1>직원List</h1>

		<%--JSP가 .java로 변환시 주석은 무시한다. ${} --%>
		<!-- HTML주석이므로 .java로 변환시 포함된다 -->
		<div id="table_here">
			<table class="table table-striped table-hover">
				<tr>
					<td>직원번호</td>
					<td>fname</td>
					<td>lname</td>
					<td>email</td>
					<td>phone</td>
					<td>job</td>
					<td>hiredate</td>
					<td>commission</td>
					<td>salary</td>
					<td>manager</td>
					<td>department</td>
					<td>get요청</td>
					<td>post요청</td>
				</tr>
				<c:forEach items="${empDatas}" var="emp">
					<tr>
						<td><a href="${path}/emp/detail.do?empid=${emp.employee_id}">${emp.employee_id}</a>
						</td>
						<td><a href="${path}/emp/detail.do?empid=${emp.employee_id}">${emp.first_name}</a>
						</td>
						<td>${emp.last_name}</td>
						<td>${emp.email}</td>
						<td>${emp.phone_number}</td>
						<td>${emp.job_id}</td>
						<td>${emp.hire_date}</td>
						<td>${emp.commission_pct}</td>
						<td>${emp.salary}</td>
						<td>${emp.manager_id}</td>
						<td>${emp.department_id}</td>
						<td>
							<button class="btn btn-success"
								onclick="location.href='${path}/emp/delete.do?empid=${emp.employee_id}'">
								삭제(get)</button>
						</td>
						<td>
							<form action="${path}/emp/delete.do" method="post">
								<input type="hidden" name="empid" value="${emp.employee_id}">
								<button class="btn btn-success">삭제(post)</button>
							</form>
						</td>
					</tr>
				</c:forEach>
				<!-- c:은 jsp 태그 -->
			</table>
		</div>
	</div>
	<script>
		$(function() {

			var result = "${resultMessage}";
			if (result != "") {
				alert(result);
			}

			var d = new Date();
			d.setFullYear(d.getFullYear() - 20);

			$('[name="hire_date"]').val(d.toISOString().split("T")[0]);
			$("#btn_condition").on("click", f_ajax);
			$("#btn_condition").on("click", f_ajax);
			$("#btnSalary").on("click", f_salary);
			$("#btnJob").on("click", f_job);
			$("#btnDept").on("click", f_dept);
			$("#btnJobJoin").on("click", f_jobjoin);
			$("#btnJobJoin2").on("click", f_jobjoin2);
			$("#btnArray").on("click", f_deptArray);
			$("#btnTransfer").on("click", f_transfer);

		});

		function f_transfer() {
			alert("f_transfer")
			$.ajax({
				url : "${path}/emp/transfer.do",
				success : function(responseData) {
					alert(responseData);
				},
				error : function() {
				}
			})
		}

		function f_deptArray() {
			alert("f_deptArray");
			$.ajax({
				url : "${path}/emp/listByArray.do",
				data : {
					deptArr : [ 10, 60, 90 ]
				},
				success : function(responseData) {
					$("#table_here").html(responseData)
				},
				error : function() {
				}
			})
		}

		function f_salary() {
			alert("f_salary");
			$.ajax({
				url : "${path}/emp/listBySalary.do",
				data : {
					salary : $("input[name='salary']").val()
				},
				success : function(responseData) {
					$("#table_here").html(responseData)
				},
				error : function() {
				}
			})
		}
		function f_job() {
			alert("f_job");
			$.ajax({
				url : "${path}/emp/listByJob.do",
				data : {
					job : $("select[name='job_id']").val()
				},
				success : function(responseData) {
					$("#table_here").html(responseData)
				},
				error : function() {
				}
			})
		}
		function f_dept() {
			alert("f_dept");
			$.ajax({
				url : "${path}/emp/listByDept.do",
				data : {
					deptid : $("select[name='department_id']").val()
				},
				success : function(responseData) {
					$("#table_here").html(responseData)
				},
				error : function() {
				}
			})

		}
		function f_jobjoin() {
			alert("f_jobjoin");
			$.ajax({
				url : "${path}/emp/listByJobJoin.do",
				data : {
					job : $("select[name='job_id']").val()
				},
				success : function(responseData) {
					$("#table_here").html(responseData)
				},
				error : function() {
				}
			})

		}
		function f_jobjoin2() {
			alert("f_jobjoin2");
			$.ajax({
				url : "${path}/emp/listByJobJoin2.do",
				data : {
					job : $("select[name='job_id']").val()
				},
				success : function(responseData) {
					$("#table_here").html(responseData)
				},
				error : function() {
				}
			})

		}

		/* 		<button id="btnSalary" class="btn btn-danger">조회(only 급여)</button>
		 <button id="btnJob" class="btn btn-danger">조회(only 직책)</button>
		 <button id="btnJobJoin" class="btn btn-danger">조회(only 직책join)</button>
		 <button id="btnDept" class="btn btn-danger">조회(only 부서)</button> */

		function f_ajax() {
			$.ajax({
				url : "${path}/emp/list2.do",
				type : "get",
				data : {
					"deptid" : $('[name="department_id"]').val(),
					"jobid" : $('[name="job_id"]').val(),
					"salary" : $('[name="salary"]').val(),
					"hdate" : $('[name="hire_date"]').val(),
					"chk" : $('[name="checkDate"]').prop("checked")

				},
				success : function(responseData) {
					//2.data를 바아서 HTML만들까? no
					//3.JSP를 받ㅇ서 현재 화면에 대치(replace)? ok
					$("#table_here").html(responseData);
				},
				error : function(err) {
					alert(err);
				}
			});
		}
	</script>
</body>
</html>