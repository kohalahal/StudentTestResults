package org.ctr;

import java.util.InputMismatchException;

import org.dao.StudentList;
import org.view.GoToHomeException;
import org.view.View;

public class Service {
	static final int STUDENTS = 100;
	StudentList Dao;
	
	public Service() {
		Dao = new StudentList(STUDENTS);
	}
	
	public static void main(String[] args) throws InputMismatchException, GoToHomeException {
		Service svr = new Service();
		switch(svr.start()) {
		case 1: svr.classCtr();
		case 2: svr.studentCtr();
		}		
	}
	
	
	public int start() throws InputMismatchException, GoToHomeException {
		View.IO.menuL(View.appName);
		View.IO.menuM(View.menuArr[0]);
		return View.IO.selectMenu(View.menuArr);
	}
	
	public void classCtr() throws InputMismatchException, GoToHomeException {
		View.IO.prtClasses(Dao.getClasses());
		switch (View.IO.selectMenu(View.dataCtr)) {
		case 1:insertC(); break;
		case 2:modifyC(); break;
		case 3:dellC(); break;
		}
	}
	
	public void insertC() throws GoToHomeException {
		View.IO.input("새 과목 ");
		int j = Dao.insertClass(View.IO.nextLine());
		View.IO.complt("과목 추가 ");
		if(Dao.getTotalS()>0&&View.IO.ask("지금 성적을 입력")) {
			for(int i = 0; i < Dao.getTotalS(); i++) {
				Dao.setScore(i, j, View.IO.inputScoreAid(Dao.copyList(), i, Dao.getClasses(), j));
			}
			View.IO.complt("성적 입력 ");
		}
	}
	
	public void modifyC() throws InputMismatchException, GoToHomeException {
		View.IO.prtClasses(Dao.getClasses());
		View.IO.input("수정할 과목 번호 ");
		int i = View.IO.nextInt();
		if(i>0 && i<=Dao.getCurrentClasses()) {
		Dao.dellClass(i-1);
		View.IO.inputSimple("새 과목 ");
		Dao.insertClass(View.IO.nextLine());
		View.IO.complt("과목 수정 ");
		} else View.IO.inputError(); 
	}
	
	public void dellC() throws GoToHomeException {
		View.IO.prtClasses(Dao.getClasses());
		View.IO.input("삭제할 과목 번호 ");
		int i = View.IO.nextInt();
		if(i>0 && i<=Dao.getCurrentClasses()) {
			if(View.IO.ask("삭제")) {
				Dao.dellClass(i-1);
				View.IO.complt("과목 삭제");
			} else return;
		} else View.IO.inputError();
	}
	
	public void studentCtr() throws InputMismatchException, GoToHomeException {
		switch (View.IO.selectMenu(View.dataCtr)) {
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
			if(Dao.getCurrentClasses()>0) {
				View.IO.inputScoreInfo();
				for(int i = 0; i<Dao.getCurrentClasses(); i++) {
					Dao.setScore(st, Dao.getClasses()[i], View.IO.inputScoreAid(Dao.getClasses(),i));
				}
			}	
			View.IO.cutPage();
			View.IO.complt("학생 저장 ");
			if(View.IO.ask("더 입력")) {
				insertS();
			}
		} else View.IO.full();
	}
	
	public void selectS() {
		
	}
	
	public void modifyS() {
		
	}
	
	public void dellS() {
		
	}
	
	public void printS() throws InputMismatchException, GoToHomeException {
		switch(View.IO.selectMenu(View.prtArr)) {
		case 1:View.IO.printArr(Dao.copyList(), Dao.getClasses());
		case 2:
		case 3:
		case 4:
		}
	}
	


}
