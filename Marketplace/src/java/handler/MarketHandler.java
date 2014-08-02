package handler;

import database.MySQL;
import java.io.IOException;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import storage.Contractmodel;
import storage.Stream;
import storage.Service;

public class MarketHandler extends DefaultHandler {
	private Service service;
	private Stream datastream;
	
	private Contractmodel contractmodel;
	
	private ArrayList<String> termvalueList;	
	private String characters;

        private String user;
	/*servicecost reference*/
//	ArrayList<String> serviceCostReference;
	ArrayList<String> serviceContractReference;
	
        private MySQL mysql = new MySQL();
	/*constructor to parse XML string*/
	public MarketHandler(String inputXML, String user) {
                this.user = user;
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(new InputSource (new StringReader(inputXML)), this);
    	} 
		catch (ParserConfigurationException | SAXException | IOException t) {
		}
	}

	/*initialization*/
        @Override
	public void startDocument() throws SAXException {
		this.termvalueList = new ArrayList<>();				
	}

	/*start of an element*/
        @Override
	public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes attributes) throws SAXException {
            switch (qualifiedName) {
                case "market:service":
                    /*create a service entity*/
                    service = new Service();
                    service.setServiceOwner(user);
                    break;
                case "market:datastream":
                    /*create a datastream entity*/
                    datastream = new Stream();
                    datastream.setServiceUUID(service.getServiceUUID());
                    break;
                case "market:contractmodel":
                    /*add categories to service entity*/
                    termvalueList.clear();
                    termvalueList.add("name");
                    termvalueList.add(attributes.getValue("name"));
                    termvalueList.add("src");
                    termvalueList.add(attributes.getValue("src"));
                    /*update database*/
                    service.addServiceContract(updateContract());
                    break;
                case "market:category":
                    /*add categories to service entity*/
                    termvalueList.clear();
                    termvalueList.add("name");
                    termvalueList.add(attributes.getValue("name"));
                    termvalueList.add("src");
                    termvalueList.add(attributes.getValue("src"));
                    /*update database*/
                    service.addServiceCategory(updateCategory());
                    break;
                case "market:qos":
                    /*add QoS to service entity*/
                    termvalueList.clear();
                    termvalueList.add("name");
                    termvalueList.add(attributes.getValue("name"));
                    termvalueList.add("src");
                    termvalueList.add(attributes.getValue("src"));
                    /*update database*/
                    service.addQoS(updateQoS());
                    break;
                case "market:datacategory":
                    /*add categories to datastream entity*/
                    termvalueList.clear();
                    termvalueList.add("name");
                    termvalueList.add(attributes.getValue("name"));
                    termvalueList.add("src");
                    termvalueList.add(attributes.getValue("src"));
                    /*update database*/
                    datastream.setDataCategory(updateCategory());
                    break;
                case "market:device":
                    /*add device to datastream entity*/
                    termvalueList.clear();
                    termvalueList.add("name");
                    termvalueList.add(attributes.getValue("name"));
                    termvalueList.add("src");
                    termvalueList.add(attributes.getValue("src"));
                    /*update database*/
                    datastream.setDevice(updateDevive());
                    break;
                case "market:datatype":
                    /*add device to datastream entity*/
                    termvalueList.clear();
                    termvalueList.add("name");
                    termvalueList.add(attributes.getValue("name"));
                    termvalueList.add("src");
                    termvalueList.add(attributes.getValue("src"));
                    /*update database*/
                    datastream.setDataType(updateDataType());
                    break;
                case "market:datacontract":
                    /*add device to datastream entity*/
                    termvalueList.clear();
                    termvalueList.add("name");
                    termvalueList.add(attributes.getValue("name"));
                    termvalueList.add("src");
                    termvalueList.add(attributes.getValue("src"));
                    /*update database*/
                    datastream.setDataContract(updateContract());
                    break;
            }
	}

	/*end of an element*/
        @Override
	public void endElement(String namespaceURI, String simpleName, String qualifiedName) throws SAXException {
		if (service != null) {			
                    /*service description*/
                    switch (qualifiedName) {
                        case "market:service":
                            saveService();
                            break;
                        case "market:serviceuuid":
                            service.setServiceUUID(characters);
                            break;
                        case "market:datastream":
                            datastream.setServiceUUID(service.getServiceUUID());
                            saveDataStream();
                            break;
                        case "market:servicename":
                            service.setServiceName(characters);
                            break;
                        case "market:servicecost":
                            service.setCostURL(characters);
                            break;
                        case "market:provider":
                            service.setProvider(characters);
                            break;
                        case "market:servicedescription":
                            service.setServiceDescription(characters);
                            break;
                        case "market:servicedatabus":
                            service.setDatabus(characters);
                            break;
                        case "market:streamuuid":
                            datastream.setStreamUUID(characters);
                            break;
                        case "market:datadescription":
                            datastream.setDataDescription(characters);
                            break;
                        case "market:datacost":
                            datastream.setDataCostURL(characters);
                            break;
                        case "market:streamdatabus":
                            datastream.setDatabus(characters);
                            break;
                        case "market:timeseries":
                            datastream.setTimeSeries(characters);
                            break;
                        case "market:uncertainly":
                            datastream.setUncertainly(characters);
                            break;
                        case "market:dataorigin":
                            datastream.setDataOrigin(characters);
                            break;
                        case "market:datarate":
                            datastream.setDataRate(characters);
                            break;
                        case "market:latency":
                            datastream.setLatency(characters);
                            break;
                    }
		}		
	}

        @Override
	public void characters(char buf[], int offset, int len) throws SAXException {
		characters = new String(buf, offset, len);
	}	
        
        /**
         * return contract_ID
         * @return 
         */
        private String updateContract(){
            String id = null;
            String sql = "SELECT ID FROM tbl_ContractModel WHERE SourceURL=\'" + termvalueList.get(3) + "\'";
            try {
                ResultSet rs = mysql.query(sql);
                if (rs.absolute(1)) {
                    id = rs.getString("ID");
                }else{
                    id = "ConId" + System.currentTimeMillis();
                    sql = "INSERT INTO tbl_ContractModel(ID, Name, SourceURL) VALUES(\'"+ id +"\',\'"+ termvalueList.get(1) +"\',\'" + termvalueList.get(3) + "\')";
                    mysql.execute(sql);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MarketHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            return id;
        }
        
        /**
         * return catefory_ID
         * @return 
         */
        private String updateCategory(){
            String id = null;
            String sql = "SELECT ID FROM tbl_Categories WHERE Scheme=\'" + termvalueList.get(3) + "\'";
            try {
                ResultSet rs = mysql.query(sql);
                if (rs.absolute(1)) {
                    id = rs.getString("ID");
                }else{
                    id = "CatId" + System.currentTimeMillis();
                    sql = "INSERT INTO tbl_Categories(ID, Term, Scheme) VALUES(\'"+ id +"\',\'"+ termvalueList.get(1) +"\',\'" + termvalueList.get(3) + "\')";
                    mysql.execute(sql);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MarketHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            return id;
        }
        
        /**
         * return qos_ID
         * @return 
         */
        private String updateQoS(){
            String id = null;
            String sql = "SELECT Id FROM tbl_QoS WHERE SourceURL=\'" + termvalueList.get(3) + "\'";
            try {
                ResultSet rs = mysql.query(sql);
                if (rs.absolute(1)) {
                    id = rs.getString("Id");
                }else{
                    id = "QoSId" + System.currentTimeMillis();
                    sql = "INSERT INTO tbl_QoS(Id, Name, SourceURL) VALUES(\'"+ id +"\',\'"+ termvalueList.get(1) +"\',\'" + termvalueList.get(3) + "\')";
                    mysql.execute(sql);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MarketHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            return id;
        }
        
        /**
         * return device_ID
         * @return 
         */
        private String updateDevive(){
            String id = null;
            String sql = "SELECT Id FROM tbl_Devices WHERE SourceUrl=\'" + termvalueList.get(3) + "\'";
            try {
                ResultSet rs = mysql.query(sql);
                if (rs.absolute(1)) {
                    id = rs.getString("Id");
                }else{
                    id = "QoSId" + System.currentTimeMillis();
                    sql = "INSERT INTO tbl_Devices(Id, Type, SourceUrl) VALUES(\'"+ id +"\',\'"+ termvalueList.get(1) +"\',\'" + termvalueList.get(3) + "\')";
                    mysql.execute(sql);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MarketHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            return id;
        }
        
        /**
         * return device_ID
         * @return 
         */
        private String updateDataType(){
            String id = null;
            String sql = "SELECT Id FROM tbl_DataType WHERE SourceURL=\'" + termvalueList.get(3) + "\'";
            try {
                ResultSet rs = mysql.query(sql);
                if (rs.absolute(1)) {
                    id = rs.getString("Id");
                }else{
                    id = "TypeId" + System.currentTimeMillis();
                    sql = "INSERT INTO tbl_DataType(Id, Type, SourceURL) VALUES(\'"+ id +"\',\'"+ termvalueList.get(1) +"\',\'" + termvalueList.get(3) + "\')";
                    mysql.execute(sql);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MarketHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            return id;
        }
        
        private void saveService()
        {
            String sql = "INSERT INTO tbl_Services(UUID, Name, Provider, Description, CostURL, Databus, CategoryListIds, ContractListIds, QoSListIds, owner, registered_date)" +
                        " VALUES(\'" + service.getServiceUUID() + "\',\'"+ service.getServiceName() +"\',\'"+ service.getProvider() +"\',\'"+ service.getServiceDescription() + "\',\'"+ service.getCostURL() +
                        "\',\'"+ service.getDatabus() +"\'," + (!service.getServiceCategoryList().isEmpty()?"\'"+ service.getServiceCategoryList() + "\'":"NULL") + "," +
                        (!service.getServiceContractList().isEmpty()?"\'"+ service.getServiceContractList() +"\'":"NULL") + "," +
                        (!service.getQosList().isEmpty()?"\'"+ service.getQosList() +"\'":"NULL") + ",\'"+ service.getServiceOwner() +"\', NOW())";
            try {
                mysql.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(MarketHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        private void saveDataStream(){
            String sql ="INSERT INTO tbl_Streams(StreamUUID, ServiceUUID, Description, CostURL, DeviceID, DataTypeID, Databus, TimeSeries, Uncertainly, DataOrigin, DataRate, Latency, CategoryID, ContractID)" +
                        " VALUES(\'"+ datastream.getStreamUUID() +"\',\'"+ datastream.getServiceUUID() +"\',\'"+ datastream.getDataDescription() +"\',\'"+ datastream.getDataCostURL() +"\',\'"+ datastream.getDevice() +"\',\'"+ datastream.getDataType() +
                        "\',\'"+ datastream.getDatabus() +"\',"+ datastream.getTimeSeries() +","+ datastream.getUncertainly() +",\'"+ datastream.getDataOrigin() +"\',"+ datastream.getDataRate() +","+ datastream.getLatency() +
                        ",\'"+ datastream.getDataCategory() +"\',\'"+ datastream.getDataContract() +"\')";
            try {
                mysql.execute(sql);
            } catch (SQLException ex) {
                Logger.getLogger(MarketHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
//        private void saveCostModel(String type){
//            if (type.equals("SizePrice")) {
//                String sql = "INSERT INTO tbl_CostDataSize(serviceUUID," + (costdatasize.getStreamUUID()!=null?"streamUUID,":"") + " price, Description, data_size, data_unit)" +
//                              " VALUES(\'"+ costdatasize.getServiceUUID()+"\'," +(costdatasize.getStreamUUID()!=null?"\'"+costdatasize.getStreamUUID()+"\',":"") + costdatasize.getBasicPrice() +
//                              ",\'"+ costdatasize.getCostDescription() +"\',"+ costdatasize.getData_size() +",\'"+ costdatasize.getData_unit() +"\')";
//                try {
//                    mysql.execute(sql);
//                } catch (SQLException ex) {
//                    Logger.getLogger(MarketHandler.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }else if(type.equals("APIPrice")){
//                String sql = "INSERT INTO tbl_CostAPIHandle(serviceUUID," + (costapi.getStreamUUID()!=null?"streamUUID,":"") + " price, Description, package_size)" +
//                              " VALUES(\'"+ costapi.getServiceUUID()+"\'," +(costapi.getStreamUUID()!=null?"\'"+costapi.getStreamUUID()+"\',":"") + costapi.getBasicPrice() +
//                              ",\'"+ costapi.getCostDescription() +"\',"+ costapi.getSize_of_package() +")";
//                try {
//                    mysql.execute(sql);
//                } catch (SQLException ex) {
//                    Logger.getLogger(MarketHandler.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }else if(type.equals("PlanPrice")){
//                String sql = "INSERT INTO tbl_CostTimePlan(serviceUUID," + (costtime.getStreamUUID()!=null?"streamUUID,":"") + " price, Description, time_duration, time_unit)" +
//                              " VALUES(\'"+ costtime.getServiceUUID()+"\'," +(costtime.getStreamUUID()!=null?"\'"+costtime.getStreamUUID()+"\',":"") + costtime.getBasicPrice() +
//                              ",\'"+ costtime.getCostDescription() +"\',"+ costtime.getDuration() +",\'"+ costtime.getTime_unit() +"\')";
//                try {
//                    mysql.execute(sql);
//                } catch (SQLException ex) {
//                    Logger.getLogger(MarketHandler.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }else if(type.equals("SubscriptionPrice")){
//                String sql = "INSERT INTO tbl_CostSubscription(serviceUUID," + (costsub.getStreamUUID()!=null?"streamUUID,":"") + " price, Description, time_duration, time_unit)" +
//                              " VALUES(\'"+ costsub.getServiceUUID()+"\'," +(costsub.getStreamUUID()!=null?"\'"+costsub.getStreamUUID()+"\',":"") + costsub.getBasicPrice() +
//                              ",\'"+ costsub.getCostDescription() +"\',"+ costsub.getDuration() +",\'"+ costsub.getTime_unit() +"\')";
//                try {
//                    mysql.execute(sql);
//                } catch (SQLException ex) {
//                    Logger.getLogger(MarketHandler.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
}
