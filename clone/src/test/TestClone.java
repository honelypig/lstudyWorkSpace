package test;

import implcloneable_depth.Student_depth;
import implcloneable_shallow.StudentShallow;
import implcloneable_shallow.Teacher;
import implserialzable.Student;

public class TestClone {

	public static void main(String[] args) {
		testShallow();
		testDepth();
		testDepthImplSerial();
		/*
		 start testing shallow====>
		implcloneable_shallow.Teacher@2a139a55   teacherName=������
		implcloneable_shallow.Teacher@2a139a55   teacherName=������
		����Teacher�ĵ�ַһ��
		end testing shallow<====
		start testing depth====>
		implcloneable_depth.Teacher@15db9742   teacherName=������
		implcloneable_depth.Teacher@6d06d69c   teacherName=������
		����Teacher�ĵ�ַ��һ��
		end testing depth<====
		start testing depthImplSerial====>
		implserialzable.Teacher@677327b6   teacherName=������
		implserialzable.Teacher@4aa298b7   teacherName=������
		����Teacher�ĵ�ַ��һ��
		end testing depthImplSerial<====
		 */
	}
	public static void  testShallow() {
		System.out.println("start testing shallow====>");
			StudentShallow shallow=new StudentShallow();
			Teacher shallowTeacher=new Teacher();
			shallowTeacher.setName("������");
			shallow.setTeacher(shallowTeacher);
			
			try {
				StudentShallow copu_shallow=(StudentShallow)shallow.clone();
				System.out.println(copu_shallow.getTeacher()+"   teacherName="+copu_shallow.getTeacher().getName());
				System.out.println(shallowTeacher+"   teacherName="+shallowTeacher.getName());
				if(copu_shallow.getTeacher()==shallowTeacher){
					System.out.println("����Teacher�ĵ�ַһ��");
				}else{
					System.out.println("����Teacher�ĵ�ַ��һ��");
				}
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("end testing shallow<====");
	}
	public static void  testDepth() {
		System.out.println("start testing depth====>");
		Student_depth depth=new Student_depth();
		implcloneable_depth.Teacher depthTeacher=new implcloneable_depth.Teacher();
		depthTeacher.setName("������");
		depth.setTeacher(depthTeacher);
		
		try {
			Student_depth copu_depth=(Student_depth)depth.clone();
			System.out.println(copu_depth.getTeacher()+"   teacherName="+copu_depth.getTeacher().getName());
			System.out.println(depthTeacher+"   teacherName="+depthTeacher.getName());
			if(copu_depth.getTeacher()==depthTeacher){
				System.out.println("����Teacher�ĵ�ַһ��");
			}else{
				System.out.println("����Teacher�ĵ�ַ��һ��");
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end testing depth<====");
	}
	public static void  testDepthImplSerial() {
		System.out.println("start testing depthImplSerial====>");

		Student student=new Student();
		implserialzable.Teacher teacher=new implserialzable.Teacher();
		teacher.setName("������");
		student.setTeacher(teacher);
		
		try {
			Student copu_student=(Student)student.deepCopt();
			System.out.println(copu_student.getTeacher()+"   teacherName="+copu_student.getTeacher().getName());
			System.out.println(teacher+"   teacherName="+teacher.getName());
			if(copu_student.getTeacher()==teacher){
				System.out.println("����Teacher�ĵ�ַһ��");
			}else{
				System.out.println("����Teacher�ĵ�ַ��һ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("end testing depthImplSerial<====");
	}
}
