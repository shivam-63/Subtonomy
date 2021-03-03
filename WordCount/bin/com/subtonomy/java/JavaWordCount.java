package com.subtonomy.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaWordCount {
	
	// this method calculates the occurenct of each word in the given file and then sort out the top 10 most common words
	public void countWords(String pathToFile) throws IOException {
		
		String str = new String(Files.readAllBytes(Paths.get(pathToFile)));
		
		List<String> list = Stream.of(str).map(w -> w.split("\\s+")).flatMap(Arrays::stream)
	            .collect(Collectors.toList());
		
		Map<String, Integer> wordCounter = list.stream()
	            .collect(Collectors.toMap(w -> w.toLowerCase(), w -> 1, Integer::sum));
		
		List<Map.Entry<String, Integer>> result = wordCounter.entrySet().stream()
		        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
		        .limit(10)
		        .collect(Collectors.toList());
		
		System.out.println("Top ten most common words in the file and their corresponding occurrences are");
		System.out.println(result);
		
	}
	

}
