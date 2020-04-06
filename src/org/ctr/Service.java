package org.ctr;

import java.util.Arrays;
import java.util.InputMismatchException;

import org.dao.StudentList;
import org.view.GoToHomeException;
import org.view.View;
import org.vo.Student;

public class Service {
	static final int STUDENTS = 100;
	StudentList Dao;
	
	public Service() {
		Dao = new StudentList(STUDENTS);
	}

	
	public int start() throws InputMismatchException, GoToHomeException {
		View.IO.menuL(View.appName);
		View.IO.menuM(View.menuArr[0]);
		return View.IO.selectMenu(View.menuArr);
	}
	
	public void classCtr() throws InputMismatchException, GoToHomeException {
		View.IO.prtClasses(Dao.getClasses(), Dao.getCurrentClasses(), Dao.getClassesLength());
		switch (View.IO.selectMenu(View.dataClCtr)) {
		case 1:insertC(); break;
		case 2:modifyC(); break;
		case 3:dellC(); break;
		}
	}
	
	public void insertC() throws GoToHomeException {
		if(Dao.getCurrentClasses()<Dao.getClassesLength()) {
			View.IO.input("새 과목 ");
			int j = Dao.insertClass(View.IO.nextLine());
			View.IO.complt("과목 추가 ");
			if(Dao.getTotalS()>0&&(View.IO.ask("지금 성적을 입력")==1)) {
				View.IO.inputScoreInfo();
				for(int i = 0; i < Dao.getTotalS(); i++) {
					Dao.setScore(i, j, View.IO.inputScoreAid(Dao.copyList(), i, Dao.getClasses(), j, 1));
				}
				View.IO.complt("성적 입력 ");
			}
		} else View.IO.full();
	}
	
	public void modifyC() throws InputMismatchException, GoToHomeException {
		View.IO.prtClasses(Dao.getClasses(), Dao.getCurrentClasses(), Dao.getClassesLength());
		View.IO.input("수정할 과목 번호 ");
		int i = View.IO.nextInt()-1;
		if(i>-1 && i<=Dao.getCurrentClasses()) {
			View.IO.inputSimple("새 과목 ");
			String str = View.IO.nextLine();
			int j = View.IO.ask(View.modiC);
			if(j==1) {Dao.modiC(Dao.getClasses()[i], str);View.IO.complt("과목 수정 ");}
			else if(j==0) {Dao.dellClass(Dao.getClasses()[i]); Dao.insertClass(i, str);View.IO.complt("과목 수정 ");}
			//else View.IO.inputError();
		} else View.IO.inputError(); 
	}
	
	public void dellC() throws GoToHomeException {
		View.IO.prtClasses(Dao.getClasses(), Dao.getCurrentClasses(), Dao.getClassesLength());
		View.IO.input("삭제할 과목 번호 ");
		int i = View.IO.nextInt()-1;
		if(i>-1 && i<=Dao.getCurrentClasses()) {
			int j = View.IO.ask("삭제");
			if(j==1) {
				Dao.dellClass(Dao.getClasses()[i]);
				View.IO.complt("과목 삭제");
			} else if(j==0) return;
		} else View.IO.inputError();
	}
	
	public void studentCtr() throws InputMismatchException, GoToHomeException {
		switch (View.IO.selectMenu(View.dataStCtr)) {
		case 1:insertS(); break;
		case 2:modifyS(); break;
		case 3:dellS(); break;
		}
	}
	
	public void insertS() throws GoToHomeException {
		if(Dao.getTotalS() < Dao.getMaxS()) {
			View.IO.input("학생 이름 ");
			String name = View.IO.nextLine();
			int st = Dao.insertST(name);
			View.IO.complt("학생 저장 ");
			if(Dao.getCurrentClasses()>0) {
				inputScore(st, name);
			}	
			View.IO.cutPage();	
			if((View.IO.ask("학생을 더 추가")==1)) {
				insertSSimple();
			} else return;
		} else View.IO.full();
	}
	
	public void insertSSimple() throws GoToHomeException {
		if(Dao.getTotalS() < Dao.getMaxS()) {
			View.IO.inputSimple("학생 이름 ");
			String name = View.IO.nextLine();
			int st = Dao.insertST(name);
			View.IO.complt("학생 저장 ");
			if(Dao.getCurrentClasses()>0) {
				inputScore(st, name);
			}	
			if((View.IO.ask("더 입력")==1)) {
				insertSSimple();
			} else return;
		} else View.IO.full();
	}

	public void inputScore(int st, String name) throws GoToHomeException {
			int j = View.IO.ask("지금 "+name+"의 성적을 입력");
			if(j==1) {
			View.IO.inputScoreInfo();
			for(int i = 0; i<Dao.getCurrentClasses(); i++) {
				Dao.setScore(st, Dao.getClasses()[i], View.IO.inputScoreAid(Dao.getClasses(),i, name));
			}
			} else if(j==0) {
				for(int i = 0; i<Dao.getCurrentClasses(); i++) {
					Dao.setScore(st, Dao.getClasses()[i], -2);
				} 
			} else if(j==-1) {
				View.IO.inputError();
				inputScore(st, name);
			}
	}

	
	public int selectS(String s) throws InputMismatchException, GoToHomeException {
		print(Dao.copyList());
		View.IO.input(View.IO.srcOrNumber(s));
		String str = View.IO.nextLine();
		if(View.IO.isNumeric(str)) {
			if(Integer.parseInt(str)<Dao.getTotalS()+1) {
				return Integer.parseInt(str)-1;
			} else View.IO.inputError(); selectS(s);
		} else { //이름으로 찾아서 프린트, 번호입력.
			return View.IO.printArr(Dao.copyList(), Dao.getClasses(), str, s+" 할 학생 번호 ");		
		}
		return -1;
	}
	
	public void modifyS() throws InputMismatchException, GoToHomeException {
		int i = selectS("수정 ");
		if(i<0) {View.IO.inputError(); return;}
		else {
			View.IO.menuM(View.dataStCtr[2]);
			switch(View.IO.selectMenu(View.StCtrArr)) {
			case 1://이름
				String name = View.IO.inputReturn("수정할 이름");
				Dao.changeName(i, name);
				View.IO.complt("이름 수정");
				break;
			case 2://점수
				if(Dao.getCurrentClasses()>0) {
					View.IO.inputScoreInfo2();
					for(int j = 0; j<Dao.getClasses().length; j++) {
						if(Dao.getClasses()[j]!=null) {
					
							String d = View.IO.modiScoreAid(Dao.copyList(), i, Dao.getClasses(), j);
							if(d.equals("")) continue;
							else {
								Dao.setScore(i, j, Double.valueOf(d));
							}
						}
					} View.IO.cutPage();View.IO.complt("점수 수정");
				} else View.IO.noClasses();
			}
		}
		
	}
	
	public void dellS() throws InputMismatchException, GoToHomeException {
		int i = selectS("삭제")-1;
		if(i<0||i>=Dao.getTotalS()) {View.IO.inputError(); return;}
		else { doDellS(i);	}
	}
	
	public void doDellS(int i) throws GoToHomeException {
		int j =View.IO.ask("삭제");
		switch(j) {
			case 1: Dao.dellST(i);View.IO.complt("삭제");break;
			case 0: break;
			case -1: View.IO.inputError(); doDellS(i);
		}
	}
	
	public void printS() throws InputMismatchException, GoToHomeException {
		print(sortArr(Dao.copyList(), View.IO.selectMenu(View.prtArr)));
	}
	
	public void print(Student[] list) throws GoToHomeException {
		try {
			View.IO.printArr(list, Dao.getClasses());
		} catch (NullPointerException e) {}
	}
	
	public Student[] sortArr(Student[] list, int i) throws GoToHomeException {	
			switch(i) {
			case 1:return list; 
			case 2:
				return Dao.sortByN(list);
			case 3:
				Arrays.sort(list);
				return list; 
			case 4:
				Arrays.sort(list);
				Student[] copy = new Student[list.length];
				for(int j = 0; j < list.length; j++) {
					copy[j]=list[list.length-1-j];
				}
				return copy; 
			case 5:
				View.IO.input("검색할 이름 ");
				String s = View.IO.nextLine();
				View.IO.printArr(Dao.copyList(), Dao.getClasses(), s);
				return null;
			} return null;		
	}
	
	public void info() {
		View.IO.info(Dao.getClassesLength(), Dao.getCurrentClasses(), Dao.getMaxS(), Dao.getTotalS());
	}
	
	public void exit() throws GoToHomeException {
		switch(View.IO.ask("종료")) {
		case 1: View.IO.exit(); System.exit(0);
		case 0: return;
		case -1: View.IO.inputError();
		}
	}

}
