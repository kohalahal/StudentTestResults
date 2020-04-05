package org.view;

public interface View {
		
	Pages page = new Pages();
	InOut IO = new InOut();
	
	String[] menuArr = {"메 뉴", "과 목 관 리", "학 생 관 리", "성 적 보 기", "정 보", "종 료"};
	String[] dataCtr = {null, "추 가", "수 정", "삭 제"};
	String[] inArr = {null, "한 명 입력", "여러 명 입력"};
	String[] prtArr = {null, "입력순", "성명 가나다순", "성적 오름차순", "성적 내림차순", "이름으로 찾아서 보기"};
	String[] upDArr = {null, "이름", "과목", "점수"};
	
	String appName = "학생 성적 관리 시스템 ";
	String pageCutter = "---------------------------------------";
	String zero = "";
	String name = "이름 ";
	String classes = "과목 ";
	String score = "점수 ";
	String input = "입력";
	String errorSymbol = "\t┏(°.°)┛!!";
	String inputError = " 잘못 입력하셨습니다.";
	String noResult = " 찾을 수 없습니다.";
	String att = " #\t이름\t\t전화 번호\n---------------------------------------.";
	String insertP = "전화 번호 저장하기";
	String full = "전화 번호부가 가득찼습니다.";
	String happySymbol = "\t{ ♥‿♥ } ";
	String complt = " 성공!";
	String pageName =" 페이지";
	String pageMove = "\t\t이전(<) / 다음(>)";
	String prevP = "\t이전 페이지";
	String nextP = "\t다음 페이지";
	String firstPage ="\t( ͡°_̯° )? 첫번째 페이지입니다.";
	String lastPage ="\t( ͡°_̯° )? 마지막 페이지입니다.";
	String goBack = "\t\t         [↩] 돌아가기(.)";
	String yesNo = "선택(Y/N) ";
	String upDChoice = "무엇을 수정하시겠습니까?";
	String doYou = "하시겠습니까?\n\t\t\t─◕████▄\n\t\t\t────◕██\n\t\t\t──◕███\n\t\t\t──◕\n\t\t\t──◕██\n";
	String currentClasses = "  현재 과목 : ";
	String noClasses = "저장된 과목이 없습니다.";
	String inputScoreInfo = "\t\ta수※ 수강하지 않는 과목인 경우:-1 입력";
}
