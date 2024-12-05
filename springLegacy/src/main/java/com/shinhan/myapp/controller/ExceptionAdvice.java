package com.shinhan.myapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

//���� �߻��� ���������� ó���ϴ� controller
//500 : ��������
//404 : �������� �ʴ� ������
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
	@ExceptionHandler(Exception.class)
	public String f1(Exception ex, Model model) {
		log.info("���� �߻� class : " + ex.getClass().getName());
		log.info("���� �߻� �޽��� : " + ex.getMessage());
		ex.printStackTrace();
		model.addAttribute("message","������");
		return "error/error500";
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public String f2(HttpServletRequest request) {
		log.info("��û�ּ� : " + request.getRequestURI());
		return "error/error404";
	}
}
