package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

	@Autowired
	private DiscoveryClient discoveryClient;


	@RequestMapping("/")
	@ResponseBody
	public String home() {
//		List<ServiceInstance> list = discoveryClient.getInstances("ACCOUNTS-SERVICE");
		return "Web home";
	}

}
