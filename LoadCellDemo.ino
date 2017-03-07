#include "HX711.h"
HX711 cellS(3,2);
HX711 cellN(4,2);
HX711 cellE(5, 2);
HX711 cellO(6, 2);

HX711 cellNE(7,2);
HX711 cellNO(8, 2);
HX711 cellME(9, 2);
HX711 cellMO(12, 2);
HX711 cellSS(13, 2);


void setup() {
  Serial.begin(9600);
}

long valS = 0;
long valO = 0;
long valE = 0;
long valN = 0;
float valSf=0;
float valOf=0;
float valEf=0;
float valNf=0;

long total;

long total2 =0;
float count = 0;
int propS;
int propO ;
int propE;
int propN ;

long valNO = 0;
long valNE = 0;
long valMO = 0;
long valME = 0;
long valSS = 0;
float valNOf=0;
float valNEf=0;
float valMOf=0;
float valMEf=0;
float valSSf=0;
long total3 = 0;
long total4 =0;
float diffE;
float diffN;
float count2 = 0;
int propNO;
int propNE ;
int propMO;
int propME ;
int propSS ;
void loop() {
  count = count + 1;
  
  //// Use only one of these
 // valS = ((count-1)/count) * valS  +  (1/count) * cellS.read();
 valS=cellS.read();
 valN=cellN.read();
  valO = cellO.read();
  valE = cellE.read();
   valNE=cellNE.read();
 valNO=cellNO.read();
  valME = cellME.read();
  valMO = cellMO.read();
    valSS = cellSS.read();
 //valN = ((count-1)/count) * valN   +  (1/count) * cellN.read(); // take long term average

//valS = 0.5 * valS    +   0.5 * cellS.read();
 //valO = 0.5 * valO    +   0.5 * cellO.read();
 //valE = 0.5 * valE    +   0.5 * cellE.read();*/
 //valN = 0.5 * valN    +   0.5 * cellN.read();// take recent average
 // val1 = cell1.read();& 
  //val2 = cell2.read();
  // most recent reading
  
  valSf= valS-8450200;
 // if(valSf<0) valSf=0;
  //valOf=
  //valEf=
  valNf= valN-8370910;
  // if(valNf<0) valNf=0;
 
  total=abs(valSf) + abs(valNf);
 diffN=valNf-(total/2);
   propN= 50 + (diffN/total)*50;
   propS=100 - propN;

  
  //if(valEf<0) valEf=0;
  //valOf=
  //valEf=
  valOf= valO-8329000;
  //if(valOf<0) valOf=0;
  valEf=valE-8240000;
 

total2 =  abs(valEf) + abs(valOf);
   diffE=valEf-(total2/2);
   propE= 50 + (diffE/total2)*50;
   propO=100 - propE;
   
  
 /* propO=((valOf/total2)*100);

  propE=((valEf/total2)*100);*/
//  propE=50 + ((totalE/valMaxEO)*100)/2;
  // propO=50 - ((totalE/valMaxEO)*100)/2;
  
  //valOf=
   valNOf=valNO-8277970;
  valNEf = valNE-8248688;
  valMOf=valMO-8152700;
  valMEf=valME-8450250;
  valSSf= valSS-8455150;


 if(propO<0) propO=0;
 if(propE<0) propE=0;
 if(propS<0) propS=0;
 if(propN<0) propN=0;

total3=valNEf + valNOf;
 
  Serial.println("Val S :");
   
  // Serial.println(valSf);
   Serial.println(propS);
    Serial.println("Val N:");
     // Serial.println(valNf);
   Serial.println(propN);
   
   Serial.println("Val O :");
 Serial.println(valOf);
  Serial.println(propO);
  
   Serial.println("Val E :");
  Serial.println(valEf);
   Serial.println(propE);

 
    /*Serial.println("Val NO :");
   Serial.println(valNOf);
    Serial.println("Val NE :");
   Serial.println(valNEf);
    Serial.println("Val MO :");
   Serial.println(valMOf);
    Serial.println("Val ME :");
   Serial.println(valMEf);*/
     Serial.println("Val SS :");
   Serial.println(valSSf);
    Serial.println("total Nord :");
    
   if (total3<350000) {
    Serial.println("Ne s'appuie pas :");
    }
   else if(total3>350000) Serial.println("IL s'appuie");
   
 
 if (propO<20) Serial.println("bcp trop sur ta droite");
 if ((propO>20)&&(propO<40)) Serial.println("un peu trop sur ta droite");
 if ((propO>40)&&(propO<60)) Serial.println("Bonne posture");
 if ((propO>60)&&(propO<80)) Serial.println("un peu trop sur ta gauche");
 if ((propO>80)&&(propO<1000)) Serial.println("bcp trop sur ta gauche");
/*
if (propN<20) Serial.println("bcp trop allongé");
 if ((propN>20)&&(propN<35)) Serial.println("un peu trop allongé");
 if ((propN>35)&&(propN<65)) Serial.println("Bonne posture");
 if ((propN>65)&&(propN<80)) Serial.println("un peu trop droit");
 if ((propN>80)&&(propN<1000)) Serial.println("bcp trop droit");
 */
 
 /*Serial.println(prop2);
 Serial.println("VAL 4:");
  Serial.println(val4f);
  Serial.println(prop4);
  //Serial.println("Total :");
  //Serial.println(total);
  */

//Serial.println((val-8223545)/9636.0f*162);
  //zero 88277036
delay(500);

}
