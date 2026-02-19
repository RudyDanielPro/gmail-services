package backend.servicio.correo.demo.Services;

import com.resend.Resend;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {
    @Value("${resend.api.key}")
    private String apiKey;

    public void sendEmail(String asunto, String nombre , String mensaje , String gmail){
        Resend resend = new Resend(apiKey);

       CreateEmailOptions options = CreateEmailOptions.builder()
                .from("onboarding@resend.dev")
                .to("rudydanielcarballo@gmail.com")
                .replyTo(gmail)
                .subject(asunto)
                .html("<h3>Hola Rudy me llamo </h3>"+nombre+"<p>"+mensaje+"</p>")
                .build();
        try {
            CreateEmailResponse data = resend.emails().send(options);
            System.out.println("✅ Correo enviado con éxito. ID: " + data.getId());
        } catch (Exception e) {
            System.err.println("❌ Error al enviar con Resend: " + e.getMessage());
            throw new RuntimeException("Error en el servicio de correo");
        }
    }
}
