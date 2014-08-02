/**
 * 
 */
package api.market.broker;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author ttu01
 *
 */
public class Duration {
	private Date begin;
	private Date end;
	/**
	 * @return the begin
	 */
        @XmlElement(required = true)
	public Date getBegin() {
		return begin;
	}
	/**
	 * @param begin the begin to set
	 */
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	/**
	 * @return the end
	 */
        @XmlElement(required = true)
	public Date getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(Date end) {
		this.end = end;
	}
}
