package org.vo;

public class Student {
	public static final int CLASSES = 10;
	
	private int id;
	private String name;
	private double[] results;
	private double totalScore;
	private double avrg;
	
	public Student() {
		results = new double[CLASSES];
		for(int i = 0; i < results.length; i++) {
			results[i] = -2;
		}
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
		String str = "";
		for(int i = 0; i < results.length; i++) {
			if(results[i]==-1) {
				str += "수강X\t";
			} else if(results[i]==-2) {
				continue;
			} else str += results[i]+"\t";
		}
		return name+"\t"+str;
	}

	public Student copyStudent(Student a) {
		Student copy = new Student(a.getId(), a.getName());
		copy.setResults(a.getResults());
		return copy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setScore(int i, double d) {
		results[i] = d;
	}
	
	public double getScore(int i) {
		return results[i];
	}
	
	public void calcScore() {
		int cnt = 0;
		for(int i = 0; i<results.length; i++) {
			if(results[i]!=-1&&results[i]!=-2) {
				totalScore += results[i];
				cnt++;
			}
		}
		setAvrg(totalScore/cnt);
	}

	public void setAvrg(double avrg) {
		this.avrg = avrg;
	}
}
