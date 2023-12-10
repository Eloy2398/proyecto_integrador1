package com.apsolutions.util;

public class HTMLTemplate {
    public static final String MESSAGE01 = "<!DOCTYPE html>\n" +
            "<html lang=\"es\">\n" +
            "\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Confirmación de Cotización</title>\n" +
            "    <style>\n" +
            "        body {\n" +
            "            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;\n" +
            "            background-color: #f7f7f7;\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "            display: flex;\n" +
            "            justify-content: center;\n" +
            "            align-items: center;\n" +
            "            height: 100vh;\n" +
            "        }\n" +
            "\n" +
            "        .container {\n" +
            "            background-color: #ffffff;\n" +
            "            border-radius: 8px;\n" +
            "            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);\n" +
            "            overflow: hidden;\n" +
            "            width: 80%;\n" +
            "            max-width: 400px;\n" +
            "            padding: 20px;\n" +
            "            text-align: center;\n" +
            "        }\n" +
            "\n" +
            "        h2 {\n" +
            "            color: #333333;\n" +
            "            margin-bottom: 20px;\n" +
            "        }\n" +
            "\n" +
            "        p {\n" +
            "            color: #666666;\n" +
            "            line-height: 1.6;\n" +
            "            margin-bottom: 20px;\n" +
            "        }\n" +
            "\n" +
            "        .details {\n" +
            "            background-color: #f9f9f9;\n" +
            "            border-radius: 8px;\n" +
            "            padding: 15px;\n" +
            "            margin-top: 20px;\n" +
            "            text-align: left;\n" +
            "        }\n" +
            "\n" +
            "        .download-link {\n" +
            "            text-decoration: none;\n" +
            "            background-color: #3498db;\n" +
            "            color: white;\n" +
            "            padding: 12px 20px;\n" +
            "            border-radius: 5px;\n" +
            "            display: inline-block;\n" +
            "            margin-top: 20px;\n" +
            "            transition: background-color 0.3s ease;\n" +
            "        }\n" +
            "\n" +
            "        .download-link:hover {\n" +
            "            background-color: #2980b9;\n" +
            "        }\n" +
            "\n" +
            "        .footer {\n" +
            "            margin-top: 20px;\n" +
            "            color: #888888;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "    <div class=\"container\">\n" +
            "        <h2>Confirmación de Cotización</h2>\n" +
            "        <p>¡Hola [Nombre del Cliente]!</p>\n" +
            "        <p>Tu cotización ha sido generada con éxito. A continuación, encontrarás un resumen de tus selecciones:</p>\n" +
            "\n" +
            "        <div class=\"details\">\n" +
            "            <h3>Detalles de la Cotización</h3>\n" +
            "            <p>Se ha adjuntado un archivo PDF con los detalles completos de tu cotización.</p>\n" +
            "        </div>\n" +
            "\n" +
            "        <a href=\"url-a-tu-pdf\" class=\"download-link\" download>Descargar Cotización en PDF</a>\n" +
            "\n" +
            "        <div class=\"footer\">\n" +
            "            <p>Gracias por elegir nuestro servicio. Si tienes alguna pregunta, no dudes en ponerte en contacto con nosotros.</p>\n" +
            "            <p>Equipo de Cotizaciones</p>\n" +
            "            <p>[Nombre de tu Empresa]</p>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</body>\n" +
            "\n" +
            "</html>";
}
