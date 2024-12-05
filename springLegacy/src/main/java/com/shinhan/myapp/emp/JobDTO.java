package com.shinhan.myapp.emp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter@Builder
public class JobDTO {
	 String job_id ;      
	 String job_title  ;  
	 int min_salary   ;
	 int max_salary  ; 
}
