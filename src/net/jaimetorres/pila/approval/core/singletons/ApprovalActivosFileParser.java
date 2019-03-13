package net.jaimetorres.pila.approval.core.singletons;

import java.io.InputStream;

import org.beanio.StreamFactory;

//ELIMINAR ESTA CLASE
@Deprecated
public class ApprovalActivosFileParser {

	private StreamFactory streamFactory;
	
	private ApprovalActivosFileParser(){
		
		try {
			String mappingFileName = "mapping_activos.xml";
			
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream mappingFileIS = classLoader.getResourceAsStream(mappingFileName);
			
			// create a StreamFactory
			streamFactory = StreamFactory.newInstance();
	        // load the mapping file
			streamFactory.load(mappingFileIS);
		} catch (Exception e) {
e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	//SINGLETON
	private static ApprovalActivosFileParser instance;
	public static ApprovalActivosFileParser getInstance() {
		
		if (instance == null ) {
            synchronized (ApprovalActivosFileParser.class) {
                if (instance == null) {
                    instance = new ApprovalActivosFileParser();
                }
            }
        }
 
        return instance;
	}
	//end singleton
	
	public StreamFactory getStreamFactory() {
		return streamFactory;
	}
	
	public static void main(String[] args) {
		String line = "00000102CC26564571        LOZANO              RODRIGUEZ                     OLINDA                                                                                                                                                                  11N5 41001      300036210000.16000000579400000000000000579400000018200000018200000000000                              003621000                    ";
		System.out.println(line.length());
		line.substring(427,437).trim().equals(""); 
	}
}
