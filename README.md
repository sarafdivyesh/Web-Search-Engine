Web-Search-Engine

A search engine is developed using Java which will search words in html files, frequency of the word in a file and 
giving word suggestion if the word is misspelled. 

Project feature : 
1. Web Crawler : Implemented using java Jsoup library and java regex
2. HTML to text file conversion : Implemented in Java using Jsoup library
3. Word Search: Implemented in java using Ternary Search Trie
4. Page Ranking : Implemented using java HashMap
5. Word suggestion : Implemented in java using edit distance algorithm

Project Details : 
1. External Library : Jsoup
2. Imported Packages : Text Processing, Edit distance
3. Text Files: website.txt
4. Folders: textfiles, webFiles
5. Java package : scr/websearchengine
		    a. Crawler.java
         	    b. EditDistance.java
		    c. ParseURL.java
		    d. SearchEngine.java
		    e. SuggestAltWord.java



Search engine flow of execution

1. A web crawler is used to search the internet and get more than 100 URLs in a recursive manner.
2. JSoup lirary is used to parse each URL into a text file.
3. The Java String Tokenizer is used to transform a string to a token.
4. Java Regex is used to remove invalid characters
5. A HashMap is used to index all URLs.
6. For each text file, a TST is constructed, and the frequency of keywords is extracted.
7. HashMap is sroted in decreasing order to find the ranking of the pages.
8. Edit Distance algorithm is used to suggest alternative similar words in case a word is not found in files


