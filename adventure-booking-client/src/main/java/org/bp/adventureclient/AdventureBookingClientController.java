package org.bp.adventureclient;

import org.bp.adventureclient.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.ResponseEntity;

@Controller
public class AdventureBookingClientController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/booking")
	public String bookingForm(Model model) {
		model.addAttribute(new AdventureBookingRequest());
		
		return "booking";
	}
	
	@GetMapping("/booking/payment")
	public String paymentForm(Model model) {
		model.addAttribute(new String());
		
		return "payment";
	}
	
	@GetMapping("/booking/payment/{id}")
	public String getPayment(@PathVariable String id, Model model) {
		
		try {
			ResponseEntity<PaymentResponse> response = restTemplate.getForEntity(String.format("http://localhost:8085/api/adventure/booking/payment/%s", id), PaymentResponse.class);
			model.addAttribute("paymentResponse", response.getBody());
			
			return "result";
			
		} catch(Exception e) {
			model.addAttribute("fault", e);
			
			return "fault";
		}

	}
	
	@PostMapping("/booking")
	public String booking(@ModelAttribute("adventureBookingRequest") AdventureBookingRequest request, Model model) {

		try {
			model.addAttribute("booking", request);
			ResponseEntity<PaymentResponse> response = restTemplate.postForEntity("http://localhost:8085/api/adventure/booking", request, PaymentResponse.class);
			model.addAttribute("paymentResponse", response.getBody());
			
			return "result";
			
		} catch(Exception e) {
			model.addAttribute("fault", e);
			
			return "fault";
		}
	}
	
	@PostMapping("/booking/payment")
	public ModelAndView redirect(@ModelAttribute("id") String id) {
	    return new ModelAndView(String.format("redirect:/booking/payment/%s", id));
	}

}