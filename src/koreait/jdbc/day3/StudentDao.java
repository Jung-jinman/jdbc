package koreait.jdbc.day3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import koreait.jdbc.day2.OracleUtility;

//DAO : Data Access(����-�б�� ����) Object
//		�� SQL ���� �޼ҵ带 ��� ���� Ŭ����.

/* StudentDao �� ������ ���.
 * insert,update  �� sql �Ķ���Ϳ� ������ �������� Ÿ���� dto 
 * delete��                                        ������ �Ǵ� String
 * delete sql �� ������ �÷��� ������ �϶��� dto�� �ɼ� �ֽ��ϴ�. map �� ���� ����մϴ�.
 * 
 * insert,update,delete �� ���� ���ϰ����� �ݿ��� ���� ������ ����. 
 * 
 * selectOne : sql �Ķ���Ϳ� ������ �����͸� �޼ҵ� ���ڷ� ��.
 * selectAll : �Ķ���� ������ �������� ���� ������ ��ü�� List Ÿ��.
 * 
 */

public class StudentDao {
	
	//���߿� db�� `���� �ڵ�`�ϱ� ���� `�����ӿ�ũ`�� ����ϸ� Exception ó�� ���ص� �˴ϴ�.
	public int insert(StudentDto student) throws SQLException {
		
		Connection connection = OracleUtility.getConnection();
		
		String sql = "insert into TBL_STUDENT values(?,?,?,?)";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, student.getStuno());
		ps.setString(2, student.getName());
		ps.setInt(3, student.getAge());
		ps.setString(4, student.getAddress());
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();		
		return result;
	}
	
	public int update(StudentDto student) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "update TBL_STUDENT set age = ? , address = ? where stuno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setInt(1, student.getAge());
		ps.setString(2, student.getAddress());
		ps.setString(3, student.getStuno());
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		return result;
	}
	
	//delete �޼ҵ�� �������� ��������. - �Ű������� ? 
	public int delete(String stuno) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "delete from TBL_STUDENT where stuno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		
		ps.setString(1, stuno);		//�޼ҵ� ����(�Ű�����)���� sql ������ ����
		int result = ps.executeUpdate();
		
		ps.close();
		connection.close();
		return result;
	}
	
	//select ��� ��ȸ - ��ȸ��� ������ List ��ü�� �����մϴ�.�޼ҵ忡�� ��ȸ�� ������� ����.
	//    ��� �� ��Ÿ��ɵ��� �� �޼ҵ带 ����ϴ� ���α׷����� �����մϴ�.
	public List<StudentDto> selectAll() throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql = "select * from TBL_STUDENT";
		PreparedStatement ps = connection.prepareStatement(sql);
		List<StudentDto> results = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			StudentDto dto = new StudentDto(rs.getString(1),
											rs.getString(2),
											rs.getInt(3),
											rs.getString(4));
			results.add(dto);
		}
		return results;
	}
	
	//select * from TBL_STUDENT where stuno = '2023002'; ������ ���� 
	//   �� selectOne �޼ҵ� ����
	public StudentDto selectOne(String stuno) throws SQLException {
		Connection connection = OracleUtility.getConnection();
		String sql ="select * from TBL_STUDENT where stuno = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, stuno);
		
		ResultSet rs = ps.executeQuery();
		StudentDto result=null;
		if(rs.next()) {
			//String stuno2 = rs.getString(1);
			String name = rs.getString(2);
			int age = rs.getInt(3);
			String address = rs.getString(4);
			result = new StudentDto(rs.getString(1), name, age, address);
	// �ڵ� ���̸� �Ʒ��� ���� ���ϴ�.		
	//		return  new StudentDto(stuno, rs.getString(2), rs.getInt(3), rs.getString(4));
		}
		return result;
	}
}