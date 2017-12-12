package formatting.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/*
 * Sends messages to the scheduling tool
 * provides fallback if message send fails
 * 
 */

@Service
//@RibbonClient(name = "schedule-service", configuration = ScheduleServConfiguration.class)
public class SchedulingService {

	@Autowired
	private SchedulingService schedulingService;

	
    @LoadBalanced
    @Bean
    RestTemplate restTemplate(){
    	return new RestTemplate();
    }
    
    @Autowired
    RestTemplate restTemplate;
	
	
	//posts message to scheduling service, has fallback method in case of failure
	//@HystrixCommand(fallbackMethod = "defaultMessageResponse" )
	public String postMessage( Message message ) {
	
		//RestTemplate restTemplate2 = new RestTemplate();
		
		return this.restTemplate.postForObject("http://schedule-service/sendMessage/", message, String.class);
		//return restTemplate2.postForObject("http://localhost:8081/sendMessage/", message, String.class);
	}
/*
	public String defaultMessageResponse( Message message ) {
		RestTemplate restTemplate2 = new RestTemplate();
		
		//return this.restTemplate.postForObject("http://schedule-service/sendMessage/", message, String.class);
		return restTemplate2.postForObject("http://localhost:8081/sendMessage/", message, String.class);
	}
*/	
}
