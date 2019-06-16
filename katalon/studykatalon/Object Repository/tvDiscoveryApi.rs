<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>tvDiscoveryApi</name>
   <tag></tag>
   <elementGuidId>559d3874-c54d-41b6-a114-30b46bb2fbce</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <useRalativeImagePath>false</useRalativeImagePath>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\&quot;CommandDetails\&quot;:{\&quot;TVDiscoveryParameters\&quot;:{\&quot;PowerStatus\&quot;:\&quot;On\&quot;,\&quot;TVIPAddress\&quot;:\&quot;192.168.0.33\&quot;,\&quot;TVMACAddress\&quot;:\&quot;1C:5A:6B:BC:65:65\&quot;,\&quot;TVModelNumber\&quot;:\&quot;32HFL5011T/12\&quot;,\&quot;TVRoomID\&quot;:\&quot;11111\&quot;,\&quot;TVSerialNumber\&quot;:\&quot;12345678901234\&quot;,\&quot;VSecureTVID\&quot;:\&quot;800000000000000F4FC7\&quot;},\&quot;WebServiceParameters\&quot;:{\&quot;PollingFrequency\&quot;:10,\&quot;TVUniqueID\&quot;:\&quot;123456789012341C5A6BBC6565\&quot;}},\&quot;CmdType\&quot;:\&quot;Response\&quot;,\&quot;Cookie\&quot;:293,\&quot;Fun\&quot;:\&quot;TVDiscoveryService\&quot;,\&quot;Svc\&quot;:\&quot;WebServices\&quot;,\&quot;SvcVer\&quot;:\&quot;3.0\&quot;}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Accept-Charset</name>
      <type>Main</type>
      <value>utf-8</value>
   </httpHeaderProperties>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Accept-Encoding</name>
      <type>Main</type>
      <value>utf-8</value>
   </httpHeaderProperties>
   <migratedVersion>5.4.1</migratedVersion>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>http://localhost:8080/SmartInstall/webservices.jsp?=</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceFunction></soapServiceFunction>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()
</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
