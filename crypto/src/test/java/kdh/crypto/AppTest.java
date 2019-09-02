package kdh.crypto;

import java.util.Base64.Encoder;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.jce.provider.JCEMac.SHA256;

import java.util.Base64;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import kdh.crypto.util.PasswordUtil;
import kdh.crypto.util.Base64Helper;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
        System.out.println(PasswordUtil.encryptPassword("{SHA256}", "test"));
        String p = "plainText";
        String ep = Base64Helper.encode(p.getBytes());
        System.out.println(ep);
        Encoder ec = Base64.getEncoder();
        String ecp = ec.encodeToString(p.getBytes());
        System.out.println(ecp);
        
   
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
