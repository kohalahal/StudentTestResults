package org.dao;

import java.util.Arrays;
import org.vo.Student;

public class StudentList {
	
	private Student[] List;
	private String[] Classes;
	private int id;
	private int totalS = 6;
	private int totalC = 4;
	
	public StudentList() {
		id = 1000;
		totalS = 6;//예시지우면 0하기
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
			List[0]=new Student(id++, "하무무");
			List[1]=new Student(id++, "차무무");
			List[2]=new Student(id++, "팍무무");
			List[3]=new Student(id++, "감무무");
			List[4]=new Student(id++, "김무빈");
			List[5]=new Student(id++, "강감찬");		
		};
		List[0].setScore(0, 90);
		List[0].setScore(1, 98);
		List[0].setScore(2, 87);
		List[0].setScore(3, 76);
		List[1].setScore(0, 99);
		List[1].setScore(1, 78);
		List[1].setScore(2, 77);
		List[1].setScore(3, 76);
		List[2].setScore(0, 69);
		List[2].setScore(1, 48);
		List[2].setScore(2, 77);
		List[2].setScore(3, 66);
		List[3].setScore(0, 99);
		List[3].setScore(1, 8);
		List[3].setScore(2, 7);
		List[3].setScore(3, 66);
		List[4].setScore(0, 9);
		List[4].setScore(1, 88);
		List[4].setScore(2, 77);
		List[4].setScore(3, 6);
		List[5].setScore(0, 99);
		List[5].setScore(1, 88);
		List[5].setScore(2, 57);
		List[5].setScore(3, 46);
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
	
	public int getIdxS(String name)  {
		for(int i = 0; i < List.length; i++) {
			if(List[i].getName().equals(name)) return i;
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
		for(int i = 0; i<totalS; i++) {
			List[i].setScore(j, -2);
		}
		return j;
	}
	
	public int insertClass(int j, String str) {
		Classes[j] = str;
		totalC++;
		for(int i = 0; i<totalS; i++) {
			List[i].setScore(j, -2);
		}
		return j;
	}
	
	public void dellClass(String str) {
		int i = getClassIdx(str);
		Classes[i]=null;
		for(int j = 0; j < totalS; j++) {
			List[j].setScore(i, -3);
		}
		totalC--;
	}
	
	public void dellST(int i) {
		List[i] = null;
		// TODO Auto-generated method stub
		totalS--;
		for(int j = i; j < List.length; j++) {
			if(List[j+1]!=null) {
				List[j]=List[j+1];
			} else { List[j]=null; return;}
		}
	}
	
	public Student[] copyList() {
		// TODO Auto-generated method stub
		if(totalS==0) return null;
		else {	
			Student[] copy = new Student[totalS];
			int j = 0;
			for(int i = 0; i < List.length;i++) {
				if(List[i]!=null) {
					copy[j++]=List[i];
				}
			}
			return copy;
		}
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
	
	public void setScore(int i, int j, double d) {
		List[i].setScore(j, d);
	}
	
	public void setScore(int i, String str, double d) {
		List[i].setScore(getClassIdx(str), d);
	}
	
	public void changeName(int i, String str) {
		List[i].setName(str);
	}
	
	public void modiC(String prev, String str) {
		int j = getClassIdx(prev);
		Classes[j] = str;
	}
	
	public Student[] sortByN(Student[] s) {
		if(s!=null) {
		String[] name = new String[s.length];
		Student[] r = new Student[s.length];
		for(int i = 0; i<s.length; i++) {
			name[i]=s[i].getName();
		}
		Arrays.sort(name);
		for(int i = 0; i<s.length; i++) {
			r[i]=new Student(name[i]);
		}
		for(int i = 0; i<s.length; i++) {
			r[i].setId(s[getIdxS(r[i].getName())].getId());
			r[i].setResults(s[getIdxS(r[i].getName())].getResults());
			r[i].setAvrg(s[getIdxS(r[i].getName())].getAvrg());
			r[i].setTotalS(s[getIdxS(r[i].getName())].getTotalS());
		}
		return r;
		} return null;
	}

}
