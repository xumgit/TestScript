<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>deleteDataAPI</name>
   <tag></tag>
   <elementGuidId>f795e637-eed4-478e-968f-dbfef1eb49ef</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <httpBody></httpBody>
   <httpBodyContent></httpBodyContent>
   <httpBodyType></httpBodyType>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>GET</restRequestMethod>
   <restUrl>http://localhost:8080/angularjs/deletedata?id=${testId}</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <variables>
      <defaultValue>4</defaultValue>
      <description>test id</description>
      <id>88166c98-98a6-44e4-ade0-94359a5b2921</id>
      <masked>false</masked>
      <name>testId</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()

//println('restUrl:' + request.getRestUrl())
//println('requestMethod:' + request.getRestRequestMethod())
//println('request parameter list:' + request.getRestParameters())

//println('response status code:' + response.getStatusCode())

</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
