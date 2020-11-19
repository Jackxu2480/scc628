package org.apache.commons.mail;

import javax.mail.Session;
import javax.net.ssl.SSLSession;
import static org.junit.Assert.*;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import java.util.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import junit.framework.TestCase;

public class EmailTest extends TestCase{
	 EmailMock testEmail;
	 static String[] emails = {"a@b.com", "c@d.com", "e@f.com","h@i.com"};
	 static String[] eMail = {"a@b.com", "c@d.com", "e@f.com"};
	public void setUp() {
		testEmail = new EmailMock();
	}
	public void testAddBcc() throws EmailException {
		String[] emails = {"a@b.com", "c@d.com", "e@f.com", "h@i.com"};
		testEmail.addBcc(emails);
		assertEquals("a@b.com", testEmail.getBccAddresses().get(0).toString());
		assertEquals("c@d.com", testEmail.getBccAddresses().get(1).toString());
		assertEquals("e@f.com", testEmail.getBccAddresses().get(2).toString());
		assertEquals("h@i.com", testEmail.getBccAddresses().get(3).toString());
	}
	public void testAddBccEmpty() throws EmailException {
		String[] emails = {};
		try{
			testEmail.addBcc(emails);
			fail("Fail to throw exception");
		}catch(EmailException e){
			
		}
		
	}
	public void testAddBccNull() throws EmailException {
		String[] emails = null;
		try{
			testEmail.addBcc(emails);
			fail("Fail to throw exception");
		}catch(EmailException e){
			
		}
		//assertEquals("Email length is ",testEmail.getBccAddresses().size());
		
	}
	public void testAddHeader(){
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("tony", "a@b.com");
		headers.put("rogers", "c@d.com");
		headers.put("micky", "e@f.com");
		
		for(Map.Entry<String, String>header : headers.entrySet()){
			String n = header.getKey();
			String v = header.getValue();
			testEmail.addHeader(n, v);
		}
		assertEquals(headers.size(), testEmail.getHeaders().size());
		assertEquals(headers, testEmail.getHeaders());
	}
	
	public void testAddHeaderEmptyName() throws IllegalArgumentException{
		try{
			testEmail.addHeader("", "a@b.com");
			fail("Fail to throw exception");
		}catch(IllegalArgumentException e){
			
		}
}
	public void testAddHeaderEmptyValue() throws IllegalArgumentException{
		try{
			testEmail.addHeader("Tony", "");
			fail("Fail to throw exception");
		}catch(IllegalArgumentException e){
			
		}
}
	public void testAddHeaderNullName() throws IllegalArgumentException{
		try{
			testEmail.addHeader(null, "a@b.com");
			fail("Fail to throw exception");
		}catch(IllegalArgumentException e){
			
		}
}
	public void testAddHeaderNullValue() throws IllegalArgumentException{
		try{
			testEmail.addHeader("Tony", null);
			fail("Fail to throw exception");
		}catch(IllegalArgumentException e){
			
		}
}
	
	public void testGetMailSessionWithEmptyHostName() throws EmailException{
		try{
			testEmail.getMailSession();
			fail("email exception should be thrown with empty host.");
		} catch(EmailException e){
			
		}
	}
	public void testGetNewMaillSession() throws EmailException{
		testEmail.setHostName("b.com");
		Session s = testEmail.getMailSession();
		assertNotNull(s);
	}
	public void testGetMailSessionWithHostName() throws EmailException{
		
			testEmail.setHostName("b.com");
			testEmail.getMailSession();
	}
	public void testGetMailSessionWithAuthenticator() throws EmailException{
		
		testEmail.setHostName("b.com");
		testEmail.setAuthenticator(new DefaultAuthenticator("abc", "123"));
		testEmail.getMailSession();

}
	public void testGetMailSessionWithSSLOnConnect() throws EmailException{
		testEmail.setSSLOnConnect(true);
		testEmail.setHostName("b.com");
		testEmail.getMailSession();
}
	public void testSend() throws EmailException{
		testEmail.setHostName("b.com");
		testEmail.setFrom("a@b.com");
		testEmail.addTo("c@b.com");
		testEmail.setSubject("subject");
		testEmail.setMsg("msg");
		testEmail.setAuthentication("abc", "123");
		testEmail.setCharset(EmailConstants.US_ASCII);
		testEmail.send();
		assertEquals("subject", testEmail.getSent());
	}
	
	public void testAddCc() throws Exception{
		List<InternetAddress> test = new ArrayList<InternetAddress>();
	
			test.add(new InternetAddress("a@b.com"));
			test.add(new InternetAddress("c@d.com"));
			test.add(new InternetAddress("e@f.com"));
			test.add(new InternetAddress("h@i.com"));
		for(String address : emails){
			testEmail.addCc(address);
		}
		assertEquals(test.size(), testEmail.getCcAddresses().size());
		assertEquals(test.toString(),testEmail.getCcAddresses().toString());
	}
	public void testAddCcStr() throws Exception{
		String[] emails = {"a@b.com", "c@d.com", "e@f.com", "h@i.com"};
		testEmail.addCc(emails);

	}

	public void testAddCcNull() throws Exception{
		String[] testEmailNull = {null, "", "x"};
		List<InternetAddress> test = new ArrayList<InternetAddress>();
	
			test.add(new InternetAddress("a@b.com"));
			test.add(new InternetAddress("c@d.com"));
			test.add(new InternetAddress("e@f.com", "x"));
		for(int i = 0; i < eMail.length; i++){
			testEmail.addCc(eMail[i],testEmailNull[i]);
		}
		assertEquals(test.size(), testEmail.getCcAddresses().size());
		assertEquals(test.toString(),testEmail.getCcAddresses().toString());
	}
	
	public void testAddReplyTo() throws Exception{
		String[] testNames = {"tony", "michael", "alex", "mark"};
		List<InternetAddress> testList = new ArrayList<InternetAddress>();
		testList.add(new InternetAddress("a@b.com", "tony"));
		testList.add(new InternetAddress("c@d.com", "michael"));
		testList.add(new InternetAddress("e@f.com", "alex"));
		testList.add(new InternetAddress("h@i.com", "mark"));
		for(int i = 0; i < emails.length; i++){
			testEmail.addReplyTo(emails[i], testNames[i]);
		}
		assertEquals(testList.size(), testEmail.getReplyToAddresses().size());
		assertEquals(testList.toString(),testEmail.getReplyToAddresses().toString());
	}
	
	public void testGetHostName(){
		testEmail.setHostName("host");
		testEmail.getHostName();
		assertEquals("host",testEmail.getHostName().toString());
	}
	public void testGetHostNameNull(){
		testEmail.getHostName();
		assertNull(testEmail.getHostName());
	}
	
	public void testBuildMimeMessage() throws EmailException{
		testEmail.setHostName("host");
		testEmail.setSmtpPort(0101);
		testEmail.setFrom("a@b.com");
		testEmail.addTo("c@d.com");
		testEmail.setCharset(EmailConstants.US_ASCII);
		testEmail.setSubject("subject");
		testEmail.setMsg("mes");
		testEmail.setAuthentication("abc", "123");
		testEmail.addCc("g@h.com");
		testEmail.addBcc("a@b.com");
		testEmail.addReplyTo("e@f.com");
		testEmail.addHeader("tony", "send");
		testEmail.setSentDate(null);
		testEmail.setSSLOnConnect(true);
		testEmail.setPopBeforeSmtp(true, "Lhost", "def", "456");
		//testEmail.buildMimeMessage();
	}

	public void testSendGetData(){
		Date dateTest = Calendar.getInstance().getTime();
		testEmail.setSentDate(dateTest);
		assertEquals(dateTest, testEmail.getSentDate());
	}
	
	public void testSendGetDataNull(){
		testEmail.setSentDate(null);
		Date sendDate = testEmail.getSentDate();
		assertEquals(sendDate, testEmail.getSentDate());
	}

	public void testGetSocketConnectionTimeout() throws Exception{
		testEmail.setHostName("host");
		testEmail.setSmtpPort(0101);
		testEmail.setFrom("a@b.com");
		testEmail.addTo("c@d.com");
		testEmail.setCharset(EmailConstants.US_ASCII);
		testEmail.setSubject("subject");
		testEmail.setMsg("mes");
		testEmail.setAuthentication("abc", "123");
		testEmail.buildMimeMessage();
		try{
			testEmail.setSocketConnectionTimeout(100000);
		}catch (IllegalStateException e){
			
		}
		testEmail.getSocketConnectionTimeout();
		assertEquals("TimeOut", 60000, testEmail.getSocketConnectionTimeout());
	}
	
	public void testSetFrom() throws Exception{
		List<InternetAddress> test = new ArrayList<InternetAddress>();
		
		test.add(new InternetAddress("a@b.com"));
		test.add(new InternetAddress("c@d.com"));
		test.add(new InternetAddress("e@f.com"));
		test.add(new InternetAddress("h@i.com"));
		for(int i = 0; i < emails.length; i++){
			testEmail.setFrom(emails[i]);
			assertEquals(test.get(i).toString(), testEmail.getFromAddress().toString());
		}
		
	}
	public void testUpdateContentType(){
		String testContent = " ; charset=" + EmailConstants.US_ASCII;
		testEmail.setContent("object", " ; charset=" + EmailConstants.US_ASCII);
		assertEquals(testContent, testEmail.getContentType());
	}
	
	public void testUpdateContentTypeNullObject(){
		String testContent = " ; charset=" + EmailConstants.US_ASCII;
		testEmail.setContent(null, " ; charset=" + EmailConstants.US_ASCII);
		assertEquals(testContent, testEmail.getContentType());
	}
	
	public void testUpdateContentTypeNull(){
		testEmail.setContent("object", null);
		assertNull(testEmail.getContentType());
	}
	public void testUpdateContentTypeNullBoth(){
		testEmail.setContent(null, null);
		assertNull(testEmail.getContentType());
	}
	
	public void testUpdateContentTypeNullObjectInC(){
		String testContent = "wrong";
		testEmail.setContent(null, "wrong");
		assertEquals(testContent, testEmail.getContentType());
	}
	public void testUpdateContentTypeObjectInC(){
		String testContent = "wrong";
		testEmail.setContent("object", "wrong");
		assertEquals(testContent, testEmail.getContentType());
	}
	
	public void testUpdateContentTypeText(){
		testEmail.setContent("object", "text/plain");
		assertNotNull(testEmail.getContentType().toString());
	}
		
}
	
