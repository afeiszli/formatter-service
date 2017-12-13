package formatting.service;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * This class defines the rest service
 * exposes an enpoint and allows post messages to be sent
 * 
 */

@RestController
//@RibbonClient(name = "schedule-service")
//, configuration = ScheduleServConfiguration.class)
public class RESTFormatter {

	@Autowired
	private SchedulingService schedulingService;

    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){
    	return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;
	
    @HystrixCommand(fallbackMethod = "defaultMessageResponse" )
    @RequestMapping(value = "/formatter/", method = RequestMethod.POST)
    public String returnMessage(@RequestBody Message message){
        message.convertBody();

		return schedulingService.postMessage(message);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String returnHelloWorld(){
		return "Hello World";
    }
    
	public String defaultMessageResponse( Message message ) {
		return message.toString() + " (HYSTRIX)";
	}
}
