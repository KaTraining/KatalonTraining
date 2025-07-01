package continentCustom

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static org.assertj.core.api.Assertions.*
import internal.GlobalVariable

public class validateExcelDate {

	@Keyword
	def excelData(def responseJSON,def conName) {
		def found = false
		for (continent in responseJSON) {
			if (continent.name == conName ) {
				println "continent found"
				found = true
			}
		}
		if (!found) {
			println "${conName} not present"
		}
	}

	@Keyword
	def schemaValidation(def response,def schema) {
		boolean successful = WS.validateJsonAgainstSchema(response,schema)
		assert successful == true
	}

	@Keyword
	def verifyStatusCode(def response) {
		WS.verifyResponseStatusCode(response, 200)
		assert response.getStatusCode() == 200
		assertThat(response.getStatusCode()).isIn(Arrays.asList(200, 201, 202))
	}

	@Keyword
	def findOceans(def conName) {
		def data = findTestData("ContinentsData")
		def found = false
		for(def row=1;row<=data.getRowNumbers();row++) {
			if(data.getValue("name", row) == conName) {
				println data.getValue("oceans", row)
				found = true
			}
		}

		if (!found) {
			println "${conName} not present"
		}
	}
	
	@Keyword
	def oceanCount() {
		def data = findTestData("ContinentsData")
		
	}
}
