package CSprotocols.Ruppin;

public class Client {
 private String userName;
 private String password;
 private char ruppinStudent;
 private char happy;
 
 public Client()
 {
	 userName = " ";
	 password = " ";
	 ruppinStudent = ' ';
	 happy = ' ';
 }
 
 public String getUserName() {
	 return userName;
 }
	public void SetUserName(String userName) {
			this.userName = userName;	
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
			this.password = password;
	}
	public char getRuppinStudent() {
		return ruppinStudent;
	}
	public void setRuppinStudent(char ruppinStudent){
			this.ruppinStudent = Character.toLowerCase(ruppinStudent);
	}
	public char getHappy() {
		return happy;
	}
	public void setHappy(char happy) {
			this.happy=Character.toLowerCase(happy);
}
	public String Info(){
		String str = "Last time you gave me the following information: you are ";
		if(ruppinStudent == 'n')
			str += "not";
		str += "student at Ruppin and you are ";
		if(happy == 'n')
			str += "not";
		str += "happy. ";
		return str;
	}
	
	public String toCSV(){
		String str = userName + "," + password + ",";
		if(ruppinStudent == 'y')
			str += "Ruppin student,";
		else
			str += " Not Ruppin student,";
		
		if(happy == 'y')
			str += "Happy";
		else
			str += " Not happy";
		
		return str;
	}
}
