
package norm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para SnomedRelationship complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="SnomedRelationship">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="relationship" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relationshipTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relationshipValue" type="{http://NormalForm/xsd}NormalizedExpression" minOccurs="0"/>
 *         &lt;element name="relationshipValueTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SnomedRelationship", namespace = "http://NormalForm/xsd", propOrder = {
    "relationship",
    "relationshipTitle",
    "relationshipValue",
    "relationshipValueTitle"
})
public class SnomedRelationship {

    @XmlElementRef(name = "relationship", namespace = "http://NormalForm/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> relationship;
    @XmlElementRef(name = "relationshipTitle", namespace = "http://NormalForm/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> relationshipTitle;
    @XmlElementRef(name = "relationshipValue", namespace = "http://NormalForm/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<NormalizedExpression> relationshipValue;
    @XmlElementRef(name = "relationshipValueTitle", namespace = "http://NormalForm/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> relationshipValueTitle;

    /**
     * Obtiene el valor de la propiedad relationship.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRelationship() {
        return relationship;
    }

    /**
     * Define el valor de la propiedad relationship.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRelationship(JAXBElement<String> value) {
        this.relationship = value;
    }

    /**
     * Obtiene el valor de la propiedad relationshipTitle.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRelationshipTitle() {
        return relationshipTitle;
    }

    /**
     * Define el valor de la propiedad relationshipTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRelationshipTitle(JAXBElement<String> value) {
        this.relationshipTitle = value;
    }

    /**
     * Obtiene el valor de la propiedad relationshipValue.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link NormalizedExpression }{@code >}
     *     
     */
    public JAXBElement<NormalizedExpression> getRelationshipValue() {
        return relationshipValue;
    }

    /**
     * Define el valor de la propiedad relationshipValue.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link NormalizedExpression }{@code >}
     *     
     */
    public void setRelationshipValue(JAXBElement<NormalizedExpression> value) {
        this.relationshipValue = value;
    }

    /**
     * Obtiene el valor de la propiedad relationshipValueTitle.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRelationshipValueTitle() {
        return relationshipValueTitle;
    }

    /**
     * Define el valor de la propiedad relationshipValueTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRelationshipValueTitle(JAXBElement<String> value) {
        this.relationshipValueTitle = value;
    }

}
