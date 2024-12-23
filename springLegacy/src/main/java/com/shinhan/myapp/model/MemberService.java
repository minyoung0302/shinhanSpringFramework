package com.shinhan.myapp.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shinhan.myapp.vo.MemberDTO;

@Service //@Component + Service기능을 한다
public class MemberService {

	@Autowired
	MemberDAO mdao;
	public MemberDTO loginService(String mid, String mpass){
		return mdao.login(mid,  mpass);
	}
}
