package firebase;

public class FirebaseDataTest {
	private String dateOfBirth;
    private String fullName;
    private String nickName;
    
    public FirebaseDataTest(String dateOfBirth, String fullName)
    {
        this.dateOfBirth = dateOfBirth;
        this.fullName = fullName;
    }
    
    public FirebaseDataTest(String dateOfBirth, String fullName, String nickName)
    {
        this(dateOfBirth, fullName);
        this.nickName = nickName;
    }
    
    public String getDateOfBirth()
    {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getFullName()
    {
        return fullName;
    }
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }
    
    public String getNickName()
    {
        return nickName;
    }
    
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
    
    @Override
    public String toString()
    {
        return "User{" + "dateOfBirth=" + dateOfBirth + ", fullName=" + fullName + ", nickName=" + nickName + '}';
    }
}
