package com.soumet.cloud.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.logging.Logger;


@Controller
public class DataController {

    final long version = Math.round(Math.random() * 1000);
    protected Logger logger = Logger.getLogger(DataController.class.getName());

	@RequestMapping("/")
	@ResponseBody
	public String home(HttpServletRequest request, HttpServletResponse response) {
		return "More cool sh*t. Version " + version;
	}

	@RequestMapping("/status")
	@ResponseBody
	public String status(HttpServletRequest request, HttpServletResponse response) {
        logger.warning("Call to status. version is " + version);
        Enumeration<String> headers = request.getHeaderNames();
        String data = "";
//        while (headers.hasMoreElements()) {
//            String header = headers.nextElement();
//            data += header + " : " + request.getHeader(header) + "<BR>";
//        }
        return data + "<BR>Version " + version;
	}

}
