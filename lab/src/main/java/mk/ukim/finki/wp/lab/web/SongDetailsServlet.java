package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.impl.ArtistServiceImpl;
import mk.ukim.finki.wp.lab.service.impl.SongServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

//site detali za pesnata vo koja e dodaden selektiraniot artist od prethodniot
//cekor
//treba da gi izlistate naslovot, zanrot, godinata na objavuvanje
//izveduvacite

@WebServlet(name = "SongDetailsServlet", urlPatterns = {"/songDetails"})
public class SongDetailsServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final SongServiceImpl songService;
    private final ArtistServiceImpl artistService;

    public SongDetailsServlet(SpringTemplateEngine templateEngine, SongServiceImpl songService, ArtistServiceImpl artistService) {
        this.templateEngine = templateEngine;
        this.songService = songService;
        this.artistService = artistService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Song s = songService.listSongs()
                .stream()
                .findFirst()
                .orElse(null);

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(iWebExchange);
        context.setVariable("entity", s);
        templateEngine.process("songDetails.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String trackId = req.getParameter("trackId");
        String artistId = req.getParameter("artistId");
        Song s = songService.listSongs()
                .stream()
                .findFirst()
                .orElse(null);

        if(trackId != null && artistId != null){
            s = songService.findByTrackId(trackId);
            Artist a = artistService.findById(Long.valueOf(artistId));
            s.addArtistToList(a);
        }

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(iWebExchange);
        context.setVariable("entity", s);
        templateEngine.process("songDetails.html", context, resp.getWriter());
    }
}
