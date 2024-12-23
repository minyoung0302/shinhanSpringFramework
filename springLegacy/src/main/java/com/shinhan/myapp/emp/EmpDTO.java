package com.shinhan.myapp.emp;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//DTO : Data Transfer Object 
//VO : Value Object 
@Getter@Setter@ToString@Builder@NoArgsConstructor@AllArgsConstructor
public class EmpDTO {
	//기본형 datattype은 null을 setting 불가
	//기본형 ==> Wrapper class로 변경
	Integer employee_id;    
	String first_name ;    
	String last_name;      
	String email ;         
	String phone_number ;  
	Date hire_date ;     
	String job_id ;        
	Double salary;         
	Double commission_pct ;
	Integer manager_id;     
	Integer department_id  ;
	
}