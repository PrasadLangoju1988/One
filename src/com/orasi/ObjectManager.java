
package com.orasi;

import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.By.*;
import org.openqa.selenium.*;
import com.orasi.datasource.*;


public class ObjectManager {
  private static final ObjectManager singleton = new ObjectManager();
  
  public static final ObjectManager instance() {
    return singleton;
  }
  
  private final Map<String,ByFactory> objectMap = new HashMap<>( 10 );
  
  private ObjectManager() {
    ByFactoryCollection bC = null;
    /*
    Site: www.instagram.com
    
    */
    /* Page: Instagram 
    
    */

    

bC = new ByFactoryCollection("username");
bC.add( new ByFactory( ByXPath.class, "//INPUT[@name=\"username\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "username", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@name=\"username\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@aria-label='Phone number, username, or email']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@aria-label='Phone number, username, or email']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@maxlength='75']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@maxlength='75']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@class='_aa4b _add6 _ac4d focus-visible']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@class='_aa4b _add6 _ac4d focus-visible']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@type='text']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@type='text']", null ) );
bC.add( new ByFactory( ByXPath.class, "(//SPAN[@class='_aa4a'])[1]/following-sibling::INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@aria-required='true'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@autocapitalize='off'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@autocorrect='off'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//LABEL[@class='_aa48'])[1]/INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[2]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/SECTION[1]/MAIN[1]/ARTICLE[1]/DIV[2]/DIV[1]/DIV[2]/FORM[1]/DIV[1]/DIV[1]/DIV[1]/LABEL[1]/INPUT[1]", null ) );

objectMap.put( "11652.2243", bC );
/*
    Site: twitter.com
    
    */
    /* Page: X 
    
    */

    /* Page: X. Its whats happening / X 
    
    */

    

bC = new ByFactoryCollection("Createaccount");
bC.add( new ByFactory( ByXPath.class, "//A[./DIV[@style='color: rgb(255, 255, 255);']]", null ) );
bC.add( new ByFactory( ByXPath.class, "//A[./*[@style='color: rgb(255, 255, 255);']]", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@class='css-1dbjc4n r-13awgt0 r-117bsoe r-1mf7evn r-17w48nw r-1ipicw7']/following-sibling::A[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//A[@href='/i/flow/signup']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@href='/i/flow/signup']", null ) );
bC.add( new ByFactory( ByXPath.class, "//A[@class='css-4rbku5 css-18t94o4 css-1dbjc4n r-1vtznih r-sdzlij r-1phboty r-rs99b7 r-1loqt21 r-a9p05 r-eu3ka r-5oul0u r-1mf7evn r-17w48nw r-2yi16 r-1qi8awa r-1ny4l3l r-ymttw5 r-o7ynqc r-6416eg r-lrvibr r-1ipicw7']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@class='css-4rbku5 css-18t94o4 css-1dbjc4n r-1vtznih r-sdzlij r-1phboty r-rs99b7 r-1loqt21 r-a9p05 r-eu3ka r-5oul0u r-1mf7evn r-17w48nw r-2yi16 r-1qi8awa r-1ny4l3l r-ymttw5 r-o7ynqc r-6416eg r-lrvibr r-1ipicw7']", null ) );
bC.add( new ByFactory( ByXPath.class, "//A[@data-testid='signupButton']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@data-testid='signupButton']", null ) );
bC.add( new ByFactory( ByXPath.class, "//A[@style='border-color: rgba(0, 0, 0, 0);']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@style='border-color: rgba(0, 0, 0, 0);']", null ) );
bC.add( new ByFactory( ByXPath.class, "(//A[@role='link'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='css-1dbjc4n'])[2]/A[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[1]/DIV[1]/DIV[1]/DIV[2]/MAIN[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[3]/A[1]", null ) );

objectMap.put( "11652.2204", bC );
/* Page: Sign up for Twitter / X 
    
    */

    

bC = new ByFactoryCollection("name");
bC.add( new ByFactory( ByXPath.class, "//INPUT[@name=\"name\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "name", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@name=\"name\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@autocomplete='name']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@autocomplete='name']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@maxlength='50']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@maxlength='50']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@type='text']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@type='text']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@data-focusvisible-polyfill='true']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@data-focusvisible-polyfill='true']", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@autocapitalize='sentences'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@autocorrect='on'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@spellcheck='true'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@dir='auto'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@class='r-30o5oe r-1niwhzg r-17gur6a r-1yadl64 r-deolkf r-homxoj r-poiln3 r-7cikom r-1ny4l3l r-t60dpp r-1dz5y72 r-fdjqy7 r-13qz1uu'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@dir='ltr'])[4]/INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='css-901oao r-1awozwy r-18jsvk2 r-6koalj r-37j5jr r-1inkyih r-16dba41 r-135wba7 r-bcqeeo r-13qz1uu r-qvutc0'])[1]/INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[2]/DIV[2]/DIV[1]/DIV[1]/DIV[2]/DIV[2]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/LABEL[1]/DIV[1]/DIV[2]/DIV[1]/INPUT[1]", null ) );

objectMap.put( "11652.2129", bC );


bC = new ByFactoryCollection("css-18t94o4css-1dbjc4nr-1ydqjzzr-sdzlijr-1phbotyr-rs99b7r-2yi16r...");
bC.add( new ByFactory( ByXPath.class, "//DIV[./SPAN[@style='border-bottom: 2px solid rgb(15, 20, 25);']]", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[./*[@style='border-bottom: 2px solid rgb(15, 20, 25);']]", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@aria-label='Close']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@aria-label='Close']", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@class='css-18t94o4 css-1dbjc4n r-1ydqjzz r-sdzlij r-1phboty r-rs99b7 r-2yi16 r-1qi8awa r-1ny4l3l r-o7ynqc r-6416eg r-lrvibr']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@class='css-18t94o4 css-1dbjc4n r-1ydqjzz r-sdzlij r-1phboty r-rs99b7 r-2yi16 r-1qi8awa r-1ny4l3l r-o7ynqc r-6416eg r-lrvibr']", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@data-testid='app-bar-close']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@data-testid='app-bar-close']", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@style='border-color: rgba(0, 0, 0, 0); margin-left: calc(-8px);']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@style='border-color: rgba(0, 0, 0, 0); margin-left: calc(-8px);']", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@role='button'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@tabindex='0'])[5]", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@class='css-1dbjc4n r-1habvwh r-1pz39u2 r-1777fci r-15ysp7h r-s8bhmr']/DIV[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@class='css-1dbjc4n r-1habvwh r-1pz39u2 r-1777fci r-15ysp7h r-s8bhmr']/DIV[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[2]/DIV[2]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]", null ) );

objectMap.put( "11652.2171", bC );
/*
    Site: www.google.com
    
    */
    /* Page: Google 
    
    */

    

bC = new ByFactoryCollection("q");
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@id=\"APjFqb\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "APjFqb", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"APjFqb\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@name=\"q\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "q", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@name=\"q\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@jsname='vdLsw']/following-sibling::TEXTAREA[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@title=\"Search\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@title=\"Search\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@jsname='yZiJbe']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@jsname='yZiJbe']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@class='gLFyf']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@class='gLFyf']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@jsaction='paste:puy29d;']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@jsaction='paste:puy29d;']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@maxlength='2048']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@maxlength='2048']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@rows='1']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@rows='1']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@aria-autocomplete='both']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@aria-autocomplete='both']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@aria-controls='Alh6id']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@aria-controls='Alh6id']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@aria-expanded='true']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@aria-expanded='true']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@aria-haspopup='both']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@aria-haspopup='both']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@aria-owns='Alh6id']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@aria-owns='Alh6id']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@autocapitalize='off']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@autocapitalize='off']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@autocomplete='off']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@autocorrect='off']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@autocorrect='off']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@role='combobox']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@role='combobox']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@spellcheck='false']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@spellcheck='false']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@title='Search']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@title='Search']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@type='search']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@type='search']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@aria-label='Search']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@aria-label='Search']", null ) );
bC.add( new ByFactory( ByXPath.class, "//TEXTAREA[@data-ved='0ahUKEwiHrNTy7IGBAxVNGogKHVUKAbYQ39UDCAQ']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@data-ved='0ahUKEwiHrNTy7IGBAxVNGogKHVUKAbYQ39UDCAQ']", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@jscontroller='vZr2rb']/TEXTAREA[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@jscontroller='vZr2rb']/TEXTAREA[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@class='a4bIc']/TEXTAREA[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@class='a4bIc']/TEXTAREA[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@data-hpmde='false']/TEXTAREA[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@data-hpmde='false']/TEXTAREA[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@data-mnr='10']/TEXTAREA[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@data-mnr='10']/TEXTAREA[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@jsname='gLFyf']/TEXTAREA[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@jsname='gLFyf']/TEXTAREA[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@jsaction='h5M12e;input:d3sQLd;blur:jI3wzf;keydown:uYT2Vb']/TEXTAREA[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@jsaction='h5M12e;input:d3sQLd;blur:jI3wzf;keydown:uYT2Vb']/TEXTAREA[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[1]/DIV[3]/FORM[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[2]/TEXTAREA[1]", null ) );

objectMap.put( "11652.1995", bC );
/* Page: Helloworld - Google Search 
    
    */

    /*
    Site: www.facebook.com
    
    */
    /* Page: Facebook  log in or sign up 
    
    */

    

bC = new ByFactoryCollection("pass");
bC.add( new ByFactory( ByXPath.class, "//INPUT[@id=\"pass\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "pass", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"pass\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@name=\"pass\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "pass", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@name=\"pass\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@class='inputtext _55r1 _6luy _9npi']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@class='inputtext _55r1 _6luy _9npi']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@type='password']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@type='password']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@data-testid='royal_pass']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@data-testid='royal_pass']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@placeholder='Password']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@placeholder='Password']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@aria-label='Password']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@aria-label='Password']", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@id=\"passContainer\"]/INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"passContainer\"]/INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@class='_6luy _55r1 _1kbt']/INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@class='_6luy _55r1 _1kbt']/INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]/FORM[1]/DIV[1]/DIV[2]/DIV[1]/INPUT[1]", null ) );

objectMap.put( "11652.1620", bC );


bC = new ByFactoryCollection("login");
bC.add( new ByFactory( ByXPath.class, "//BUTTON[@id=\"u_0_5_cK\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "u_0_5_cK", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"u_0_5_cK\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//BUTTON[@name=\"login\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "login", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@name=\"login\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//BUTTON[@class='_42ft _4jy0 _6lth _4jy6 _4jy1 selected _51sy']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@class='_42ft _4jy0 _6lth _4jy6 _4jy1 selected _51sy']", null ) );
bC.add( new ByFactory( ByXPath.class, "//BUTTON[@type='submit']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@type='submit']", null ) );
bC.add( new ByFactory( ByXPath.class, "//BUTTON[@data-testid='royal_login_button']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@data-testid='royal_login_button']", null ) );
bC.add( new ByFactory( ByXPath.class, "//BUTTON[@value='1']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@value='1']", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='_6ltg'])[1]/BUTTON[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//BUTTON", null ) );
bC.add( new ByFactory( ByXPath.class, "//BUTTON[text()=\"Log in\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//BUTTON[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]/FORM[1]/DIV[2]/BUTTON[1]", null ) );

objectMap.put( "11652.1665", bC );


bC = new ByFactoryCollection("email");
bC.add( new ByFactory( ByXPath.class, "//INPUT[@id=\"email\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "email", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"email\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@name=\"email\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "email", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@name=\"email\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@type='text']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@type='text']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@class='inputtext _55r1 _6luy']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@class='inputtext _55r1 _6luy']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@data-testid='royal_email']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@data-testid='royal_email']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@placeholder='Email address or phone number']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@placeholder='Email address or phone number']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@autofocus='1']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@autofocus='1']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@aria-label='Email address or phone number']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@aria-label='Email address or phone number']", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='_6lux'])[1]/INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]/FORM[1]/DIV[1]/DIV[1]/INPUT[1]", null ) );

objectMap.put( "11652.1711", bC );


bC = new ByFactoryCollection("u_0_3_TB");
bC.add( new ByFactory( ByXPath.class, "//DIV[@id=\"u_0_3_TB\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "u_0_3_TB", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"u_0_3_TB\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//A[./DIV[@class='_9lsa']]", null ) );
bC.add( new ByFactory( ByXPath.class, "//A[./*[@class='_9lsa']]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@id=\"pass\"]/following-sibling::DIV[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@class='_9ls7']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@class='_9ls7']", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@id=\"passContainer\"]/DIV[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"passContainer\"]/DIV[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@class='_6luy _55r1 _1kbt _9nyi']/DIV[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@class='_6luy _55r1 _1kbt _9nyi']/DIV[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]/FORM[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]", null ) );

objectMap.put( "11652.1757", bC );


bC = new ByFactoryCollection("u_0_0_W3");
bC.add( new ByFactory( ByXPath.class, "//A[@id=\"u_0_0_W3\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "u_0_0_W3", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"u_0_0_W3\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//A[@class='_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@class='_42ft _4jy0 _6lti _4jy6 _4jy2 selected _51sy']", null ) );
bC.add( new ByFactory( ByXPath.class, "//A[@data-testid='open-registration-form-button']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@data-testid='open-registration-form-button']", null ) );
bC.add( new ByFactory( ByXPath.class, "//A[@ajaxify='/reg/spotlight/']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@ajaxify='/reg/spotlight/']", null ) );
bC.add( new ByFactory( ByXPath.class, "//A[@rel='async']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@rel='async']", null ) );
bC.add( new ByFactory( ByXPath.class, "(//A[@href='#'])[2]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//A[@role='button'])[2]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='_6ltg'])[2]/A[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//A[text()=\"Create new account\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]/FORM[1]/DIV[5]/A[1]", null ) );

objectMap.put( "11652.1786", bC );


bC = new ByFactoryCollection("firstname");
bC.add( new ByFactory( ByXPath.class, "//INPUT[@id=\"u_3_b_W7\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "u_3_b_W7", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"u_3_b_W7\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@name=\"firstname\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "firstname", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@name=\"firstname\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@placeholder='First name']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@placeholder='First name']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@aria-label='First name']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@aria-label='First name']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@tabindex='0']", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@type='text'])[3]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@class='inputtext _58mg _5dba _2ph-'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@data-type='text'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@aria-required='true'])[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//DIV[@id=\"u_3_a_3R\"]/INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"u_3_a_3R\"]/INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='_5dbb'])[1]/INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[3]/DIV[2]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]/DIV[1]/FORM[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/DIV[1]/INPUT[1]", null ) );

objectMap.put( "11652.1821", bC );


bC = new ByFactoryCollection("lastname");
bC.add( new ByFactory( ByXPath.class, "//INPUT[@id=\"u_3_d_pU\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "u_3_d_pU", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"u_3_d_pU\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@name=\"lastname\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "lastname", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@name=\"lastname\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@aria-label='Surname']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@aria-label='Surname']", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='placeholder'])[2]/following-sibling::INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@type='text'])[4]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@class='inputtext _58mg _5dba _2ph-'])[2]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@data-type='text'])[2]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@aria-required='true'])[2]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='uiStickyPlaceholderInput uiStickyPlaceholderEmptyInput'])[1]/INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[3]/DIV[2]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]/DIV[1]/FORM[1]/DIV[1]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]/INPUT[1]", null ) );

objectMap.put( "11652.1863", bC );


bC = new ByFactoryCollection("reg_email__");
bC.add( new ByFactory( ByXPath.class, "//INPUT[@id=\"u_3_g_sq\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "u_3_g_sq", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"u_3_g_sq\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@name=\"reg_email__\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "reg_email__", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@name=\"reg_email__\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@aria-label='Mobile number or email address']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@aria-label='Mobile number or email address']", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='placeholder'])[3]/following-sibling::INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@type='text'])[5]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@class='inputtext _58mg _5dba _2ph-'])[3]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@data-type='text'])[3]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@aria-required='true'])[3]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='uiStickyPlaceholderInput uiStickyPlaceholderEmptyInput'])[1]/INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[3]/DIV[2]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]/DIV[1]/FORM[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]/INPUT[1]", null ) );

objectMap.put( "11652.1897", bC );


bC = new ByFactoryCollection("reg_passwd__");
bC.add( new ByFactory( ByXPath.class, "//INPUT[@id=\"password_step_input\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "password_step_input", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@id=\"password_step_input\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@name=\"reg_passwd__\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "reg_passwd__", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@name=\"reg_passwd__\"]", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@type='password']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@type='password']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@data-type='password']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@data-type='password']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@aria-label='New password']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@aria-label='New password']", null ) );
bC.add( new ByFactory( ByXPath.class, "//INPUT[@autocomplete='new-password']", null ) );
bC.add( new ByFactory( ByXPath.class, "//*[@autocomplete='new-password']", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='placeholder'])[5]/following-sibling::INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@class='inputtext _58mg _5dba _2ph-'])[5]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//INPUT[@aria-required='true'])[5]", null ) );
bC.add( new ByFactory( ByXPath.class, "(//DIV[@class='uiStickyPlaceholderInput uiStickyPlaceholderEmptyInput'])[2]/INPUT[1]", null ) );
bC.add( new ByFactory( ByXPath.class, "/BODY[1]/DIV[3]/DIV[2]/DIV[1]/DIV[1]/DIV[2]/DIV[1]/DIV[1]/DIV[1]/FORM[1]/DIV[1]/DIV[4]/DIV[1]/DIV[1]/INPUT[1]", null ) );

objectMap.put( "11652.1930", bC );
/* Page: Log in to Facebook 
    
    */

    

  }
  
  public By getObject( Object alchemyIdentifier, Map<String,Object> contextMap, DataSourceProvider dM ) {
    
    if ( alchemyIdentifier instanceof By ) {
      return (By) alchemyIdentifier;
    }
    
    ByFactory by = objectMap.get( alchemyIdentifier + "" );
    if ( by == null ) {
      return new By() {
        @Override
        public List<WebElement> findElements(SearchContext sc) {
          throw new RuntimeException( "Could not find and object using [" + alchemyIdentifier + "]" );
        }
      };
    }
    return by.create(contextMap, dM);
  }

  public ByFactory getObject( String alchemyIdentifier ) {
   
    return objectMap.get( alchemyIdentifier );
  }
}
