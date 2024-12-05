package com.shinhan.myapp.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.vo.DeptDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("deptMybatis") 
public class DeptDAOMybatis implements DeptDAOInterface {

	//DB���� ==> DataSource ==> sqlSessionFactory ==> sqlSession

	@Autowired
	SqlSession sqlSession;
	String namespace = "com.shinhan.dept.";
			
	public List<DeptDTO> selectAll() {
		List<DeptDTO> deptlist =  sqlSession.selectList(namespace + "selectAll");
		log.info("dept��ȸ�Ǽ� : " + deptlist.size());
		return deptlist;
	}

	public DeptDTO selectById(int deptid) {
		DeptDTO dept = sqlSession.selectOne(namespace + "selectById", deptid);
		log.info("dept 1�� : " + dept);
		return dept;

	}

	public int insert(DeptDTO dept) {
		int result = sqlSession.insert(namespace + "insert", dept);
		log.info("�Է°Ǽ� : " + result);
		return result;
	}

	public int update(DeptDTO dept) {
		int result = sqlSession.update(namespace + "update", dept);
		log.info("�����Ǽ� : " + result);
		return 0;
	}

	public int delete(int deptid) {
		int result = sqlSession.delete(namespace + "delete", deptid);
		log.info("�����Ǽ� : " + result);
		return result;
	}

	public int deleteArray(Integer[] deptid) {
	

		return 0;
	}

}
