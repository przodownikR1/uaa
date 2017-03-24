package pl.java.scalatech.camel.nbp.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Pozycja {
    @XmlElement
    private String nazwa_waluty;
    @XmlElement
    private String przelicznik;
    @XmlElement
    private String kod_waluty;
    @XmlElement
    private String kurs_sredni;

}