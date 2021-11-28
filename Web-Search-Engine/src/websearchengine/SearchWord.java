package websearchengine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import textprocessing.In;
import textprocessing.TST;

public class SearchWord {
	
	 public static HashMap<String,String> fileMapper()
	{
		int i = 0;
		HashMap<String,String> UrlIndex = new HashMap<String,String>();	
		In in = new In("website.txt");
		
		   while (!in.isEmpty()) 
	        {
	        	
	        	String text = in.readLine();
	        	String testSplit[] = text.split(":::");
	        	UrlIndex.put(testSplit[0], testSplit[1]);
	        }
		   return UrlIndex;
	}
	
	public static TST<Integer> getTST(String finalPath) 
	{	
		int j = 0;
		TST<Integer> tst = new TST<Integer>();	
		In in = new In(finalPath);
		
        while (!in.isEmpty()) 
        {
        	String text = in.readLine();
	         StringTokenizer st  = new StringTokenizer(text, " ");
	        	while (st.hasMoreTokens()) { 
	    			
	    			String word = st.nextToken();
	    			word = word.toLowerCase();
	    			//System.out.println(word);
	    			
	        		if(tst.contains(word)) {
	        			
	        			tst.put(word, tst.get(word)+1);
	        			//System.out.println("true");
	        			
	        		} else {
	        			
	        			tst.put(word, 1);
	        		}
	            }
	        }	

        
        System.out.println(finalPath);
        for (String key : tst.keys()) {
            System.out.println(key + " " + tst.get(key));
        }
        
		return tst;
	}

	
public static HashMap<String, Integer> getFreqList(String[] keyWords){
		
		
		//Map each text file to its corresponding number into an arraylist
		ArrayList<String> textList = new ArrayList<>();
		HashMap<String,Integer > freqList = new HashMap<String, Integer>();
	//	HashMap<String,String> mapper = fileMapper();
	       
		File folder = new File("textfiles"); 
        File[] files = folder.listFiles();
 
        for (int i = 0 ; i < files.length ; i++) {
        	
	        String filePath = "textfiles/";
	        String fileName = files[i].getName();
	        String finalPath = filePath+fileName;
	        
	        //System.out.println(finalPath);
	        
//	        String tempFileIndex = fileName.substring(0, (fileName.length()-4));
//        	int fileIndex = Integer.parseInt(tempFileIndex);
			//System.out.println(fileIndex); 
			
	        TST<Integer> tst = new TST<Integer>();
	        tst = getTST(finalPath);
	        //System.out.println(tst);
	        
//	        for (String key : tst.keys()) {
//	        	System.out.println(key + " " + tst.get(key));
//	        }
	        
	        int counter = 0;
	        
	        for (String str: keyWords) {	        	
	        	if (tst.contains(str)){
	
	        		int count = tst.get(str);
	        		//System.out.println(str+" "+count);
	        		counter = counter + count;        		
	        	}
	        }
	       
	        freqList.put(fileName, counter);
        }  
        
        //System.out.println(freqList);
		return freqList;
	}


	public static void main(String[] args) throws IOException {
		
		crawler.runCrawler();
		HashMap<String,String> hash = fileMapper();
		String str[] = {"python"};
		HashMap<String,Integer> freq = getFreqList(str);
		System.out.println("---------------Output-------------------");
		for( Entry<String, Integer> entry : freq.entrySet() ){
		    System.out.println( entry.getKey() + " => " + entry.getValue() );
		
		

	}

}
}
