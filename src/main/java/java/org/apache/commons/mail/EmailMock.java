package org.apache.commons.mail;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Map;

public class EmailMock extends SimpleEmail {
	private String sent;
	public String getSent(){
		return sent;
	}
	public String sendMimeMessage() throws EmailException{
		EmailUtils.notNull(this.message, "MimeMessage has not been created yet");
		try
		{
			this.sent = this.message.getSubject();
			return this.message.getMessageID();
		}
		catch (Throwable t)
		{
			String msg = "sending the email to the following server failed :"
			+ this.getHostName()
			+ ":"
			+ this.getSmtpPort();
			
		}
		return sent ;
		
	} 
	
	public boolean isDebug(){
		return this.debug;
	}
	
	public Map<String, String> getHeaders(){
		return this.headers;
	}
	
	public MimeMessage getMessage(){
		return this.message;
	 }


	public Authenticator getAuthenticator(){
		return this.authenticator;
	 }


	public String getCharset(){
		return this.charset;
	  }
	public Object getContentObject(){
		return this.content;
	  }
 
	public String getPopHost(){
		return this.popHost;
	   }
	 
	public String getPopPassword(){
		return this.popPassword;
	   }
	 
	public String getPopUsername(){
		return this.popUsername;
	   }
	 
	public String getContentType(){
		return contentType;
	   }
	 
	public boolean isPopBeforeSmtp(){
		return popBeforeSmtp;
	}
	public Email setMsg(String msg) throws EmailException
    {
        if (EmailUtils.isEmpty(msg))
        {
            throw new EmailException("Invalid message supplied");
        }

        setContent(msg, EmailConstants.TEXT_PLAIN);
        return this;
    }

}
