

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// megadunk egy listát hogy melyik csomagokat nézze végig.
@ComponentScan(basePackages = "org.example")
@Configuration
/*bekapcsoljuk a webws MVC featurest*/
@EnableWebMvc
/*implementáljuk a WebMvcCOnfigurer*/
public class WebConfig implements WebMvcConfigurer {

    /*Ez a beállítás azt csinálja hogy ha meglátogatjuk az alkalmazásnak a normál
    * domain-jét /  vagy a /indexet akkor minden esetben az index.html fájlt töltse be.*/
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
    }
}
