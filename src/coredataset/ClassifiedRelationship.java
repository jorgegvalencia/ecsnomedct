
package coredataset;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para classifiedRelationship complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="classifiedRelationship">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codeOrig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="concept" type="{http://CoreDataset/xsd}classifiedConcept" minOccurs="0"/>
 *         &lt;element name="relationship" type="{http://CoreDataset/xsd}Concept" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "classifiedRelationship", namespace = "http://CoreDataset/xsd", propOrder = {
    "codeOrig",
    "concept",
    "relationship"
})
public class ClassifiedRelationship {

    @XmlElementRef(name = "codeOrig", namespace = "http://CoreDataset/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codeOrig;
    @XmlElementRef(name = "concept", namespace = "http://CoreDataset/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<ClassifiedConcept> concept;
    @XmlElementRef(name = "relationship", namespace = "http://CoreDataset/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Concept> relationship;

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
     * Obtiene el valor de la propiedad concept.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ClassifiedConcept }{@code >}
     *     
     */
    public JAXBElement<ClassifiedConcept> getConcept() {
        return concept;
    }

    /**
     * Define el valor de la propiedad concept.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ClassifiedConcept }{@code >}
     *     
     */
    public void setConcept(JAXBElement<ClassifiedConcept> value) {
        this.concept = value;
    }

    /**
     * Obtiene el valor de la propiedad relationship.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Concept }{@code >}
     *     
     */
    public JAXBElement<Concept> getRelationship() {
        return relationship;
    }

    /**
     * Define el valor de la propiedad relationship.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Concept }{@code >}
     *     
     */
    public void setRelationship(JAXBElement<Concept> value) {
        this.relationship = value;
    }

}
