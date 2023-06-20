package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;

public class JProductDao {		// ���ſ� ���õ� CRUD ���� SQL. DAO : JCustomerDao, JProduct
// �޼ҵ� �̸��� insert, update, delete, select, selectByPname ������� �̸��� �ۼ��ϼ���.
	// 2. ��ǰ ��� ����
	public List<JProduct> selectAll() throws SQLException {
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from j_product";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		List<JProduct> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new JProduct(rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getInt(4)));
		}
		ps.close();
		conn.close();
		return list;
	}
	
	// 3. ��ǰ������ �˻��ϱ� (����˻� -> �١�'�˻�� ���Ե� ��ǰ��'�١��� ��� ��ȸ�ϱ�)
	public List<JProduct> selectByPname(String pname) throws SQLException {
		// pname�� ����ڰ� �Է��� �˻���
		Connection conn = OracleUtility.getConnection();
		String sql = "select * from j_product" 
				+ "where pname like '%' || ? || '%'";		// like�� ���� ��. % ��ȣ ���
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, pname);
		ResultSet rs = ps.executeQuery();
		
		List<JProduct> list = new ArrayList<>();
		while(rs.next()) {
			list.add(new JProduct(rs.getString(1),
					rs.getString(2),
					rs.getString(3),
					rs.getInt(4)));
		}
		return list;
	}
}
