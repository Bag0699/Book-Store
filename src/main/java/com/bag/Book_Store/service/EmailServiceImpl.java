package com.bag.Book_Store.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.sendgrid.api-key}")
    private String sendGridApiKey;

    @Value("${spring.sendgrid.from.email}")
    private String fromEmail;

    @Override
    public Boolean sendEmail(String toEmail, String subject, String body) {
        Email from = new Email(fromEmail);
        Email to = new Email(toEmail);
        Content content = new Content("text/html", body);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            if(response.getStatusCode() >= 200 && response.getStatusCode() <= 299) {
                System.out.println("Email enviado con éxito a: " + toEmail);
                System.out.println("Código de estado: " + response.getStatusCode());
                System.out.println("Cuerpo de la respuesta: " + response.getBody());
                System.out.println("Cabeceras de la respuesta: " + response.getHeaders());
                return true;
            } else {
                System.err.println("Error al enviar email a: " + toEmail);
                System.err.println("Código de estado: " + response.getStatusCode());
                System.err.println("Cuerpo de la respuesta: " + response.getBody());
                System.err.println("Cabeceras de la respuesta: " + response.getHeaders());
                return false;
            }
        } catch (IOException ex){
            System.err.println("Excepción al enviar email: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean sendOrderConfirmationEmail(String toEmail, String orderNumber, Double totalAmount, String fullName) {
        String subject = "¡Tu compra en La Catedral ha sido confirmada! - Orden #" + orderNumber;
        String body = "<html lang='es'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "<title>Confirmación de Compra - La Catedral</title>" +
                "<style>" +
                "  body { font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f4f4; color: #333; }" +
                "  .container { width: 100%; max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 8px; overflow: hidden; box-shadow: 0 4px 12px rgba(0,0,0,0.05); }" +
                "  .header { background-color: #8B4513; padding: 25px 30px; text-align: center; color: #ffffff; }" +
                "  .header h1 { margin: 0; font-size: 28px; }" +
                "  .content { padding: 30px; line-height: 1.7; }" +
                "  .content h2 { color: #8B4513; font-size: 24px; margin-top: 0; }" +
                "  .highlight { color: #333333; font-weight: bold; }" +
                "  .order-details { background-color: #f9f9f9; border-left: 5px solid #8B4513; padding: 20px; margin: 25px 0; border-radius: 4px; }" +
                "  .order-details p { margin: 5px 0; }" +
                "  .footer { background-color: #f0f0f0; padding: 20px 30px; text-align: center; font-size: 14px; color: #666; border-top: 1px solid #e0e0e0; }" +
                "  .button { display: inline-block; background-color: #4CAF50; color: #ffffff; padding: 12px 25px; border-radius: 5px; text-decoration: none; font-weight: bold; margin-top: 20px; }" +
                "  .button:hover { background-color: #45a049; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "  <div class='header'>" +
                "    <h1>La Catedral</h1>" +
                "  </div>" +
                "  <div class='content'>" +
                "    <h2>¡Tu compra ha sido confirmada!</h2>" +
                "    <p>Hola <span class='highlight'>" + fullName + "</span>,</p>" +
                "    <p>Nos complace confirmarte que tu pedido ha sido recibido con éxito en <strong>La Catedral</strong>.</p>" +
                "    <div class='order-details'>" +
                "      <p>Número de Orden: <span class='highlight'><strong>#" + orderNumber + "</strong></span></p>" +
                "      <p>Importe Total: <span class='highlight'><strong>S/ " + String.format("%.2f", totalAmount) + "</strong></span></p>" +
                "    </div>" +
                "    <p>¡Gracias por elegirnos! Tu satisfacción es nuestra prioridad.</p>" +
                "    <p>Pronto recibirás más detalles sobre el estado de tu envío.</p>" +
                "    <p>Si tienes alguna pregunta o necesitas ayuda, no dudes en contactarnos. Estamos aquí para asistirte.</p>" +
                "    <p>Puedes revisar los detalles de tu pedido en cualquier momento iniciando sesión en tu cuenta de <strong>La Catedral</strong>.</p>" +
                "  </div>" +
                "  <div class='footer'>" +
                "    <p>&copy; 2025 La Catedral. Todos los derechos reservados.</p>" +
                "    <p>Av. Principal 123, Miraflores, Lima, Perú</p>" +
                "  </div>" +
                "</div>" +
                "</body>" +
                "</html>";

        return sendEmail(toEmail, subject, body);
    }
}
