package com.shinhan.myapp.emp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.shinhan.myapp.vo.DeptDTO;

import lombok.extern.slf4j.Slf4j;
import net.firstzone.util.DBUtil;
import net.firstzone.util.DateUtil;

@Slf4j
@Repository
public class EmpDAO implements EmpDAOInterface {
	
	@Autowired
	@Qualifier("dataSource")
	DataSource ds;
	Connection conn;
	public Map<String, Object> selectJoin2(String jobid) {
		String sql = 
				" select employee_id, first_name, salary, department_name, city, country_name"+
				" from employees join departments using(department_id)"+
				"                      join locations using(location_id)"+
				"                      join countries USING (country_id)"+
				" where  job_id = ? ";
		Map<String, Object> map = new HashMap<>();
		List<EmpDTO> emplist = new ArrayList<>();
		List<DeptDTO> deptlist = new ArrayList<>();
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, jobid);
			rs = st.executeQuery();
			while(rs.next()) {
				EmpDTO emp = new EmpDTO();
				emp.setEmployee_id(rs.getInt("Employee_id"));
				emp.setFirst_name(rs.getString("First_name"));
				emp.setSalary(rs.getDouble("salary"));
				emplist.add(emp);
				
				DeptDTO dept = new DeptDTO();
				dept.setDepartment_name(rs.getString("Department_name"));
				deptlist.add(dept);
			}
			map.put("emp", emplist);
			map.put("dept", deptlist);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return map;
		
	}
	
	//Jobs??´ë¸ì ëª¨ë  dataê°?? ¸?¤ê¸? 
	public List<JobDTO> selectAllJob() {
		//1.DTOë§ë ?¤ 2.MAP?¬?©??¤. 
		String sql = "select * from jobs";
		List<JobDTO> joblist = new ArrayList<>();
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			rs = st.executeQuery();
			while(rs.next()) {
				JobDTO emp = JobDTO.builder()
						.job_id(rs.getString("job_id"))
						.job_title(rs.getString("job_title"))
						.min_salary(rs.getInt("min_salary"))
						.max_salary(rs.getInt("max_salary"))
						.build();
				joblist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return joblist;
		
	}
	
	public List<EmpJoinDTO> selectJoin(String jobid) {
		//1.DTOë§ë ?¤ 2.MAP?¬?©??¤. 
		String sql = 
				" select employee_id, first_name, salary, department_name, city, country_name"+
				" from employees join departments using(department_id)"+
				"                       join locations using(location_id)"+
				"                       join countries USING (country_id)"+
				" where  job_id = ? "
				;
		List<EmpJoinDTO> emplist = new ArrayList<EmpJoinDTO>();

		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setString(1, jobid);
			rs = st.executeQuery();
			while(rs.next()) {
				EmpJoinDTO emp = EmpJoinDTO.builder()
						.city(rs.getString("city"))
						.employee_id(rs.getInt("employee_id"))
						.first_name(rs.getString("first_name"))
						.country_name(rs.getString("country_name"))
						.department_name(rs.getString("department_name"))
						.build();
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return emplist;
		
	}
	
	
	
	
	//1.?¹? ë¶??? ì§ìì¡°í      where department_id = ? 
	public List<EmpDTO> selectByDept(int dept_id) {
		// ëª¨ë  ì§ì? ì¡°í?ê¸?
		String sql = "select * from employees where department_id = ?" ;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<EmpDTO> emplist = new ArrayList<EmpDTO>();
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql); //SQLë¬? ì¤?ë¹? 
			st.setInt(1, dept_id);  //?? ê°ì ì±ì°ê¸? 
			rs = st.executeQuery(); //DB? ê°?? ?¤??ê³? ê²°ê³¼ë¥? ê°?? ¸?¨?¤. 
			while (rs.next()) {
				EmpDTO emp = makeEmp2(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return emplist;
	}

	
	//2.?¹? job_id?¸ ì§ìì¡°í   where job_id = ? 
	public List<EmpDTO> selectByJob(String job_id) {
		// ëª¨ë  ì§ì? ì¡°í?ê¸?
		String sql = "select * from employees where job_id = ?" ;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<EmpDTO> emplist = new ArrayList<EmpDTO>();
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql); //SQLë¬? ì¤?ë¹? 
			st.setString(1, job_id);  //?? ê°ì ì±ì°ê¸? 
			rs = st.executeQuery(); //DB? ê°?? ?¤??ê³? ê²°ê³¼ë¥? ê°?? ¸?¨?¤. 
			while (rs.next()) { //?¤?dataê°? ??ì§??
				EmpDTO emp = makeEmp2(rs); //?ê±´ì DTOë§ë ?¤. 
				emplist.add(emp);          //?¬?¬ê±´ì´ë¯?ë¡? Collection? ?´ê¸?  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return emplist;
	}
	
	//3.ê¸ì¬ê°? ??´??¸ ì§ìì¡°í   where salary >= ? 
	public List<EmpDTO> selectBySalary(double salary) {
		// ëª¨ë  ì§ì? ì¡°í?ê¸?
		String sql = "select * from employees where salary >= ?" ;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<EmpDTO> emplist = new ArrayList<EmpDTO>();
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql); //SQLë¬? ì¤?ë¹? 
			st.setDouble(1, salary);  //?? ê°ì ì±ì°ê¸? 
			rs = st.executeQuery(); //DB? ê°?? ?¤??ê³? ê²°ê³¼ë¥? ê°?? ¸?¨?¤. 
			while (rs.next()) { //?¤?dataê°? ??ì§??
				EmpDTO emp = makeEmp2(rs); //?ê±´ì DTOë§ë ?¤. 
				emplist.add(emp);          //?¬?¬ê±´ì´ë¯?ë¡? Collection? ?´ê¸?  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return emplist;
	}
	
	//4.ë¶??, ì§ì±, ê¸ì¬, ??¬?¼ ì¡°ê±´?¼ë¡? ì¡°í 
	//     where department_id = ? and job_id = ? and salary >= ? and  hire_date >= ?
	public List<EmpDTO> selectByCondition(Map<String,Object> map) {
		// ëª¨ë  ì§ì? ì¡°í?ê¸?
		String sql = "select * "
				+ " from employees "
				+ " where (-1 =? or department_id = ?) "
				+ " and ('-1' =? or job_id = ?) "
				+ " and salary >= ? "
				+ " and  hire_date >= ?" ;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<EmpDTO> emplist = new ArrayList<EmpDTO>();
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql); //SQLë¬? ì¤?ë¹? 
			
			String str_deptid =(String) map.get("deptid");
			int deptid = Integer.parseInt(str_deptid);
			String str_sal =(String) map.get("salary");
			String str_hdate =(String) map.get("hdate");
			Date hdate = DateUtil.convertSqlDate(DateUtil.convertDate(str_hdate));
			
			st.setInt(1, deptid);  //?? ê°ì ì±ì°ê¸? 
			st.setInt(2, deptid);  //?? ê°ì ì±ì°ê¸? 
			st.setString(3, (String)map.get("jobid"));  //?? ê°ì ì±ì°ê¸? 
			st.setString(4, (String)map.get("jobid"));  //?? ê°ì ì±ì°ê¸? 
			st.setDouble(5, Double.parseDouble(str_sal));  //?? ê°ì ì±ì°ê¸? 
			st.setDate(6, hdate);  //?? ê°ì ì±ì°ê¸? 
			
			rs = st.executeQuery(); //DB? ê°?? ?¤??ê³? ê²°ê³¼ë¥? ê°?? ¸?¨?¤. 
			while (rs.next()) { //?¤?dataê°? ??ì§??
				EmpDTO emp = makeEmp2(rs); //?ê±´ì DTOë§ë ?¤. 
				emplist.add(emp);          //?¬?¬ê±´ì´ë¯?ë¡? Collection? ?´ê¸?  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		log.info("empDAOJDBC - Á¶°ÇÁ¶È¸ÇÔ" + emplist.size());
		return emplist;
	}	
	
	public List<EmpDTO> selectAll() {
		// ëª¨ë  ì§ì? ì¡°í?ê¸?
		String sql = "select * from employees order by 1";
		Statement st = null;
		ResultSet rs = null;
		List<EmpDTO> emplist = new ArrayList<EmpDTO>();
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				EmpDTO emp = makeEmp(rs);
				emplist.add(emp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return emplist;
	}

	public EmpDTO selectById(int empid) {
		// ?¹?  ì§ì? ì¡°í?ê¸?
		String sql = "select  *  from employees where employee_id = " + empid;

		Statement st = null;
		ResultSet rs = null;
		EmpDTO emp = null;
		try {
			conn = ds.getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs.next()) {
				emp = makeEmp(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, rs);
		}
		return emp;
	}

	// DB? ?? ¥
	public int insert(EmpDTO emp) {
		int result = 0;
		String sql = "insert into employees values (?,?,?,?,?,?,?,?,?,?,?)";
		// Statement? ?(bindingë³?? ì§????¨) <------PreparedStatement? ì§??
		PreparedStatement st = null;
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, emp.getEmployee_id());
			st.setString(2, emp.getFirst_name());
			st.setString(3, emp.getLast_name());
			st.setString(4, emp.getEmail());
			st.setString(5, emp.getPhone_number());
			st.setDate(6, emp.getHire_date());
			st.setString(7, emp.getJob_id());
			st.setDouble(8, emp.getSalary());
			st.setDouble(9, emp.getCommission_pct());
			st.setObject(10, emp.getManager_id()==-1?null:emp.getManager_id());
			st.setObject(11, emp.getDepartment_id()==-1?null:emp.getDepartment_id());

			result = st.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, null);
		}

		return result;
	}

	// ?? 
	public int update(EmpDTO emp) {
		int result = 0;
		String sql = 
				"update employees set"+
				"		FIRST_NAME=?"+
				"		LAST_NAME=?"+
				"		EMAIL=?"+
				"		PHONE_NUMBER=?"+
				"		HIRE_DATE=?"+
				"		JOB_ID=?"+
				"		SALARY=?"+
				"		COMMISSION_PCT=?"+
				"		MANAGER_ID=?"+
				"		DEPARTMENT_ID=?"+
				" where EMPLOYEE_ID=?";
		// Statement? ?(bindingë³?? ì§????¨) <------PreparedStatement? ì§??
		PreparedStatement st = null;
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(11, emp.getEmployee_id());
			st.setString(1, emp.getFirst_name());
			st.setString(2, emp.getLast_name());
			st.setString(3, emp.getEmail());
			st.setString(4, emp.getPhone_number());
			st.setDate(5, emp.getHire_date());
			st.setString(6, emp.getJob_id());
			st.setDouble(7, emp.getSalary());
			st.setDouble(8, emp.getCommission_pct());
			st.setInt(9, emp.getManager_id()==-1?null:emp.getManager_id());
			st.setInt(10, emp.getDepartment_id()==-1?null:emp.getDepartment_id());

			result = st.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, null);
		}

		return result;
	}

	// ?­? 
	public int delete(int empid) {
		int result = 0;
		String sql = "delete from employees"+  
				" where EMPLOYEE_ID=?";
		PreparedStatement st = null;
		try {
			conn = ds.getConnection();
			st = conn.prepareStatement(sql);
			st.setInt(1, empid);
			result = st.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.dbDisconnect(conn, st, null);
		}

		return result;
	}

	private static EmpDTO makeEmp2(ResultSet rs) throws SQLException {
		EmpDTO emp = EmpDTO.builder()
				.commission_pct(rs.getDouble("Commission_pct"))
				.department_id(rs.getInt("Department_id"))
				.email(rs.getString("email"))
				.employee_id(rs.getInt("Employee_id"))
				.first_name(rs.getString("first_name"))
				.last_name(rs.getString("Last_name"))
				.hire_date(rs.getDate("Hire_date"))
				.job_id(rs.getString("job_id"))
				.manager_id(rs.getInt("Manager_id"))
				.phone_number(rs.getString("Phone_number"))
				.salary(rs.getDouble("salary"))
				.build();
		return emp;
	}
	
	//±âÁ¸ JDBCÇÁ·Î±×·¥ÀÇ ´ÜÁ¡ : SQL¹®ÀÌ ÀÚ¹Ù¿Í ¼¯¿©ÀÖÀ½, DBÀÇ table(Object) Ä®·³°ú ÀÚ¹ÙÀÇ VO field¸¦
	private static EmpDTO makeEmp(ResultSet rs) throws SQLException {
		EmpDTO emp = new EmpDTO();
		emp.setCommission_pct(rs.getDouble("Commission_pct"));
		emp.setDepartment_id(rs.getInt("Department_id"));
		emp.setEmail(rs.getString("email"));
		emp.setEmployee_id(rs.getInt("Employee_id"));
		emp.setFirst_name(rs.getString("First_name"));
		emp.setLast_name(rs.getString("Last_name"));
		emp.setHire_date(rs.getDate("Hire_date"));
		emp.setJob_id(rs.getString("job_id"));
		emp.setManager_id(rs.getInt("Manager_id"));
		emp.setPhone_number(rs.getString("Phone_number"));
		emp.setSalary(rs.getDouble("salary"));
		 
		return emp;
	}

}