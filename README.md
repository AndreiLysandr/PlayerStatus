# ---PlayerStatus---

  Aplicatia este la baza un joc, in care un obiect de tipul PlayerStatus va fi initializat si va rula un set de comenzi impotriva unui caracter prestabilit, care este tot de tipul PlayerStatus, pentru a se putea defini un castigator.
#
Modelarea Armelor: 

   Pentru a modela armele si metodele necesare rularii aplicatiei, am creat clasa PlayerStatus, in care au fost definite metodele necesare functionarii aplicatiei. In aceasta clasa au fost incapsulate toate campurile (gameName, nickname, health, lives, score, weaponInHand, positionX si positionY) si am implementat metodele comune, care vor fi folosite la rularea corecta a aplicatiei.
	 
   	Comportamentul aplicatiei va fi urmatorul:
         - 3 constructori, ce ajuta la initializarea profilului jucatorului.
         - metoda findArtifact(int artifactCode), actualizeaza starea jucatorului in functie de un artifact pe care jucatorul il poate gasi la inceputul fiecarei runde.
         - metoda setWeaponInHand(String weaponInHand), schimba arma folosita de jucator in joc.
         - metoda getWeaponInHand(), returneaza arma curenta pe care jucatorul o are in mana.
         - positionX si positionY definesc punctul in care jucatorul se afla in momentul actual, astfel calculandu-se distanta dintre jucator si caracterul prestabilit. Campurile positionX si positionY sunt si proprietati ale obiectelor clasei PlayerStatus.
         - gameName, la randul sau, este tot o proprietate a obiectelor clasei PlayerStatus.
         - metoda movePlayerTo(double positionX, double positionY), actualzieaza pozitia jucatorului.
         - campul nickname, poate fi citit, dar nu poate fi modificat din exterior.
         - metoda shouldAtackOpponent(PlayerStatus opponent), va simula o batalie intre jucator si oponentul prestabilit, simuland o lupta intre acestia si returnant o valoare de tipul boolean, pentru a sfatui jucatorul daca ar trebui sa atace sau nu.
#
MODELAREA JOCULUI:
  
   Pentru modelarea jocului, simularea miscarii jucatorului, schimbarea armelor si simularea luptei dintre cei 2 adversari, s-a folosit clasa TestareJoc.  
  
   	In interiorul clasei TestareJoc gasim: 
         - metoda statica info(), care afiseaza optiunile pe care jucatorul le are la dispozitie.
         - obiectul oponent, de tipul PlayerStatus si obiectul player care are tot tipul PlayerStatus.
         - un while care contine in interior un switch, in care se desfasoara jocul.
#
REGULILE JOCULUI:
  
   	Regulile jocului sunt:
         - cand viata unui jucator ajunge la 0, jocul se termina.
         - daca au trecut 5 runde, jocul se termina.
         - daca jucatorii au aceeasi arma in mana, va castiga jucatorul care are arma mai puternica, in functie de distanta dintre jucatori.
         - daca jucatorii au arme identice, se va folosi o formula ce va calcula o valoare bazata pe scorul si viata jucatorului, urmand sa castige cel care are o valoare mai mare.
