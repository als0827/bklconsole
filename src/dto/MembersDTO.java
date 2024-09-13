package dto;

public class MembersDTO {

	private String memId;	
	private String pwd;
	private String memName;
	private String email;
	private String birth;	
	private String phone;	
	private String gender;	
	private String address;	
	private String joinDate;	
	private String withDate;
	private String status;
	
	public MembersDTO() {
	}
	
	public MembersDTO(String memId, String pwd) {
		super();
		this.memId = memId;
		this.pwd = pwd;
	}


	public MembersDTO(String memId, String pwd, String memName, String email, String birth, String phone, String gender,
			String address, String joinDate, String withDate, String status) {
		super();
		this.memId = memId;
		this.pwd = pwd;
		this.memName = memName;
		this.email = email;
		this.birth = birth;
		this.phone = phone;
		this.gender = gender;
		this.address = address;
		this.joinDate = joinDate;
		this.withDate = withDate;
		this.status = status;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getWithDate() {
		return withDate;
	}

	public void setWithDate(String withDate) {
		this.withDate = withDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MembersDTO [memId=" + memId + ", pwd=" + pwd + ", memName=" + memName + ", email=" + email + ", birth="
				+ birth + ", phone=" + phone + ", gender=" + gender + ", address=" + address + ", joinDate=" + joinDate
				+ ", withDate=" + withDate + ", status=" + status + "]";
	}
	
}
