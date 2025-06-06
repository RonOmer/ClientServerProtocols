package CSprotocols.Ruppin;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RuppinProtocol {
private static final int WELCOME = 0;
private static final int NEWOROLD = 1;
private static final int NEWUSERNAME = 2;
private static final int CHECKUSERNAME = 3;
private static final int CHECKPASSWORD = 4;
private static final int ANYCHANGES = 5;
private static final int SETRUPPINSTUDENT = 6;
private static final int SETHAPPY = 7;
private static final int QCHANGEPASSWORD= 8;
private static final int CHANGEPASSWORD= 9;
private static final int FINISHED = 10;

private int state = WELCOME;
private ArrayList<Client>clientState;
Client client = new Client();

public RuppinProtocol(ArrayList<Client>clientState) {
	this.clientState = clientState;
}

public String processInput(String input){
	String output = null;
	char c = ' ';
	
	if(input != null)
	{
		if(input.trim().isEmpty())
			output = "Invalid input. Please try again.";
		else
			c = Character.toLowerCase(input.charAt(0));
	}
	switch(state)
	{
	case WELCOME :{
		output = "new user?(y/n)";
		state = NEWOROLD;
		break;
	}
	case NEWOROLD :{
		if(c == 'y') {
			output = "Choose your username:";
			state = NEWUSERNAME;
		}
		else if(c == 'n') {
			output = "username:";
			state = CHECKUSERNAME;
		}
		else
			output = "invalid answer. please try again (y/n)"; 
		break;
	}
	case NEWUSERNAME :{
		if(checkUser(input) == null && input.trim().isEmpty() == false) {
			client.SetUserName(input);
			output = "Name OK. Choose your password:";
			state = CHECKPASSWORD;
		}
		else
			output = "Name not OK. Username exists. Choose a different name :";
		break;
	}
	case CHECKUSERNAME :{
		if(checkUser(input) != null && input.trim().isEmpty() == false) {
			this.client = checkUser(input);
			output = "password:";
			state = CHECKPASSWORD;
		}
		else
			output = "This username is not exist. please try again";
		break;
	}
	case CHECKPASSWORD :{
		if(client.getPassword() == " ")
		{
			if(checkPassword(input)) {
				client.setPassword(input);
				output = "Password OK. Are you a student at Ruppin? (y/n)";
				state = SETRUPPINSTUDENT;
			}
			else {
				output = "Password is invalid. Please try again.";
			}
		}
		else if(client.getPassword().equals(input))
		{
			client.setPassword(input);
			output = client.Info() + "Any changes since last time?(y/n)";
			state = ANYCHANGES;
		}
		else
			output = "Password is invalid. Please try again.";
		break;
	}
	case ANYCHANGES :{
		if(c == 'y')
		{
			output = "Are you a student at Ruppin? (y/n)";
			state = SETRUPPINSTUDENT;
		}
		else if(c == 'n')
		{
			output = "Thanks";
			state = FINISHED;
		}
		else
			output = "Answer is invalid. Please try again.(y/n)";
		break;
	}
	case SETRUPPINSTUDENT :{
		if(c == 'y' || c == 'n' )
		{
			client.setRuppinStudent(c);
			output = "Are you Happy? (y/n)";
			state = SETHAPPY;
		}
		else
			output = "Answer is invalid. Please try again.(y\\n)";
		break;
	}
	case SETHAPPY :{
		if(client.getHappy() == ' ')
		{
			if(c == 'y' || c == 'n')
			{
				client.setHappy(c);
				addUser(client);
				output = "Thanks";
				state = FINISHED;
			}
			else
				output = "Answer is invalid. Please try again(y\n).";
		}
		else if(c == 'y' || c == 'n')
		{
			client.setHappy(c);
			output= "Do you want to change your password? (y/n)";
			state = QCHANGEPASSWORD;
		}
		else
			output = "Answer is invalid. Please try again(y\n).";
		break;
	}
	case QCHANGEPASSWORD:{
		if(c == 'y') {
			output = "Choose your new password:";
			state = CHANGEPASSWORD;
		}
		else if (c == 'n') {
			output = "Thanks";
			state = FINISHED;
		}
		else
			output = "Answer is invalid. Please try again(y\n).";
		break;
	}
	case CHANGEPASSWORD:{
		if(checkPassword(input)) {
			client.setPassword(input);
			output="Thanks";
			state = FINISHED;
		}
		else
			output = "Password is invalid. Please try again.";
		break;
	}
	case FINISHED:{
		output = "q";
		state = WELCOME;
		break;
	}
	}
	return output;
}

public Client checkUser(String userName)
{
	for(int i=0;i<clientState.size();i++)
	{
		if(clientState.get(i).getUserName().equals(userName))
		{
			return clientState.get(i);
		}
	}
	return null;
}

public boolean checkPassword(String password) {
	if(password.length() < 9)
		return false;
	else if(!containsUpperCase(password)){
		return false;
	}
	else if(!containsLowerCase(password)){
		return false;
	}
	else if(!containsDig(password))
	{
		return false;
	}
	return true;
}
public boolean containsUpperCase(String str) {
	for(int i =0;i<str.length();i++){
		if(Character.isUpperCase(str.charAt(i)))
		{
			return true;
		}
	}
	return false;
}

public boolean containsLowerCase(String str) {
	for(int i =0;i<str.length();i++){
		if(Character.isLowerCase(str.charAt(i)))
		{
			return true;
		}
	}
	return false;
}
public boolean containsDig(String str) {
	for(int i =0;i<str.length();i++){
		if(Character.isDigit(str.charAt(i)))
		{
			return true;
		}
	}
	return false;
}

public boolean isValidChar(char c) {
	if(Character.toLowerCase(c) == 'y' || Character.toLowerCase(c) == 'n') {
		return true;
	}
	return false;
}

public void addUser(Client client)
{
	clientState.add(client);
	if(clientState.size() % 3 == 0)
	{
		backupToFile();
	}
}

private void backupToFile() {
    // יצירת שם קובץ עם תאריך ושעה
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    String fileName = "backup_" + LocalDateTime.now().format(formatter) + ".csv";

    try (FileWriter writer = new FileWriter(fileName)) {
        // כתיבת הלקוחות לקובץ
        for (Client client: clientState) {
            writer.write(client.toCSV() + "\n");
        }
        System.out.println("Backup created: " + fileName);
    } catch (IOException e) {
        System.err.println("Error writing to file: " + e.getMessage());
    }
}
}
