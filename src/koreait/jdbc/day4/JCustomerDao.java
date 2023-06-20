package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import koreait.jdbc.day2.OracleUtility;

public class JCustomerDao {
	
	// 1. ȸ�� �α��� - ������ ȸ�� ���̵� �Է��ؼ� �����ϸ� �α��� ����
	public JCustomer selectById(String customid) throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from j_custom"
					 + " where custom_id = ?";				// PK ��ȸ : ��� �� 0 �Ǵ� 1��
		PreparedStatement ps = conn.prepareStatement(sql);
	// Statement : SQL, Prepared : SQL �� �̸� ������ �Ǿ� �غ��.
	// PreparedStatement �� Statement �������̽��� ���� �� �ֽ��ϴ�.
	// Statement �������̽� : SQL ���࿡ �ʿ��� �����͸� ���ÿ� ���Խ��Ѽ� �������� �մϴ�.
			
		ps.setString(1, customid);
	// �غ�� SQL �� �Ķ���� �����Ͽ�
		
		ResultSet rs = ps.executeQuery();
	// select ���� ����
		JCustomer temp = null;
		if (rs.next()) {
			temp = new JCustomer(rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getInt(4),
					rs.getDate(5));
		}
		ps.close();
		conn.close();
		
		return temp;
	}
}
