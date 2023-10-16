package com.orasi;
 
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 import java.util.concurrent.BlockingQueue;
 import java.util.concurrent.LinkedBlockingQueue;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 
 public class EndpointDeviceManager {
   
   private static final Logger log = LoggerFactory.getLogger(EndpointDeviceManager.class);
   private static final EndpointDeviceManager singleton = new EndpointDeviceManager();
   
   private final Map<String,Router> routerMap = new HashMap<>( 10 );
   private final Map<String,ExecutionTarget> targetMap = new HashMap<>( 10 );
   
   private EndpointDeviceManager() {
     
   }
   
   public Router getRouter( String alchemyIdentifier ) {
     return routerMap.get( alchemyIdentifier );
   }
   
   public ExecutionTarget getTarget( String alchemyIdentifier ) {
     return targetMap.get( alchemyIdentifier );
   }
   
   public void addRouter( Router r ) {
     routerMap.put( r.getAlchemyIdentifier(), r );
   }
   
   public void addTarget( ExecutionTarget eT ) {
     targetMap.put( eT.getAlchemyIdentifier(), eT);
   }
   
   public static EndpointDeviceManager instance() {
     return singleton;
   }
   
   private final List<String> completeEndpointList = new ArrayList<>(10);
   private final List<BlockingQueue<EndpointDevice>> endpointList = new ArrayList<>(10);
   private int listIndex = 0;
   
   public void registerEndpoint( String alchemyIdentifier ) {
     
     ExecutionTarget eT = targetMap.get(alchemyIdentifier);
     if ( eT == null ) {
       throw new IllegalArgumentException( "Unable to locate Target for " + alchemyIdentifier );
     }
     
     Router r = routerMap.get( eT.getRouterIdentifier() );
     if ( r == null ) {
       throw new IllegalArgumentException( "Unable to locate Router for " + eT.getRouterIdentifier() );
     }
     
     EndpointDevice eD = new EndpointDevice( r, eT );
     
     BlockingQueue q = new LinkedBlockingQueue<EndpointDevice>(eD.getMaximumAvailable());
     
     eD.setQueueIndex(endpointList.size());
     
     for (int i = 0; i < eD.getMaximumAvailable(); i++) {
       q.add(eD);
     }
     
     log.info("Registering " + eD.getMaximumAvailable() + " instance(s) of " + eD.getName() + " at " + endpointList.size());
     
     endpointList.add(q);
   }
   
   public int getSize() {
     return endpointList.size();
   }
   
   public boolean isValid() {
     if ( log.isDebugEnabled() ) {
       log.info(completeEndpointList.size() + " endpoint devices completed of " + endpointList.size());
     }
     return completeEndpointList.size() < endpointList.size();
   }
   
   public synchronized EndpointDevice acquireEndpoint() {
     for (int i = 0; i < endpointList.size(); i++) {
       if (i + listIndex > endpointList.size() - 1) {
         listIndex = 0;
       }
       
       EndpointDevice eD = endpointList.get(listIndex++).poll();
       
       if (eD != null) {
         log.info("Checking out a [" + eD.getName() + "] - " + endpointList.size() + " device remain idle");
         return eD;
       }
     }
     
     log.debug("There are currently no idle devices");
     return null;
   }
   
   public void releaseEndpoint(EndpointDevice eD) {
     log.info("Releasing an instance of [" + eD.getName() + "]");
     releaseEndpoint(eD, false);
   }
   
   public synchronized void releaseEndpoint(EndpointDevice eD, boolean invalidate) {
     log.info( "Releasing " + eD.getName() + ", " + invalidate );

     if (!invalidate) {
       if (!completeEndpointList.contains(eD.getName())) {
         log.info("Releasing an instance of [" + eD.getName() + "]");
         endpointList.get(eD.getQueueIndex()).offer(eD);
       }
     } else {
       log.info("Invalidating an [" + eD.getName() + "]");
       completeEndpointList.add(eD.getName());
       endpointList.get(eD.getQueueIndex()).clear();
       
     }
     
   }
   
 }
 