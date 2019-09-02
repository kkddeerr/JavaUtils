package matcherTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

class Mathcertest extends TestCase {
	
	List<Pattern> pList = new ArrayList<Pattern>();
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Test init");
		Pattern pNumber = Pattern.compile("(^[0-9]*$)");
		Pattern pEngLet = Pattern.compile("^[a-zA-Z]*$");
		Pattern pKorLet = Pattern.compile("^[°¡-ÆR]*$");
		Pattern pEngNum = Pattern.compile("^[a-zA-Z0-9]*$");
		Pattern pKorNum = Pattern.compile("^[°¡-ÆR0-9]*$");
		Pattern pEmail = Pattern.compile("^[a-zA-Z0-9]+@[a-zA-Z0-9]+$");
		Pattern pPhone = Pattern.compile("^01(?:0|1|[6-9]) - (?:\\d{3}|\\d{4}) - \\d{4}$\r\n");
		Pattern pGephone = Pattern.compile("^\\d{2,3} - \\d{3,4} - \\d{4}$");
		Pattern pJumin = Pattern.compile("\\d{6} \\- [1-4]\\d{6}");
		Pattern pIpAddr = Pattern.compile("([0-9]{1,3}) \\. ([0-9]{1,3}) \\. ([0-9]{1,3}) \\. ([0-9]{1,3})");
		pList.add(pNumber);
		pList.add(pEngLet);
		pList.add(pKorLet);
		pList.add(pEmail);
		pList.add(pEngNum);
		pList.add(pKorNum);
		pList.add(pPhone);
		pList.add(pGephone);
		pList.add(pJumin);
		pList.add(pIpAddr);
		
	}
	

	@Test
	void test() throws Exception {
		setUp();
		System.out.println("test start");
		String pTestStr = "01067482791";
		Iterator<Pattern> e= pList.iterator();
		int count=0;
		while(e.hasNext()) {
			Pattern p = e.next();
			Matcher m = p.matcher(pTestStr);
			boolean t = m.find();
			
			if(t) {
				count++;
				System.out.println(m.toString());
			}
			
		}
		assertEquals(3, count);
		
		
	}
	
	@Test
	void test2() throws Exception {
		basicDTO dto = new basicDTO();
		dto.setName("");
		
		String a = dto.getName();
		System.out.println("param" + a);
		
		
	}

}
