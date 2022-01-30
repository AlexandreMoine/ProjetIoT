/*Capteur gaz sur A3-A2
 * Led sur A0-A1
 * Speaker sur D2-D3
 * Ventilateur sur D4-D5
 */

#include <Arduino.h>
#include <ChainableLED.h>
#include <WiFi.h>
#include <WebServer.h>
#include <ArduinoJson.h>
#include <FreeRTOS.h>

const char *SSID = "HuaweiP30Quentin";
//const char *SSID = "Linksys_RM";
const char *PWD = "quentinphone";
//const char *PWD = "VictorRatanaestnele291203.";


WebServer server(80);

#define ledPin 13
#define NUM_LEDS 1
#define SPEAKER 14
#define VENTI 15
#define GazSensor 34

ChainableLED leds(26,25, NUM_LEDS); //defines the pin used on arduino.

int BassTab[]={1911,1702};
float seuil = 2000;      //A Modifier Seuil=0 (dans setup() qu'on initialise la valeur)
int i=0;
float sensorValue;
int ventilateur=0;

//Json Buffer
StaticJsonDocument<250> jsonDocument;
char buffer[250];

void connectToWiFi() {
  Serial.print("Connecting to ");
  Serial.println(SSID);

  WiFi.begin(SSID, PWD);

  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
    // we can even make the ESP32 to sleep
  }

  Serial.print("Connected. IP: ");
  Serial.println(WiFi.localIP());
}

void setup_routing() {
  server.on("/tauxGaz", getTauxGaz);
  server.on("/seuil", getSeuil);
  server.on("/modifierSeuil", HTTP_POST, handlePost);
  server.on("/ventilation", HTTP_POST, handlePostVenti);

  // start server
  server.begin();
}

void getTauxGaz() {
  Serial.println("Get Taux Gaz");
  create_json("Taux gaz", sensorValue);
  server.send(200, "application/json", buffer);
}

void getSeuil() {
  Serial.println("Get seuil");
  create_json("Seuil ", seuil);
  server.send(200, "application/json", buffer);
}

void create_json(char *tag, float value) {
  jsonDocument.clear();
  jsonDocument["type"] = tag;
  jsonDocument["value"] = value;
  serializeJson(jsonDocument, buffer);
}

void add_json_object(char *tag, float value) {
  JsonObject obj = jsonDocument.createNestedObject();
  obj["type"] = tag;
  obj["value"] = value;
}

void getEnv() {
  Serial.println("Get env");
  jsonDocument.clear();
  add_json_object("Taux gaz", sensorValue);
  add_json_object("Seuil", seuil);
  serializeJson(jsonDocument, buffer);
  server.send(200, "application/json", buffer);
}

void handlePost() {
  if (server.hasArg("plain") == false) {
    Serial.println("Erreur post seuil");
  }
  String body = server.arg("plain");
  deserializeJson(jsonDocument, body);

  seuil = jsonDocument["seuil"];
  Serial.print("Nouveau seuil: ");
  Serial.println(seuil);

  // Respond to the client
  server.send(200, "application/json", "{}");
}


void handlePostVenti() {
  if (server.hasArg("plain") == false) {
    Serial.println("Erreur post activer Ventilateur");
  }
  String body = server.arg("plain");
  deserializeJson(jsonDocument, body);

  ventilateur = jsonDocument["ventilateur"];
  Serial.print("Valeur ventilateur: ");
  Serial.println(ventilateur);

  // Respond to the client
  server.send(200, "application/json", "{}");
}

void ledLight(int dta) // light led
    {

        dta = dta/10; // Nombre à modifier selon le max et le min du taux de gaz

        int colorR = dta;
        int colorG = 255-dta;
        int colorB = 0;

        leds.setColorRGB(0, colorR, colorG, colorB);
    }

void tone(byte pin, int freq) {
  ledcSetup(0, 2000, 8); // setup beeper
  ledcAttachPin(pin, 0); // attach beeper
  ledcWriteTone(0, freq); // play tone
}

void setup_task() {
    xTaskCreate(
  read_sensor_data,
  "Read sensor data",
  8000,
  NULL,
  1,
  NULL
  );
}

void read_sensor_data(void * parameter){
  for (;;) {
    Serial.print("Sensor value = ");
    sensorValue = analogRead(GazSensor);
    Serial.println(sensorValue);

    if(sensorValue>seuil){
        ventilateur=255;
        digitalWrite(ledPin, HIGH);
        leds.setColorRGB(0, 255, 0, 0);
        digitalWrite(VENTI, ventilateur);

        tone(SPEAKER,208);
        delayMicroseconds(BassTab[0]);
        tone(SPEAKER,3136);
        delayMicroseconds(BassTab[1]);

      }else{
        digitalWrite(ledPin, LOW);
        ledLight(sensorValue);
        digitalWrite(VENTI, ventilateur);

        tone(SPEAKER,0);
      }

   vTaskDelay(1000 / portTICK_PERIOD_MS);
  }
}

void setup(){
  Serial.begin(9600);
  pinMode(GazSensor,INPUT);
  pinMode(ledPin, OUTPUT);
/*    seuil = input(front-end) */
  pinMode(SPEAKER,OUTPUT);
  tone(SPEAKER,0);
  pinMode(VENTI,OUTPUT);

  connectToWiFi();
  setup_task();
  setup_routing();
}

void loop()
{
    server.handleClient();
}
