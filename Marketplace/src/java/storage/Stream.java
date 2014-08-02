package storage;

public class Stream {

    	private String streamUUID;
        
	private String serviceUUID;
	
	private String device;
	
	private String dataDescription;
	
        private String dataCostURL;
        
	private String dataContract;

        private String dataType;
	
	private String dataCategory;
	
	private String databus;
	
	private String timeSeries;
	
	private String dataOrigin;
	
	private String dataRate;
	
	private String latency;
	
        private String uncertainly;
        
	public void setStreamUUID(String streamUUID){
		this.streamUUID = streamUUID;
	}
	
	public String getStreamUUID(){
		return this.streamUUID;
	}
	
	public void setServiceUUID(String serviceUUID){
		this.serviceUUID = serviceUUID;
	}
	
	public String getServiceUUID(){
		return this.serviceUUID;
	}
	
	public void setDevice(String device){
		this.device = device;
	}
	
	public String getDevice(){
		return this.device;
	}
	
	public void setDataDescription(String dataDescription){
		this.dataDescription = dataDescription;
	}
	
	public String getDataDescription(){
		return this.dataDescription;
	}
	
	public void setDataContract(String dataContract){						
		this.dataContract = dataContract;		
	}
	
	public String getDataContract(){
		return this.dataContract;
	}
	
	public void setDataType(String dataType){
		this.dataType = dataType;
	}
	
	public String getDataType(){
		return this.dataType;
	}
	
	public void setDataCategory(String dataCategory){						
		this.dataCategory = dataCategory;		
	}
	
	public String getDataCategory(){
		return this.dataCategory;
	}
	
	public void setDatabus(String databus){
		this.databus = databus;
	}
	
	public String getDatabus(){
		return this.databus;
	}
	
	public void setTimeSeries(String timeSeries){
		this.timeSeries = timeSeries;
	}
	
	public String getTimeSeries(){
		return this.timeSeries;
	}
	
	public void setDataOrigin(String dataOrigin){
		this.dataOrigin = dataOrigin;
	}
	
	public String getDataOrigin(){
		return this.dataOrigin;
	}
	
	public void setDataRate(String dataRate){
		this.dataRate = dataRate;
	}
	
	public String getDataRate(){
		return this.dataRate;
	}
	
	public void setLatency(String latency){
		this.latency = latency;
	}
	
	public String getLatency(){
		return this.latency;
	}
        
        public String getUncertainly() {
         return uncertainly;
        }

        public void setUncertainly(String uncertainly) {
            this.uncertainly = uncertainly;
        }

        public String getDataCostURL() {
            return dataCostURL;
        }

        public void setDataCostURL(String dataCostURL) {
            this.dataCostURL = dataCostURL;
        }
}
