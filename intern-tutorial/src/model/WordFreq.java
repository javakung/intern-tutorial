/**
 * S. Chatchawal 
 * Apr 26, 2558 BE 2:16:50 PM
 * http://www.conan.in.th
 * chatchawal.sangkeettrakarn@nectec.or.th, conan@conan.in.th
 */
package model;

/**
 * @author conan
 *
 */
public class WordFreq implements Comparable<WordFreq> {


	public String word;
	public int freq;
	
	
	public WordFreq(){
		
	}

	
	public WordFreq(String word,int freq){
		setWord(word);
		setFreq(freq);
	}
	
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public int compareTo(WordFreq o) {
		// TODO Auto-generated method stub
		return o.getFreq()-this.getFreq();
	}

}
