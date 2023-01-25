package d2s.historyfiller.controllers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication  
@RestController  
public class HistoryFillerController {
	
	@GetMapping("/d2shistoryfiller")
	public String defaultAnswer() {
		return "hi, once an hour I ask www.dotaprotracker for recent pro matches then requesting them from www.stratz.com and then I send them to history DB service";
	}
	
}
