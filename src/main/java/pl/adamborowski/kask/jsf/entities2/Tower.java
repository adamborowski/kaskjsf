
package pl.adamborowski.kask.jsf.entities2;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tower complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="tower">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wizzard" type="{http://www.adamborowski.pl/kask/jsf}sorcerer" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="height" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tower", propOrder = {"wizzards"})
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
public class Tower {
    @XmlElement(required = true, name = "wizzard")
    protected List<Sorcerer> wizzards = new ArrayList<>();
    @XmlAttribute(name = "id", required = true)
    protected int id;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "height")
    protected Integer height;
}
