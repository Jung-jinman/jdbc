package koreait.jdbc.day2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentSelectOneMenu {
	
	public static void main(String[] args) {
		Connection conn = OracleUtility.getConnection();
		System.out.println("==================== �л��� �й��� ��ȸ�ϴ� �޴� ====================");
		selectOneStudent(conn);
		
		OracleUtility.close(conn);
	}
private static void selectOneStudent(Connection conn) {
		Scanner sc = new Scanner(System.in);
		String stuno = "2023999";
		String sql = "select * from TBL_STUDENT where stuno = ? ";
		try(
			PreparedStatement ps = conn.prepareStatement(sql);
		){
			ps.setString(1, stuno);
			// sql �����ϰ� 'select �� ��ȸ �����͸� ����� �޾� �ڹ� ������ ����'�ؾ� �մϴ�.
			//								 -> ResultSet �������̽� Ÿ�� ��ü�� �����մϴ�.
			ResultSet rs = ps.executeQuery();		// select �����ϱ�
			System.out.println("rs ��ü�� ���� Ŭ������ " + rs.getClass().getName());
			// 		�� oracle.jdbc.driver.OracleResultSetImpl Ŭ���� ��ü�� ��������ϴ�.
			
			// rs.next() �����͸� ������ Ŀ��(��ġ)�� ���� ������ �̵��մϴ�.
			// ��ȸ ��� ������ �˷��� '���� ���� �����ؾ� �� �޼ҵ�-��ȸ ��� ù ��° ������ �̵�' �Դϴ�.
			System.out.println("��ȸ ����� ������� ? " + rs.next());
			// ��ȸ�� rs���� Ư�� �÷����� �������� �� ��, �÷��� ������ Ÿ���� Ȯ���ϰ�
			// getXXXX �޼ҵ� ���ϱ�. getXXXX �޼ҵ��� ���ڴ� �÷��� �ε��� �Ǵ� �÷� �̸��Դϴ�.
			System.out.println("��ȸ ��� ù ��° �÷��� �� : " + rs.getString(1));
			System.out.println("��ȸ ��� �� ��° �÷��� �� : " + rs.getString(2));
			System.out.println("��ȸ ��� �� ��° �÷��� �� : " + rs.getInt(3));
			System.out.println("��ȸ ��� �� ��° �÷��� �� : " + rs.getString(4));
			
			System.out.println("���� ��ȸ ��� ���� �� ������� ? " + rs.next());
		}
		catch (SQLException e) {
			System.out.println("������ ��ȸ�� ������ ������ϴ�. �󼼳��� - " + e.getMessage());
			// ��� ������ ��� �Ҹ����� -> ��ȸ ����� ���µ� rs.getXXXX �޼ҵ� ������ ���� ���� �޽���.
		}
		sc.close();
	}
}