package koreait.jdbc.day5;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class CustomerDto {
	
	private int cusno;
	private String cusname;
	private String phone;
	private String address;
	private Date joindate;
	private String grade;
	private String city;
	
}
