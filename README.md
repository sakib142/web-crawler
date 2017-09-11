# web-crawler
Java Web Crawler

## Required Softwares to build this project:
  
  1) JDK 1.8 
  2) Maven 
  
## Build Process:
  1) Check the version of JDK(java -version) and Maven(mvn -version)
  2) Now using command prompt go inside the project folder, In the folder you should check for the POM.XML file.
	 Now run the below command.
	 Example :
	 ```bash
    $ D:\java_projects\repo\web-crawler> mvn clean install
	```
	This will create a jar file inside the target folder --> crawler-0.0.1-SNAPSHOT.jar
   
  3) To execute the jar you need to open the jar using some extractor, Inside the jar go to the META-INF folder and open the MANIFEST.MF file and add the main class detail inside it, Below is the Example
	```xml
		Class-Path: .
		Main-Class: com.web.crawler.CrawlerStart
	```
	
  4) Now run the jar using this command
	```bash
	java -jar crawler-0.0.1-SNAPSHOT.jar
	```
	NOTE: If you face any issue in running the jar it means that either the MNIFEST.MF file entry is not validated or the jar is not packaged properly.
	In this case you can use some tool like - ECLIPSE to create your jar.
	Follow this link for detail
	https://help.eclipse.org/luna/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Ftasks%2Ftasks-37.htm
	
	
  5) As soon as you run this command the app will generate one json file and you will see this message in the command prompt:
	 INFO: Crawler Structured site map file location ------->D:\crawlerSiteMap.json
	 
	 Now open this json file (the contents will be mostly unstructured), To view it in the structure manner copy all the data from the JSON file and paste in some JSON editor:
	 http://jsoneditoronline.org/
	 
  6) To check the code coverage detail Go to the target\cobertura folder and open the index.html file
	 
	 D:\java_projects\repo\web-crawler\target\cobertura - index.html
  
	