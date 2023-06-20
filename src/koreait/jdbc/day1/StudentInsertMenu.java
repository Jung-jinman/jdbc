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
		
		System.out.println(":::::::::::::::::::: �л� ��� �޴� �Դϴ�. ::::::::::::::::::::");
		System.out.println("�л���ȣ �Է½� 0000 �Է��� �����Դϴ�.");
		
		String stuno;
		String name;
		int age;
		String address;
		
		Scanner sc = new Scanner(System.in);
		
		try (
			Connection conn = DriverManager.getConnection(url, user, password);
			){
			System.out.println("���� ���� = " + conn);
			if(conn != null)
				System.out.println("����Ŭ �����ͺ��̽� ���� ����!! ");

			String sql = "insert into TBL_STUDENT values (?,?,?,?)";
		
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			System.out.println("���������� ���ο� �л��� �Է� �Ǿ����ϴ�.!!!");
			
			boolean status = true;
			while (status) {
				System.out.println("�л���ȣ �Է��ϼ��� >>>");
				stuno = sc.nextLine();
				pstmt.setString(1, stuno);
				if (stuno.equals("0000")) {
					System.out.println("�л� ����� �ߴ��մϴ�.");
					status = false;
					break;
				}
				System.out.println("�̸��� �Է��ϼ��� >>>");
				name = sc.nextLine();
				pstmt.setString(2, name);
				
				System.out.println("���̸� �Է��ϼ��� >>>");
				age = Integer.parseInt(sc.nextLine());
				pstmt.setInt(3, age);
				
				System.out.println("�ּҸ� �Է��ϼ��� >>>");
				address = sc.nextLine();
				pstmt.setString(4, address);

				pstmt.execute();			// insert �ϸ鼭 ���� �߻�.
				}
			pstmt.close();
			sc.close();
			System.out.println("�Է��� �л������� ���������� ����� �Ǿ����ϴ�.");
		}catch (Exception e) {
			System.out.println("�����޽��� = " + e.getMessage());
		}// catch end
	}// main end
}// class end
