package subClass;

public class sub1 {
	private String name;
	private int age;
	
	public sub1(){};
	public sub1(String name, int age){
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "sub1: name = " + name + " age = " + age;
	}
}