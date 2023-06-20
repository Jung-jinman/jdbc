package koreait.jdbc.day4;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyMallMain {
	
	public static void main(String[] args) {
		System.out.println(":::::::::::::::::::: �趯�� ���θ��� ���Ű� ȯ���մϴ�. ::::::::::::::::::::");
		System.out.println("<< ��ǰ �Ұ� >>");
		JProductDao jProductDao = new JProductDao();
		try {
			List<JProduct> products = jProductDao.selectAll();
			for(JProduct product : products)
				System.out.println(product);
			
		} catch (SQLException e) {
			System.out.println("��ǰ�Ұ� ���� : " + e.getMessage());
		}
		System.out.println("\n<< ���� ��ǰ���Ÿ� ���� �˻��ϱ� >>");
		Scanner sc = new Scanner(System.in);
		System.out.print("�˻��� �Է� >>> ");
		String pname = sc.nextLine();
		try {
			List<JProduct> products = jProductDao.selectByPname(pname);
			for(JProduct product : products)
				System.out.println(product);
			
		} catch (SQLException e) {
			System.out.println("��ǰ�˻� ���� : " + e.getMessage());
		}
		
		JCustomerDao cdao = new JCustomerDao();
		JCustomer customer = null;
		boolean isLogin = false;							// �α��� ���� ���� ����
		String customid = null;
		System.out.println("\n<< ��ٱ��� ���� ��ǰ ���Ÿ� ���� �α��� �ϱ�(�ʼ�) >> ");
		while(!isLogin) {
			System.out.print("���� �α��� - ����� ID �Է� (�α��� ��Ҵ� 0000) >> ");
			customid = sc.nextLine();
			if(customid.equals("0000")) break;				// �ݺ� ����
			try {
				customer = cdao.selectById(customid);
				if(customer != null) {
					System.out.println(customer.getName() + " ���� ȯ���մϴ�.!!");
					isLogin = true;							// �ݺ� ����
				}else
					System.out.println("�Է��Ͻ� �� ID�� �������� �ʽ��ϴ�.!!");
			} catch (SQLException e) {
				System.out.println("���� �α��� ���� : " + e.getMessage());
			}
		}
		
		// ��ٱ��� ���� �α��� ������ ��츸 �����ϱ�
		// 4. ��ǰ ��ٱ��� ��� - ��ٱ��� ���̺��� �����Ƿ� ���Ÿ� ���ϴ� ��ǰ�� List �� ���
		JBuyDao bdao = new JBuyDao();
		List<JBuy> carts = new ArrayList<>();
		if(isLogin) {
			while(true) {
				System.out.println("\n��ٱ��Ͽ� ��� �մϴ�. �׸��Ϸ��� ��ǰ�ڵ� 0000 �Է��ϼ���.");
				System.out.println("������ ��ǰ �ڵ带 �Է��ϼ���. >>> ");
				String pcode = sc.nextLine();
					if(pcode.equals("0000")) break;
				System.out.println("������ ������ �Է��ϼ���. >>> ");
					int quantity = Integer.parseInt(sc.nextLine());
				carts.add(new JBuy(0, customid, pcode, quantity, null));
				
				System.out.println("��ٱ��Ͽ� ��� ��ǰ�� �����Ͻðڽ��ϱ�? (y/Y) ");
				if(sc.nextLine().toLowerCase().equals("y")) break;
			}
			
			System.out.println("��ٱ��� Ȯ�� : " + carts);
			System.out.println("���������� - ��ٱ���");
			for(JBuy b : carts) {
				System.out.println("��ǰ�ڵ� : " + b.getPcode() + ", ���� : " + b.getQuantity());
			}
			// dao���� carts �� ���޹޾� �������� insert ��ŭ �ݺ��ϴ� insert �����ϱ�
			int count = bdao.insertMany(carts);				// ���� ���̺� �����ϱ�
			if(count != 0)
				System.out.println("\n������ �Ϸ��߽��ϴ�.");
		// 5���� ��ٱ��� ��� ��ǰ�� j_buy ���̺� 1)���� ����, 2) �߸��� ���� rollback ���� �Ǵ��� Ȯ��
		// 6. ���������� - ���� ���� ����. �� ���� �ݾ��� ����� �ֱ� -> sql �׽�Ʈ �غ��� �޼ҵ� �ۼ� �õ��ϱ�
			System.out.println("::: ������� " + customer.getName() + " ȸ������ ���� ���� �Դϴ�. :::");
		// ����ؾ���
			try {
				List<MyPageBuy> buys = bdao.myPageBuy(customid);
				DecimalFormat df = new DecimalFormat("###,###,###");
				for(MyPageBuy b : buys) {
					System.out.println(String.format("%15s %20s %5d %10d %16d", 
							b.getBuy_date(),
							b.getPname(),
							b.getQuantity(),
							df.format(b.getPrice()),
							df.format(b.getTotal())));
				}
				long total = bdao.myMoney(customid);
				System.out.println("�� ���űݾ� : " + df.format(total));
				
			}catch (SQLException e) {
				System.out.println("���ų��� ���� : " + e.getMessage());
			}
			
		}else { // �α��� �� ���� ��
			System.out.println("�α����� ����߽��ϴ�. ���α׷��� �����մϴ�.");
		}
		sc.close();		// �� ���� �ۼ�.
	}
}