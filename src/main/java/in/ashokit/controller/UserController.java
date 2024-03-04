package in.ashokit.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ashokit.bindings.LoginForm;
import in.ashokit.bindings.RegisterForm;
import in.ashokit.bindings.ResetPwdForm;
import in.ashokit.constants.AppConstants;
import in.ashokit.entity.User;
import in.ashokit.props.AppProps;
import in.ashokit.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private AppProps appProps;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("login", new LoginForm());
		return "index";
	}
	
	@PostMapping("/login")
	public String loginCheck(@ModelAttribute("login") LoginForm login, Model model) {
		User user = userService.login(login);
		
		if(user == null) {
			model.addAttribute(AppConstants.ERR_MSG, appProps.getMessages().get(AppConstants.INVALID_CREDENTIALS));
			return "index";
		}
		
		if(user.getPwdUpdated().equals("NO")) {
			
			ResetPwdForm formObj = new ResetPwdForm();
			formObj.setUserId(user.getUserId());
			
			model.addAttribute("resetPwd", formObj);
			return AppConstants.RESET_PWD;
		}
		
		return "redirect:dashboard";
	}
	
	@PostMapping("/updatePwd")
	public String updatePwd(@ModelAttribute(AppConstants.RESET_PWD) ResetPwdForm resetPwd, Model model) {
		
		if(!resetPwd.getNewPwd().equals(resetPwd.getConfirmPwd())) {
			model.addAttribute(AppConstants.ERR_MSG, appProps.getMessages().get(AppConstants.BOTH_PWD_SHOULD_SAME));
			return AppConstants.RESET_PWD;
		}
		
		boolean status = userService.resetPwd(resetPwd);
		
		if(status) {
			return "redirect:dashboard";
		}
		else {
		model.addAttribute(AppConstants.ERR_MSG, appProps.getMessages().get(AppConstants.PWD_UPDATE_FAIL));
		return AppConstants.RESET_PWD;
		}
		
	}
	
	@GetMapping("/register")
	public String loadRegisterPage(Model model) {
		model.addAttribute("registerForm", new RegisterForm());
		Map<Integer,String> countries = userService.getCountries();
		model.addAttribute("countries", countries);
		return "register";
	}

	@GetMapping("/getStates")
	@ResponseBody
	public Map<Integer, String> getStates(@RequestParam("countryId") Integer countryId) {
		return userService.getStates(countryId);
	}
	
	@GetMapping("/getCities")
	@ResponseBody
	public Map<Integer, String> getCities(@RequestParam("stateId") Integer stateId){
		return userService.getCities(stateId);
	}
	

	@PostMapping("/register")
	public String registerUser(@ModelAttribute RegisterForm registerForm, Model model) {
		
		boolean saveUser = userService.saveUser(registerForm);
		
		if(saveUser) {
			model.addAttribute("succMsg",appProps.getMessages().get(AppConstants.REG_SUCCESS) );
		}else {
			model.addAttribute(AppConstants.ERR_MSG, appProps.getMessages().get(AppConstants.REG_FAIL));
		}
		
		Map<Integer,String> countries = userService.getCountries();
		model.addAttribute("countries", countries);
		
		return "register";
	}
	
	
	
}



















