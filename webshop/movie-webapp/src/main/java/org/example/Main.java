package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*A Main osztályra rádobjuk a SpringBootApplication annotációt
* ez három fajta annotációt vált ki.
* az automata konfiguráció bekapcsolásnak
* a componentScannek illetve a sima configurationnak
* A SPringBoot automata konfigurációkkal rendelkezik.
* SpringBoot minden ami a packages hierarchiába a Main osztály alatt van ott át szkenneli az
* egészet*/
@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        // A program fő belépés pontja és átadjuk ugyaanzokat az argumentumokat amit kap a main

        /*Az alkalmazásunk egy tomcats serveren fut a 8080 porton*/
        SpringApplication.run(Main.class, args);

        /*probléma mi magunk készítjuk el a main osztályon belül az egyik függőségünk példányát
        * Ha bármi változzna a commandProcessor elkészítési módszerében akkor azt itt le kel követni
        * */

        /*context
         * ezen belül értelmezhetőek azok az objektumok amelyeket a spring maga hozz létre.
         * és azokat az objektumok bean-nek nevezzük mert a spring hozza létre, konfigurálja
         * kezeli az életciklus fázisaikat. Ezek a bean-ek a contextben érhetők el*/

        /*Azt szeretnénk hogy a spring maga készítse el a CommandProcessor példányt
        * nyissuk meg a CommendProcessor-t*/

        /*CommandProcessor commandProcessor = new CommandProcessor();
        Mivek a CommandProcessort ellátuk a component annotációval nincs szükség erre az új
        objektum létrehozására hanem a spring-re bizzuk hogy elkészüljön.
        * */


        /*Több fajtája van mi a javas annotácókat fogjuk használni
        * Ez fogja elinditani az egész folyamatot amivel elindul maga a Spring
        * az IOC Container és elkezdi előállítani az objektumainkat amelyek részt fognak venni
        * al alkalmazás müködösében
        * tehát azokat az objektumokat amit new kulcsszóval hozunk létre
        * és ha függőség áll fent két osztály között akkor használni fogja a dependency injection-t
        * és be injektálja a függőségeket a megfelelő helyre
        * ez az IOC kezeli a konfigurációkat ezekhez a példányokhoz a létrehozást és az életciklus fázisokat
        *Létrehozzuk az AppConfig osztály a confog csomagban
        * */

        /*A kontextusbol le tudjuk kérni magát a bean-t*/

        /*Ezt már nem fogjuk használni mivel webes lesz az alaklmazásunk
        *  AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        *  CommandProcessor commandProcessor = context.getBean(CommandProcessor.class);
           commandProcessor.process();
        * */

    }
}