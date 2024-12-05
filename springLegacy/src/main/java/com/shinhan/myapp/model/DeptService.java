package com.shinhan.myapp.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shinhan.myapp.vo.DeptDTO;

@Service 
public class DeptService {
    
	//@Autowired : type�� ������ �ڵ����� Injection�Ѵ�.
	//���� type�� ���� �� ������ ����
	//@Qualifier : �̸����� Injection�Ѵ�.
	@Autowired   
	@Qualifier("deptMybatis")
	DeptDAOInterface deptDao ;

	// 1.모두조회
	public List<DeptDTO> selectAllService() {
		return deptDao.selectAll();
	}

	// 2.?��?��보기
	public DeptDTO selectByIdService(int deptid) {
		return deptDao.selectById(deptid);
	}

	// 3.?��?��
	public int insertService(DeptDTO dept) {
		
		return deptDao.insert(dept);
	}

	// 4.?��?��
	public int updateService(DeptDTO dept) {
		return deptDao.update(dept);
	}

	// 5.?��?��
	public int deleteService(int deptid) {
		return deptDao.delete(deptid);
	}
}






