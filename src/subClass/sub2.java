package subClass;

public class sub2 {
	private String name;
	private int age;

	public sub2(){};
	public sub2(String name, int age){
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
		return "sub2: name = " + name + " age = " + age;
	}
}