package formatting.service;


/*
 * Defines the format of the Message object
 * and adds several helful methods for converting text
 * 
 */
public class Message {

	private String id;
	private String messageType;
	private String messageBody;
	private String timeFrame;

	public Message(String id, String messageType, String messageBody, String timeFrame) {
		
		this.id = id;
		this.messageType = messageType;
		this.messageBody = messageBody;
		this.timeFrame = timeFrame;
		
	}

	public Message() {
		
	}
	
	//Checks the message type and converts based on what it is
	public void convertBody() {
		
		switch( this.messageType ) {
			
			case "email": this.setEmailMessageBody();
							break;
			
			case "twitter": this.setTwitterMessageBody();
							break;
			
			case "facebook": this.setFacebookMessageBody();
							break;
			
			default: break;
							
		}
		
		
	}
	
	//formats message for facebook
	private void setFacebookMessageBody() {

		this.messageBody = "Hi friends, this is my FB message: " + this.messageBody;
	}

	//cuts off all characters past 140 for twitter
	private void setTwitterMessageBody() {
		
		this.messageBody = this.messageBody.substring(0, 139);
	}

	//adds a starting line for email
	private void setEmailMessageBody() {
		
		this.messageBody = "To whom it may concern, \n" + this.messageBody;		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(String timeFrame) {
		this.timeFrame = timeFrame;
	}
		
}
