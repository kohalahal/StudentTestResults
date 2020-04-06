package org.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.vo.Student;

public class InOut implements View {
	
	Scanner sc = new Scanner(System.in);
	
	public String inputReturn(String s) throws GoToHomeException {
		input(s);
		return nextLine();
	}
	
	public String srcOrNumber(String s) {
		return "\t\t\t\t※ 이름을 입력하면 검색 가능\n"+s+"할 학생 번호 ";
	}
	
	public int nextInt() throws GoToHomeException, InputMismatchException {
		String s = sc.nextLine();
		cutPage();
		if(isNumeric(s)) {return Integer.parseInt(s);}
		//System.out.print("넥스트인트");
		else if(s.equals(".")) {
			throw new GoToHomeException();
		} else {
			throw new InputMismatchException();
		}
	}
	
	public int nextInt(int a) {
		cutPage();
		return a;
	}
	
	public int nextInt(String s) {
		cutPage();
		return Integer.parseInt(s);
	}

	public String nextLine() throws GoToHomeException {
		String s = sc.nextLine();
		//System.out.print("넥스트라인");
		cutPage();
		if(s.equals("0")) {
			throw new GoToHomeException();
		}		
		return s;
	}
	
	public String nextLine(String s) throws GoToHomeException {
		cutPage();
		if(s.equals("0")) {
			throw new GoToHomeException();
		}		
		return s;
	}
	
	public boolean isNumeric(String s) { 
		  try {  
		    Integer.parseInt(s);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
	}
	
	public int selectMenu(String[] menu) throws InputMismatchException, GoToHomeException {
		System.out.print("  ");
		for(int i = 1; i < menu.length; i++) {
			System.out.print(i+". "+menu[i]+"\t");
			if(i%3==0) System.out.print("\n  ");
		}
		System.out.println();
		input("");
		int i = nextInt();
		menuM(menu[i]);
		return i;
	}
	
	public void menuL(String str) {
		System.out.println("  <<< "+str+" >>>\n");
	}
	
	public void menuM(String str) {
		System.out.println("   << "+str+" >>\n");
	}
	
	public void menuS(String str) {
		System.out.println("\t< "+str+" >\n");
	}

	public void cutPage() {
		System.out.println(pageCutter);
	}
	
	public void prtClasses(String[] s, int a, int b) {
		if(s==null) { 
			noClasses();
		} else {
			classInfo(a,b);
			for(int i = 0; i < s.length; i++ ) {
				System.out.print((i+1)+". "+s[i]+"   ");
				if((i+1)%5==0) {System.out.println();System.out.print("     ");}
			}
			System.out.println("\n");
		}
	}
	
	public void classInfo(int a, int b) {
		System.out.print(currentClasses+" ("+a+"/"+b+")");
		System.out.print(" :\n\t");
		
	}
	
	public int ask(String s) throws GoToHomeException  {
			System.out.print("  "+s+doYou);
			input(yesNo);
			String a = nextLine();
			if(a.equals("Y")||a.equals("y")||a.equals("ㅛ")) return 1;
			else if(a.equals("n")||a.equals("N")||a.equals("ㅜ")) return 0;
			else if(a.equals(".")) return -2;
			else return -1;	
	}
	
	public void input(String s) {
		cutPage();
		goBack();
		System.out.print(s+input+"...\t");
	}
	
	public void inputSimple(String s ) {
		System.out.print(s+input+"...\t");
	}
	
	public void inputScoreInfo() {
		System.out.println(inputScoreInfo);
	}
	public void inputScoreInfo2() {
		cutPage();
		System.out.println(inputScoreInfo2);
	}
	
	public double inputScoreAid(String[] cl, int i) throws GoToHomeException {
		inputSimple(cl[i]+" 점수 ");
		return nextDouble();
	}
	
	public double inputScoreAid(String[] cl, int i, String name) throws GoToHomeException {
		inputSimple(name+"의 "+cl[i]+" 점수 ");
		return nextDouble();
	}
	
	public double inputScoreAid(Student[] st, int j, String[] cl, int i, int k) throws GoToHomeException {
		System.out.println(" ["+(j+1)+"] "+st[j].getName()+"의 "+cl[i]+"점수 입력...");
		return nextDouble();
	}
	
	
	public String modiScoreAid(Student[] st, int j, String[] cl, int i) throws GoToHomeException {
		System.out.print("  "+st[j].getName()+"의 "+cl[i]+" 점수 입력 (현재 점수:"+st[j].prtScore(i)+")...");
		return sc.nextLine();
	}
	
	
	public double nextDouble() {
		double d = sc.nextDouble();
		sc.nextLine();
		return d;
	}
	
	public void inputError() {
		System.out.println(errorSymbol+inputError);
		cutPage();
	}
	
	public void noResult() {
		System.out.println(errorSymbol+noResult);
		cutPage();
	}
	
	public void noClasses() {
		System.out.println(errorSymbol+noClasses);
		System.out.println();
	}
	
	public void goBack() {
		System.out.println(goBack);
	}
	
	public void full() {
		System.out.println(errorSymbol+full);
	}
	
	public void complt(String s) {
		System.out.println(happySymbol+s+complt);
		cutPage();
	}
	
	public void att(String[] cl) {
		System.out.print(" [#] 이름\t");
		for(int i = 0; i < cl.length; i++) {
			System.out.print(cl[i]+"\t");
		}	System.out.print("[총점]\t[평균]\t");
		System.out.println();
	}
	
	public void printArr(Student[] st, String[] cl) throws GoToHomeException {
		if(st==null) {noResult(); return;}
		att(cl);
		for(int i = 0; i < st.length; i++) {
			System.out.println(" ["+(i+1)+"] "+st[i].toString());
		}
	}
	
	public void printArr(Student[] st, String[] cl, String name) throws GoToHomeException {
		if(st==null) {noResult();}
		else {
			int cnt = 0;
			for(int i = 0; i < st.length; i++) {
				if(st[i].getName().equals(name)) cnt++;
			}
			if(cnt>0) {
				att(cl);
				for(int i = 0; i < st.length; i++) {
					if(st[i].getName().equals(name)) System.out.println(" ["+(i+1)+"] "+st[i].toString());
				}

			}  else noResult();
		}
	} 
	
	public int printArr(Student[] st, String[] cl, String name, String str) throws GoToHomeException {
		if(st==null) {noResult(); return -1;}
		else {
			int cnt = 0;
			for(int i = 0; i < st.length; i++) {
				if(st[i].getName().equals(name)) cnt++;
			}
			if(cnt>0) {
				att(cl);
				for(int i = 0; i < st.length; i++) {
					if(st[i].getName().equals(name)) System.out.println(" ["+(i+1)+"] "+st[i].toString());
				}
			input(str);
			int i = nextInt()-1;
			if(i<st.length) return i;	
			} 
		}
		return -1;
	} 
	
	public void info(int a, int b, int c, int d) {
		System.out.println();
		System.out.print("   저장 가능한 과목 : "+a+"개");
		System.out.print("   현재 설정된 과목 : "+b+"개\n");
		System.out.print("   저장 가능한 학생 : "+c+"명");
		System.out.print("   현재 저장된 학생 : "+d+"명\n\n");
		cutPage();
	}

	public void exit() {
		System.out.println(bye);
	}
}
