package com.alekseytyan.controller;

import com.alekseytyan.logiweb.LogiwebService;
import com.alekseytyan.logiweb.controller.WelcomeController;
import com.alekseytyan.logiweb.controller.auth.AuthController;
import com.alekseytyan.logiweb.controller.auth.RegisterController;
import com.alekseytyan.logiweb.controller.error.ErrorController;
import com.alekseytyan.logiweb.controller.role.HomePageController;
import com.alekseytyan.logiweb.controller.role.admin.AdminController;
import com.alekseytyan.logiweb.controller.role.admin.UserCrudController;
import com.alekseytyan.logiweb.controller.role.driver.DriverController;
import com.alekseytyan.logiweb.controller.role.employee.EmployeeController;
import com.alekseytyan.logiweb.controller.role.employee.lorry.LorryCrudController;
import com.alekseytyan.logiweb.controller.role.employee.order.LoadCrudController;
import com.alekseytyan.logiweb.controller.role.employee.order.OrderCrudController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LogiwebService.class)
public class ControllerTest {

	@Autowired
	private WelcomeController welcomeController;

	@Autowired
	private HomePageController homePageController;

	@Autowired
	private AuthController authController;

	@Autowired
	private RegisterController registerController;

	@Autowired
	private ErrorController errorController;

	@Autowired
	private AdminController adminController;

	@Autowired
	private UserCrudController userCrudController;

	@Autowired
	private DriverController driverController;

	@Autowired
	private LorryCrudController lorryCrudController;

	@Autowired
	private LoadCrudController loadCrudController;

	@Autowired
	private OrderCrudController orderCrudController;

	@Autowired
	private EmployeeController employeeController;



	@Test
	public void contextLoads() throws Exception {
		assertThat(authController).isNotNull();
		assertThat(welcomeController).isNotNull();
		assertThat(homePageController).isNotNull();

		assertThat(registerController).isNotNull();
		assertThat(errorController).isNotNull();

		assertThat(adminController).isNotNull();
		assertThat(userCrudController).isNotNull();
		assertThat(driverController).isNotNull();
		assertThat(lorryCrudController).isNotNull();
		assertThat(loadCrudController).isNotNull();
		assertThat(orderCrudController).isNotNull();
		assertThat(employeeController).isNotNull();
	}

}