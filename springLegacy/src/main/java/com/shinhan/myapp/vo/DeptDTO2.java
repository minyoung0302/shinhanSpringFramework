package com.shinhan.myapp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter 
public class DeptDTO2 {
	int dept_id  ;   //Į�� �̸��� �ٸ���
	String dept_name ;  //Į�� �̸��� �ٸ���
	int manager_id  ;      //Į�� �̸��� ����
	int location_id  ;     //Į�� �̸��� ����
}

















