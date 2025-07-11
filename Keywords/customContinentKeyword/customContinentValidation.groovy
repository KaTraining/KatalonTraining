package customContinentKeyword

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

import internal.GlobalVariable

public class customContinentValidation {

	@Keyword
	def schemaValidation(def response) {
		WS.validateJsonAgainstSchema(response, 'customSchema.txt')
	}

	//fetching developed countries from response
	@Keyword
	def findDevelopedCountries(def responseJson) {
		for (continent in responseJson.data.continent) {
			println "Developed countries in ${continent.name}"
			for(countries in continent.countries) {
				if(countries.developed == true)
					println countries.name
			}
		}
	}
	//validating countries in  with response response
	@Keyword
	def findNonDevelopedCountries(def responseJson) {
		def countriesData = findTestData('DevelopedCountries')
		(1..countriesData.getRowNumbers()).each{ row ->
			for(countryResp in responseJson.data.continent.countries) {
				(countryResp.developed).each{dev ->

					if(dev == false && countriesData.getValue('Developed', row) == 'false' && !(countryResp.name.contains(countriesData.getValue('Country', row))))
						println "${countriesData.getValue('Country', row)} from ${countriesData.getValue('Continent', row)} is not present in response"
				}
			}
		}
		println "----------Results after each---------------"
		for(def row =1;row<=countriesData.getRowNumbers();row++) {
			for(countryResp in responseJson.data.continent.countries) {
				for(dev in countryResp.developed) {
					if(dev == false && countriesData.getValue('Developed', row) == 'false' && !(countryResp.name.contains(countriesData.getValue('Country', row))))
						println "${countriesData.getValue('Country', row)} from ${countriesData.getValue('Continent', row)} is not present in response"
				}
			}
		}
	}
	//finding all countries with english language
	@Keyword
	def findLanguageCountires(def responseJson) {
		(responseJson.data.continent).each { continent ->
			(continent.countries).each { country ->
				for(language in country.languages) {
					if(language == "English")
						println country.name
					}
				}
		}
		
	}
}
