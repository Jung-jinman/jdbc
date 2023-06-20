package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentSelectOneMenuComplete {
	
	public static void main(String[] args) {
		Connection conn = OracleUtility.getConnection();
		System.out.println("==================== �л� �й��� ��ȸ�ϴ� �޴� ====================");
		selectOneStudent(conn);
		
		OracleUtility.close(conn);
	}
private static void selectOneStudent(Connection conn) {
		Scanner sc = new Scanner(System.in);
		String sql = "select * from TBL_STUDENT where stuno = ? ";
		System.out.println("��ȸ�� �й� �Է� >>> ");
		String stuno = sc.nextLine();
		try(
			PreparedStatement ps = conn.prepareStatement(sql);
		){
			ps.setString(1, stuno);
			
			ResultSet rs = ps.executeQuery();		// select �����ϱ�
			System.out.println("rs ��ü�� ���� Ŭ������ " + rs.getClass().getName());
			
			if(rs.next()) {		// ���� : ���̺� �÷��� ������ �˾ƾ� �ε����� ���� �� �ֽ��ϴ�.
				// getXXX �޼ҵ�� rs �� ����Ű�� ���� ���� �÷����� �������� �մϴ�.
				System.out.println("�й� : " + rs.getString(1));
				System.out.println("�й� : " + rs.getString("stuno"));		// �ε��� ��� �÷������� ��.
				System.out.println("�̸� : " + rs.getString(2));
				System.out.println("�̸� : " + rs.getString("name"));
				System.out.println("���� : " + rs.getInt(3));
				System.out.println("���� : " + rs.getInt("age"));
				System.out.println("�ּ� : " + rs.getString(4));
				System.out.println("�ּ� : " + rs.getString("address"));
			} else {
				System.out.println("<< ��ȸ ����� �����ϴ�. >>");
			}
		}
		catch (SQLException e) {
			System.out.println("������ ��ȸ�� ������ ������ϴ�. �󼼳��� - " + e.getMessage());
			// ��� ������ ��� �Ҹ����� -> ��ȸ ����� ���µ� rs.getXXXX �޼ҵ� ������ ���� ���� �޽���.
		}
		sc.close();
	}
}
/*
 * ��� �л� ��ȸ�ϴ� StudentSelectAllMenu Ŭ���� : 1�ٿ� 1�� ���� ����ϼ���.
 * ������� �Է��ϸ� �ش� ���� ��ȸ�ϴ� ScoreSelectWithSubject Ŭ����
 */
