package com.shinhan.myapp.emp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

//Data Source(DB연결) ==> SqlSession Factory(DB, Mapper) ==> SqlSession생성
//				==>DAO ==> Service ==> Controller

@Slf4j
@Repository("empMybatis")
public class EmpDAOMybatis implements EmpDAOInterface  {
	
	@Autowired
	SqlSession sqlSession;
	String namespace = "com.shinhan.emp.";
	
	//returnr값 = sqlSession.selectOne("namespace와 mapper의 ID", 파라미터)
	//returnr값 = sqlSession.selectList("")
	//returnr값 = sqlSession.selectMap("")
	
	public List<EmpDTO> selectByArray(List<Integer> deptArr) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByArray", deptArr);
		log.info("파라미터(list,array):" + emplist.size());
		return emplist;
	}
	
	
	
	public Map<String, Object> selectJoin2(String jobid) {
		Map<String, Object>emplist = sqlSession.selectMap(namespace + "selectJoin2", jobid);
		log.info("mybatis 이용(map) : " + emplist.size() + "건");
		return emplist;
	}
	public List<JobDTO> selectAllJob() {
		List<JobDTO> joblist = sqlSession.selectList(namespace + "selectAllJob");
		log.info("mybatis 이용(job) : " + joblist.size() + "건");
		return joblist;
	}
	
	//Employees, Departments, Locations, Countries, Jobs
	public List<EmpJoinDTO> selectJoin(String jobid) {
		List<EmpJoinDTO> emplist = sqlSession.selectList(namespace + "selectJoin", jobid);
		log.info("mybatis 이용 : " + emplist.size() + "건");
		return emplist;
	}
	
	public List<EmpDTO> selectByDept(int dept_id) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByDept", dept_id);
		log.info("mybatis 이용 : " + emplist.size() + "건");
		return emplist;
	}

	public List<EmpDTO> selectByJob(String job_id) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByJob", job_id);
		log.info("mybatis 이용 : " + emplist.size() + "건");
		return emplist;
	}
	
	public List<EmpDTO> selectBySalary(double salary) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectBySalary", salary);
		log.info("mybatis 이용 : " + emplist.size() + "건");
		return emplist;
	}
	

	public List<EmpDTO> selectByCondition(Map<String,Object> map) {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectByCondition", map);
		log.info("mybatis 이용 : " + emplist.size() + "건");
		return emplist;
	}	
	
	public List<EmpDTO> selectAll() {
		List<EmpDTO> emplist = sqlSession.selectList(namespace + "selectAll");
		log.info("mybatis 이용 : " + emplist.size() + "건");
		return emplist;
	}

	public EmpDTO selectById(int empid) {
		EmpDTO emp = sqlSession.selectOne(namespace + "selectById", empid);
		log.info("mybatis 이용 : " + emp.toString());
		return emp;
	}

	public int insert(EmpDTO emp) {
		int result = sqlSession.insert(namespace + "insert", emp);
		log.info("mybatis 이용 : " + result + "건 입력");
		return result;
	}

	public int update(EmpDTO emp) {
		int result = sqlSession.update(namespace + "update", emp);
		log.info("mybatis 이용 : " + result + "건 수정");
		return result;
	}

	public int delete(int empid) {
		int result = sqlSession.delete(namespace + "delete", empid);
		log.info("mybatis 이용 : " + result + "건 삭제");
		return result;
	}


}