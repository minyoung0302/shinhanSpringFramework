package com.shinhan.myapp.emp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

//Data Source(DB����) ==> SqlSession Factory(DB, Mapper) ==> SqlSession����
//				==>DAO ==> Service ==> Controller

@Slf4j
@Repository("empMybatis")
public class EmpDAOMybatis implements EmpDAOInterface  {
	
	@Autowired
	SqlSession sqlSession;
	String namespace = "com.shinhan.emp.";
	
	//returnr�� = sqlSession.selectOne("namespace�� mapper�� ID", �Ķ����)
	//returnr�� = sqlSession.selectList("")
	//returnr�� = sqlSession.selectMap("")
	
	public List<EmpDTO> selectByArray(List<Integer> deptArr) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByArray", deptArr);
		log.info("�Ķ����(list,array):" + emplist.size());
		return emplist;
	}
	
	
	
	public Map<String, Object> selectJoin2(String jobid) {
		Map<String, Object>emplist = sqlSession.selectMap(namespace + "selectJoin2", jobid);
		log.info("mybatis �̿�(map) : " + emplist.size() + "��");
		return emplist;
	}
	public List<JobDTO> selectAllJob() {
		List<JobDTO> joblist = sqlSession.selectList(namespace + "selectAllJob");
		log.info("mybatis �̿�(job) : " + joblist.size() + "��");
		return joblist;
	}
	
	//Employees, Departments, Locations, Countries, Jobs
	public List<EmpJoinDTO> selectJoin(String jobid) {
		List<EmpJoinDTO> emplist = sqlSession.selectList(namespace + "selectJoin", jobid);
		log.info("mybatis �̿� : " + emplist.size() + "��");
		return emplist;
	}
	
	public List<EmpDTO> selectByDept(int dept_id) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByDept", dept_id);
		log.info("mybatis �̿� : " + emplist.size() + "��");
		return emplist;
	}

	public List<EmpDTO> selectByJob(String job_id) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByJob", job_id);
		log.info("mybatis �̿� : " + emplist.size() + "��");
		return emplist;
	}
	
	public List<EmpDTO> selectBySalary(double salary) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectBySalary", salary);
		log.info("mybatis �̿� : " + emplist.size() + "��");
		return emplist;
	}
	

	public List<EmpDTO> selectByCondition(Map<String,Object> map) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByCondition", map);
		log.info("mybatis �̿� : " + emplist.size() + "��");
		return emplist;
	}	
	
	public List<EmpDTO> selectAll() {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectAll");
		log.info("mybatis �̿� : " + emplist.size() + "��");
		return emplist;
	}

	public EmpDTO selectById(int empid) {
		EmpDTO emp = sqlSession.selectOne(namespace + "selectById", empid);
		log.info("mybatis �̿� : " + emp.toString());
		return emp;
	}

	public int insert(EmpDTO emp) {
		int result = sqlSession.insert(namespace + "insert", emp);
		log.info("mybatis �̿� : " + result + "�� �Է�");
		return result;
	}

	public int update(EmpDTO emp) {
		int result = sqlSession.update(namespace + "update", emp);
		log.info("mybatis �̿� : " + result + "�� ����");
		return result;
	}

	public int delete(int empid) {
		int result = sqlSession.delete(namespace + "delete", empid);
		log.info("mybatis �̿� : " + result + "�� ����");
		return result;
	}


}