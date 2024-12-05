package com.shinhan.myapp.emp;

import java.util.List;
import java.util.Map;

//interface(�԰ݼ�) : ���Ǵ� �ְ� ������ ����
public interface EmpDAOInterface {
	public Map<String, Object> selectJoin2(String jobid);

	public List<JobDTO> selectAllJob();

	public List<EmpJoinDTO> selectJoin(String jobid);

	public List<EmpDTO> selectByDept(int dept_id);

	public List<EmpDTO> selectByJob(String job_id);

	public List<EmpDTO> selectBySalary(double salary);

	public List<EmpDTO> selectByCondition(Map<String, Object> map);

	public List<EmpDTO> selectAll();

	public EmpDTO selectById(int empid);

	public int insert(EmpDTO emp);

	public int update(EmpDTO emp);

	public int delete(int empid);
}
