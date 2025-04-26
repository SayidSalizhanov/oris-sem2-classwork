package com.solncev.util;

import java.util.Map;

public class MailUtil {

    public static String buildEmailContent(Map<String, Double> rates, String sender, String baseCurrency) {
        StringBuilder contentBuilder = new StringBuilder();

        contentBuilder.append("<html><body>");
        contentBuilder.append("<h1>Hello,</h1>");
        contentBuilder.append("<p>Here are the latest currency rates for the %s:</p>".formatted(baseCurrency));

        contentBuilder.append("<table border='1' cellpadding='5' cellspacing='0'>");
        contentBuilder.append("<tr><th>Currency</th><th>Rate</th></tr>");

        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            contentBuilder.append("<tr>")
                    .append("<td>").append(entry.getKey()).append("</td>")
                    .append("<td>").append(entry.getValue()).append("</td>")
                    .append("</tr>");
        }

        contentBuilder.append("</table>");

        contentBuilder.append("<p>Best regards,<br>").append(sender).append("</p>");
        contentBuilder.append("</body></html>");

        return contentBuilder.toString();
    }
}
