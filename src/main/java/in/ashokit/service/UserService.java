package in.ashokit.service;

import java.util.Map;

import in.ashokit.bindings.LoginForm;
import in.ashokit.bindings.RegisterForm;
import in.ashokit.bindings.ResetPwdForm;
import in.ashokit.entity.User;

public interface UserService {


	public User getUser(String email);

	public boolean saveUser(RegisterForm formObj);

	public User login(LoginForm formObj);

	public boolean resetPwd(ResetPwdForm formObj);

}
