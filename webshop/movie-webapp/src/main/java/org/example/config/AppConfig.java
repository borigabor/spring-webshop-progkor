package org.example.config;

/*A Springnek van egy olyan annotációja hogy Configuration
* A spring rengetegszer használja a reflexiót
* A java reflexió esetében van a megfigyelő típus és a beavatkozó típus
* a mi esetünkben csak megadjuk ezeket az annotációkat a spring az indulás során
* átszkenneli az egész package szerkezetet amit megadunk neki ilyen annotációkat
* hozzá osztályokat*/

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/*Configuration megjelöli azt osztályt hogy ez egy konfiguráció és tartlmaz konfigurációs beállításokat*/
@Configuration
/*ComponentScan Azt adja meg hogy melyik az a package amit a springnek át kell néznie*/
@ComponentScan(basePackages = "org.example")
public class AppConfig {
}
