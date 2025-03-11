package org.example.controller;

import org.example.domain.order.model.Movie;
import org.example.domain.order.model.impl.SimpleMovie;
import org.example.repository.MovieRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*A controller osztályok a szerviz osztályokkal szoktak kommunikálni.*/
@Controller
/*Itt tudjuk megadni hogy mi legyen az a URL amin keresztül elérjük
* ennek a controller osztálynak az end ponmtjait. (akátmit megadhatunk)*/
@RequestMapping("/movies")
public class MovieController {

    /*A következő függőségekre lesz szükség*/

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /*itt először kilistázzuk a termékeket.
    * Ehhez létr kell hoznunk egy új metódust Stringnek kell lennie a visszatérési
    * típusának és a template nevét kell tartalmaznia allmapákkal együtt
    * paraméterül megkapja a model objektumot
    * A következő dolgunk hogy a model objektumot felpopuláljuk valahogy*/

    /*itt megadjuk hogy milyen http metódus fogadható el ehhez az endpointhoz
    * ez a GetMapping lesz. Itt nem módosítunk semmit csak információt kérünmk re a szervertől.
    * A GetMapping belül pedi meg kell adnunk hogy mi lesz az az all URL amin keresztül elérhető*/
    @GetMapping("/list") // products/list
    public String getMovies(Model model) {
        List<Movie> movies = movieRepository.findAllMovies();
        /*Beállítjuk a filmekkel kapcsolatos információkat*/
        model.addAttribute("movies", movies);
        return "movies/list";
    }

    /*Tölés funkcionalitás
    Ha az URL-ben adunk meg értéket akoor azt úgy tudjuk kezelni hogy
    kapcsoszárójelek között megadjuk a nevét
    Ha az URL böl szeretnénk értéket kivenni akkor kell PathVariable anootáció
    Ebben az esetben nem csak a modelleünk lesz, hanem lesz  még egy plusz
    paraméterünk ami nem más mint a movie id-ja
    Törlés után mivel mindig vissza kell térnie valamilyen nézettel
    ezért ki kell pótolni a funkcionalitását
    */

    @GetMapping("/delete/{id}")
    public String deleteMovie(Model model, @PathVariable Long id) {
        movieRepository.deleteMovie(id);
        /*itt látható a funkcionalitás pótlás
        * a wearhosue pédában van átirányítás (nem kell kódót ismételni) redirectAttributes*/
        List<Movie> movies = movieRepository.findAllMovies();
        model.addAttribute("movies", movies);
        return "movies/list";
    }

    /*Movie módosítása ehhez két különböző endpointit kell késuítenünk
    * egy ahhoz hogy megtudjuk nézni a terméknek a részletetit és az edit html-t betölteni
    * a másik pedig arra hogy konkrétan hogy amikor az edit formot elküldjük akkor
    * a másik endpoint meghívásra kerüljön és módosításra kerüljön sor az adott filmen
    *
    * Tehát létrehozunk egy úgy GetMappinges endpoint-ot ennek edit lesz az URL-je
    * és ugyanúgy elvárja a terméknek az azonosítóját
    * ugyanúgy Stringgel fog visszatérni (a template nevével)
    * megkeressük az addott filmhez tartozó adatokat
    * */
    @GetMapping("/edit/{id}")
    public String editMovie(Model model, @PathVariable Long id) {
        Movie movie = movieRepository.findMovie(id);
        model.addAttribute("movie", movie);
        return "movies/edit";
    }

    /*A másik endponts update
    * Ez agy Post http metódus lesz nem Get Amikor egy from-ból adatokat küldön ki egy
    * endpointra akkor érdemes Postot használni mert sima get kérésnél nem
    * lesznek kódolva az adatok
    *
    * Sima update lesz az URL-je
    * paraméterként ez kéri a ModelAttribute -ot ez azt csinál hogy a form-bol amit küldünk adatokat
    * fel parsolja erre az objektumra erre a simpleMove-ra.
    * ezek az endPointsok nem szeretik ha sima interface típust adunk meg kell nekik egy konstrukto
    * Ha meg van a felparsolt SimpleMovie filmünk és meghívjuk a movieRepositorynak az updateMovie metódusát
    * itt megadjuk a film azonoitóját és hogy mik a változtatások
    * */
    @PostMapping("/update")
    public String updateMovie(Model model, @ModelAttribute("movie")SimpleMovie movie) {
        Movie updateMovies = movieRepository.updateMovie(movie.id(), movie);
        model.addAttribute("movies", updateMovies);
        return "movies/list";
    }
}
