<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>Continents</name>
   <tag></tag>
   <elementGuidId>930370b9-38ba-490f-92e2-3d0f7dd23163</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>true</autoUpdateContent>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <katalonVersion>10.2.2</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path></path>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>https://dummy-json.mock.beeceptor.com/continents</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()


WS.verifyResponseStatusCode(response, 200)

assertThat(response.getStatusCode()).isEqualTo(200)






String jsonPass =
&quot;&quot;&quot;
{
  &quot;\$id&quot;: &quot;https://example.com/person.schema.json&quot;,
  &quot;\$schema&quot;: &quot;https://json-schema.org/draft/2020-12/schema&quot;,
  &quot;title&quot;: &quot;Person&quot;,
  &quot;type&quot;: &quot;object&quot;,
  &quot;properties&quot;: {
    &quot;firstName&quot;: {
      &quot;type&quot;: &quot;string&quot;,
      &quot;description&quot;: &quot;The person's first name.&quot;
    },
    &quot;lastName&quot;: {
      &quot;type&quot;: &quot;string&quot;,
      &quot;description&quot;: &quot;The person's last name.&quot;
    },
    &quot;age&quot;: {
      &quot;description&quot;: &quot;Age in years which must be equal to or greater than zero.&quot;,
      &quot;type&quot;: &quot;integer&quot;,
      &quot;minimum&quot;: 0
    }
  }
}
&quot;&quot;&quot;
boolean successful = WS.validateJsonAgainstSchema(response,jsonPass)


assertThat(response.getStatusCode()).isIn(Arrays.asList(200, 201, 202))




</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
