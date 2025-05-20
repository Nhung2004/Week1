package salary;

import java.util.*; // hoặc giữ lại các import đúng
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.*;
import java.util.stream.*;

import java.lang.foreign.Linker.Option;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import salary.Employee.duplicateId;

public class Main {

	public static void main(String[] args) {
		List<Employee> employees= new ArrayList<>(Arrays.asList(
				new Employee("H01", "Hung", 28, 1000, "IT"),
				new Employee("H02", "Toan", 28, 7660, "IT"),
				new Employee("H03", "Tuan", 28, 5500, "IT"),
				new Employee("H04", "Tu", 28, 9000, "IT"),
				new Employee("H05", "Lien", 39, 6000, "HR"),
				new Employee("H06", "Long", 28, 8000, "HR")

	));
		
		Scanner sc=new Scanner(System.in);
		try {
			System.out.println("Nhap ma nhan vien");
			String id=sc.nextLine();
			if(employees.stream().anyMatch(e->e.getId().equals(id))) {
				throw new duplicateId("Mã nhân viên đã tồn tại");
			}
			System.out.print("Nhap ten nhan vien");
			String name=sc.nextLine();
			System.out.println("Nhap tuoi nhan vien");
			int age=sc.nextInt();
			System.out.println("Nhap luong nhan vien");
			double salary=sc.nextDouble();
			sc.nextLine();
			System.out.println("Nhap phong nhan vien");
			String department=sc.nextLine();
			
			Employee newe=new Employee(name, id, age, salary, department);
			employees.add(newe);
			System.out.print("Da them nhan vien"+newe);
		} catch (duplicateId e) {
			// TODO Auto-generated catch block
			System.out.println("Looix"+e.getMessage());
		}catch (InputMismatchException e2) {
			System.out.print("Nhap sai dinh dang");
			// TODO: handle exception
		}
	
	 System.out.println("Tuoi lon hon 30 va luong giam dan la");
	 employees.stream()
	 .filter(e->e.getAge()>30)
	 .sorted(Comparator.comparing(Employee::getSalary).reversed())
	 .forEach(System.out::println);
	 
	 // Tong luong phong IT
	 System.out.println("-------------");
	 double sumSalaryIT= employees.stream()
			 .filter(e->e.getDepartment().equals("IT"))
			 .mapToDouble(em->em.getSalary())
			 .sum();
	 System.out.print("tong luong phong IT la :"+sumSalaryIT);
	// nhan vien luong cao nhat phong HR
	 Optional<Employee> highestHR=employees.stream()
			 .filter(e->e.getDepartment().equals("HR"))
			 .max(Comparator.comparing(em->em.getSalary()));
			 System.out.println("Nhan vien cp luong cao nhat phong HR la");
			 highestHR.ifPresent(System.out::println);
	// gom nhom cac phong ban 
			 System.out.println("\nd. Nhóm theo phòng ban:");
			 Map<String, List<Employee>> groupByDept = employees.stream()
			     .collect(groupingBy(Employee::getDepartment));
			 groupByDept.forEach((dept, list) -> {
			     System.out.println("Phòng " + dept + ":");
			     list.forEach(System.out::println);
			 });
			 
			 // luong bat dau bang chu A va luong lon hon 5000
			Optional<Employee> emplyess=employees.stream()
					 .filter(e->e.getName().startsWith("A")&&e.getSalary()>5000).findAny();
			 System.out.println("Co ai ten bat dau bang chu A va luon lon hon 5000 khong"+emplyess);

	// tang 10% luong cho nhan vien >25 tuoi
			employees.stream()
			.filter(e->e.getAge()>25)
			.peek(e->e.setSalary(e.getSalary()*1.1))
			.forEach(System.out::println);
		
			// tinh trung binh luong theo phong ban

	        System.out.println("\ng. Trung bình lương theo phòng ban:");
	        Map<String, Double> avgSalary = employees.stream()
	            .collect(groupingBy(Employee::getDepartment, averagingDouble(Employee::getSalary)));
	        avgSalary.forEach((dept, avg) ->
	            System.out.println("Phòng " + dept + ": " + avg));
	        
	        // tim danh sach nhan vien thuoc phong it va co ten dai hon 4 kitu
	        List<Employee> ems=employees.stream()
	        		.filter(e->e.getDepartment().equals("IT")&&e.getName().length()>4)
	        		.collect(Collectors.toList());
	        System.out.println("Danh sach"+ems);
	        
	        // tim nhan vien co luong thap nhat
	        //
	        Employee em=employees.stream()
	        		.min(Comparator.comparing(e->e.getSalary()))
	        		.orElse(null);
	        System.out.println("Nhan viên co luong thap nhat"+em);
	        //gop danh sach nhan vien va ngan cach boi dau ,
	        String result=employees.stream()
	        		.map(e->e.getName())
	        		.collect(Collectors.joining(","));
	        System.out.println(result);
	        
	        //
	        Map<String, Double> nameSalaryMap = employees.stream()
	        	    .collect(Collectors.toMap(Employee::getName, Employee::getSalary));
	        	System.out.println("Map tên -> lương:");
	        	nameSalaryMap.forEach((name, salary) -> System.out.println(name + " : " + salary));

	        // dem so luong nhan vien moi phong ban
	        	Map<String, Long> c=employees.stream()
	        			.collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
	        	System.out.println("So luong nhan vien tung phon ban la");
	        	c.forEach((debt,count)->System.out.println(debt+":"+count));
	        			
	        // gom nhosm theo tung phong
	        	Map<String, List<Employee>> emgg = employees.stream()
	        	        .collect(Collectors.groupingBy(Employee::getDepartment));

	        	System.out.println("Danh sách nhân viên theo từng phòng ban:");
	        	emgg.forEach((dept, list) -> {
	        	    System.out.println("Phòng: " + dept);
	        	    list.forEach(System.out::println);
	        	});

	        			
	        	/////////////////////////////////////////////////////////////////
	        	// loc danh sach nhan vien co tuoi>30 va sap xep theo 
	        	// luong giam dan in ra man hin
	        	System.out.println("Danh sach nhan vien co tuoi lon hon 30 va sap xep luong giam dan");
	        	List<Employee> liste=employees.stream()
	        			.filter(e->e.getAge()>30)
	        			.sorted(Comparator.comparing(Employee::getSalary).reversed())
	        			.collect(Collectors.toList());
	        			liste.forEach(System.out::println);
	        			
	        // cau 2 tinh tong luong nhan vien phong It va in ra KQ
	        			double sumsalary=employees.stream()
	        					.filter(e->e.getDepartment().equals("IT"))
	        					.mapToDouble(emh->emh.getSalary()).sum();
	        			System.out.println("Tong luong phong It la"+sumsalary);
	        // cau 3 tim nhan vien co luong cao nhat phong HR
	        			Employee ess=employees.stream()
	        					.filter(e->e.getDepartment().equals("HR"))
	        					.max(Comparator.comparing(emj->emj.getSalary()))
	        					.orElse(null);
	        			
	        					
}
}
