package pl.java.scalatech.camel.nbp;

import static javax.xml.bind.JAXBContext.newInstance;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;
import org.springframework.stereotype.Component;

import pl.java.scalatech.camel.nbp.bean.Tabela_kursow;

@Component
class UnmarshalCurrencyRoute extends RouteBuilder{
    private static final String UNMARSHAL_TO_BEAN_ROUTE = "unmarshalToBeanRoute";
    private static final String JAXB_UNMARSHAL_ROUTE = "jaxbUnmarshalRoute";
    private static final String DIRECT_UMARSHAL_READY = "direct:umarshalReady";
   
    @Override
    public void configure() throws Exception {
        DataFormat jaxb =  new JaxbDataFormat(newInstance(Tabela_kursow.class));//1
       from("file://nbp?move=old&moveFailed=failed").routeId(JAXB_UNMARSHAL_ROUTE).unmarshal(jaxb).to(DIRECT_UMARSHAL_READY);
       from(DIRECT_UMARSHAL_READY).routeId(UNMARSHAL_TO_BEAN_ROUTE)
       .beanRef("currentExchange");
    }

}
