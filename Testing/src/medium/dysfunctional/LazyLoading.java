package medium.dysfunctional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

// First
public class LazyLoading {
	
	/**
	 *  let's take the example of code to load the content of a file
	 *	The code is barely 30 lines long it is already waaay more complex than it needs to be. 
	 *	There is an inter-dependency between the getContents method 
	 *	and the loadContents method that we’ve left for client code to resolve.
	 */
	
	private File file;
	private String content;
	
	public void loadContent() {
		try {
			content = loadFromFile();
		}catch (IOException e) {
			// throw new DataFileUnavailableException(e);
			e.printStackTrace();
		}
	}
	
	private String loadFromFile() throws IOException {
        return new String(Files.readAllBytes(file.toPath()));
    }

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getContent() {
		return content;
	}

	
	/**
	 *  We can fix this inter-dependency by ensuring that loadContents is called prior to getContents 
	 *  and then hiding the loadContents method outside of the class.
	 * 
	 *  using an initialization block to set the content with loadContent(), also making it private
	 *  
	 *  better approach is to put it inside the getter, to insure we don't load the file until we really need it.
	 *  Now we are beginning to get somewhere! We also don’t need to load the contents from disk everytime getContents is called, 
	 *  so we can introduce a null check to only load the contents on the first call.
	 *  
	 *  lines 40 to 42 become :
	 *  public String getContent() {
	 *  	if( content==null )
	 *  		loadContent();
	 *		return content;
	 *	}
	 *  
	 *  let's refactor with suppliers!
	 */
	
	private Supplier<String> theContent = this::loadContentRefactored;
	
	private String loadContentRefactored() {
		
		String content = "";
		try {
			
			return new String(Files.readAllBytes(file.toPath()));
			
		}catch (IOException e) {
			// throw new DataFileUnavailableException(e);
			e.printStackTrace();
		}
		
		return content;
	}
	
	public String getContents() {
		return theContent.get();
	}
	
	/**
	 *  What about the caching, well... we can use Memoization
	 */
	
	public static Supplier<String> memoizeSupplier(final Supplier<String> s) {
	   final Map<Long,String> lazy = new ConcurrentHashMap<>();
	   return () -> lazy.computeIfAbsent(1l, i-> s.get());
	}
	
	
	private Supplier<String> lazyCaching = memoizeSupplier(theContent);
	
	public String getContentLazyCaching() {
		return lazyCaching.get();
	}
	
	/**
	 * using Cyclops's Eval 
	 *  <dependency>
	 *	 	<groupId>com.oath.cyclops</groupId>
	 *	 	<artifactId>cyclops</artifactId>
	 *	 	<version>10.0.4</version>
	 *	</dependency>
	 *
	 * the code becomes :
	 * 
	 * private File file;
     * private Eval<String> contents = Eval.later(this::loadContents);
	 *
     * public String getContents(){
     *    return contents.get();
     * }
     *
     * private String loadContents(){
     *    try {
     *       return new String(Files.readAllBytes(file.toPath()));
     *    }catch(IOException e){
     *       throw new DataFileUnavailableException(e);
     *    }
     * }
	 */
	
	
	
	
	
}
