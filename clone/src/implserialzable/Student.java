package implserialzable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Student implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	//ʹ�����л�student��ʱ��Ҳ�Ὣteacher���л�
	public Object deepCopt()throws Exception
	{
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream  oos = new ObjectOutputStream(bos);
		oos.writeObject(this);
		//����ǰ�������д��һ����������У�����Ϊ����������ʵ����Serializable����ӿڣ��������������
		//��һ�����ã�����������ʵ�������л�����ô���Ҳ��д��������������
		
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bis);
		return ois.readObject();
		//������ǽ����еĶ��������࣬����һ�����������У������Ϳ��Է�������������Ķ�����ʵ�����¡
	}
	
}
