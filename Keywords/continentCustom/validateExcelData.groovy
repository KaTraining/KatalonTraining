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

public class validateExcelData {

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
	def oceanCount(def response) {
		def data = findTestData("ContinentsData")
		for(def row =1;row<=data.getRowNumbers();row++) {
			def excelOceanCount = (data.getValue("oceans", row)).contains(',')?data.getValue("oceans", row).split(',').collect { it.trim() }:data.getValue("oceans", row).collect { it.trim() }
			def responseVal = response.oceans[row-1]
			def responseOceanCount = (response.size() == 1 && response.contains(','))?responseVal.split(',').collect { it.trim() }:responseVal
			println "Oceans in response for continent ${response.name[row-1]}"
			println response.oceans[row-1]
			if (excelOceanCount.size() == responseOceanCount.size())
				println "${response.name[row-1]} has ${data.getValue("oceans", row)}"
			else {
				println "${response.name[row-1]} has mismatch"
			}
		}
	}

	//finding continents that have area greater than continent
	@Keyword
	def areaComparison(def responseJson) {
		for(def row=1;row<=findTestData("ContinentsData").getRowNumbers();row++) {
			for(response in responseJson) {
				if((findTestData("ContinentsData").getValue("areaSqKm",row)).toInteger() > (response.areaSqKm))
					print response.name + " "
			}
			println ""
		}
	}

	//finding continents that share oceans
	@Keyword
	def findOceanContinent(def responseJson) {
		for(def continent in responseJson) {
			def oceanVal = continent.oceans[0]
			def ocean = (oceanVal.contains(','))?oceanVal.split(',')[0]:oceanVal
			println "Continents sharing '${ocean}' with ${continent.name}:"
			for(def row=1;row<=findTestData("ContinentsData").getRowNumbers();row++) {
				if( (findTestData("ContinentsData").getValue("oceans", row)).contains(ocean) && findTestData("ContinentsData").getValue("name", row) != continent.name){
					println findTestData("ContinentsData").getValue("name", row)
				}
			}
		}
	}
}
