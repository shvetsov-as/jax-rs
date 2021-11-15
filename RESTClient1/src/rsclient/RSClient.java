/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rsclient;

import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:GenericResource [generic]<br>
 * USAGE:
 * <pre>
 *        RSClient client = new RSClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author User
 */
public class RSClient {

    private javax.ws.rs.client.WebTarget webTarget;//opredeliaet slyjby
    private javax.ws.rs.client.Client client;
    private static final String BASE_URI = "http://localhost:8080/REST/webresources";// opred bazoviy URI

    public RSClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("generic");// ykaz na slujby po adresy http://localhost:8080/REST/webresources + generic
    }

    public String getXml() throws javax.ws.rs.ClientErrorException {//Y res berem znachenie webTarget
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(String.class);//y request poluchaem xml-doc .APPLICATION_XML i peredaem tip klassa string dlia sozdaniya otveta String.class
    }

    public void putXml(Object requestEntity) throws javax.ws.rs.ClientErrorException {//dobavliaem
        webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_XML).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_XML));
    }// berem target vizivaem request javax.ws.rs.client.Entity.entity - object kotoriy doljnbI zanesti

    public void close() {
        client.close();
    }
    
}
