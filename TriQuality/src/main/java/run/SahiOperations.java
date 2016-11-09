package run;

import java.util.HashMap;

import net.sf.sahi.test.TestRunner;

public class SahiOperations {



	public String sahiScriptLauncher(String threads, String suiteName, String browserType, String base,
			HashMap<String, Object> variableHashMap) {

		String status = null;

	//	for (int i = 0; i < browserTypes.length; i++) {

			 TestRunner testRunner = new TestRunner(suiteName, browserType, base, threads);
			 testRunner.setInitJS(variableHashMap);
			 status = testRunner.execute();

		//}

		return status;
	}

}