/**
 * 
 */
package api.market.broker;

/**
 * @author ttu01
 *
 */
public class Stream {
	private String uuid;
	private String device;
	private String description;
	private String datatype;
	private String pubsub_url;
	private String timeseries;
	private String dataorigin;
	private String datarate;
	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	/**
	 * @return the device
	 */
	public String getDevice() {
		return device;
	}
	/**
	 * @param device the device to set
	 */
	public void setDevice(String device) {
		this.device = device;
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
	 * @return the datatype
	 */
	public String getDatatype() {
		return datatype;
	}
	/**
	 * @param datatype the datatype to set
	 */
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	/**
	 * @return the pubsub_url
	 */
	public String getPubsub_url() {
		return pubsub_url;
	}
	/**
	 * @param pubsub_url the pubsub_url to set
	 */
	public void setPubsub_url(String pubsub_url) {
		this.pubsub_url = pubsub_url;
	}
	/**
	 * @return the timeseries
	 */
	public String getTimeseries() {
		return timeseries;
	}
	/**
	 * @param timeseries the timeseries to set
	 */
	public void setTimeseries(String timeseries) {
		this.timeseries = timeseries;
	}
	/**
	 * @return the dataorigin
	 */
	public String getDataorigin() {
		return dataorigin;
	}
	/**
	 * @param dataorigin the dataorigin to set
	 */
	public void setDataorigin(String dataorigin) {
		this.dataorigin = dataorigin;
	}
	/**
	 * @return the datarate
	 */
	public String getDatarate() {
		return datarate;
	}
	/**
	 * @param datarate the datarate to set
	 */
	public void setDatarate(String datarate) {
		this.datarate = datarate;
	}
}
