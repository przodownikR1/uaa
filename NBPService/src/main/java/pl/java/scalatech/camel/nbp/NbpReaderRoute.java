package pl.java.scalatech.camel.nbp;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
class NbpReaderRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {        
        RecipientListBean recipient= new RecipientListBean("file://nbp?fileName=${date:now:yyyyMMddHHmmss}.xml","stream:out"); 
        from("timer://nbp?fixedRate=true&period=5000s")
        .routeId("httpCurrencyLoaderRoute")
        .to("http://www.nbp.pl//kursy/xml/LastA.xml").convertBodyTo(String.class).recipientList(method(recipient));
    }

}
