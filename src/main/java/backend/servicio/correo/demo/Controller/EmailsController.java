package backend.servicio.correo.demo.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.servicio.correo.demo.Repository.ContactoDTO;
import backend.servicio.correo.demo.Services.SendEmail;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/emails")
@CrossOrigin(origins = "*")
public class EmailsController {

    
    private final SendEmail sendEmailService;

    public EmailsController(SendEmail sendEmailService) {
        this.sendEmailService = sendEmailService;
    }


    @PostMapping("/enviar")
    public String recibirFormulario(@RequestBody ContactoDTO contacto) {
    try {
        sendEmailService.sendEmail(contacto.asunto(), contacto.nombre(), contacto.mensaje(), contacto.gmail());
        return "Correo enviado con éxito";
    } catch (Exception e) {
       return "Error al enviar el correo: " + e.getMessage();
    }
    }
    
}
