@RestController
    @RequestMapping("/")
    public class ComputeController {
    	
    	private String welcome = "Welcome to Feign"; 

        @GetMapping("/message/{name}")
        public String add(@PathVariable(value="name") String name) {
            
            logger.info("Received request from {} for a greeting.", name);
            
            return welcome + " " + name;
        }
        
        @PostMapping("/message/{greeting}") 
        public void updateGreeting(@PathVariable(value="greeting") String greeting) {
        	welcome = greeting;
        	
        	logger.info("Greeting update to {}", welcome);
        }
}
