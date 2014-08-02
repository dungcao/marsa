package storage;

public class Contractmodel {

	private String contractDescription;
	
	private String UUID;
	
        private String sourceURL;
        
	private ContractType contractType; 
	
	public Contractmodel(String contractDescription, ContractType contractType, String UUID){
		this.contractDescription = contractDescription;
		this.contractType = contractType;
		this.UUID = UUID;
	}
	
	public void setContractDescription(String contractDescription){
		this.contractDescription = contractDescription;
	}
	
	public String getContractDescription(){
		return this.contractDescription;
	}
	
	public void setContractType(ContractType contractType){
		this.contractType = contractType;
	}
	
	public ContractType getContractType(){
		return this.contractType;
	}
	
	public void setUUID(String UUID){
		this.UUID = UUID;
	}
	
	public String getUUID(){
		return this.UUID;
	}

        public String getSourceURL() {
            return sourceURL;
        }

        public void setSourceURL(String sourceURL) {
            this.sourceURL = sourceURL;
        }
}