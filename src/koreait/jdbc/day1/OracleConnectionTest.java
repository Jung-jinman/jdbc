package koreait.jdbc.day1;

import java.sql.Connection;
import java.sql.DriverManager;
// �ٸ� DBMS Ŭ���̾�Ʈ ���α׷��� ���� db�� ����� �� �ִ� ������ ����.
// �� �ҽ��� ���� ���� �ؾ��� �� - '�����ͺ��̽� ����'
public class OracleConnectionTest {

	public static void main(String[] args) {
		// 0. Connection �� �������̽��� ���� ��ü�� �������� �ʰ�
		//	  ����Ŭ������ �־�� �մϴ�. db������ db����̹��� �����Ϸ��� db�� ������ ����
		//	  �˾Ƽ�(���Ͻ�) ����Ŭ������ ���� ��ü�� ����ϴ�.
		Connection conn = null;
		
		
		// 1. �Ʒ� 4���� �ʼ� ���� ������ �����մϴ�.
		// �����ϰ��� �ϴ� ������ �ּ� (�������� https://www.naver.com �� ����� ����)
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		// oracle.jdbc.driver�� ojdbc6.jar�� ���Ե� ��Ű���̸�
		// OracleDriver �� ����Ŭ ����̹� Ŭ���� �̸�
		String driver = "oracle.jdbc.driver.OracleDriver";
		String user = "iclass";
		String password = "0419";							// �츮���� 0419
		
		
		try {
			
		// 2. ����̹� Ŭ���� ��ü�� �޸𸮿� �ε�(�ø���)
		// 		�� ���� ��ü�� ������ִ� ������ �մϴ�.
			Class.forName(driver);
			
		// 3. DriverManager Ŭ������ ���ᰴü�� ����ϴ�. - 2���� ��ü�� ���۽�ŵ�ϴ�.
		//	  �̶� 2������ ���� ��ü �� dbms �� ���� ������ü�� ��������ϴ�.
			conn = DriverManager.getConnection(url, user, password);
			
		// 4. 3���� ����� ����Ŭ db�� �´� ���ᰴü�� �����˴ϴ�.
			System.out.println("���� ���� = " + conn);
			if(conn != null) {
				System.out.println("conn ��ü�� ���� Ŭ���� : " + conn.getClass().getName());
				// oracle.jdbc.driver.T4CConnection Ŭ������ ��ü�� ����
				
				System.out.println("����Ŭ �����ͺ��̽� ���� ����!! ");
			} else
				System.out.println("����Ŭ �����ͺ��̽� ���� ����!! ");
			
		}catch (Exception e) {	// ClassNotFoundException, SQLException ó�� �ʿ�
			System.out.println("ClassNotFoundException = ����̹� ��ΰ� �߸�����ϴ�.");
			System.out.println("SQLException = url �Ǵ� user �Ǵ� password�� �߸�����ϴ�.");
			System.out.println("�����޽��� = " + e.getMessage());
			e.printStackTrace();		// Exception �߻��� ��� ������ cascade �������� ���.
		}finally {
			try {
				if(conn != null)
					conn.close();			// SQLException ó��
			}catch(Exception e) {}
		}// ��������� �ڿ��� close => ������ص� ������ �ȳ��ϴ�.
		 // finally end
	}// main end
}// class end
/*
 * API : Application Programming Interface
 * 	   : ���� �ٸ� ����Ʈ���� �ý��� ���� ������ ���� ���(���̺귯���� �����˴ϴ�.) �������̽��� ����.
 * 
 * ���̺귯�� : �ڹ� ���̺귯���� ���� Ư�� ����� �����ϴ� Ŭ�������� ����. Ȯ���ڴ� �������� .jar
 * 
 * jdbc : �ڹٿ� dbms�� �����ϴ� api. ����Ŭ�� ojdbcX.jar �̰� x�� ����Ŭ jdbc ���� ǥ��.
 */
