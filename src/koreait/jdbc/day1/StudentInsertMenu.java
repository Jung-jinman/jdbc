package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;
public class StudentInsertMenu {

	public static void main(String[] args) {

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		
		System.out.println(":::::::::::::::::::: 학생 등록 메뉴 입니다. ::::::::::::::::::::");
		System.out.println("학생번호 입력시 0000 입력은 종료입니다.");
		
		String stuno;
		String name;
		int age;
		String address;
		
		Scanner sc = new Scanner(System.in);
		
		try (
			Connection conn = DriverManager.getConnection(url, user, password);
			){
			System.out.println("연결 상태 = " + conn);
			if(conn != null)
				System.out.println("오라클 데이터베이스 연결 성공!! ");

			String sql = "insert into TBL_STUDENT values (?,?,?,?)";
		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			System.out.println("정상적으로 새로운 학생이 입력 되었습니다.!!!");
			
			boolean status = true;
			while (status) {
				System.out.println("학생번호 입력하세요 >>>");
				stuno = sc.nextLine();
				pstmt.setString(1, stuno);
				if (stuno.equals("0000")) {
					System.out.println("학생 등록을 중단합니다.");
					status = false;
					break;
				}
				System.out.println("이름을 입력하세요 >>>");
				name = sc.nextLine();
				pstmt.setString(2, name);
				
				System.out.println("나이를 입력하세요 >>>");
				age = Integer.parseInt(sc.nextLine());
				pstmt.setInt(3, age);
				
				System.out.println("주소를 입력하세요 >>>");
				address = sc.nextLine();
				pstmt.setString(4, address);

				pstmt.execute();			// insert 하면서 오류 발생.
				}
			pstmt.close();
			sc.close();
			System.out.println("입력한 학생정보가 정상적으로 등록이 되었습니다.");
		}catch (Exception e) {
			System.out.println("오류메시지 = " + e.getMessage());
		}// catch end
	}// main end
}// class end
