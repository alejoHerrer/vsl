package net.jaimetorres.pila.approval.core.singletons;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.beanio.StreamFactory;

import net.jaimetorres.pila.approval.core.processors.EntireApprovalFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosCajasFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosEsapFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosIcbfFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosMineduFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosPensionFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosRegistroAFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosRiesgosFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosSaludFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.activos.ApprovalActivosSenaFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.pensionados.ApprovalPensionadosCajasFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.pensionados.ApprovalPensionadosFspFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.pensionados.ApprovalPensionadosPensionFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.pensionados.ApprovalPensionadosRegistroAFileProcessor;
import net.jaimetorres.pila.approval.core.processors.admins.pensionados.ApprovalPensionadosSaludFileProcessor;
import net.jaimetorres.pila.approval.core.processors.adres.ApprovalAdresFileProcessor;
import net.jaimetorres.pila.approval.core.processors.minps.ApprovalMinpsFileProcessor;

public class ApprovalFileParser {

	private Map<Set<Class <? extends EntireApprovalFileProcessor<?> >>,StreamFactory> streamFactoryMap;
	
	private ApprovalFileParser(){
		this.init();
	}
	
	private void init(){
		
		//[[MAPPINGS]]
		this.streamFactoryMap = new HashMap<>();
		
		//activos
		this.associateProcessorsToMappingFile(
				Arrays.asList(
						ApprovalActivosCajasFileProcessor.class,
						ApprovalActivosEsapFileProcessor.class,
						ApprovalActivosIcbfFileProcessor.class,
						ApprovalActivosMineduFileProcessor.class,
						ApprovalActivosPensionFileProcessor.class,
						ApprovalActivosRegistroAFileProcessor.class,
						ApprovalActivosRiesgosFileProcessor.class,
						ApprovalActivosSaludFileProcessor.class,
						ApprovalActivosSenaFileProcessor.class),
					"mapping_activos.xml"
		);
		
		
		//pensionados
		this.associateProcessorsToMappingFile(
				Arrays.asList(
						ApprovalPensionadosCajasFileProcessor.class,
						ApprovalPensionadosFspFileProcessor.class,
						ApprovalPensionadosPensionFileProcessor.class,
						ApprovalPensionadosRegistroAFileProcessor.class,
						ApprovalPensionadosSaludFileProcessor.class),
					"mapping_pensionados.xml");
		
		//minps
		this.associateProcessorsToMappingFile(
				Arrays.asList(ApprovalMinpsFileProcessor.class),
					"mapping_minps.xml");
		
		//adres
		this.associateProcessorsToMappingFile(
				Arrays.asList(ApprovalAdresFileProcessor.class),
					"mapping_adres.xml");
		
	}
	
	private void associateProcessorsToMappingFile(
			List<Class <? extends EntireApprovalFileProcessor<?> > > processorsList,
			String mappingFileName){
		
			try{	
				Set<Class <? extends EntireApprovalFileProcessor<?> >>  activosProcessorsSet 
					= new HashSet<>(processorsList);
				
				ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
				InputStream mappingFileIS = classLoader.getResourceAsStream(mappingFileName);
				
				// create a StreamFactory
				StreamFactory streamFactory = StreamFactory.newInstance();
		        // load the mapping file
				streamFactory.load(mappingFileIS);
				
				streamFactoryMap.put(activosProcessorsSet, streamFactory);
			}catch(Exception e){
				throw new RuntimeException(e);
			}
	}
	
	public StreamFactory getStreamFactory(
			Class <? extends EntireApprovalFileProcessor<?>> specificClassFileProcessor) {
		Optional<Entry<Set<Class <? extends EntireApprovalFileProcessor<?>>>, StreamFactory>> streamFactoryEntry = 
				streamFactoryMap.entrySet().stream().filter(e->e.getKey().contains(specificClassFileProcessor)).findFirst();
		return streamFactoryEntry.isPresent()?streamFactoryEntry.get().getValue():null;
	}
	
	//SINGLETON
	private static ApprovalFileParser instance;
	public static ApprovalFileParser getInstance() {
		
		if (instance == null ) {
            synchronized (ApprovalFileParser.class) {
                if (instance == null) {
                    instance = new ApprovalFileParser();
                }
            }
        }
 
        return instance;
	}
	//end singleton

	
}
