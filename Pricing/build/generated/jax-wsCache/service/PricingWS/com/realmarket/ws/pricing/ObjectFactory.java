
package com.realmarket.ws.pricing;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.realmarket.ws.pricing package. 
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

    private final static QName _QueryAllVersion_QNAME = new QName("http://realmarket.com/ws/pricing", "QueryAllVersion");
    private final static QName _Update_QNAME = new QName("http://realmarket.com/ws/pricing", "Update");
    private final static QName _QueryAllVersionResponse_QNAME = new QName("http://realmarket.com/ws/pricing", "QueryAllVersionResponse");
    private final static QName _QueryByVersion_QNAME = new QName("http://realmarket.com/ws/pricing", "QueryByVersion");
    private final static QName _QueryByVersionResponse_QNAME = new QName("http://realmarket.com/ws/pricing", "QueryByVersionResponse");
    private final static QName _Create_QNAME = new QName("http://realmarket.com/ws/pricing", "Create");
    private final static QName _CreateResponse_QNAME = new QName("http://realmarket.com/ws/pricing", "CreateResponse");
    private final static QName _UpdateResponse_QNAME = new QName("http://realmarket.com/ws/pricing", "UpdateResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.realmarket.ws.pricing
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateResponse }
     * 
     */
    public UpdateResponse createUpdateResponse() {
        return new UpdateResponse();
    }

    /**
     * Create an instance of {@link Create }
     * 
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link CreateResponse }
     * 
     */
    public CreateResponse createCreateResponse() {
        return new CreateResponse();
    }

    /**
     * Create an instance of {@link QueryByVersionResponse }
     * 
     */
    public QueryByVersionResponse createQueryByVersionResponse() {
        return new QueryByVersionResponse();
    }

    /**
     * Create an instance of {@link QueryAllVersionResponse }
     * 
     */
    public QueryAllVersionResponse createQueryAllVersionResponse() {
        return new QueryAllVersionResponse();
    }

    /**
     * Create an instance of {@link QueryByVersion }
     * 
     */
    public QueryByVersion createQueryByVersion() {
        return new QueryByVersion();
    }

    /**
     * Create an instance of {@link Update }
     * 
     */
    public Update createUpdate() {
        return new Update();
    }

    /**
     * Create an instance of {@link QueryAllVersion }
     * 
     */
    public QueryAllVersion createQueryAllVersion() {
        return new QueryAllVersion();
    }

    /**
     * Create an instance of {@link ApiHande }
     * 
     */
    public ApiHande createApiHande() {
        return new ApiHande();
    }

    /**
     * Create an instance of {@link Period }
     * 
     */
    public Period createPeriod() {
        return new Period();
    }

    /**
     * Create an instance of {@link DataSize }
     * 
     */
    public DataSize createDataSize() {
        return new DataSize();
    }

    /**
     * Create an instance of {@link Subscription }
     * 
     */
    public Subscription createSubscription() {
        return new Subscription();
    }

    /**
     * Create an instance of {@link DataUnit }
     * 
     */
    public DataUnit createDataUnit() {
        return new DataUnit();
    }

    /**
     * Create an instance of {@link Stream }
     * 
     */
    public Stream createStream() {
        return new Stream();
    }

    /**
     * Create an instance of {@link QueryResponse }
     * 
     */
    public QueryResponse createQueryResponse() {
        return new QueryResponse();
    }

    /**
     * Create an instance of {@link TimePlan }
     * 
     */
    public TimePlan createTimePlan() {
        return new TimePlan();
    }

    /**
     * Create an instance of {@link CostbaseModel }
     * 
     */
    public CostbaseModel createCostbaseModel() {
        return new CostbaseModel();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryAllVersion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://realmarket.com/ws/pricing", name = "QueryAllVersion")
    public JAXBElement<QueryAllVersion> createQueryAllVersion(QueryAllVersion value) {
        return new JAXBElement<QueryAllVersion>(_QueryAllVersion_QNAME, QueryAllVersion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Update }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://realmarket.com/ws/pricing", name = "Update")
    public JAXBElement<Update> createUpdate(Update value) {
        return new JAXBElement<Update>(_Update_QNAME, Update.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryAllVersionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://realmarket.com/ws/pricing", name = "QueryAllVersionResponse")
    public JAXBElement<QueryAllVersionResponse> createQueryAllVersionResponse(QueryAllVersionResponse value) {
        return new JAXBElement<QueryAllVersionResponse>(_QueryAllVersionResponse_QNAME, QueryAllVersionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryByVersion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://realmarket.com/ws/pricing", name = "QueryByVersion")
    public JAXBElement<QueryByVersion> createQueryByVersion(QueryByVersion value) {
        return new JAXBElement<QueryByVersion>(_QueryByVersion_QNAME, QueryByVersion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryByVersionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://realmarket.com/ws/pricing", name = "QueryByVersionResponse")
    public JAXBElement<QueryByVersionResponse> createQueryByVersionResponse(QueryByVersionResponse value) {
        return new JAXBElement<QueryByVersionResponse>(_QueryByVersionResponse_QNAME, QueryByVersionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Create }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://realmarket.com/ws/pricing", name = "Create")
    public JAXBElement<Create> createCreate(Create value) {
        return new JAXBElement<Create>(_Create_QNAME, Create.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://realmarket.com/ws/pricing", name = "CreateResponse")
    public JAXBElement<CreateResponse> createCreateResponse(CreateResponse value) {
        return new JAXBElement<CreateResponse>(_CreateResponse_QNAME, CreateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://realmarket.com/ws/pricing", name = "UpdateResponse")
    public JAXBElement<UpdateResponse> createUpdateResponse(UpdateResponse value) {
        return new JAXBElement<UpdateResponse>(_UpdateResponse_QNAME, UpdateResponse.class, null, value);
    }

}
