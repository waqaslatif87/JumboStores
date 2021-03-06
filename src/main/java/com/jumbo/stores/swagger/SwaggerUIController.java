package com.jumbo.stores.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * SwagerUIController To expose SWAGGER UI from context path and redirect all
 * request to Swagger UI.
 * 
 * @author Waqas
 *
 */
@Controller
public class SwaggerUIController {
	@RequestMapping(value = "/swagger")
	public String index() {
		return "redirect:swagger-ui.html";
	}
}
