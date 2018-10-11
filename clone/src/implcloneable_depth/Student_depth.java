package implcloneable_depth;

public class Student_depth implements Cloneable{
	private Integer age;
	private String name;
	private Teacher teacher;
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Student_depth student=(Student_depth)super.clone();
		student.setTeacher((Teacher)student.getTeacher().clone());
		return student;
	}
	
}
