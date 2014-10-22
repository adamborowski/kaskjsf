
package pl.adamborowski.kask.jsf.entities2;

import lombok.*;

import javax.persistence.*;
import javax.xml.bind.annotation.*;


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
@ToString(exclude = "tower")
@EqualsAndHashCode(exclude = "tower")
@AllArgsConstructor
@Getter
@Setter
//
@Entity
@Table(name = "wizzards")
@NamedQuery(name = "Sorcerer.findAll", query = "SELECT b FROM Sorcerer b")
public class Sorcerer {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlAttribute(name = "id", required = true)
    protected Integer id;
    @Column
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @Column
    @XmlAttribute(name = "mana", required = true)
    protected Integer mana;
    @Column
    @Enumerated(EnumType.STRING)
    @XmlAttribute(name = "environment", required = true)
    protected Environment environment;
    @XmlTransient
    @ManyToOne()
    @JoinColumn(name = "tower_id")
    protected Tower tower;
}
