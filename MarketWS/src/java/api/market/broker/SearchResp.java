/**
 * 
 */
package api.market.broker;

import java.util.List;

/**
 * @author ttu01
 *
 */
public class SearchResp {
	private String serviceUUID;
	private String serviceName;
	private String provider;
	private String description;
	private String categories;
	private String QoS;
	private String pricing_model;
	private List<Stream> streams;
	/**
	 * @return the serviceUUID
	 */
	public String getServiceUUID() {
		return serviceUUID;
	}
	/**
	 * @param serviceUUID the serviceUUID to set
	 */
	public void setServiceUUID(String serviceUUID) {
		this.serviceUUID = serviceUUID;
	}
	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * @return the provider
	 */
	public String getProvider() {
		return provider;
	}
	/**
	 * @param provider the provider to set
	 */
	public void setProvider(String provider) {
		this.provider = provider;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the categories
	 */
	public String getCategories() {
		return categories;
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(String categories) {
		this.categories = categories;
	}
	/**
	 * @return the qoS
	 */
	public String getQoS() {
		return QoS;
	}
	/**
	 * @param qoS the qoS to set
	 */
	public void setQoS(String qoS) {
		QoS = qoS;
	}
	/**
	 * @return the pricing_model
	 */
	public String getPricing_model() {
		return pricing_model;
	}
	/**
	 * @param pricing_model the pricing_model to set
	 */
	public void setPricing_model(String pricing_model) {
		this.pricing_model = pricing_model;
	}
	/**
	 * @return the streams
	 */
	public List<Stream> getStreams() {
		return streams;
	}
	/**
	 * @param streams the streams to set
	 */
	public void setStreams(List<Stream> streams) {
		this.streams = streams;
	}
}
