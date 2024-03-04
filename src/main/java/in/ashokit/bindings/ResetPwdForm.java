package in.ashokit.bindings;

import lombok.Data;

@Data
public class ResetPwdForm {

	private Integer userId;
	private String email;
	private String newPwd;
	private String confirmPwd;

	
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

}
