package com.abcb.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abcb.customPropertyEditors.CustomStringPropertyEditor;
import com.abcb.customPropertyEditors.PhonePropertyEditor;
import com.abcb.dao.AdminDAOImpl;
import com.abcb.dto.AddTellerDTO;
import com.abcb.dto.UserInfoDTO;

@Controller
public class AdminController {

	@Autowired
	AdminDAOImpl adminDAO;

	Logger logger = Logger.getLogger(getClass());

	@RequestMapping("employees")
	String getEmployeeList(Model model, HttpSession session) {

		Object permission_type = session.getAttribute("permission_type");

		if (permission_type == null) {

			return "visitor/page-not-found";
		} else if (permission_type.toString().matches("admin|manager")) {

			List<UserInfoDTO> employeeList = adminDAO.getEmployeeList();
			if (!employeeList.isEmpty()) {

				model.addAttribute("employeeList", employeeList);
				return permission_type + "/employee-list";
			}
		}
		return permission_type + "/page-not-found";
	}

	@RequestMapping("view-employee-details/{user_id}")

	String getEmployeeDetails(@PathVariable(value = "user_id") String user_id, HttpSession session, Model model) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {

			return "visitor/page-not-found";

		} else if (permissionType.toString().matches("admin|manager")) {

			boolean validEmployee = adminDAO.validEmployee(Integer.parseInt(user_id));

			if (validEmployee) {

				UserInfoDTO userInfo = adminDAO.getDetails(Integer.parseInt(user_id));

				model.addAttribute("userInfo", userInfo);

				return permissionType + "/view-employee-details";

			}
//				If the account number received from the URL
//				is invalid
			return permissionType + "/page-not-found";

		}

		return permissionType + "/page-not-found";

	}

	@RequestMapping("un-freeze-employee/{user_id}/{action}")
	String unFreezeEmployee(@PathVariable(value = "user_id") String user_id,
			@PathVariable(value = "action") String action, Model model, HttpSession session) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {

			return "visitor/page-not-found";

		} else if (permissionType.toString().matches("admin|manager")) {

			boolean validEmployee = adminDAO.validEmployee(Integer.parseInt(user_id));

			if (validEmployee) {

				if (action.equals("freeze")) {

					adminDAO.unFreezeUser(session, Integer.parseInt(user_id), action);

					UserInfoDTO userInfo = adminDAO.getDetails(Integer.parseInt(user_id));
					model.addAttribute("userInfo", userInfo);

					model.addAttribute("accountStatus", "deactivated");
					return permissionType + "/updated-employee-details";
				} else if (action.equals("unfreeze") && permissionType.equals("manager")) {

					adminDAO.unFreezeUser(session, Integer.parseInt(user_id), action);

					UserInfoDTO userInfo = adminDAO.getDetails(Integer.parseInt(user_id));
					model.addAttribute("userInfo", userInfo);

					model.addAttribute("accountStatus", "activated");
					return permissionType + "/updated-employee-details";
				}
			}
//				If the account number received from the URL
//				is invalid
//			return permissionType + "/page-not-found";

		}

		return permissionType + "/page-not-found";

	}

	@RequestMapping("users")
	String getUserList(Model model, HttpSession session) {

		Object permission_type = session.getAttribute("permission_type");

		if (permission_type == null) {

			return "visitor/page-not-found";
		} else if (permission_type.toString().matches("admin|manager")) {

			List<UserInfoDTO> userList = adminDAO.getUserList();
			if (!userList.isEmpty()) {

				model.addAttribute("userList", userList);
				return permission_type + "/user-list";
			}
		}
		return permission_type + "/page-not-found";
	}

	@RequestMapping("view-user-details/{user_id}")
	String getUserDetails(@PathVariable(value = "user_id") String user_id, HttpSession session, Model model) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {

			return "visitor/page-not-found";

		} else if (permissionType.toString().matches("admin|manager")) {

			boolean validUser = adminDAO.validUser(Integer.parseInt(user_id));

			if (validUser) {

				UserInfoDTO userInfo = adminDAO.getDetails(Integer.parseInt(user_id));

				model.addAttribute("userInfo", userInfo);

				return permissionType + "/view-user-details";

			}

		}
		return permissionType + "/page-not-found";
	}

	@RequestMapping("un-freeze-user/{user_id}/{action}")
	String unFreezeUser(@PathVariable(value = "user_id") String user_id, @PathVariable(value = "action") String action,
			Model model, HttpSession session) {

		Object permissionType = session.getAttribute("permission_type");

		if (permissionType == null) {

			return "visitor/page-not-found";

		} else if (permissionType.toString().matches("admin|manager")) {

			boolean validUser = adminDAO.validUser(Integer.parseInt(user_id));

			if (validUser) {

				if (action.equals("freeze")) {

					adminDAO.unFreezeUser(session, Integer.parseInt(user_id), action);

					UserInfoDTO userInfo = adminDAO.getDetails(Integer.parseInt(user_id));
					model.addAttribute("userInfo", userInfo);

					model.addAttribute("accountStatus", "deactivated");
					return permissionType + "/updated-user-details";
				} else if (action.equals("unfreeze") && permissionType.equals("manager")) {

					adminDAO.unFreezeUser(session, Integer.parseInt(user_id), action);

					UserInfoDTO userInfo = adminDAO.getDetails(Integer.parseInt(user_id));
					model.addAttribute("userInfo", userInfo);

					model.addAttribute("accountStatus", "activated");
					return permissionType + "/updated-user-details";
				}
			}
//				If the account number received from the URL
//				is invalid
//			return permissionType + "/page-not-found";

		}

		return permissionType + "/page-not-found";

	}

	@RequestMapping("add-teller")
	String getAddTellerPage(@ModelAttribute("addTellerDTO") AddTellerDTO addTellerDTO, HttpSession session) {

		Object permission_type = session.getAttribute("permission_type");

		if (permission_type == null) {
			return "visitor/page-not-found";
		}

		if (permission_type.toString().equals("admin")) {

			return permission_type + "/add-teller";
		}

		return permission_type + "/page-not-found";
	}

	@RequestMapping("save-teller")
	String saveTeller(@Valid @ModelAttribute("addTellerDTO") AddTellerDTO addTellerDTO, BindingResult result,
			HttpSession session, Model model) {

		Object permission_type = session.getAttribute("permission_type");

		if (permission_type == null) {
			return "visitor/page-not-found";
		}

		if (permission_type.toString().equals("admin")) {

			if (result.hasErrors()) {

				return permission_type + "/add-teller";
			}

			boolean duplicateExists = adminDAO.checkDuplicateTeller(addTellerDTO);
			if (duplicateExists) {

				model.addAttribute("duplicateTeller", true);
				return permission_type + "/add-teller";
			}

			adminDAO.saveTeller(session, addTellerDTO);
			model.addAttribute("tellerAdded", true);
			return permission_type + "/add-teller";

		}

		return permission_type + "/page-not-found";
	}

	@RequestMapping("remove-employee/{user_id}")
	String removeEmployee(@PathVariable(value = "user_id") String user_id, HttpServletResponse response,
			HttpSession session) throws IOException {

		logger.info("Remove Employee method check: Emp Id: " + user_id);
		Object permission_type = session.getAttribute("permission_type");

		if (permission_type == null) {
			return "visitor/page-not-found";
		}

		if (permission_type.toString().equals("admin")) {

			boolean validEmployee = adminDAO.validEmployee(Integer.parseInt(user_id));
//			logger.info("Remove Employee validity check: " + validEmployee);

			if (validEmployee) {

				logger.info("Inside Employee is valid: Emp Id: " + user_id);
				adminDAO.removeEmployee(session, Integer.parseInt(user_id));
				response.sendRedirect("/InternetBanking/employees");

//				in case redirection fails
				return permission_type + "/" + permission_type + "-home";
			}
		}

		return permission_type + "/page-not-found";
	}

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		CustomStringPropertyEditor editor = new CustomStringPropertyEditor();

//		Removing all the white spaces
		binder.registerCustomEditor(String.class, "email", editor);
		binder.registerCustomEditor(String.class, "phone_number", editor);
		binder.registerCustomEditor(String.class, "password", editor);
		binder.registerCustomEditor(String.class, "name", editor);
		binder.registerCustomEditor(String.class, "father_name", editor);
		binder.registerCustomEditor(String.class, "mother_name", editor);
		binder.registerCustomEditor(String.class, "address", editor);

		PhonePropertyEditor phoneEditor = new PhonePropertyEditor();

//		Removing country code
		binder.registerCustomEditor(String.class, "phone_number", phoneEditor);
	}
}
