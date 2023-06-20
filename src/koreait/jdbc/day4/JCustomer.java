package koreait.jdbc.day4;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@ToString
public class JCustomer {
// DTO : Jbuy, JProduct
	
	private String custom_id;
	private String name;
	private String email;
	private int age;
	private Date regDate;
	
	
	public String getCustom_id() { return custom_id; }
	public String getName() { return name; }
	public String getEmail() { return email; }
	public int getAge() { return age; }
	
}