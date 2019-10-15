package entity;

import java.io.Serializable;
/**
 * 实体类
 *		
 */
public class Employee implements Serializable{
	private int id; 
	private String name;
	private double salary;
	private int age;
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" 
	+ name + ", salary=" + salary + ", age=" 
				+ age + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}



