package salary;

public class Employee {

	private String name;
	
	private String id;
	
	private int age;
	
	private double salary;
	
	private String department;

	public Employee(String name, String id, int age, double salary, String department) {
		super();
		this.name = name;
		this.id = id;
		this.age = age;
		this.salary = salary;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", id=" + id + ", age=" + age + ", salary=" + salary + ", department="
				+ department + "]";
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public static class duplicateId extends Exception{
		public duplicateId(String msg) {
			super(msg);
		}
	}
	
	
}
