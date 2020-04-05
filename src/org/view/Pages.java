package org.view;

import org.vo.Student;

public class Pages extends InOut {
	
	public void printArr(Student[] st, String[] cl) {
		att(cl);
		for(int i = 0; i < st.length; i++) {
			System.out.println(" ["+(i+1)+"] "+st.toString());
		}
	}
	
	
}
