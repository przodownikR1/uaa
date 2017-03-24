@RestController
@RequestMapping("/nbp")
@RequiredArgsConstructor
public class SimpleController {
   
    private final Environment environment;

    @GetMapping("/info")
    String getMessage(){
        return "sample message from  NBP : port : " +  environment.getProperty("server.port"); 
    }
    
}
