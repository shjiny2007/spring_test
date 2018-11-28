package spring.model.team;

public class TeamDTO {

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String[] getSkill() {
		return skill;
	}

	public void setSkill(String[] skill) {
		this.skill = skill;
	}

	public String getSkills() {
		if (skill != null) { //insert,update 때만 사용하는 if문
			String skills = "";
			for (int i = 0; i < skill.length; i++) {
				skills = skills + skill[i];

				if (i < skill.length - 1) {
					skills = skills + ", ";
				}
			}
			this.skills = skills; 
		}

		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	
	public int getGrpno() {
		return grpno;
	}

	public void setGrpno(int grpno) {
		this.grpno = grpno;
	}
	
	public int getIndent() {
		return indent;
	}
	
	public void setIndent(int indent) {
		this.indent = indent;
	}
	
	public int getAnsnum() {
		return ansnum;
	}
	
	public void setAnsnum(int ansnum) {
		this.ansnum = ansnum;
	}
	private int no;
	private String name;
	private String gender;
	private String hobby;
	private String skills;
	private String[] skill; //form에서 보낸 데이터저장
	private String phone; //db에서 가져올 떄
	private String zipcode;
	private String address;
	private String address2;
	private int grpno;
	private int indent;
	private int ansnum;
}

