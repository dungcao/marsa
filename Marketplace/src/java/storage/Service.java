package storage;

import java.util.ArrayList;
import java.util.List;

public class Service {

	private String serviceUUID;
	
	private String serviceName;
	
	private String provider;
        
        private String serviceDescription;
        
        private String costURL;
	
	private List<String> serviceContractList;
	
	private List<String> serviceCategoryList;
	
	private List<String> qosList;
	
        private String databus;
	
        private String serviceOwner;

    
	public Service(){
		this.serviceContractList = new ArrayList<String>();
		this.serviceCategoryList = new ArrayList<String>();
		this.qosList = new ArrayList<String>();
	}
	
	public void setServiceUUID(String serviceUUID){
		this.serviceUUID = serviceUUID;
	}
	
	public String getServiceUUID(){
		return this.serviceUUID;
	}
	
	public void setServiceName(String serviceName){
		this.serviceName = serviceName;
	}
	
	public String getServiceName(){
		return this.serviceName;
	}
	
	public void setProvider(String provider){
		this.provider = provider;
	}
	
	public String getProvider(){
		return this.provider;
	}

        public void setServiceDescription(String serviceDescription){
		this.serviceDescription = serviceDescription;
	}
	
	public String getServiceDescription(){
		return this.serviceDescription;
	}
	
	public void addServiceCategory(String serviceCategory){		
		this.serviceCategoryList.add(serviceCategory);		
	}

	public List<String> getServiceCategoryList(){
		return this.serviceCategoryList;
	}	
	
	public void addServiceContract(String serviceContract){
		this.serviceContractList.add(serviceContract);
	}
	
	public List<String> getServiceContractList(){
		return this.serviceContractList;
	}
	
	public void addQoS(String qos){
		this.qosList.add(qos);
	}
	
	public List<String> getQoSList(){
		return this.qosList;
	}
	
	public void setDatabus(String databus){
		this.databus = databus;
	}
	
	public String getDatabus(){
		return this.databus;
	}
        public List<String> getQosList() {
            return qosList;
        }

        public void setQosList(List<String> qosList) {
            this.qosList = qosList;
        }

        public String getServiceOwner() {
            return serviceOwner;
        }

        public void setServiceOwner(String serviceOwner) {
            this.serviceOwner = serviceOwner;
        }

        public String getCostURL() {
            return costURL;
        }

        public void setCostURL(String costURL) {
            this.costURL = costURL;
        }
        
        
}
