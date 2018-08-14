package se.walkercrou.places.processor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * 
 * @author robin
 *
 */
public class CustomCsvWriter {

    private File file;
    private char separator;
    private String[] fieldMap;
    private boolean append = false;
    
    /**
     * 
     * @param file
     * file to write in
     * @param separator
     * column separator
     * @param fieldMap
     * Format : 'key1:value1,key2:value2,key3:value3...'
     * key[i] is header name of csv and value[i] is the name of the field in java object
     */
    public CustomCsvWriter(File file, char separator, String fieldMap){
        this.file = file;
        this.separator = separator;
        this.fieldMap = fieldMap.split(",");
    }
    
    public File getFile() {
        return file;
    }

    public char getSeparator() {
        return separator;
    }

    public String[] getFieldMap() {
        return fieldMap;
    }
    
    /**
     * @return Returns the headers of the csv from fieldMap
     */
    public String[] getHeaderValues() {
        String[] headerColumns = new String[fieldMap.length];
        
        for(int i=0;i<fieldMap.length;i++){
            headerColumns[i] = fieldMap[i].split(":")[0];
        }
        return headerColumns;
    }
    
    /**
     * 
     * @param object
     * @return Using reflection apis, it convert fields of an object to string array
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    private String[] convertToStringArray(Object object) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
        String[] objectArray = new String[fieldMap.length];
        
        for(int i=0;i<fieldMap.length;i++){
            objectArray[i] = BeanUtils.getProperty(object, fieldMap[i].split(":")[1]);
        }
        
        return objectArray;
    }
    
    /**
     * appends subsequent lines to the file from object list
     * @param objectList
     * @throws Exception
     */
    public void writeSubsequentData(List<?> objectList) throws Exception{

        CSVWriter csvWriter = null;
        try{
            csvWriter = new CSVWriter(new FileWriter(file, true), separator, CSVWriter.NO_QUOTE_CHARACTER);
            
            // Write Data
            for(Object pogDTO : objectList){
                csvWriter.writeNext(convertToStringArray(pogDTO));
            }
            
            csvWriter.flush();
            
        }catch (IOException ioe) {
            throw ioe;
        }catch(Exception ex){
            throw ex;
        }finally{
            if(csvWriter != null){
                try {
                    csvWriter.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
        
        
        
    
    	
    }
    
    /**
     * First write headers into the csv then appends object list
     * @param objectList
     * @throws Exception
     */
    public void writeToFile(List<?> objectList) throws Exception{
        CSVWriter csvWriter = null;
        try{
            csvWriter = new CSVWriter(new FileWriter(file, append), separator, CSVWriter.NO_QUOTE_CHARACTER);
            
            // Write header
            csvWriter.writeNext(getHeaderValues());
            
            // Write Data
            for(Object dto : objectList){
                csvWriter.writeNext(convertToStringArray(dto));
            }
            
            csvWriter.flush();
            
        }catch (IOException ioe) {
            throw ioe;
        }catch(Exception ex){
        	ex.printStackTrace();
            throw ex;
        }finally{
            if(csvWriter != null){
                try {
                    csvWriter.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
        
    }

	public boolean isAppend() {
		return append;
	}

	public void setAppend(boolean append) {
		this.append = append;
	}
    
}
