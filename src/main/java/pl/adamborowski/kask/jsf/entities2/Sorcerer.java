
package pl.adamborowski.kask.jsf.entities2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sorcerer complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="sorcerer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mana" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="environment" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="water"/>
 *             &lt;enumeration value="fire"/>
 *             &lt;enumeration value="wind"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sorcerer")
@NoArgsConstructor
public class Sorcerer {

    @Getter
    @Setter
    @XmlAttribute(name = "id", required = true)
    protected int id;
    @Getter
    @Setter
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @Getter
    @Setter
    @XmlAttribute(name = "mana", required = true)
    protected int mana;
    @Getter
    @Setter
    @XmlAttribute(name = "environment", required = true)
    protected Environment environment;

}
