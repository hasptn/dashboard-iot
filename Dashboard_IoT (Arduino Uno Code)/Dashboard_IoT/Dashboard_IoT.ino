#include <ESP8266WiFi.h>
#include <FirebaseArduino.h>
#include "DHT.h"

#define DHTPIN 5
#define DHTTYPE DHT11
DHT dht(DHTPIN, DHTTYPE); 

#define FIREBASE_HOST "dashboard-iot-cd3ab-default-rtdb.firebaseio.com"
#define FIREBASE_AUTH "ABS5YDC2r529hboW38L5YrYEtJ6tovIuLE11vgCB"

#define WIFI_SSID "TDN200"
#define WIFI_PASSWORD "isptdn200@@"

void setup() {
 Serial.begin (9600);
 WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
 Serial.print("Connecting...");
 Serial.println("Humidity and temperature\n\n");
 delay(500);
 
 while(WiFi.status() != WL_CONNECTED){
  Serial.print(".");
  delay(250); 
  }
 Serial.println();
 Serial.print("Connected!");
 Serial.println(WiFi.localIP());

 Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);

}

void loop() {
 int LDR = analogRead(0); 
 Serial.println(LDR);
 int t = dht.readTemperature();

 Firebase.setInt("value", LDR);
 Firebase.setInt("value2", t);

 if(Firebase.failed()){
  Serial.println("Gagal kirim data!");
  Serial.println(Firebase.error());
  return;
  }

  Serial.println("Sukses!");
  Serial.print("LDR Firebase : ");
  Serial.println(Firebase.getInt("value"));
  Serial.print("Temperature = ");
  Serial.print(t); 
  Serial.println("C  ");
  Serial.println(Firebase.getInt("value2"));
  
  delay(250);
}
