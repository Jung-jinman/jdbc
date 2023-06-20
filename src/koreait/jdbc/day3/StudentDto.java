package koreait.jdbc.day3;

// DTO : Data Transfer Object �� ������ü�� �����͸� ��ü���� �����ϱ� ���� �����Դϴ�.
// 	 �� �޼ҵ��� �Ű����� �Ǵ� ���� Ÿ������ ����ϱ� ����
// 	 �� ���̺��� �÷� ������ ���� Ŭ������ �������Ͽ� ���� �����ϱ� ���� ���Դϴ�.
// VO : Value Object �� �Һ���ü�� equals �� hashcode �����Ǹ� �Ͽ� set �Ǵ� map ��ü���� Ȱ��,
public class StudentDto {
	private String stuno;
	private String name;
	private int age;
	private String address;
	
	
	public StudentDto(String stuno, String name, int age, String address) {
		super();
		this.stuno = stuno;
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
	// getter �� toString
	public String getStuno() { return stuno; }
	public String getAddress() { return address; }
	public int getAge() { return age; }
	public String getName() { return name; }
	
	@Override
	public String toString() {
		return "StudentDto [stuno = " + stuno + ", name = " + name + ", age =" + age + ", address = " + address + "]";
	}
}
