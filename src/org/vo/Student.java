package org.vo;

public class Student implements Comparable<Student>, Cloneable {
	public static final int CLASSES = 10; 
	
	private int id;
	private String name;
	private double[] results;
	private double totalS;
	private double avrg;

	
	public Student() {
		results = new double[CLASSES];
		for(int i = 0; i < results.length; i++) {
			results[i] = -3;
		}
	}
	
	public Student(String name) {
		this();
		this.name=name;	
	}

	
	public Student(int id, String name) {
		this();
		this.id=id;
		this.name=name;	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double[] getResults() {
		double[] copy = new double[results.length];
		for(int i = 0; i < results.length; i++) {
			copy[i]=results[i];
		}
		return copy;
	}

	public void setResults(double[] results) {
		this.results = results;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		calcScore();
		String str = name+"\t";
		for(int i = 0; i < results.length; i++) {
			if(results[i]==-1) {
				str += "수강X\t";
			} else if(results[i]==-2) {
				str += "입력X\t";
			} else if(results[i]==-3) {
			} else {str += results[i]+"\t";
			}
		}
		str += totalS+"\t";
		str += String.format("%.2f", avrg);
		return str;
	}

	public Student copyStudent(Student a) throws CloneNotSupportedException {
		Student copy = new Student(a.getId(), a.getName());
		copy.setResults(a.getResults());
		copy.setAvrg(a.getAvrg());
		copy.setTotalS(a.getTotalS());
		return copy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setScore(int i, double d) {
		if(i<results.length-2) results[i] = d;
	}
	
	public double getScore(int i) {
		return results[i];
	}
	
	
	
	public void setTotalS(double totalS) {
		this.totalS = totalS;
	}

	public void setAvrg(double avrg) {
		this.avrg = avrg;
	}

	public double getTotalS() {
		calcScore();
		return totalS;
	}

	public double getAvrg() {
		calcScore();
		return avrg;
	}

	public void calcScore() {
		int cnt = 0;
		totalS = 0;
		for(int i = 0; i<results.length; i++) {
			if(results[i]!=-1&&results[i]!=-2&&results[i]!=-3) {
				totalS += results[i];
				cnt++;
			}
		}
		avrg = totalS/cnt;
	}
	
	public String prtScore(int i) {
		calcScore();
		if(results[i]==-1) {
		return "수강X";
		} else if(results[i]==-2) {
			return "입력X";
		} else return String.format("%.2f", results[i]);
	}

	public int compareTo(Student other) {
		// TODO Auto-generated method stub
		calcScore();
		if(this.avrg<other.avrg) {
			return -1;
		} else if(this.avrg==other.avrg) {
			return 0;
		} else {
			return 1;
		}
			
	}
}
