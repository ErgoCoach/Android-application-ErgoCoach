// Basic Bluetooth sketch HC-05_02
// Connect the HC-05 module and communicate using the serial monitor
//
// The HC-05 defaults to commincation mode when first powered on.
// The default baud rate for communication mode is 9600
 
#include "HX711.h"
#include <SoftwareSerial.h>
#define ATTENTE 500
#define ATTENTE2 500

SoftwareSerial BTserial(10, 11); // RX | TX
HX711 nord(4, 2); // Data | Clk
HX711 sud(3, 2); // Data | Clk
HX711 est(5, 2); // Data | Clk
HX711 ouest(6, 2); // Data | Clk
HX711 hautdroit(7, 2); // Data | Clk
HX711 hautgauche(8, 2); // Data | Clk
HX711 milieudroit(9, 2); // Data | Clk
HX711 milieugauche(12, 2); // Data | Clk
HX711 basbas(13, 2); // Data | Clk
 
char received = '0';

long sommeAssiseSud = 0;
long sommeAssiseNord = 0;
long sommeAssiseEst = 0;
long sommeAssiseOuest = 0;
long sommeDossierSud, sommeDossierMilieuOuest, sommeDossierMilieuEst, sommeDossierNordOuest, sommeDossierNordEst;
long moyenneAssiseSud, moyenneAssiseNord, moyenneAssiseEst, moyenneAssiseOuest;
long moyenneDossierSud, moyenneDossierMilieuOuest, moyenneDossierMilieuEst, moyenneDossierNordOuest, moyenneDossierNordEst;

//valeurs du siège
long valeurAssiseSud = 0;
long valeurAssiseOuest = 0;
long valeurAssiseEst = 0;
long valeurAssiseNord = 0;
float valeurAssiseSudFinal = 0;
float valeurAssiseOuestFinal = 0;
float valeurAssiseEstFinal = 0;
float valeurAssiseNordFinal = 0;

//valeurs du dossier
long valeurDossierNordOuest = 0;
long valeurDossierNordEst = 0;
long valeurDossierMilieuOuest = 0;
long valeurDossierMilieuEst = 0;
long valeurDossierSud = 0;
long valeurDossierNordOuestFinal=0;
long valeurDossierNordEstFinal=0;
long valeurDossierMilieuOuestFinal=0;
long valeurDossierMilieuEstFinal=0;
long valeurDossierSudFinal=0;

// Déclaration de variables qui nous serviront au calcul des ratios 
long total;
long total2 = 0;
float total3 = 0;
float total4 = 0;
float total5 = 0;
float total6 = 0;
float total7 = 0;
float total8 = 0;
float total9 = 0;

float diffE;
float diffN;
float diffG;
float diffS;
float diffSS;
float diffM;

int propS;
int propO ;
int propE;
int propN ;
int propNO;
int propNE ;
int propMO;
int propME ;
int propSS ;
int propG;
int propD;
int propM;
int propNN;

int i=0;
int count = 0;
 
void setup() 
{
    Serial.begin(9600);
    // HC-05 serial speed pour communication 9600
    BTserial.begin(9600);
}

void loop()
{
    //On relie les capteurs de force à nos variables
    valeurDossierNordEst = hautdroit.read();
    valeurDossierNordOuest = hautgauche.read();
    valeurDossierMilieuEst = milieudroit.read();
    valeurDossierMilieuOuest = milieugauche.read();
    valeurDossierSud = basbas.read();
    valeurAssiseNord = nord.read();
    valeurAssiseSud = sud.read();
    valeurAssiseEst = est.read();
    valeurAssiseOuest = ouest.read();

    // Si on reçoit quelque chose du téléphone
    if (BTserial.available()>0)
    {  
      received = BTserial.read();
      Serial.println("received: ");
      Serial.println(received);
    }

    //Si le module bluetooth émet et est reçu
    if(received == '1') {
        // Tarage des capteurs (on les pusse à 0) via calcul de la valeurAssiseEstur moyenne à vide 
        if(count < 10)
        {   
          sommeAssiseEst = sommeAssiseEst + valeurAssiseEst;
          sommeAssiseOuest = sommeAssiseOuest + valeurAssiseOuest;
          sommeAssiseNord = sommeAssiseNord + valeurAssiseNord;
          sommeAssiseSud = sommeAssiseSud + valeurAssiseSud;
          sommeDossierSud = sommeDossierSud + valeurDossierSud;
          sommeDossierMilieuOuest = sommeDossierMilieuOuest + valeurDossierMilieuOuest;
          sommeDossierMilieuEst = sommeDossierMilieuEst + valeurDossierMilieuEst;
          sommeDossierNordOuest = sommeDossierNordOuest + valeurDossierNordOuest;
          sommeDossierNordEst = sommeDossierNordEst + valeurDossierNordEst;  
          
          Serial.println(count);
          count = count + 1;
          delay(ATTENTE2);
        }
        
        if(count == 10)
        {   
          //Calcul des moyenneAssiseSud
          moyenneAssiseSud = sommeAssiseSud/10;
          moyenneAssiseEst = sommeAssiseEst/10;
          moyenneAssiseNord = sommeAssiseNord/10;
          moyenneAssiseOuest = sommeAssiseOuest/10;
          moyenneDossierSud = sommeDossierSud/10;
          moyenneDossierMilieuEst = sommeDossierMilieuEst/10;
          moyenneDossierMilieuOuest = sommeDossierMilieuOuest/10;
          moyenneDossierNordOuest = sommeDossierNordOuest/10;
          moyenneDossierNordEst = sommeDossierNordEst/10;  
          
          count = count + 1;
        }
        
        if(count > 10)
        {
          //Une fois calcul de la moyenne fait, à chaque tour de Clock, calcul des différentes valeurAssiseEsturs (= écart au 0). 
          //On divise par 1000 pour avoir des plus petites valeurAssiseEsturs
    
          valeurAssiseSudFinal = (valeurAssiseSud - moyenneAssiseSud)/1000+1500;
          valeurAssiseNordFinal = (valeurAssiseNord - moyenneAssiseNord)/1000+1500;
          valeurAssiseOuestFinal = (valeurAssiseOuest - moyenneAssiseOuest)/1000+1500;
          valeurAssiseEstFinal = (valeurAssiseEst - moyenneAssiseEst)/1000+1500;
          valeurDossierNordOuestFinal = (valeurDossierNordOuest - moyenneDossierNordOuest)/1000+300;
          valeurDossierNordEstFinal = (valeurDossierNordEst - moyenneDossierNordEst)/1000+300;
          valeurDossierMilieuOuestFinal = (valeurDossierMilieuOuest - moyenneDossierMilieuOuest)/1000+300;
          valeurDossierMilieuEstFinal = (valeurDossierMilieuEst - moyenneDossierMilieuEst)/1000+300;
          valeurDossierSudFinal = (valeurDossierSud - moyenneDossierSud)/1000+300;

          //Calcul des ratios

          //Ratio assis Nord-Sud
          total = abs(valeurAssiseSudFinal) + abs(valeurAssiseNordFinal);
          if(valeurAssiseSudFinal<0)
          { 
            if(abs(valeurAssiseSudFinal)>abs(valeurAssiseNordFinal)){
              propN=(abs(valeurAssiseSudFinal)/total)*100;
            }
            else {
            
            propN = (abs(valeurAssiseNordFinal)/total)*100; 
            }
            propS = 100 - propN;
          }
          
          if(valeurAssiseSudFinal > 0 && valeurAssiseNordFinal > 0) 
          {
            propS = (valeurAssiseSudFinal/total)*100; 
            propN = 100 - propS;
          }
          
          if(valeurAssiseNordFinal < 0)
          {
            if(abs(valeurAssiseNordFinal)>abs(valeurAssiseSudFinal)){
              propS=(abs(valeurAssiseNordFinal)/total)*100;
            }
            else {
           
            propS = (abs(valeurAssiseSudFinal)/total)*100; 
            }
            propN = 100 - propS;
          }
         
          //Ratio assis Est-Ouest
          total2 = abs(valeurAssiseEstFinal) + abs(valeurAssiseOuestFinal);
          if(valeurAssiseEstFinal < 0)
          { 
            if(abs(valeurAssiseEstFinal)>abs(valeurAssiseOuestFinal)){
              propO=(abs(valeurAssiseEstFinal)/total2)*100;
            }
            else {
            
            
            propO = (abs(valeurAssiseOuestFinal)/total2)*100; 
            }
            propE = 100 - propO;
          }
          
          if(valeurAssiseEstFinal > 0 && valeurAssiseOuestFinal > 0) 
          {
            propE = (valeurAssiseEstFinal/total2)*100; 
            propO = 100 - propE;
          }
          
          if(valeurAssiseOuestFinal < 0)
          {
            if(abs(valeurAssiseOuestFinal)>abs(valeurAssiseEstFinal)){
              propE=(abs(valeurAssiseOuestFinal)/total2)*100;
            }
            else {
            
            propE = (abs(valeurAssiseEstFinal)/total2)*100; 
            }
            propO = 100 - propE;
          }
         
         
          
          //Ratio GAUCHE-DROITE dossier
          total7 = abs(valeurDossierMilieuEstFinal) + abs(valeurDossierMilieuOuestFinal);
          if(valeurDossierMilieuOuestFinal < 0)
          { 
            valeurDossierMilieuEstFinal = valeurDossierMilieuEstFinal + abs(valeurDossierMilieuOuestFinal);
            total7 = abs(valeurDossierMilieuOuestFinal) + total7; 
            propD = (valeurDossierMilieuEstFinal/total7)*100; 
            propG = 100 - propD;
          }
          
          if(valeurDossierMilieuOuestFinal > 0 && valeurDossierMilieuEstFinal > 0) 
          {
            propG = (valeurDossierMilieuOuestFinal/total7)*100; 
            propD = 100 - propG;
          }
          
          if(valeurDossierMilieuEstFinal < 0)
          {
            valeurDossierMilieuOuestFinal = valeurDossierMilieuOuestFinal + abs(valeurDossierMilieuEstFinal); 
            total7 = total7 + abs(valeurDossierMilieuEstFinal); 
            propG = (valeurDossierMilieuOuestFinal/total7)*100; 
            propD = 100 - propG;
          }
          
          total3 = valeurDossierNordEstFinal + valeurDossierNordOuestFinal;
          total4 = valeurDossierMilieuEstFinal + valeurDossierMilieuOuestFinal;

          //Ratio haut-bas dossier
          total8 = (total3 + total4)/4;
          total9 = abs(total8) + abs(valeurDossierSudFinal);
          if(total8 < 0)
          { 
            valeurDossierSudFinal += abs(total8);
            total9 += abs(total8); 
            propSS = (valeurDossierSudFinal/total9)*100; 
            propNN = 100 - propSS;
          }
          
          if(valeurDossierSudFinal > 0 && total8 > 0) 
          {
            propSS = (valeurDossierSudFinal/total9)*100; 
            propNN = 100 - propSS;
          }
          
          if(valeurDossierSudFinal < 0)
          {
            total8 += abs(valeurDossierSudFinal); 
            total9 += abs(valeurDossierSudFinal); 
            propNN = (total8/total9)*100; 
            propSS = 100 - propNN;
          }
          
          //Pour savoir si la personne touche ou non le dossier, on élimine valeurs = bruit.
          if((valeurDossierSudFinal < 400)&&(valeurDossierNordOuestFinal < 400)&&(valeurDossierMilieuOuestFinal < 400)&&(valeurDossierNordEstFinal < 400)&&(valeurDossierMilieuEstFinal < 400))
          {
            propD=0;
            propG=0;
            propSS=0;
            propNN=0;
          }

          if((valeurAssiseSudFinal < 1550)&&(valeurAssiseNordFinal < 1550)&&(valeurAssiseOuestFinal < 1550)&&(valeurAssiseEstFinal < 1550))
          {
            propE=0;
            propO=0;
            propS=0;
            propN=0;
          }
          

          //affichage des pourcentages capteurs dans le moniteur série
         
          Serial.println("Est");
          Serial.println(propE);
          Serial.println("Ouest");
          Serial.println(propO);
         Serial.println("NORD");
          Serial.println(propN);
           Serial.println("SUD");
          Serial.println(propS);
          
         Serial.println("Bas");
          Serial.println(propSS);
          Serial.println("Haut");
          Serial.println(propNN);
          Serial.println("gauche");
          Serial.println(propG);
          Serial.println("Droite");
          Serial.println(propD);
          
          
          
          
          // Envoi des valeurs au module bluetooth
          BTserial.print("v1"); //assis avant
          if((propS>=0)&&(propS<10)) BTserial.print("0");
          BTserial.print(propS);
          BTserial.print("#");
          
          BTserial.print("v2"); //assis derriere
          if((propN>=0)&&(propN<10)) BTserial.print("0");
          BTserial.print(propN);
          BTserial.print("#");
          
          BTserial.print("v3"); //assise droite 
          if((propO>=0)&&(propO<10)) BTserial.print("0");
          BTserial.print(propO);
          BTserial.print("#");
          
          BTserial.print("v4"); //assise gauche
          if((propE>=0)&&(propE<10)) BTserial.print("0");
          BTserial.print(propE);
          BTserial.print("#");
          
          BTserial.print("v5"); //bas
          if((propSS>=0)&&(propSS<10)) BTserial.print("0");
          BTserial.print(propSS);
          BTserial.print("#");
          
          BTserial.print("v6"); //dossier haut
          if((propNN>=0)&&(propNN<10)) BTserial.print("0");
          BTserial.print(propNN);
          BTserial.print("#");
          
          BTserial.print("v7"); //dossier gauche
          if((propG>=0)&&(propG<10)) BTserial.print("0");
          BTserial.print(propG);
          BTserial.print("#");
          
          BTserial.print("v8"); //droite
          if((propD>=0)&&(propD<10)) BTserial.print("0");
          BTserial.print(propD);
          BTserial.print("#");
          
          delay(ATTENTE);       
        }   
    }

    else {
      count=0; 
      received=0;
      
      //valeurs du siège
      valeurAssiseSud = 0;
      valeurAssiseOuest = 0;
      valeurAssiseEst = 0;
      valeurAssiseNord = 0;
      
      //valeurs du dossier
      valeurDossierNordOuest = 0;
      valeurDossierNordEst = 0;
      valeurDossierMilieuOuest = 0;
      valeurDossierMilieuEst = 0;
      valeurDossierSud = 0;
      sommeAssiseSud = 0;
      sommeAssiseNord = 0;
      sommeAssiseEst = 0;
      sommeAssiseOuest = 0;
      sommeDossierSud=0;
      sommeDossierMilieuOuest=0; 
      sommeDossierMilieuEst=0; 
      sommeDossierNordOuest=0; 
      sommeDossierNordEst=0;
    }
}

