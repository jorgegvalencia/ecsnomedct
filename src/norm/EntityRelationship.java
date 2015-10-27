
package norm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para entityRelationship complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="entityRelationship">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codeOrig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="concept" type="{http://CoreDataset/xsd}Concept" minOccurs="0"/>
 *         &lt;element name="entityClassCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="participationTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relationship" type="{http://CoreDataset/xsd}Concept" minOccurs="0"/>
 *         &lt;element name="roleClassCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "entityRelationship", namespace = "http://CoreDataset/xsd", propOrder = {
    "codeOrig",
    "concept",
    "entityClassCode",
    "participationTypeCode",
    "relationship",
    "roleClassCode",
    "roleCode"
})
public class EntityRelationship {

    @XmlElementRef(name = "codeOrig", namespace = "http://CoreDataset/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codeOrig;
    @XmlElementRef(name = "concept", namespace = "http://CoreDataset/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Concept> concept;
    @XmlElementRef(name = "entityClassCode", namespace = "http://CoreDataset/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> entityClassCode;
    @XmlElementRef(name = "participationTypeCode", namespace = "http://CoreDataset/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> participationTypeCode;
    @XmlElementRef(name = "relationship", namespace = "http://CoreDataset/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<Concept> relationship;
    @XmlElementRef(name = "roleClassCode", namespace = "http://CoreDataset/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roleClassCode;
    @XmlElementRef(name = "roleCode", namespace = "http://CoreDataset/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roleCode;

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
     *     {@link JAXBElement }{@code <}{@link Concept }{@code >}
     *     
     */
    public JAXBElement<Concept> getConcept() {
        return concept;
    }

    /**
     * Define el valor de la propiedad concept.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Concept }{@code >}
     *     
     */
    public void setConcept(JAXBElement<Concept> value) {
        this.concept = value;
    }

    /**
     * Obtiene el valor de la propiedad entityClassCode.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEntityClassCode() {
        return entityClassCode;
    }

    /**
     * Define el valor de la propiedad entityClassCode.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEntityClassCode(JAXBElement<String> value) {
        this.entityClassCode = value;
    }

    /**
     * Obtiene el valor de la propiedad participationTypeCode.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getParticipationTypeCode() {
        return participationTypeCode;
    }

    /**
     * Define el valor de la propiedad participationTypeCode.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setParticipationTypeCode(JAXBElement<String> value) {
        this.participationTypeCode = value;
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

    /**
     * Obtiene el valor de la propiedad roleClassCode.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRoleClassCode() {
        return roleClassCode;
    }

    /**
     * Define el valor de la propiedad roleClassCode.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRoleClassCode(JAXBElement<String> value) {
        this.roleClassCode = value;
    }

    /**
     * Obtiene el valor de la propiedad roleCode.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRoleCode() {
        return roleCode;
    }

    /**
     * Define el valor de la propiedad roleCode.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRoleCode(JAXBElement<String> value) {
        this.roleCode = value;
    }

}
