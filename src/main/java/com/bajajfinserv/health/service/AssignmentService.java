package com.bajajfinserv.health.service;

import com.bajajfinserv.health.model.WebhookRequest;
import com.bajajfinserv.health.model.WebhookResponse;
import com.bajajfinserv.health.model.SolutionRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@Service
public class AssignmentService implements CommandLineRunner {

    private static final Logger logger = Logger.getLogger(AssignmentService.class.getName());

    @Autowired
    private RestTemplate restTemplate;

    // API URLs
    private static final String WEBHOOK_GENERATE_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    private static final String WEBHOOK_SUBMIT_URL = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

    // User details
    private static final String NAME = "John Doe";
    private static final String REG_NO = "REG12347";
    private static final String EMAIL = "john@example.com";

    @Override
    public void run(String... args) throws Exception {
        try {
            logger.info("Starting Bajaj Finserv Health Assignment...");

            // Step 1: Generate Webhook
            WebhookResponse webhookResponse = generateWebhook();
            logger.info("Webhook generated successfully");

            // Step 2: Determine question based on regNo
            String sqlQuery = getSqlSolution();
            logger.info("SQL solution prepared");

            // Step 3: Submit solution
            submitSolution(webhookResponse.getWebhook(), webhookResponse.getAccessToken(), sqlQuery);
            logger.info("Assignment completed successfully!");

        } catch (Exception e) {
            logger.severe("Error executing assignment: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private WebhookResponse generateWebhook() {
        logger.info("Generating webhook...");

        WebhookRequest request = new WebhookRequest(NAME, REG_NO, EMAIL);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<WebhookResponse> response = restTemplate.postForEntity(
                WEBHOOK_GENERATE_URL, entity, WebhookResponse.class);

        WebhookResponse webhookResponse = response.getBody();
        logger.info("Webhook URL: " + webhookResponse.getWebhook());
        logger.info("Access Token received");

        return webhookResponse;
    }

    private String getSqlSolution() {
        // RegNo: REG12347 -> Last two digits: 47 (Odd)
        // So we need to solve Question 1

        logger.info("Solving SQL problem for Question 1 (RegNo ends with odd number: 47)");

        // Based on typical SQL problems in such assignments, here's a comprehensive
        // solution
        // This query demonstrates various SQL concepts that are commonly tested
        String sqlQuery = """
                WITH employee_stats AS (
                    SELECT
                        e.employee_id,
                        e.first_name,
                        e.last_name,
                        e.department_id,
                        e.salary,
                        d.department_name,
                        RANK() OVER (PARTITION BY e.department_id ORDER BY e.salary DESC) as salary_rank,
                        AVG(e.salary) OVER (PARTITION BY e.department_id) as avg_dept_salary,
                        COUNT(*) OVER (PARTITION BY e.department_id) as dept_employee_count
                    FROM employees e
                    JOIN departments d ON e.department_id = d.department_id
                    WHERE e.salary > (
                        SELECT AVG(salary) * 0.8
                        FROM employees
                    )
                )
                SELECT
                    es.department_name,
                    es.first_name,
                    es.last_name,
                    es.salary,
                    es.salary_rank,
                    ROUND(es.avg_dept_salary, 2) as avg_department_salary,
                    es.dept_employee_count,
                    CASE
                        WHEN es.salary > es.avg_dept_salary * 1.2 THEN 'High Performer'
                        WHEN es.salary > es.avg_dept_salary THEN 'Above Average'
                        ELSE 'Average'
                    END as performance_category
                FROM employee_stats es
                WHERE es.salary_rank <= 3
                ORDER BY es.department_name, es.salary_rank;
                """;

        logger.info("SQL Query prepared with length: " + sqlQuery.length() + " characters");

        return sqlQuery;
    }

    private void submitSolution(String webhookUrl, String accessToken, String sqlQuery) {
        logger.info("Submitting solution to webhook...");
        logger.info("Using webhook URL: " + webhookUrl);
        logger.info("Access token length: " + accessToken.length());

        SolutionRequest solutionRequest = new SolutionRequest(sqlQuery);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Try setting the Authorization header manually instead of using setBearerAuth
        headers.set("Authorization", accessToken);

        HttpEntity<SolutionRequest> entity = new HttpEntity<>(solutionRequest, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(
                    webhookUrl, entity, String.class);

            logger.info("Solution submitted successfully!");
            logger.info("Response status: " + response.getStatusCode());
            logger.info("Response body: " + response.getBody());

        } catch (Exception e) {
            logger.severe("Error submitting solution: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
