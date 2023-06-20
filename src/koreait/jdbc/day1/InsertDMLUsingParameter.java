package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

// �л� ����ó�� ���α׷� �߿� ���ο� �л��� ���(�Է�) �ϴ� ����� ����� ���ô�.(���̺� insert ����)
// �Ķ���͸� �����ϴ� ������� sql �����ϱ�
public class InsertDMLUsingParameter {

	public static void main(String[] args) {

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "iclass";
		String password = "0419";							// �츮���� 0419
		
		try (
			Connection conn = DriverManager.getConnection(url, user, password);
			){
			
			System.out.println("���� ���� = " + conn);
			if(conn != null)
				System.out.println("����Ŭ �����ͺ��̽� ���� ����!! ");
			
			// db���� �Ϸ� �Ŀ� sql �����ϱ�.

			// insert SQL �ۼ� : ����Ǵ� ���� �ǵ��� �Ű������� ó���մϴ�. �Ű����� ��ȣ�� '?' �Դϴ�.
			String sql = "insert into TBL_STUDENT values (?,?,?,?)";			// insert SQL �ۼ�
		
			// PreparedStatemont ��ü�� �����ϸ鼭 ������ sql�� �����մϴ�.
			// PreparedStatemont ��ü�� Connection ��ü �޼ҵ带 ����ϴ�. Connection ������ü�� dbms ������ ����
			// �����ǰ� PreparedStatemont ��ü�� �׿� ���� ���� ��ü�� �����˴ϴ�.
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			// 'PreparedStatement() �޼ҵ�� ��ü�� ����'�ؼ� �����մϴ�.
			
			// �Ű������� �� �����ϱ� : �Ű������� ���������� ���� ���ڿ�, ���ڿ�, ����, ���ڿ�
			// setXXXX �޼ҵ��� ù ��° ���ڴ� �Ű������� �ε���, �� ��° ���ڴ� ���Դϴ�. XXXX�� �Ű������� Ÿ��
			pstmt.setString(1, "2023003");		// 1�� �ε����� "2023003" ����.
			pstmt.setString(2, "�趯��");
			pstmt.setInt(3, 17);
			pstmt.setString(4, "������");
			
			pstmt.execute();			// PreparedStatemont ��ü�� execute �ϸ� 'SQL�� ����'�˴ϴ�.
			pstmt.close();
			
			System.out.println("���������� ���ο� �л��� �Է� �Ǿ����ϴ�.!!!");
			
		}catch (Exception e) {			// ��� Exception ó���մϴ�.
			System.out.println("�����޽��� = " + e.getMessage());
			// e.printStackTrace();		// Exception �߻��� ��� ������ cascade �������� ���.
		}// catch end
	}// main end
}// class end
/*
 * Statement �������̽��� sql ���� ó���� ���õ� ����� �����մϴ�.
 * ��ü�� SQL �������� �����ͺ��̽��� �����մϴ�. Connection ��ü�� ���ؼ� ����ϴ�.
 * 
 * PreparedStatement�� Statement�� �ڽ� �������̽�.
 * Ư¡�� sql�� ���� ������ �ϰ� sql ���࿡ �ʿ��� ���� ������ �� �Ű������� �����ϴ� ����Դϴ�.
 */
