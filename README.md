Web-Search-Engine
A search engine is developed using Java which will search words in html files, frequency of the word in a file and 
giving word suggestion if the word is misspelled. 

Project feature : 
-> Web Crawler : Implemented using java Jsoup library and java regex
-> HTML to text file conversion : Implemented in Java using Jsoup library
-> Word Search: Implemented in java using Ternary Search Trie
-> Page Ranking : Implemented using java HashMap
-> Word suggestion : Implemented in java using edit distance algorithm

Project Details : 
-> External Library : Jsoup
-> Imported Packages : Text Processing, Edit distance
-> Text Files: website.txt
-> Folders: textfiles, webFiles
-> Java package : scr/websearchengine
					-> Crawler.java
					-> EditDistance.java
					-> ParseURL.java
					-> SearchEngine.java
					->SuggestAltWord.java



Search engine flow of execution

1. A web crawler is used to search the internet and get more than 100 URLs in a recursive manner.
2. JSoup lirary is used to parse each URL into a text file.
3. The Java String Tokenizer is used to transform a string to a token.
4. Java Regex is used to remove invalid characters
5. A HashMap is used to index all URLs.
6. For each text file, a TST is constructed, and the frequency of keywords is extracted.
7. HashMap is sroted in decreasing order to find the ranking of the pages.
8. Edit Distance algorithm is used to suggest alternative similar words in case a word is not found in files
