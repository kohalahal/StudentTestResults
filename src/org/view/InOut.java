package org.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.vo.Student;

public class InOut implements View {
	
	Scanner sc = new Scanner(System.in);
	
	public int nextInt() throws GoToHomeException, InputMismatchException {
		String s = sc.nextLine();
		cutPage();
		if(isNumeric(s)) return Integer.parseInt(s);
		//System.out.print("넥스트인트");
		else if(s.equals(".")) {
			throw new GoToHomeException();
		} else {
			throw new InputMismatchException();
		}
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
		System.out.println("\t<<< "+str+" >>>\n");
	}
	
	public void menuM(String str) {
		System.out.println("\t<< "+str+" >>\n");
	}
	
	public void menuS(String str) {
		System.out.println("\t< "+str+" >\n");
	}

	public void cutPage() {
		System.out.println(pageCutter);
	}
	
	public void prtClasses(String[] s) {
		if(s==null) { 
			noClasses();
		} else {
			System.out.print(currentClasses);
			for(int i = 0; i < s.length; i++ ) {
				System.out.print((i+1)+"."+s[i]);
				if(i<s.length-1) System.out.print(" ");
				if((i+1)%4==0) System.out.println();
			}
			System.out.println();
		}
	}
	
	public boolean ask(String s) throws GoToHomeException  {
		System.out.print("  "+s+doYou);
		input(yesNo);
		String a = nextLine();
		if(a.equals("Y")||a.equals("y")||a.equals("ㅛ")) return true;
		else if(a.equals("n")||a.equals("N")||a.equals("ㅜ")) return false;
		else inputError(); return false;
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
	
	public double inputScoreAid(String[] cl, int i) throws GoToHomeException {
		inputSimple(cl[i]+" 점수 ");
		return nextDouble();
	}
	
	public double inputScoreAid(Student[] st, int j, String[] cl, int i) throws GoToHomeException {
		inputSimple(st[i].getName()+"의 "+cl[j]+" 점수 ");
		return nextDouble();
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
		System.out.print(" [#] 이름");
		for(int i = 0; i < cl.length; i++) {
			System.out.print(cl[i]+"\t");
		}
		System.out.println();
	}
	
	public void printArr(Student[] st, String[] cl) throws GoToHomeException {
		att(cl);
		for(int i = 0; i < st.length; i++) {
			System.out.println(" ["+(i+1)+"] "+st.toString());
		}
		input("");
		nextLine();
	}
	


}
