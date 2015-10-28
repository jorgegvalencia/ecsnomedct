
package coredataset;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para normalizedConcept complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="normalizedConcept">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="JSON" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alternatives" type="{http://CoreDataset/xsd}classifiedConcept" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="classCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="code" type="{http://CoreDataset/xsd}classifiedConcept" minOccurs="0"/>
 *         &lt;element name="codeOrig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entities" type="{http://CoreDataset/xsd}entityRelationship" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="relationshipPair" type="{http://CoreDataset/xsd}classifiedRelationship" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="text" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "normalizedConcept", namespace = "http://CoreDataset/xsd", propOrder = {
    "json",
    "alternatives",
    "classCode",
    "code",
    "codeOrig",
    "entities",
    "relationshipPair",
    "text"
})
public class NormalizedConcept {

    @XmlElementRef(name = "JSON", namespace = "http://CoreDataset/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> json;
    @XmlElement(nillable = true)
    protected List<ClassifiedConcept> alternatives;
    @XmlElementRef(name = "classCode", namespace = "http://CoreDataset/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> classCode;
    @XmlElementRef(name = "code", namespace = "http://CoreDataset/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<ClassifiedConcept> code;
    @XmlElementRef(name = "codeOrig", namespace = "http://CoreDataset/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codeOrig;
    @XmlElement(nillable = true)
    protected List<EntityRelationship> entities;
    @XmlElement(nillable = true)
    protected List<ClassifiedRelationship> relationshipPair;
    @XmlElementRef(name = "text", namespace = "http://CoreDataset/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> text;

    /**
     * Obtiene el valor de la propiedad json.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getJSON() {
        return json;
    }

    /**
     * Define el valor de la propiedad json.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setJSON(JAXBElement<String> value) {
        this.json = value;
    }

    /**
     * Gets the value of the alternatives property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alternatives property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlternatives().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClassifiedConcept }
     * 
     * 
     */
    public List<ClassifiedConcept> getAlternatives() {
        if (alternatives == null) {
            alternatives = new ArrayList<ClassifiedConcept>();
        }
        return this.alternatives;
    }

    /**
     * Obtiene el valor de la propiedad classCode.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getClassCode() {
        return classCode;
    }

    /**
     * Define el valor de la propiedad classCode.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setClassCode(JAXBElement<String> value) {
        this.classCode = value;
    }

    /**
     * Obtiene el valor de la propiedad code.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ClassifiedConcept }{@code >}
     *     
     */
    public JAXBElement<ClassifiedConcept> getCode() {
        return code;
    }

    /**
     * Define el valor de la propiedad code.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ClassifiedConcept }{@code >}
     *     
     */
    public void setCode(JAXBElement<ClassifiedConcept> value) {
        this.code = value;
    }

    /**
     * Obtiene el valor de la propiedad codeOrig.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodeOrig() {
        return codeOrig;
    }

    /**
     * Define el valor de la propiedad codeOrig.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodeOrig(JAXBElement<String> value) {
        this.codeOrig = value;
    }

    /**
     * Gets the value of the entities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntityRelationship }
     * 
     * 
     */
    public List<EntityRelationship> getEntities() {
        if (entities == null) {
            entities = new ArrayList<EntityRelationship>();
        }
        return this.entities;
    }

    /**
     * Gets the value of the relationshipPair property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relationshipPair property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelationshipPair().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ClassifiedRelationship }
     * 
     * 
     */
    public List<ClassifiedRelationship> getRelationshipPair() {
        if (relationshipPair == null) {
            relationshipPair = new ArrayList<ClassifiedRelationship>();
        }
        return this.relationshipPair;
    }

    /**
     * Obtiene el valor de la propiedad text.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getText() {
        return text;
    }

    /**
     * Define el valor de la propiedad text.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setText(JAXBElement<String> value) {
        this.text = value;
    }

}
