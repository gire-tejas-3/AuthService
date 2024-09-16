package com.microservices.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class HtmlTemplateProcessor {

	public String getProcessedHtml(String from, String to, String subject, String text, String toName)
			throws IOException {
		// Read HTML file from resources
		ClassPathResource resource = new ClassPathResource("email.html");
		StringBuilder htmlContent = new StringBuilder();

		try (InputStream inputStream = resource.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				htmlContent.append(line).append("\n");
			}
		}
		
		String htmlString = htmlContent.toString();
		htmlString = htmlString.replace("${from}", from);
		htmlString = htmlString.replace("${to}", to);
		htmlString = htmlString.replace("${subject}", subject);
		htmlString = htmlString.replace("${textPart}", text);
		htmlString = htmlString.replace("${toName}", toName);
		htmlString = htmlString.replace("${otp}", "987615");
		
		return htmlString;
	}
}
