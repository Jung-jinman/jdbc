package koreait.jdbc.day4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import koreait.jdbc.day2.OracleUtility;

public class JBuyDao {		//���ſ� ���õ� CRUD ���� SQL. DAO : JCustomerDao , JProductDao
//�޼ҵ� �̸��� insert , update , delete , select , selectByPname ������� �̸��� �ۼ��ϼ���.

		// ��Ʈ������� ó���ϴ� ���� : auto commit �� �����ϰ� ���� commit�� �մϴ�.��
		// try catch �� ���� �ϼ���. throws �ƴմϴ�.
		public int insertMany(List<JBuy> carts) {
			Connection conn = OracleUtility.getConnection(); 
		// 5. ��ǰ ����(����)�ϱ� - ��ٱ����� �����͸� `����` ���̺� �Է��ϱ� (������ insert)	
			String sql ="insert into j_buy \n" + 
					"values (jbuy_seq.nextval , ? , ?, ?, sysdate)";
			int count = 0;
			PreparedStatement ps = null;
			try {
				conn.setAutoCommit(false);			// auto commit ���� - false
				ps = conn.prepareStatement(sql);
				for(JBuy b : carts) {
					ps.setString(1, b.getCustomid());
					ps.setString(2, b.getPcode());
					ps.setInt(3, b.getQuantity());
					count += ps.executeUpdate();
				}
				conn.commit();						// Ŀ��
			}catch (SQLException e) {
				System.out.println("��ٱ��� ��ǰ �����ϱ� ���� : " + e.getMessage());
				System.out.println("��ٱ��� ��ǰ ���Ÿ� ����մϴ�.");
				try {
					conn.rollback();				// �ѹ�
				} catch (SQLException e1) {
				}
			}
			return count;
		}
		
		public List<MyPageBuy> myPageBuy(String customid) throws SQLException {
			Connection conn = OracleUtility.getConnection();
			String sql = "elect * from mypage_buy where customid= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, customid);
			ResultSet rs = ps.executeQuery();
			
			List<MyPageBuy> list = new ArrayList<>();
			while(rs.next()) {
				list.add(new MyPageBuy(rs.getDate(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getInt(6),
						rs.getLong(7)));
			}
			return list;
		}
		public long myMoney(String customid) throws SQLException {
			Connection conn = OracleUtility.getConnection();
			String sql = "select sum(total) from mypage_buy where customid= ?";
			// �Լ� ��ȸ�ϴ� selec�� �׻� ������� 1��, �÷��� 1��
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, customid);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			long sum = rs.getLong(1);
		return sum;
		}
		
		public int insert(JBuy buy) {
			return 1;
		}

		public JBuy selectOne(int buyseq) throws SQLException{
			// sql ������ ������ �ϰ� �׽�Ʈ ���̽� Ȯ���ϱ�
			Connection connection = OracleUtility.getConnection();
			String sql = "select * from j_buy where buy_seq = ?";
			
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, buyseq);
			
			JBuy buy = null;
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				buy = new JBuy(rs.getInt(1),
							   rs.getString(2),
							   rs.getString(3),
							   rs.getInt(4),
							   rs.getDate(5));
			}
			return buy;
		}
}