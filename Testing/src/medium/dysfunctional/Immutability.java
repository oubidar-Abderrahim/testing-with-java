package medium.dysfunctional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import cyclops.control.Eval;

// Second
public class Immutability {

	private final long customerId;
    private final String type;
	private final File file;
    private final Eval<String> contents = Eval.later(this::loadContents);

    public String getContents(){
        return contents.get();
    }

    private String loadContents(){
        try {
            return new String(Files.readAllBytes(file.toPath()));
        }catch(IOException e){
            //throw new DataFileUnavailableException(e);
        	e.printStackTrace();
        }
		return "";
    }

    // to ensure immutability, we make the field final, we must initalize them inside the constructor and not by setters

    public Immutability(long customerId, String type, File file) {
		super();
		this.customerId = customerId;
		this.type = type;
		this.file = file;
	}

	public long getCustomerId() {
		return customerId;
	}

	public String getType() {
		return type;
	}

	public File getFile() {
		return file;
	}
	
	// if we need to change a field, we clone it
	public Immutability withCustomerId(long customerId) {
		return ( this.customerId == customerId ? this : new Immutability(customerId, this.type, this.file) );
	}
	
	public Immutability withType(String type) {
		return ( this.type == type ? this : new Immutability(this.customerId, type, this.file) );
	}
	
	public Immutability withFile(File file) {
		return ( this.file == file ? this : new Immutability(this.customerId, this.type, file) );
	}
	
	// using Lombok, we can generate the constructor, getters and the withers by annotating the class with 3 annotations only
	/**
	 * 
	 * <dependency>
	 *		<groupId>org.projectlombok</groupId>
	 *		<artifactId>lombok</artifactId>
	 *		<optional>true</optional>
	 *	</dependency>
	 *	
	 * @AllArgsConstructor
	 * @Getter
 	 * @Wither
	 * public class Immutability {
	 *
     *   private final long customerId;
     *   private final String type;
     *   private final File file;
     *   private final Eval<String> contents = Eval.later(this::loadContents);
	 *   
     *   public String getContents(){
     *     return contents.get();
     *   }
     *   
     *   private String loadContents(){
     *     try {
     *         return new String(Files.readAllBytes(file.toPath()));
     *     }catch(IOException e){
     *         //throw new DataFileUnavailableException(e);
     *       	 e.printStackTrace();
     *     }
	 * 	 return "";
     *   }
     *   
     * }		
	 */
	
}

//a class calling our file could be refactor to something like :

class DataFileService {

    private final UserService userService;
    private final DataFileWriter writer;

    public Immutability createDataFileMetadata(final User user, 
                                                   final String type,
                                                   final String contents, 
                                                   final String location){

        return new Immutability(userService.attachCustomerInfo(user,type),
                                    type,
                                    writer.writeDataFile(contents,location));
        
    }
    
    public  DataFileService(UserService userService,DataFileWriter writer) {
		this.userService = userService;
		this.writer = writer;
	}


}

class UserService {

	public long attachCustomerInfo(User user, String type) {
		// TODO Auto-generated method stub
		return 0;
	}}
class DataFileWriter {

	public File writeDataFile(String contents, String location) {
		// TODO Auto-generated method stub
		return null;
	}}
class User {}