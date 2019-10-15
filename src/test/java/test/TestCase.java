package test;

import java.sql.SQLException;

import org.junit.Test;

import dao.EmployeeDAO;
import entity.Employee;
import util.DBUtil;

/**
 * 测试类
 *
 */
public class TestCase {
	@Test
	public void test1() throws SQLException{
		System.out.println(
				DBUtil.getConnection());
	}
	
	@Test
	public void test2(){
		EmployeeDAO dao = 
				new EmployeeDAO();
		Employee e = new Employee();
		e.setName("Sally");
		e.setSalary(20000);
		e.setAge(22);
		dao.save(e);
	}
	
	@Test
	public void test3(){
		EmployeeDAO dao = 
				new EmployeeDAO();
		System.out.println(dao.findAll());
	}
	
	@Test
	public void test4(){
		EmployeeDAO dao = 
				new EmployeeDAO();
		dao.delete(5);
	}
	
	@Test
	public void test5(){
		EmployeeDAO dao = 
				new EmployeeDAO();
		Employee e = 
				dao.findById(3);
		System.out.println(e);
	}
	
	@Test
	public void test6(){
		EmployeeDAO dao = 
				new EmployeeDAO();
		Employee e = dao.findById(3);
		e.setSalary(e.getSalary() * 2);
		dao.modify(e);
	}
	
	
	
	
	
}





