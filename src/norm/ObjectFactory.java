
package norm;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the norm package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetTillGranparentsConcept_QNAME = new QName("http://CoreDataset", "concept");
    private final static QName _GetShortNormalFormResponseReturn_QNAME = new QName("http://CoreDataset", "return");
    private final static QName _ClassifiedConceptCode_QNAME = new QName("http://CoreDataset/xsd", "code");
    private final static QName _ClassifiedConceptTable_QNAME = new QName("http://CoreDataset/xsd", "table");
    private final static QName _ClassifiedConceptAttribute_QNAME = new QName("http://CoreDataset/xsd", "attribute");
    private final static QName _ClassifiedConceptLabel_QNAME = new QName("http://CoreDataset/xsd", "label");
    private final static QName _SnomedRelationshipRelationshipTitle_QNAME = new QName("http://NormalForm/xsd", "relationshipTitle");
    private final static QName _SnomedRelationshipRelationshipValue_QNAME = new QName("http://NormalForm/xsd", "relationshipValue");
    private final static QName _SnomedRelationshipRelationshipValueTitle_QNAME = new QName("http://NormalForm/xsd", "relationshipValueTitle");
    private final static QName _SnomedRelationshipRelationship_QNAME = new QName("http://NormalForm/xsd", "relationship");
    private final static QName _EntityRelationshipRoleClassCode_QNAME = new QName("http://CoreDataset/xsd", "roleClassCode");
    private final static QName _EntityRelationshipEntityClassCode_QNAME = new QName("http://CoreDataset/xsd", "entityClassCode");
    private final static QName _EntityRelationshipConcept_QNAME = new QName("http://CoreDataset/xsd", "concept");
    private final static QName _EntityRelationshipRoleCode_QNAME = new QName("http://CoreDataset/xsd", "roleCode");
    private final static QName _EntityRelationshipRelationship_QNAME = new QName("http://CoreDataset/xsd", "relationship");
    private final static QName _EntityRelationshipCodeOrig_QNAME = new QName("http://CoreDataset/xsd", "codeOrig");
    private final static QName _EntityRelationshipParticipationTypeCode_QNAME = new QName("http://CoreDataset/xsd", "participationTypeCode");
    private final static QName _PublicExecuteQueryBinding_QNAME = new QName("http://CoreDataset", "binding");
    private final static QName _KinshipConceptsParent_QNAME = new QName("http://CoreDataset/xsd", "parent");
    private final static QName _GetRootConceptTerm_QNAME = new QName("http://CoreDataset", "term");
    private final static QName _NormalizedExpressionFocusConcept_QNAME = new QName("http://NormalForm/xsd", "focusConcept");
    private final static QName _NormalizedExpressionFocusConceptTitle_QNAME = new QName("http://NormalForm/xsd", "focusConceptTitle");
    private final static QName _NormalizedConceptClassCode_QNAME = new QName("http://CoreDataset/xsd", "classCode");
    private final static QName _NormalizedConceptJSON_QNAME = new QName("http://CoreDataset/xsd", "JSON");
    private final static QName _NormalizedConceptText_QNAME = new QName("http://CoreDataset/xsd", "text");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: norm
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Concept }
     * 
     */
    public Concept createConcept() {
        return new Concept();
    }

    /**
     * Create an instance of {@link ClassifiedRelationship }
     * 
     */
    public ClassifiedRelationship createClassifiedRelationship() {
        return new ClassifiedRelationship();
    }

    /**
     * Create an instance of {@link ClassifiedConcept }
     * 
     */
    public ClassifiedConcept createClassifiedConcept() {
        return new ClassifiedConcept();
    }

    /**
     * Create an instance of {@link NormalizedConcept }
     * 
     */
    public NormalizedConcept createNormalizedConcept() {
        return new NormalizedConcept();
    }

    /**
     * Create an instance of {@link EntityRelationship }
     * 
     */
    public EntityRelationship createEntityRelationship() {
        return new EntityRelationship();
    }

    /**
     * Create an instance of {@link KinshipConcepts }
     * 
     */
    public KinshipConcepts createKinshipConcepts() {
        return new KinshipConcepts();
    }

    /**
     * Create an instance of {@link GetParentsResponse }
     * 
     */
    public GetParentsResponse createGetParentsResponse() {
        return new GetParentsResponse();
    }

    /**
     * Create an instance of {@link CD2CDMNEW }
     * 
     */
    public CD2CDMNEW createCD2CDMNEW() {
        return new CD2CDMNEW();
    }

    /**
     * Create an instance of {@link CDM2CDResponse }
     * 
     */
    public CDM2CDResponse createCDM2CDResponse() {
        return new CDM2CDResponse();
    }

    /**
     * Create an instance of {@link GetNormalFormVersion }
     * 
     */
    public GetNormalFormVersion createGetNormalFormVersion() {
        return new GetNormalFormVersion();
    }

    /**
     * Create an instance of {@link GetNextGen }
     * 
     */
    public GetNextGen createGetNextGen() {
        return new GetNextGen();
    }

    /**
     * Create an instance of {@link GetTillGranparents }
     * 
     */
    public GetTillGranparents createGetTillGranparents() {
        return new GetTillGranparents();
    }

    /**
     * Create an instance of {@link PublicExecuteQueryResponse }
     * 
     */
    public PublicExecuteQueryResponse createPublicExecuteQueryResponse() {
        return new PublicExecuteQueryResponse();
    }

    /**
     * Create an instance of {@link GetRootConceptResponse }
     * 
     */
    public GetRootConceptResponse createGetRootConceptResponse() {
        return new GetRootConceptResponse();
    }

    /**
     * Create an instance of {@link GetOWLVersion }
     * 
     */
    public GetOWLVersion createGetOWLVersion() {
        return new GetOWLVersion();
    }

    /**
     * Create an instance of {@link GetParents }
     * 
     */
    public GetParents createGetParents() {
        return new GetParents();
    }

    /**
     * Create an instance of {@link CD2CDM }
     * 
     */
    public CD2CDM createCD2CDM() {
        return new CD2CDM();
    }

    /**
     * Create an instance of {@link GetTillGranparentsResponse }
     * 
     */
    public GetTillGranparentsResponse createGetTillGranparentsResponse() {
        return new GetTillGranparentsResponse();
    }

    /**
     * Create an instance of {@link GetRootConcept }
     * 
     */
    public GetRootConcept createGetRootConcept() {
        return new GetRootConcept();
    }

    /**
     * Create an instance of {@link GetServiceVersion }
     * 
     */
    public GetServiceVersion createGetServiceVersion() {
        return new GetServiceVersion();
    }

    /**
     * Create an instance of {@link CD2CDMResponse }
     * 
     */
    public CD2CDMResponse createCD2CDMResponse() {
        return new CD2CDMResponse();
    }

    /**
     * Create an instance of {@link PublicExecuteQuery }
     * 
     */
    public PublicExecuteQuery createPublicExecuteQuery() {
        return new PublicExecuteQuery();
    }

    /**
     * Create an instance of {@link Value }
     * 
     */
    public Value createValue() {
        return new Value();
    }

    /**
     * Create an instance of {@link GetTermBindingVersion }
     * 
     */
    public GetTermBindingVersion createGetTermBindingVersion() {
        return new GetTermBindingVersion();
    }

    /**
     * Create an instance of {@link GetServiceVersionResponse }
     * 
     */
    public GetServiceVersionResponse createGetServiceVersionResponse() {
        return new GetServiceVersionResponse();
    }

    /**
     * Create an instance of {@link CD2CDMNEWResponse }
     * 
     */
    public CD2CDMNEWResponse createCD2CDMNEWResponse() {
        return new CD2CDMNEWResponse();
    }

    /**
     * Create an instance of {@link GetTermBindingVersionResponse }
     * 
     */
    public GetTermBindingVersionResponse createGetTermBindingVersionResponse() {
        return new GetTermBindingVersionResponse();
    }

    /**
     * Create an instance of {@link ExpandQuery }
     * 
     */
    public ExpandQuery createExpandQuery() {
        return new ExpandQuery();
    }

    /**
     * Create an instance of {@link GetOWLVersionResponse }
     * 
     */
    public GetOWLVersionResponse createGetOWLVersionResponse() {
        return new GetOWLVersionResponse();
    }

    /**
     * Create an instance of {@link GetNormalFormVersionResponse }
     * 
     */
    public GetNormalFormVersionResponse createGetNormalFormVersionResponse() {
        return new GetNormalFormVersionResponse();
    }

    /**
     * Create an instance of {@link CDM2CD }
     * 
     */
    public CDM2CD createCDM2CD() {
        return new CDM2CD();
    }

    /**
     * Create an instance of {@link ExpandQueryResponse }
     * 
     */
    public ExpandQueryResponse createExpandQueryResponse() {
        return new ExpandQueryResponse();
    }

    /**
     * Create an instance of {@link GetShortNormalFormResponse }
     * 
     */
    public GetShortNormalFormResponse createGetShortNormalFormResponse() {
        return new GetShortNormalFormResponse();
    }

    /**
     * Create an instance of {@link NormalizedExpression }
     * 
     */
    public NormalizedExpression createNormalizedExpression() {
        return new NormalizedExpression();
    }

    /**
     * Create an instance of {@link GetNextGenResponse }
     * 
     */
    public GetNextGenResponse createGetNextGenResponse() {
        return new GetNextGenResponse();
    }

    /**
     * Create an instance of {@link GetShortNormalForm }
     * 
     */
    public GetShortNormalForm createGetShortNormalForm() {
        return new GetShortNormalForm();
    }

    /**
     * Create an instance of {@link SnomedRelationship }
     * 
     */
    public SnomedRelationship createSnomedRelationship() {
        return new SnomedRelationship();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "concept", scope = GetTillGranparents.class)
    public JAXBElement<String> createGetTillGranparentsConcept(String value) {
        return new JAXBElement<String>(_GetTillGranparentsConcept_QNAME, String.class, GetTillGranparents.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NormalizedExpression }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "return", scope = GetShortNormalFormResponse.class)
    public JAXBElement<NormalizedExpression> createGetShortNormalFormResponseReturn(NormalizedExpression value) {
        return new JAXBElement<NormalizedExpression>(_GetShortNormalFormResponseReturn_QNAME, NormalizedExpression.class, GetShortNormalFormResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "concept", scope = GetParents.class)
    public JAXBElement<String> createGetParentsConcept(String value) {
        return new JAXBElement<String>(_GetTillGranparentsConcept_QNAME, String.class, GetParents.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "concept", scope = ExpandQuery.class)
    public JAXBElement<String> createExpandQueryConcept(String value) {
        return new JAXBElement<String>(_GetTillGranparentsConcept_QNAME, String.class, ExpandQuery.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "code", scope = ClassifiedConcept.class)
    public JAXBElement<String> createClassifiedConceptCode(String value) {
        return new JAXBElement<String>(_ClassifiedConceptCode_QNAME, String.class, ClassifiedConcept.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "table", scope = ClassifiedConcept.class)
    public JAXBElement<String> createClassifiedConceptTable(String value) {
        return new JAXBElement<String>(_ClassifiedConceptTable_QNAME, String.class, ClassifiedConcept.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "attribute", scope = ClassifiedConcept.class)
    public JAXBElement<String> createClassifiedConceptAttribute(String value) {
        return new JAXBElement<String>(_ClassifiedConceptAttribute_QNAME, String.class, ClassifiedConcept.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "label", scope = ClassifiedConcept.class)
    public JAXBElement<String> createClassifiedConceptLabel(String value) {
        return new JAXBElement<String>(_ClassifiedConceptLabel_QNAME, String.class, ClassifiedConcept.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://NormalForm/xsd", name = "relationshipTitle", scope = SnomedRelationship.class)
    public JAXBElement<String> createSnomedRelationshipRelationshipTitle(String value) {
        return new JAXBElement<String>(_SnomedRelationshipRelationshipTitle_QNAME, String.class, SnomedRelationship.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NormalizedExpression }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://NormalForm/xsd", name = "relationshipValue", scope = SnomedRelationship.class)
    public JAXBElement<NormalizedExpression> createSnomedRelationshipRelationshipValue(NormalizedExpression value) {
        return new JAXBElement<NormalizedExpression>(_SnomedRelationshipRelationshipValue_QNAME, NormalizedExpression.class, SnomedRelationship.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://NormalForm/xsd", name = "relationshipValueTitle", scope = SnomedRelationship.class)
    public JAXBElement<String> createSnomedRelationshipRelationshipValueTitle(String value) {
        return new JAXBElement<String>(_SnomedRelationshipRelationshipValueTitle_QNAME, String.class, SnomedRelationship.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://NormalForm/xsd", name = "relationship", scope = SnomedRelationship.class)
    public JAXBElement<String> createSnomedRelationshipRelationship(String value) {
        return new JAXBElement<String>(_SnomedRelationshipRelationship_QNAME, String.class, SnomedRelationship.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "roleClassCode", scope = EntityRelationship.class)
    public JAXBElement<String> createEntityRelationshipRoleClassCode(String value) {
        return new JAXBElement<String>(_EntityRelationshipRoleClassCode_QNAME, String.class, EntityRelationship.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "entityClassCode", scope = EntityRelationship.class)
    public JAXBElement<String> createEntityRelationshipEntityClassCode(String value) {
        return new JAXBElement<String>(_EntityRelationshipEntityClassCode_QNAME, String.class, EntityRelationship.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Concept }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "concept", scope = EntityRelationship.class)
    public JAXBElement<Concept> createEntityRelationshipConcept(Concept value) {
        return new JAXBElement<Concept>(_EntityRelationshipConcept_QNAME, Concept.class, EntityRelationship.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "roleCode", scope = EntityRelationship.class)
    public JAXBElement<String> createEntityRelationshipRoleCode(String value) {
        return new JAXBElement<String>(_EntityRelationshipRoleCode_QNAME, String.class, EntityRelationship.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Concept }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "relationship", scope = EntityRelationship.class)
    public JAXBElement<Concept> createEntityRelationshipRelationship(Concept value) {
        return new JAXBElement<Concept>(_EntityRelationshipRelationship_QNAME, Concept.class, EntityRelationship.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "codeOrig", scope = EntityRelationship.class)
    public JAXBElement<String> createEntityRelationshipCodeOrig(String value) {
        return new JAXBElement<String>(_EntityRelationshipCodeOrig_QNAME, String.class, EntityRelationship.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "participationTypeCode", scope = EntityRelationship.class)
    public JAXBElement<String> createEntityRelationshipParticipationTypeCode(String value) {
        return new JAXBElement<String>(_EntityRelationshipParticipationTypeCode_QNAME, String.class, EntityRelationship.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Value }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "binding", scope = PublicExecuteQuery.class)
    public JAXBElement<Value> createPublicExecuteQueryBinding(Value value) {
        return new JAXBElement<Value>(_PublicExecuteQueryBinding_QNAME, Value.class, PublicExecuteQuery.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "concept", scope = PublicExecuteQuery.class)
    public JAXBElement<String> createPublicExecuteQueryConcept(String value) {
        return new JAXBElement<String>(_GetTillGranparentsConcept_QNAME, String.class, PublicExecuteQuery.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "return", scope = GetServiceVersionResponse.class)
    public JAXBElement<String> createGetServiceVersionResponseReturn(String value) {
        return new JAXBElement<String>(_GetShortNormalFormResponseReturn_QNAME, String.class, GetServiceVersionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "parent", scope = KinshipConcepts.class)
    public JAXBElement<String> createKinshipConceptsParent(String value) {
        return new JAXBElement<String>(_KinshipConceptsParent_QNAME, String.class, KinshipConcepts.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "return", scope = GetRootConceptResponse.class)
    public JAXBElement<String> createGetRootConceptResponseReturn(String value) {
        return new JAXBElement<String>(_GetShortNormalFormResponseReturn_QNAME, String.class, GetRootConceptResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "return", scope = GetTermBindingVersionResponse.class)
    public JAXBElement<String> createGetTermBindingVersionResponseReturn(String value) {
        return new JAXBElement<String>(_GetShortNormalFormResponseReturn_QNAME, String.class, GetTermBindingVersionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "term", scope = GetRootConcept.class)
    public JAXBElement<String> createGetRootConceptTerm(String value) {
        return new JAXBElement<String>(_GetRootConceptTerm_QNAME, String.class, GetRootConcept.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "term", scope = GetShortNormalForm.class)
    public JAXBElement<String> createGetShortNormalFormTerm(String value) {
        return new JAXBElement<String>(_GetRootConceptTerm_QNAME, String.class, GetShortNormalForm.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "term", scope = CD2CDM.class)
    public JAXBElement<String> createCD2CDMTerm(String value) {
        return new JAXBElement<String>(_GetRootConceptTerm_QNAME, String.class, CD2CDM.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://NormalForm/xsd", name = "focusConcept", scope = NormalizedExpression.class)
    public JAXBElement<String> createNormalizedExpressionFocusConcept(String value) {
        return new JAXBElement<String>(_NormalizedExpressionFocusConcept_QNAME, String.class, NormalizedExpression.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://NormalForm/xsd", name = "focusConceptTitle", scope = NormalizedExpression.class)
    public JAXBElement<String> createNormalizedExpressionFocusConceptTitle(String value) {
        return new JAXBElement<String>(_NormalizedExpressionFocusConceptTitle_QNAME, String.class, NormalizedExpression.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NormalizedConcept }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "return", scope = CD2CDMNEWResponse.class)
    public JAXBElement<NormalizedConcept> createCD2CDMNEWResponseReturn(NormalizedConcept value) {
        return new JAXBElement<NormalizedConcept>(_GetShortNormalFormResponseReturn_QNAME, NormalizedConcept.class, CD2CDMNEWResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "term", scope = CDM2CD.class)
    public JAXBElement<String> createCDM2CDTerm(String value) {
        return new JAXBElement<String>(_GetRootConceptTerm_QNAME, String.class, CDM2CD.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "return", scope = GetOWLVersionResponse.class)
    public JAXBElement<String> createGetOWLVersionResponseReturn(String value) {
        return new JAXBElement<String>(_GetShortNormalFormResponseReturn_QNAME, String.class, GetOWLVersionResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "classCode", scope = NormalizedConcept.class)
    public JAXBElement<String> createNormalizedConceptClassCode(String value) {
        return new JAXBElement<String>(_NormalizedConceptClassCode_QNAME, String.class, NormalizedConcept.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassifiedConcept }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "code", scope = NormalizedConcept.class)
    public JAXBElement<ClassifiedConcept> createNormalizedConceptCode(ClassifiedConcept value) {
        return new JAXBElement<ClassifiedConcept>(_ClassifiedConceptCode_QNAME, ClassifiedConcept.class, NormalizedConcept.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "codeOrig", scope = NormalizedConcept.class)
    public JAXBElement<String> createNormalizedConceptCodeOrig(String value) {
        return new JAXBElement<String>(_EntityRelationshipCodeOrig_QNAME, String.class, NormalizedConcept.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "JSON", scope = NormalizedConcept.class)
    public JAXBElement<String> createNormalizedConceptJSON(String value) {
        return new JAXBElement<String>(_NormalizedConceptJSON_QNAME, String.class, NormalizedConcept.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "text", scope = NormalizedConcept.class)
    public JAXBElement<String> createNormalizedConceptText(String value) {
        return new JAXBElement<String>(_NormalizedConceptText_QNAME, String.class, NormalizedConcept.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "code", scope = Concept.class)
    public JAXBElement<String> createConceptCode(String value) {
        return new JAXBElement<String>(_ClassifiedConceptCode_QNAME, String.class, Concept.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "label", scope = Concept.class)
    public JAXBElement<String> createConceptLabel(String value) {
        return new JAXBElement<String>(_ClassifiedConceptLabel_QNAME, String.class, Concept.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClassifiedConcept }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "concept", scope = ClassifiedRelationship.class)
    public JAXBElement<ClassifiedConcept> createClassifiedRelationshipConcept(ClassifiedConcept value) {
        return new JAXBElement<ClassifiedConcept>(_EntityRelationshipConcept_QNAME, ClassifiedConcept.class, ClassifiedRelationship.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Concept }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "relationship", scope = ClassifiedRelationship.class)
    public JAXBElement<Concept> createClassifiedRelationshipRelationship(Concept value) {
        return new JAXBElement<Concept>(_EntityRelationshipRelationship_QNAME, Concept.class, ClassifiedRelationship.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset/xsd", name = "codeOrig", scope = ClassifiedRelationship.class)
    public JAXBElement<String> createClassifiedRelationshipCodeOrig(String value) {
        return new JAXBElement<String>(_EntityRelationshipCodeOrig_QNAME, String.class, ClassifiedRelationship.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "term", scope = CD2CDMNEW.class)
    public JAXBElement<String> createCD2CDMNEWTerm(String value) {
        return new JAXBElement<String>(_GetRootConceptTerm_QNAME, String.class, CD2CDMNEW.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://CoreDataset", name = "return", scope = GetNormalFormVersionResponse.class)
    public JAXBElement<String> createGetNormalFormVersionResponseReturn(String value) {
        return new JAXBElement<String>(_GetShortNormalFormResponseReturn_QNAME, String.class, GetNormalFormVersionResponse.class, value);
    }

}
