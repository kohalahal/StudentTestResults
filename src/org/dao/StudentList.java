package org.dao;

import org.vo.Student;

public class StudentList {
	
	private Student[] List;
	private String[] Classes;
	private int id;
	private int totalS;
	private int totalC;
	
	public StudentList() {
		id = 1000;
		totalS = 0;
		totalC = 4;//예시지우면 0하기
		Classes = new String[Student.CLASSES];
		{
			Classes[0]="국어";
			Classes[1]="영어";
			Classes[2]="수학";
			Classes[3]="사회";
		}
	}
	
	public StudentList(int st) {
		this();
		List = new Student[st];
		{
			List[0]=new Student()
		}
	}
	
	public int getClassesLength() {
		return Classes.length;
	}
	
	public int getCurrentClasses() {
		return totalC;
	}
	
	public int getTotalS() {
		return totalS;
	}
	
	public int getMaxS() {
		return List.length;
	}
	
	public int emptyIdx() {
		for(int i = 0; i < List.length; i++) {
			if(List[i] == null) {
				return i;  
			}
		} return -1;
	}
	
	public int emptyClassIdx() {
		for(int i = 0; i < Classes.length; i++) {
			if(Classes[i] == null) {
				return i;  
			}
		} return -1;
	}
	
	public int getIdxS(Student s) {
		for(int i = 0; i < List.length; i++) {
			if(List[i].getId()==s.getId()) return i;
		}
		return -1;
	}
	
	public int getClassIdx(String str) {
		for(int i = 0; i < Classes.length; i++) {
			if(Classes[i].equals(str)) return i;
		}		
		return -1;
	}

	public int insertST(String name) {
		int i = emptyIdx();
		List[i] = new Student(id++, name);
		totalS++;
		return i;
	}
	
	public int insertClass(String str) {
		int j = emptyClassIdx();
		Classes[j] = str;
		totalC++;
		return j;
	}
	
	public void dellClass(int i) {
		Classes[i]=null;
		for(int j = 0; j < List.length; j++) {
			if(List[j]!=null) List[j].setScore(i, -2);
		}
		totalC--;
	}
	
	public void dellST(int i) {
		List[i] = null;
		// TODO Auto-generated method stub
		totalS--;
	}
	
	public Student[] copyList() {
		// TODO Auto-generated method stub
		Student[] copy = new Student[totalS];
		int j = 0;
		for(int i = 0; i < List.length;i++) {
			if(List[i]!=null) {
				copy[j++]=List[i];
			}
		}
		return copy;
	}
	
	public String[] getClasses() {
		if(totalC==0) return null;
		else {
			String[] copy = new String[totalC];
			int j = 0;
			for(int i = 0; i < Classes.length;i++) {
				if(Classes[i]!=null) {
					copy[j++]=Classes[i];
				}
			}
			return copy;
		}
	}
	
	public void setScore(int i, String str, double d) {
		List[i].setScore(getClassIdx(str), d);
	}
	
	public void setScore(int i, int j, double d) {
		List[i].setScore(j, d);
	}
	

}
