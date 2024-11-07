package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.service.impl.ArtistServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "АrtistServlet", urlPatterns = {"/artist"})
public class ArtistServlet extends HttpServlet{
    //ovoj servlet treba da ja prikazete stranata za izbor
    //na artst za dodavanje vo listata na izveduvaci na izbranata pesna
    private final ArtistServiceImpl artistService;
    private final SpringTemplateEngine templateEngine;

    public ArtistServlet(ArtistServiceImpl artistService, SpringTemplateEngine templateEngine) {
        this.artistService = artistService;
        this.templateEngine = templateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Artist> artistList = artistService.listArtists();

        String trackId = req.getParameter("trackId");
        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);

        context.setVariable("artistList", artistList);
        context.setVariable("trackId", trackId);
        templateEngine.process("artistsList.html", context, resp.getWriter());
    }
}
