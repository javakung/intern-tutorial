/**
 * S. Chatchawal 
 * Apr 9, 2560 BE 5:09:46 PM
 * http://www.conan.in.th
 * chatchawal.sangkeettrakarn@nectec.or.th, conan@conan.in.th
 */
package webparser;

import java.net.URLDecoder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import interfaces.AbdulAnswer;

/**
 * @author conan
 *
 */
public class InvestNews implements AbdulAnswer {

	/* (non-Javadoc)
	 * @see interfaces.AbdulAnswer#getAnswer(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String getAnswer(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		String url = "http://www.settrade.com/C08_News_Index.jsp";

		try{
		Document doc = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.6; rv:5.0) Gecko/20100101 Firefox/5.0")
				.timeout(15000).get();
		
		Elements ps = doc.select("a[class=greenlink]");
		String text = "";
		int count=0;
		for(Element p : ps){
			String title = p.text();
			String durl = p.absUrl("href");
			durl = durl.substring(durl.indexOf("&link=")+6, durl.length()-1);
			try{
				durl = URLDecoder.decode(durl,"utf8");
			}catch(Exception er){continue;}
			text+=title+"\n"+durl+"\n\n";
			if(count++>10)break;
		}
		

		
		return text;
		
		}catch(Exception er){er.printStackTrace();}
		return "";
	
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String res = new InvestNews().getAnswer("", "", "");
		System.out.println(res);
	}

}
