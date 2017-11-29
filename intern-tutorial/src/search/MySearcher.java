/**
 * S. Chatchawal 
 * Apr 5, 2560 BE 11:27:17 AM
 * http://www.conan.in.th
 * chatchawal.sangkeettrakarn@nectec.or.th, conan@conan.in.th
 */
package search;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.th.ThaiAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParser.Operator;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

/**
 * @author conan
 *
 */
public class MySearcher {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		 Path path = FileSystems.getDefault().getPath("index-products");
		 Directory dir = FSDirectory.open(path);
		 Analyzer analyzer = new ThaiAnalyzer();
		 IndexReader r = DirectoryReader.open(dir);

		 IndexSearcher searcher = new IndexSearcher(r);

		String keyword ="002485"; //select * from users where nick like 'โคนัน'
		
		BooleanQuery bq = new BooleanQuery();
		
		QueryParser qp1 = new QueryParser("email",analyzer);
		qp1.setDefaultOperator(Operator.AND);
		Query query1 = qp1.parse(keyword);

		QueryParser qp2 = new QueryParser("fname",analyzer);
		qp2.setDefaultOperator(Operator.AND);
		Query query2 = qp2.parse(keyword);
		
		QueryParser qp3 = new QueryParser("nick",analyzer);
		qp3.setDefaultOperator(Operator.AND);
		Query query3 = qp3.parse(keyword);
		
		QueryParser qp4 = new QueryParser("eid",analyzer);
		qp4.setDefaultOperator(Operator.AND);
		Query query4 = qp4.parse(keyword);
		
		QueryParser qp5 = new QueryParser("tel",analyzer);
	//	qp5.setDefaultOperator(Operator.AND);
		Query query5 = qp5.parse(keyword);

		bq.add(query1,Occur.SHOULD);
		bq.add(query2,Occur.SHOULD);
		bq.add(query3,Occur.SHOULD);
		bq.add(query4,Occur.SHOULD);
		bq.add(query5,Occur.SHOULD);
		
		 
		TopDocs tops = searcher.search(bq, 5);
		ScoreDoc[] sd = tops.scoreDocs;

		for(ScoreDoc s : sd){
			Document d = searcher.doc(s.doc);
			String name = d.get("fname");
			String nick = d.get("nick");
			String email = d.get("email");
			String tel = d.get("tel");
			System.out.println(name+" "+nick+" "+email+" "+tel);
		}
		
		r.close();

	}

}
