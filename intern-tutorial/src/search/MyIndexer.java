/**
 * S. Chatchawal 
 * Apr 5, 2560 BE 10:05:34 AM
 * http://www.conan.in.th
 * chatchawal.sangkeettrakarn@nectec.or.th, conan@conan.in.th
 */
package search;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.th.ThaiAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * @author conan
 *
 */
public class MyIndexer {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		try {
			new MyIndexer().index();
		} catch (IOException e) {e.printStackTrace();}
		
		System.out.println("indexer stop working"); 
	}
	
	public void index() throws IOException{
		
		 Path path = FileSystems.getDefault().getPath("index-products");
		 Directory dir = FSDirectory.open(path);
		 
		 /* set analyzer to analyze contents */
		 Analyzer analyzer=new ThaiAnalyzer();		 
		 IndexWriterConfig iwc=new IndexWriterConfig(analyzer);
		 
		 /* always replace old index */
		 iwc.setOpenMode(OpenMode.CREATE); 
		 IndexWriter writer = new IndexWriter(dir,iwc);
		 
		 /* write index here */
		 
		 Scanner sc = new Scanner(new File("files/soshi.csv"),"utf8");
		 while(sc.hasNext()){
			 String line = sc.nextLine();
			 String[] lns =  line.split("\t");
			 String title  = lns[0];
			 String time  = lns[1];
			 String url  = lns[2];
			 
			 Document doc = new Document();
			 doc.add(new Field("title", title, TextField.TYPE_STORED));
			 doc.add(new Field("time", time, StringField.TYPE_STORED));
			 doc.add(new Field("url", url, StringField.TYPE_STORED));
 
			 writer.addDocument(doc); 
		 }
		 
		 sc.close();
		 writer.close();
		

	}

}
