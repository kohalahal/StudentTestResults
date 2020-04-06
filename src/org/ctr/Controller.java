package org.ctr;

import java.util.InputMismatchException;

import org.view.GoToHomeException;
import org.view.View;

public class Controller {
	
	public static void main(String[] args) throws InputMismatchException, GoToHomeException, CloneNotSupportedException {
		Service svr = new Service();
		while(true) {
			try {
				switch(svr.start()) {
				case 1: svr.classCtr();break;
				case 2: svr.studentCtr();break;
				case 3: svr.printS();View.IO.cutPage();System.out.println();break;
				case 4: svr.info();break;
				case 5: svr.exit();break;
				}		
			} catch (GoToHomeException e) {
				continue;
			} catch (InputMismatchException e) {
				continue;
			}
		}
	}

}
