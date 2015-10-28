
package coredataset;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="concept" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="binding" type="{http://model.openrdf.org/xsd}Value" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "concept",
    "binding"
})
@XmlRootElement(name = "publicExecuteQuery")
public class PublicExecuteQuery {

    @XmlElementRef(name = "concept", namespace = "http://CoreDataset", type = JAXBElement.class, required = false)
    protected JAXBElement<String> concept;
    @XmlElementRef(name = "binding", namespace = "http://CoreDataset", type = JAXBElement.class, required = false)
    protected JAXBElement<Value> binding;

    /**
     * Obtiene el valor de la propiedad concept.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getConcept() {
        return concept;
    }

    /**
     * Define el valor de la propiedad concept.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setConcept(JAXBElement<String> value) {
        this.concept = value;
    }

    /**
     * Obtiene el valor de la propiedad binding.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Value }{@code >}
     *     
     */
    public JAXBElement<Value> getBinding() {
        return binding;
    }

    /**
     * Define el valor de la propiedad binding.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Value }{@code >}
     *     
     */
    public void setBinding(JAXBElement<Value> value) {
        this.binding = value;
    }

}
