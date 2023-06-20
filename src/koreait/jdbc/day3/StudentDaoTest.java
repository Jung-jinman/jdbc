package koreait.jdbc.day3;

import java.sql.SQLException;
import java.util.List;

public class StudentDaoTest {
	
	public static void main(String[] args) {
		StudentDao dao = new StudentDao();
		
		System.out.println(":::::::1. insert �׽�Ʈ:::::::");
		System.out.println("2023009 �趯�� 17 ������ - ������ �Է�");
		StudentDto dto = new StudentDto("2023009", "�趯��", 17, "������"); 
		try {
			int count = dao.insert(dto);
			System.out.println("�л� ��� " + count + "�� �Է� ����!!");
			System.out.println("�Է� ��� ��ȸ : " + dao.selectOne(dto.getStuno()));
		} catch (SQLException e) {
			System.out.println("���� - " + e.getMessage());
		}
		
		System.out.println("\n:::::::2. update �׽�Ʈ:::::::");
		System.out.println("2023009 �趯���� 16 ��걸 - ������ ����");
		dto = new StudentDto("2023009", "�趯��", 16, "��걸");
		try {
			int count = dao.update(dto);
			System.out.println("�л� ��� " + count + "�� ���� ���� ����!!");
			System.out.println("���� ��� ��ȸ : " + dao.selectOne(dto.getStuno()));
		} catch (SQLException e) {
			System.out.println("���� - " + e.getMessage());

		}
		
		System.out.println("\n:::::::3. delete �׽�Ʈ:::::::");
		System.out.println("2023009 - ������ ����");
		try {
			int count = dao.delete("2023009");
			System.out.println("�л� ��� " + count + "�� ���� ����!!");
			System.out.println("���� ��� ��ȸ : " + dao.selectOne("2023009"));
		} catch (SQLException e) {
			System.out.println("���� - " + e.getMessage());

		}
		System.out.println("\n:::::::4. selectAll :::::::");
		System.out.println("�л� ���̺��� ��� ������ ��ȸ�Ͽ� ����մϴ�.");
		try {
			List<StudentDto> list = dao.selectAll();
			for(StudentDto stu : list) {
				System.out.println(stu);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}