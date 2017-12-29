/**
 * S. Chatchawal 
 * Mar 6, 2560 BE 12:25:13 AM
 * http://www.conan.in.th
 * chatchawal.sangkeettrakarn@nectec.or.th, conan@conan.in.th
 */
package webparser;

import java.text.SimpleDateFormat;
import java.util.Random;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * @author conan
 *
 */
public class JobDBCollector extends Thread{
	
	public static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:5.0) Gecko/20100101 Firefox/5.0";
	String durl = "";
	MongoCollection<org.bson.Document> coll;

	public JobDBCollector(MongoCollection<org.bson.Document> coll,String durl){
		this.durl = durl;
		this.coll = coll;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MongoClient mongoClient = new MongoClient("localhost");
		MongoDatabase db = mongoClient.getDatabase("jobs");
		MongoCollection<org.bson.Document> coll = db.getCollection("job");	
		
		int count = 30;
		try{
			count = Integer.parseInt(args[0]);
		}catch(Exception er){}
		
		for(int i=0;i<count;i++){
		String url = "http://th.jobsdb.com/TH/th/Search/FindJobs?AD=30&Blind=1&Host=J&JSRV=1"
				+ "&page="+i;
		
		try {
			Document doc = Jsoup.connect(url).userAgent(USER_AGENT).timeout(15000).get();
			Elements es = doc.select("div[id=JobListingSection] a");
			for(Element e :es){
				String href = e.absUrl("href");
				if(href.equals(""))continue;
				if(!href.contains("th.jobsdb.com/th/th/job/"))continue;
				new JobDBCollector(coll,href).start();
			}
		
			Thread.sleep(1000);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mongoClient.close();
	}
	
	public void run(){
		try{

		Document doc = Jsoup.connect(durl).userAgent(USER_AGENT).timeout(15000).get();
		String title = doc.select("h1").get(0).text();
		String dpost = doc.select("p[class=data-timestamp]").get(0).text();
		Elements es = doc.select("div[itemprop=responsibilities] ul");
		
		String desc = "";
		for(Element e :es){
			String text = e.text();
			desc+=text+" ";

		}

		org.bson.Document dd = new org.bson.Document();
		dd.append("title", title);
		dd.append("desc", desc);
		dd.append("url", durl);
		dd.append("dpost", new SimpleDateFormat("dd-MMM-yy").parse(dpost));

		
		try{
			coll.insertOne(dd);
			int x = new Random().nextInt(3);
			Thread.sleep(x*1000);
		}catch(Exception er){}
		
		}catch(Exception er){er.printStackTrace();}
	}
	

}
