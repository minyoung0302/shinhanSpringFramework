package com.shinhan.myapp.controller;

//�ڵ�import : ctrl+shift+o

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.myapp.model.DeptService;
import com.shinhan.myapp.vo.DeptDTO;

//�μ��� CRUD�۾� : Controller-> Service -> DAO
//����� ��û->DispatcherServlet->Controllerã�� 
// component-scan�� ���ؼ� Bean�����ȴ�. 

//@Controller ==>��û�� �޾Ƽ� ������������ return�Ѵ�. 
//@RestController=>��û�� �޾Ƽ� ���䵥���͸� return�Ѵ�. @Controller + @ResponseBody

@Controller
public class DeptController {

	@Autowired
	DeptService dService;

	Logger logger = LoggerFactory.getLogger(DeptController.class);

	@GetMapping("/dept/insert.do")
	public String insert(DeptDTO deptid, Model model) {
		model.addAttribute("deptInfo", dService.insertService(deptid));
		return "dept/deptInsert";
	}

	@PostMapping("/dept/insert.do")
	public String insertPost(DeptDTO dept, RedirectAttributes attr) {
		int result = dService.insertService(dept);
		String message = "�Է� �Ǽ� : " + result;
		logger.info(message);
		attr.addFlashAttribute("resultMessage", message);
		return "redirect:/dept/list.do"; // ���û
	}

	@GetMapping("/dept/detail.do")
	public String detail(int deptid, Model model) {
	    model.addAttribute("deptInfo", dService.selectByIdService(deptid));
	    return "dept/deptDetail";
	}
	
	@PostMapping("/dept/detail.do")
	public String detailPost(@ModelAttribute("dept") DeptDTO dept ,RedirectAttributes attr) {
		logger.info(dept.toString());
		int result = dService.updateService(dept);
		String message = "�����Ǽ�:" + result;
		logger.info(message);
		attr.addFlashAttribute("resultMessage",attr);
		return "redirect:/dept/list.do"; //���û�ϱ� response.sendredirect()
	}
	
	/* �󼼺��� �Ŀ� ����� �����ְ� 1�� �� list�� ����
	@PostMapping("/dept/detail.do")
	public String detailPost(@ModelAttribute("dept") DeptDTO dept) {
		logger.info(dept.toString());
		dService.updateService(dept);
		return "dept/result";
	}
	*/
//	@PostMapping("/dept/detail.do")
//	public String detailPost(DeptDTO dept) {
//		logger.info(dept.toString());
//		dService.updateService(dept);
//		return "redirect:/dept/list.do"; // ���û�ϱ� response.sendRedirect()
//	}

	@RequestMapping("/dept/list.do")
	public String f1(Model model, HttpServletRequest request) {

		Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if (map != null) {
			String message = (String) map.get("resultMessage");
			model.addAttribute("result", message);
		}

		List<DeptDTO> deptlist = dService.selectAllService();
		model.addAttribute("deptlist", deptlist);
		return "dept/deptList"; // forward, include
		/// WEB-INF/views/dept/deptList.jsp

	}

	@RequestMapping(value = "/dept/delete.do", method = { RequestMethod.GET, RequestMethod.POST })
	public String delete(int deptid, RedirectAttributes attr) {
		int result = dService.deleteService(deptid);
		String message = "�����Ǽ�" + result;
		attr.addFlashAttribute("resultMessage", message);
		return "redirect:/dept/list.do";

	}

}
