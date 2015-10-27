
package norm;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para NormalizedExpression complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="NormalizedExpression">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="focusConcept" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="focusConceptTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relationships" type="{http://NormalForm/xsd}SnomedRelationship" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NormalizedExpression", namespace = "http://NormalForm/xsd", propOrder = {
    "focusConcept",
    "focusConceptTitle",
    "relationships"
})
public class NormalizedExpression {

    @XmlElementRef(name = "focusConcept", namespace = "http://NormalForm/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> focusConcept;
    @XmlElementRef(name = "focusConceptTitle", namespace = "http://NormalForm/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> focusConceptTitle;
    @XmlElement(nillable = true)
    protected List<SnomedRelationship> relationships;

    /**
     * Obtiene el valor de la propiedad focusConcept.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFocusConcept() {
        return focusConcept;
    }

    /**
     * Define el valor de la propiedad focusConcept.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFocusConcept(JAXBElement<String> value) {
        this.focusConcept = value;
    }

    /**
     * Obtiene el valor de la propiedad focusConceptTitle.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFocusConceptTitle() {
        return focusConceptTitle;
    }

    /**
     * Define el valor de la propiedad focusConceptTitle.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFocusConceptTitle(JAXBElement<String> value) {
        this.focusConceptTitle = value;
    }

    /**
     * Gets the value of the relationships property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the relationships property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRelationships().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SnomedRelationship }
     * 
     * 
     */
    public List<SnomedRelationship> getRelationships() {
        if (relationships == null) {
            relationships = new ArrayList<SnomedRelationship>();
        }
        return this.relationships;
    }

}
