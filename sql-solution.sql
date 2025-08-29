-- Bajaj Finserv Health Assignment - SQL Solution
-- Registration Number: REG12347 (Last two digits: 47 - ODD)
-- Question: Question 1

-- This SQL query demonstrates advanced SQL concepts including:
-- 1. Common Table Expressions (CTEs)
-- 2. Window Functions (RANK, AVG, COUNT)
-- 3. JOINs between tables
-- 4. Subqueries for filtering
-- 5. CASE statements for conditional logic
-- 6. Performance categorization based on salary analysis

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

-- Query Explanation:
-- 1. The CTE filters employees earning above 80% of company average
-- 2. Calculates department-wise statistics using window functions
-- 3. Ranks employees by salary within each department
-- 4. Categorizes performance based on salary comparison
-- 5. Returns top 3 earners per department with comprehensive metrics
