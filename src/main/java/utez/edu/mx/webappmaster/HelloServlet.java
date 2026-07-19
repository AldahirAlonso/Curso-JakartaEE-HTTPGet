package utez.edu.mx.webappmaster;

import jakarta.servlet.annotation.WebServlet; // Para registrar el servlet con una anotación
import jakarta.servlet.http.HttpServlet; // Clase base que proporciona soporte para peticiones HTTP
import jakarta.servlet.http.HttpServletRequest; // Representa la petición enviada por el navegador/cliente
import jakarta.servlet.http.HttpServletResponse; // Representa la respuesta que enviaremos al cliente

import java.io.IOException; // Excepción que puede lanzarse al trabajar con E/S
import java.io.PrintWriter; // Permite escribir texto en el cuerpo de la respuesta HTTP

// @WebServlet registra este servlet en el contenedor web (Tomcat, Jetty, etc.).
// name: nombre lógico del servlet; value: la ruta (URL) donde estará disponible.
// Ejemplo de acceso: http://localhost:8080/<contextPath>/hello-servlet
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {  // init() se llama una vez cuando el servlet se carga por primera vez. Ideal para inicializar configuraciones, cargar recursos o preparar conexiones.
        message = "Hola Mundo desde la clase HelloServlet! \n Usando PrintWriter para generar texto en esta clase.";
    }

    // doGet() maneja peticiones HTTP GET.
    // request: contiene datos enviados por el cliente (parámetros, cabeceras, cookies).
    // response: permite configurar la respuesta (tipo, códigos) y escribir el cuerpo.
    // Se lanza IOException si ocurre un error de entrada/salida al escribir la respuesta.
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Informamos al navegador que la respuesta será HTML. Añadir charset es buena práctica.
        response.setContentType("text/html; charset=UTF-8");

        // Obtener un PrintWriter para escribir el cuerpo de la respuesta (texto/HTML).
        PrintWriter out = response.getWriter();

        // Construcción de una página HTML simple. En proyectos reales conviene usar JSP o motores de plantillas
        // (Thymeleaf, FreeMarker) para separar lógica de presentación.
        out.println("<html>");
        out.println("  <head>");
        out.println("    <meta charset=\"UTF-8\"> <!-- Asegura la correcta visualización de acentos -->");
        out.println("    <title>Hola Servlet</title>");
        out.println("  </head>");
        out.println("  <body>");
        out.println("    <!-- Aquí mostramos el mensaje inicializado en init() -->");
        out.println("    <h1>" + message + "</h1>");
        out.println("    <!-- Ejemplo de cómo se podría leer un parámetro de la URL: ?name=Juan -->");
        out.println("    <p>Prueba: añade '?name=TuNombre' a la URL y luego modifica el servlet para usar request.getParameter(\"name\").</p>");
        out.println("  </body>");
        out.println("</html>");
        out.close();
    }

    // destroy() se llama cuando el contenedor va a descargar el servlet (por ejemplo al detener la aplicación).
    // Usar este método para liberar recursos (cerrar conexiones, liberar memoria, detener hilos).
    @Override
    public void destroy() {
        // Si hubiese recursos abiertos, cerrarlos aquí: p.ej. if (dbConn != null) dbConn.close();
    }
}