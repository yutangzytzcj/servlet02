package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;
import util.DBUtil;

/**
 * 	DAO类
 * 		封装了数据库访问逻辑
 *
 */
public class EmployeeDAO {
	
	public void modify(Employee e){
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE emp SET name=?,"
					+ "salary=?,age=? WHERE id=?";
			PreparedStatement ps = 
					conn.prepareStatement(sql);
			ps.setString(1, e.getName());
			ps.setDouble(2, e.getSalary());
			ps.setInt(3, e.getAge());
			ps.setInt(4, e.getId());
			ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}finally{
			DBUtil.close(conn);
		}
	}
	
	public Employee findById(int id){
		Employee e = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = 
					"SELECT * FROM emp WHERE id = ?";
			PreparedStatement ps = 
					conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setSalary(rs.getDouble("salary"));
				e.setAge(rs.getInt("age"));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new RuntimeException(e1);
		}finally{
			DBUtil.close(conn);
		}
		return e;
	}
	
	
	
	public void delete(int id){
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = 
					"DELETE FROM emp WHERE id = ?";
			PreparedStatement ps = 
					conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			DBUtil.close(conn);
		}
	}
	
	
	
	public List<Employee> findAll(){
		List<Employee>  employees = 
				new ArrayList<Employee>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM emp";
			PreparedStatement ps = 
					conn.prepareStatement(sql);
			ResultSet rs = 
					ps.executeQuery();
			while(rs.next()){
				Employee e = new Employee();
				e.setName(rs.getString("name"));
				e.setSalary(rs.getDouble("salary"));
				e.setAge(rs.getInt("age"));
				e.setId(rs.getInt("id"));
				employees.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			DBUtil.close(conn);
		}
		return employees;
	}
	
	
	public void save(Employee e){
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO emp "
					+ "VALUES(emp_seq.nextval,?,?,?)";
			PreparedStatement ps = 
					conn.prepareStatement(sql);
			ps.setString(1, e.getName());
			ps.setDouble(2, e.getSalary());
			ps.setInt(3, e.getAge());
			ps.executeUpdate();
		} catch (SQLException e1) {
			//step1.记日志
			e1.printStackTrace();
			/*
			 * step2.看异常能否恢复，如果能够恢复，
			 * 则立即恢复；如果不能够恢复(比如，数据库
			 * 服务暂停，一般将这样的异常称之为系统
			 * 异常)，则提示用户稍后重试。
			 */
			throw new RuntimeException(e1);
		}finally{
			DBUtil.close(conn);
		}
	}
}







