package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentUpdateMenu {
	
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		System.out.println("==================== �л� ���� ���� �޴� �Դϴ�. ====================");
		System.out.println("<< ������ �й��� ���� ���̿� �ּҸ� ������ �� �ֽ��ϴ�. >>");
		
		try(
			Connection conn = DriverManager.getConnection(url, user, password);
			){
			
			updateStudent(conn);
	}catch (Exception e) {
		System.out.println("���� �޽��� + " + e);
	}
	}
	
	private static void updateStudent(Connection conn) {
		Scanner sc = new Scanner(System.in);
		String stuno, address, age;
		String sql = "update tbl_STUDENT\r\n"
				+ "set age = ?, address =? \r\n"
				+ "where stuno = ? ";
		System.out.println("�л���ȣ 0000 �Է��� ���� ��� �Դϴ�.");
		System.out.println("�й��� �Է��ϼ��� >>>");
		stuno = sc.nextLine();
		
		if (stuno.equals("0000")) {
			System.out.println("�л� ���� ����(�Է�)�� ����մϴ�.");
			return;				// ���Ͽ� ���� ���� ���� �ܼ��ϰ� �޼ҵ� ����� ����˴ϴ�.
		}
		
		System.out.println("���̸� �Է��ϼ���(10�� �̻�, 30�� ����) >>>");
		age = sc.nextLine();
		
		System.out.println("�ּҸ� �Է��ϼ��� >>>");
		address = sc.nextLine();
		
		try (
		PreparedStatement ps = conn.prepareStatement(sql);
		){	// �Ű������� ������ ������ Ȯ���ϰ� �����ϴ� setXXX �޼ҵ� �����մϴ�.
			ps.setInt(1, Integer.parseInt(age));
			ps.setString(2, address);
			ps.setString(3, stuno);
			// ps.execute();		// insert(create), update, delete, select(read) ��� ����
			int count = ps.executeUpdate();				// ���ϰ��� �ݿ��� ���� ���� -> ���ο� �޼ҵ� �Ẹ�� 
						// insert, update, delete �� �����մϴ�.
			System.out.println("�л� ���� ���� " + count + " ���� �Ϸ�Ǿ����ϴ�.");
			
		}catch (SQLException e) {
			System.out.println("������ ������ ������ ������ϴ�. �󼼳��� - " + e.getMessage());
		}
	
	}

}
