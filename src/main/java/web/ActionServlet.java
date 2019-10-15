package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EmployeeDAO;
import entity.Employee;

public class ActionServlet extends HttpServlet{
	public void service(
			HttpServletRequest request,
			HttpServletResponse response)
	throws ServletException,IOException{
		//设置解码所使用的字符集，注意要与客户端保持一致
		request.setCharacterEncoding("utf-8");
		
		String uri = 
				request.getRequestURI();
		String action = 
			uri.substring(uri.lastIndexOf("/"),
				uri.lastIndexOf("."));
		System.out.println("action:" + action);
		if("/list".equals(action)){
			
			//访问数据库，获取所有员工信息
			EmployeeDAO dao = 
					new EmployeeDAO();
			try{
				List<Employee> employees = 
						dao.findAll();
				//依据查询到的员工信息，输出表格
				//因为servlet不擅长生成页面，所以转发
				//给jsp来生成页面。
				//step1.将处理结果绑订到request
				request.setAttribute("employees", employees);
				//step2.获得转发器
				RequestDispatcher rd = 
					request.getRequestDispatcher(
							"listEmp.jsp");
				//step3.转发
				rd.forward(request, response);
				
			}catch(Exception e){
				e.printStackTrace();
				//将异常抛给容器
				throw new ServletException(e);
			}
		}else if("/add".equals(action)){
			//调用request对象提供的方法来读取请求参数值
			String name = 
					request.getParameter("name");
			String salary = 
					request.getParameter("salary");
			String age = 
					request.getParameter("age");
			
			//一般来说，服务器端应该对请求参数值做一些
			//合法性检查，比如检查姓名是否为空，这儿
			//暂时不做。
			
			//将员工信息插入到数据库
			EmployeeDAO dao = new EmployeeDAO();
			Employee e = new Employee();
			e.setName(name);
			e.setSalary(Double.parseDouble(salary));
			e.setAge(Integer.parseInt(age));
			try{
				dao.save(e);
				//重定向
				response.sendRedirect("list.do");
			}catch(Exception e1){
				e1.printStackTrace();
				throw new ServletException(e1);
			}
		}else if("/del".equals(action)){
			//读取要删除的员工的id
			String id = 
					request.getParameter("id");
			EmployeeDAO dao = 
					new EmployeeDAO();
			try{
				dao.delete(Integer.parseInt(id));
				response.sendRedirect("list.do");
			}catch(Exception e){
				e.printStackTrace();
				throw new ServletException(e);
			}
		}else if("/load".equals(action)){
			//读取员工的id
			String id = 
					request.getParameter("id");
			//依据id访问数据库，获取员工信息
			EmployeeDAO dao = new EmployeeDAO();
			try{
				Employee e = 
						dao.findById(Integer.parseInt(id));
				//依据查询到的员工信息，生成表单
				request.setAttribute("e", e);
				request.getRequestDispatcher("updateEmp.jsp")
				.forward(request, response);
			}catch(Exception e){
				e.printStackTrace();
				throw new ServletException(e);
			}
		}else if("/modify".equals(action)){
			//读取要修改的员工信息
			String id = 
					request.getParameter("id");
			String name = 
					request.getParameter("name");
			String salary =
					request.getParameter("salary");
			String age = 
					request.getParameter("age");
			//访问数据库，完成员工信息的修改
			EmployeeDAO dao =
					new EmployeeDAO();
			
			Employee e = new Employee();
			e.setId(Integer.parseInt(id));
			e.setName(name);
			e.setSalary(Double.parseDouble(salary));
			e.setAge(Integer.parseInt(age));
			try{
				dao.modify(e);
				//重定向到员工列表
				response.sendRedirect("list.do");
			}catch(Exception e1){
				e1.printStackTrace();
				throw new ServletException(e1);
			}
		}
	}
}


	
