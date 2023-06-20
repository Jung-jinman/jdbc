package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
// try catch finally �����ϴ� ���ο� ����
public class OracleConnectionWithResources {

	public static void main(String[] args) {
		//Connection conn = null;

		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String password = "0419";							// �츮���� 0419
		
		// try with resources ���� : try �� �ڿ���ü �����ϱ�
		try (
			  // �ڿ����� close �� �ʿ��� ��ü������ ���� �����ϱ�.
			Connection conn = DriverManager.getConnection(url, user, password);
			  // 2�� �̻��� ������ �ۼ��� �� �ֽ��ϴ�.
			){
			
			// ���� ���������� DriverManager ����Ű�ùǷ� ���� ����
			// Class.forName(driver);
			
			System.out.println("���� ���� = " + conn);
			if(conn != null)
				System.out.println("����Ŭ �����ͺ��̽� ���� ����!! ");
			else
				System.out.println("����Ŭ �����ͺ��̽� ���� ����!! ");
			
		}catch (Exception e) {	// ClassNotFoundException, SQLException ó�� �ʿ�
			System.out.println("ClassNotFoundException = ����̹� ��ΰ� �߸�����ϴ�.");
			System.out.println("SQLException = url �Ǵ� user �Ǵ� password�� �߸�����ϴ�.");
			System.out.println("�����޽��� = " + e.getMessage());
			e.printStackTrace();		// Exception �߻��� ��� ������ cascade �������� ���.
		}// catch end
		
		// conn.close()�� ��������� ������ �ʿ䰡 �����ϴ�. �ڵ� close
	}// main end
}// class end