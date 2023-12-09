package com.example.cfenv.cfenv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.pivotal.cfenv.core.CfCredentials;
import io.pivotal.cfenv.core.CfEnv;
import io.pivotal.cfenv.core.CfService;

import org.springframework.amqp.rabbit.connection.RabbitConnectionFactoryBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class cfenv {
    private final CfEnv cfEnv;

    @Autowired
    public cfenv(){
        this.cfEnv=new CfEnv();
    }

    @RequestMapping(value="/")
    private String helloString(){
        return ("Hello spring boot cfenv from David. ");
    }

    @RequestMapping(value="/cfenv")
    private String[] getServiceNames() {
        List<CfService> services = cfEnv.findAllServices();
        
        CfService configService;
        CfCredentials configCredentials;
        String serviceName;
        Map<String, Object> configParametersMap;
        Map<String, Object> configCredentialMap;
        List<String> names = new ArrayList<>();
        List<CfService> rabbitServices = cfEnv.findServicesByLabel("p.rabbitmq");

        for (CfService service : services) {
            serviceName=service.getName();

            names.add(serviceName);
            configService=cfEnv.findServiceByName(serviceName);

            configCredentials=configService.getCredentials();
            configParametersMap=configService.getMap();
            configCredentialMap = configCredentials.getMap();

            names.add(serviceName + ": Parameters");
            names.add("{"); 
           
            for (Map.Entry<String, Object> entry : configParametersMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                names.add("  " + key + " = " + value);
            }

            names.add("}"); 
            names.add(serviceName + ": Crendential");
            names.add("{");  
            
            for (Map.Entry<String, Object> entry : configCredentialMap.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                names.add("  " + key + " = " + value);
            }
            
            names.add("}"); 
            
        }

        for (CfService rabbitService : rabbitServices) {
            // Get the credentials of the service
            CfCredentials credentials = rabbitService.getCredentials();
          
            // Create a connection factory with the credentials
            RabbitConnectionFactoryBean connectionFactory = new RabbitConnectionFactoryBean();
            connectionFactory.setHost(credentials.getHost());
            connectionFactory.setPort(Integer.parseInt(credentials.getPort()));
            connectionFactory.setUsername(credentials.getUsername());
            connectionFactory.setPassword(credentials.getPassword());
            
          }

        return names.toArray(new String[0]);
    } 
}
