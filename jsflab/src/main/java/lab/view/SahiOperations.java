package lab.view;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

import in.co.sahi.distributed.DSahiRunner;
import in.co.sahi.distributed.SuiteInfo;
import net.sf.sahi.test.TestRunner;

public class SahiOperations {

	public static void main(String[] args) throws UnknownHostException, UnsupportedEncodingException {

		SahiOperations so = new SahiOperations();

		String threads = "2";
		String suitePath = "C:\\Users\\Volkan TOLU\\Desktop\\"; // static
		String suiteName = "integration.sah";
		String browserType = "chrome";
		String base = "http://sahi.co.in/demo/training/"; // static
		HashMap<String, Object> variableHashMap = new HashMap<>();
		variableHashMap.put("$user", "test");
		variableHashMap.put("$pwd", "secret");

		String status = so.sahiScriptLauncher(threads, suitePath + suiteName, browserType, base, variableHashMap);

		System.out.println(status);
	}

	public String sahiScriptLauncher(String threads, String suiteName, String browserType, String base,
			HashMap<String, Object> variableHashMap) throws UnknownHostException, UnsupportedEncodingException {

		String status = null;

	//	for (int i = 0; i < browserTypes.length; i++) {

//			 TestRunner testRunner = new TestRunner(suiteName, browserType, base, threads);
//			 testRunner.setInitJS(variableHashMap);
//			 status = testRunner.execute();

			 SuiteInfo suiteInfo = new SuiteInfo();
			 
			 suiteInfo.setIsNonDistributedRunS("false");
			 suiteInfo.setScriptsPathMaster("C:\\Users\\Volkan TOLU\\Desktop\\");
			 suiteInfo.setSuiteName("integration.sah");
			 suiteInfo.setBrowserType("chrome");
			 suiteInfo.setBaseURL("http://sahi.co.in/demo/training/");
			 suiteInfo.setNodes("localhost");
			 
			 suiteInfo.setHost("localhost");
			 suiteInfo.setPort("9999");
			 
			 suiteInfo.setThreads("5");
			 
			 DSahiRunner dSahiRunner = new DSahiRunner(suiteInfo);			 
			 dSahiRunner.execute();
			 
			 System.out.println(dSahiRunner.getStatus()); 
			 
			 //dSahiRunner.startDistributedRun();
			 
			 //System.out.println(InetAddress.getLocalHost().getHostAddress());

		//}

		return status;
	}

}