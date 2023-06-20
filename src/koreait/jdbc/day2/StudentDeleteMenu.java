package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentDeleteMenu {
	
	public static void main(String[] args) {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";
		System.out.println("==================== �л� ���� ���� �޴� �Դϴ�. ====================");
		System.out.println("<< ������ �й����� �л� ������ ���� �� �� �ֽ��ϴ�. >>");
		
		try (
			Connection conn = DriverManager.getConnection(url, user, password);
			){
			
			deleteStudent(conn);
		}catch (Exception e) {
			System.out.println("���� �޽��� = " + e);
		} // catch end
	} // main end
// @SuppressWarnings("resource")		// ���ҽ��� ���� ���� ǥ������ �ʰ� ���ּ���.
private static void deleteStudent(Connection conn) {
	Scanner sc = new Scanner(System.in);
	String stuno;
	String sql = "delete from TBL_STUDENT where stuno = ? ";
	
	System.out.println("�л���ȣ 0000 �Է��� ���� ��� �Դϴ�.");
	System.out.println("�й��� �Է��ϼ��� >>> ");
	stuno = sc.nextLine();
	
	if (stuno.equals("0000")) {
		System.out.println("�л� ���� ������ ����մϴ�.");
		sc.close();
		return;					// ���Ͽ� ���� ���� ���� �ܼ��ϰ� �޼ҵ� ����� ����˴ϴ�.
	}
	try (
		PreparedStatement ps = conn.prepareStatement(sql);
		){
		ps.setString(1, stuno);
		int count = ps.executeUpdate();
		
		System.out.println("�л� ���� ���� " + count + "���� �Ϸ�Ǿ����ϴ�.");
	} catch (SQLException e) {
		System.out.println("������ ������ ������ ������ϴ�. �󼼳��� - " + e.getMessage());
	}
	sc.close();
}

}
