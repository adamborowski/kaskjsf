
package pl.adamborowski.kask.jsf.entities2;

import lombok.*;
import pl.adamborowski.kask.jsf.entities.Book;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for root complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="root">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tower" type="{http://www.adamborowski.pl/kask/jsf}tower"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlType(name = "root")
@ToString(exclude = "towers")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "root")
public class Root {
    @XmlElement(required = true, name = "tower")
    private List<Tower> towers = new ArrayList<>();
}
