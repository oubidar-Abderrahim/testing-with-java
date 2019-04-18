package medium.dysfunctional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import cyclops.control.Eval;
import cyclops.data.LazySeq;
import cyclops.reactive.ReactiveSeq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Wither;

// Third
@AllArgsConstructor
@Getter
@Wither
class Metadata {
	// this is what our code look like right now
	
	private final long customerId;
	private final String type;
	private final File file;
	private final Eval<String> contents = Eval.later(this::loadContents);
	
	public String getContents(){
		return contents.get();
	}
	
	//this was created instead of replacing the above getter so that i don't break code
	public Eval<String> getContent(){
		return contents;
	}
	
	private String loadContents(){
		
		try {
			return new String(Files.readAllBytes(file.toPath()));
		}catch(IOException e){
			//throw new DataFileException(e);
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * It is entirely immutable and the contents are lazily loaded from disk only when the getContents method is first called. 
	 * Contents, once loaded are cached, so we only load from disk once.
	 */
	
}
/**
 * Our application should be able to load the raw text data on file and transform it into domain specific entity Objects. 
 * The entities we wish to transform our file contents into look like this :
 * 
 */

@AllArgsConstructor
@Getter
@Wither
class Data {

    private final long customerId;
    private final List<Record> contents;

}

@AllArgsConstructor
@Getter
@Wither
class Record{

    private final long recordId;
    private final String record;

}


class BeforeComposition {
	
	public List<Data> loadData(List<Metadata> fileInfo){
		
		List<Data> result = new ArrayList<>();
		
		for(Metadata metadata: fileInfo) {
			List<String> contentLines = Arrays.asList(metadata.getContents().split(","));
			List<Record> records = new ArrayList<>();
			for(String recordStr : contentLines) {
				String[] idAndData = recordStr.split(":");
				long recordId = Long.valueOf(idAndData[0]);
				Record record = new Record(recordId, idAndData[1]);
				records.add(record);
			}
			result.add(new Data(metadata.getCustomerId(), records));
		}
		return result;
		
	}
	
	/**
	 * Nested for loops ! Let’s be thankful there are no indexes or conditional branches in there. — yet
	 * Mixed abstraction levels !! it is starting to get hard to follow and discern exactly what the business logic is
	 * Can we parallelize our I/O? the shared mutable state in our imperative algorithm makes parallelization difficult.
	 * How about sequential performance? we are loading all of the files from disk into memory inside the loadData method, 
	 * 	regardless of whether we will actually process them all.
	 */
}

class CompositionStep1 {
	
	/**
	 * Step 1: Replace the existing control structures with Java 8 Streams.
	 * 
	 */
	
	public List<Data> loadData(List<Metadata> fileInfo){
			
			return fileInfo.stream().flatMap(metadata -> Stream.of(metadata.getContents())
														.map(contents -> Arrays.stream(contents.split(",")))
														.map(contentLines -> contentLines.map(recordStr -> recordStr.split(":"))
																						.map(idAndData -> new Record(Long.valueOf(idAndData[0]), idAndData[1]))
																						.collect(Collectors.toList()))
														.map(record -> new Data(metadata.getCustomerId(), record))).collect(Collectors.toList());

	}
	
	/**
	 * How did we score against the problems listed above?
	 *
	 * Nested for loops ✅ [removed]
	 * Mixed abstraction levels 🛑 [still there]
	 * Parallelization of I/O tasks am ✅ [better]
	 * Lazy loading of data 🛑 [nope]
	 * 
	 */
	
}

class CompositionStep2 {
	
	/**
	 * Step 2 : factor out some of the lower level tasks into their own Functions that we can reference in our flow. 
	 */
	
	private Stream<String> splitContentByLine(String contents) {
		
		return Arrays.stream(contents.split(","));
	}
	
	private List<Record> buildRecords(String contentLines) {
		return splitContentByLine(contentLines).map(recordStr -> recordStr.split(":"))
							.map(idAndData -> new Record(Long.valueOf(idAndData[0]), idAndData[1]))
							.collect(Collectors.toList());
	}
	
	public List<Data> loadData(List<Metadata> fileInfo){
		return fileInfo.stream().flatMap(metaData -> Stream.of(metaData.getContents())
															.map(this::buildRecords)
															.map(record -> new Data(metaData.getCustomerId(), record)))
								.collect(Collectors.toList());
								
	}
	
	/**
	 * We can do better than this (clean up the boilerplate), and attempting to solve the remaining two design issues
	 *  with the original method will improve it further still.
	 *  
	 *  Let’s make it lazy!
	 *  We can start by refactoring the method signature of loadData. Cyclops has two data types that look like potential candidates for use, 
	 *  LazySeq and ReactiveSeq.
	 *  
	 *  LazySeq is a lazy LinkedList, it’s very similar to Stream in Scala or Vavr 
	 *  (with the exception that LazySeq in Cyclops in truly fully lazy), or List in Haskell.
	 *  
	 *  ReactiveSeq represents a dataflow. The dataflow can be iterative (that is data can be pulled along it), 
	 *  or it can be reactive (data can be pushed along it asynchronously) following the reactive-streams spec 
	 *  with backpressure or in an Observable-style fashion with no backpressure applied, the flow can be sequential or parallel. 
	 *  Implementations are provided that integrate Project Reactor and RxJava 2 — but a native reactive-streams implementation 
	 *  is bundled in the core library.
	 *  
	 *  Given that we are doing I/O operations that we may like to parallelize inside the loadData method, 
	 *  the more powerful ReactiveSeq type is a better fit in this case.
	 */
	
}

@AllArgsConstructor
@Getter
@Wither
class LazyData {

    private final long customerId;
    private final LazySeq<LazyRecord> contents;

}

@AllArgsConstructor
@Getter
@Wither
class LazyRecord{

    private final long recordId;
    private final String record;

}

class compositionLazy {
	
	private LazySeq<String> splitContentsByLine(String contents){
	    return LazySeq.of(contents.split(","));
	}

	private LazySeq<LazyRecord> buildRecords(String contentLines) {
	     return splitContentsByLine(contentLines)
	                        .map(recordStr -> recordStr.split(":"))
	                        .map(idAndData -> new LazyRecord(Long.valueOf(idAndData[0]), idAndData[1]))
	                        .lazySeq();
	}
	
	/**
	 * Now that we are processing everything lazily, we could perhaps consider returning not the raw eagerly evaluated 
	 * String for our contents in DataFileMetadata but rather the lazy reference to Eval — which we can chain together 
	 * inside our functionally composed data flows more cleanly.
	 * 
	 * This refactoring actually involves removing the override to the Lombok provided getContents() method. By removing it Lombok will automatically 
	 * create a getter for us that return the Eval reference.
	 */
	
	public ReactiveSeq<LazyData> loadData(ReactiveSeq<Metadata> fileInfo){
		return fileInfo.concatMap(metadata -> metadata.getContent().map(this::buildRecords).map(record -> new LazyData(metadata.getCustomerId(), record)));
	}
}

