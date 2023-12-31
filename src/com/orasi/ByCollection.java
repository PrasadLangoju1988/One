package com.orasi;
 
 import java.io.Serializable;
 import java.util.ArrayList;
 import java.util.List;
 import org.openqa.selenium.By;
 import org.openqa.selenium.NoSuchElementException;
 import org.openqa.selenium.SearchContext;
 import org.openqa.selenium.WebElement;
 
 public class ByCollection extends By implements Serializable
 {
 
   private static final long serialVersionUID = 4573668832699497306L;
 
   private By[] bys;
 
   public ByCollection( By... bys )
   {
     this.bys = bys;
   }
 
   @Override
   public WebElement findElement( SearchContext context )
   {
     List<WebElement> elements = findElements( context );
     if ( elements.isEmpty() ) {
       throw new NoSuchElementException( "Cannot locate an element using " + toString() );
     }
     return elements.get( 0 );
   }
 
   @Override
   public List<WebElement> findElements( SearchContext context )
   {
     List<WebElement> elems = new ArrayList<>();
     for ( By by : bys ) {
       try {
          List<WebElement> elementList = by.findElements(context);
          if (elementList != null && !elementList.isEmpty()) {
            elems.addAll(by.findElements(context));
            return elems;
          }
        } catch (Exception e) {
          
        }
     }
 
     return elems;
   }
 
   @Override
   public String toString()
   {
     StringBuilder stringBuilder = new StringBuilder( "By.collection(" );
     stringBuilder.append( "{" );
 
     boolean first = true;
     for ( By by : bys ) {
       stringBuilder.append( ( first ? "" : "," ) ).append( by );
       first = false;
     }
     stringBuilder.append( "})" );
     return stringBuilder.toString();
   }
 } 