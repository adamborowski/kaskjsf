package pl.adamborowski.kask.jsf.entities2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author psysiu
 */
@XmlType(name = "environment")
@XmlEnum
public enum
        Environment {
        WATER,
        FIRE,
        WIND;
        }