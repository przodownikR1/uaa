package pl.java.scalatech.camel.nbp.bean;
import static com.google.common.collect.Lists.newArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "tabela_kursow")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tabela_kursow {
    @XmlAttribute
    private String typ;
    @XmlAttribute
    private String uid;

    @XmlElement
    private String numer_tabeli;

    @XmlElement
    private Date data_publikacji;

    @XmlElement(name = "pozycja")
    private List<Pozycja> pozycje = newArrayList();

    public String getTyp() {
        return typ;
    }

}