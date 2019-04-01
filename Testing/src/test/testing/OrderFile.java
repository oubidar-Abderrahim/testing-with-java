package test.testing;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class OrderFile {

	public static void orderFileLinesAsStream(String fileName, boolean sorted) {

		try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
			if (sorted) {
				lines.sorted().forEach(System.out::println);
			} else {
				lines.forEach(System.out::println);
			}
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public static <T> Stream<T> interleave(Stream<T> a, Stream<T> b) {
		Spliterator<T> spA = a.spliterator(), spB = b.spliterator();
		long s = spA.estimateSize() + spB.estimateSize();
		
		if (s < 0)
			s = Long.MAX_VALUE;
		
		int ch = spA.characteristics() & spB.characteristics() & (Spliterator.NONNULL | Spliterator.SIZED);
		ch |= Spliterator.ORDERED;

		return StreamSupport.stream(new Spliterators.AbstractSpliterator<T>(s, ch) {
			Spliterator<T> sp1 = spA, sp2 = spB;

			@Override
			public boolean tryAdvance(Consumer<? super T> action) {
				Spliterator<T> sp = sp1;
				if (sp.tryAdvance(action)) {
					sp1 = sp2;
					sp2 = sp;
					return true;
				}
				return sp2.tryAdvance(action);
			}
		}, false);
	}

	private static int counter = 0;
	
	public static void concatFileLinesAsStream(String firstFileName, String secondFileName) {
		
		/*
		 * It retains the characteristics of the input streams as far as possible, which allows certain optimizations (e.g. for count()and toArray()). 
		 * Further, it adds the ORDERED even when the input streams might be unordered, to reflect the interleaving.
		 * When one stream has more elements than the other, the remaining elements will appear at the end.
		 */

		try (Stream<String> firstLines = Files.lines(Paths.get(firstFileName));
				Stream<String> secondLines = Files.lines(Paths.get(secondFileName))) {

			Stream<String> interleavedLines = interleave(firstLines, secondLines);
			
			interleavedLines.forEach(line -> {
				if(counter%2==0) {
					System.out.print((String)line.replaceAll(" ", ""));
				} else {
					System.out.println(line);
				}
				counter++;
			});

		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public static void main(String[] args) {

		// orderFileLinesAsStream("resources/testsubjects/teste.properties", false);
		concatFileLinesAsStream("resources/testsubjects/teste.properties", "resources/testsubjects/teste2.properties");
	}
}
