package com.cx.configprovider.resource;

import com.cx.configprovider.dto.ResourceType;
import com.cx.configprovider.dto.interfaces.ConfigResource;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesResource extends ParsableResource implements ConfigResource {
    
    protected ResourceType resourceType = ResourceType.PROPERTIES;
    Properties properties = new Properties();

    /**
     * loads a specific property
     * @param pathName full path to the property separated by . char
     * @param value value of the property
     */
    public void addPropertyPathValue(String pathName, String value){
        properties.put(pathName, value);
    }

    /**
     * loads a specific property
     * @param propertyName property name 
     * @param path full path to the property without property name separated by . char
     * @param value value of the property
     */
    public void addPropertyValue(String propertyName, String path, String value){
        properties.put(path + "." + propertyName, value);
    }

    /**
     * loads a set of properties. It is important that each key in the 
     * property file will contain a full path to the field in the final 
     * configuration object
     * @param properties a full set of properties to be converted into a configuration tree
     */
    public void loadProperties(Properties properties){
        this.properties.putAll(properties);
    }


    /**
     * loads a set of properties from property file supplied in the file system path. 
     * It is important that each key in the 
     * property file will contain a full path to the field in the final 
     * configuration object
     * @param filepath to property file location in the file system
     */
    public void loadProperties(String filepath) throws IOException {
        File file = new File(filepath);
        if(!file.exists()){
            throw new FileNotFoundException(filepath);
        }
        Properties prop = new Properties();
        InputStream is = new FileInputStream(file);
        prop.load(is);
        properties.putAll(prop);
    }
    
    @Override
    Config load() {
        return ConfigFactory.parseProperties(properties);
    }

    @Override
    public String getName() {
        return resourceType.name();
    }
    
    
}
