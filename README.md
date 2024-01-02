<div class="markdown prose w-full break-words dark:prose-invert light"><h1>MyFit API</h1><p>MyFit API is an application designed for managing workout programs, exercises, and user information. The application is built on the Spring Boot framework, integrating Spring Data JPA for data access, and MySQL as the primary database. Liquidbase is used for database migrations, and the application is containerized using Docker.</p><h2>Getting Started</h2><h3>Prerequisites</h3><ul><li>Java 17</li><li>MySQL Database</li><li>Docker (optional)</li></ul><h3>Configuration</h3><p>Ensure that you have the required database configured. Update the <code>application.properties</code> file located in <code>src/main/resources</code> with your MySQL database information:</p><pre><div class="bg-black rounded-md"><div class="flex items-center relative text-gray-200 bg-gray-800 dark:bg-token-surface-primary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><span>properties</span><button class="flex gap-1 items-center"><svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" class="icon-sm"><path fill-rule="evenodd" clip-rule="evenodd" d="M12 4C10.8954 4 10 4.89543 10 6H14C14 4.89543 13.1046 4 12 4ZM8.53513 4C9.22675 2.8044 10.5194 2 12 2C13.4806 2 14.7733 2.8044 15.4649 4H17C18.6569 4 20 5.34315 20 7V19C20 20.6569 18.6569 22 17 22H7C5.34315 22 4 20.6569 4 19V7C4 5.34315 5.34315 4 7 4H8.53513ZM8 6H7C6.44772 6 6 6.44772 6 7V19C6 19.5523 6.44772 20 7 20H17C17.5523 20 18 19.5523 18 19V7C18 6.44772 17.5523 6 17 6H16C16 7.10457 15.1046 8 14 8H10C8.89543 8 8 7.10457 8 6Z" fill="currentColor"></path></svg></button></div><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-properties">spring.datasource.url=jdbc:mysql://mysql:3307/jwtappdemo?serverTimezone=UTC
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root

spring.liquibase.change-log=classpath:liquibase/db.changelog-master.xml
spring.liquibase.url=jdbc:mysql://mysql:3307/jwtappdemo?serverTimezone=UTC
spring.liquibase.user=root
spring.liquibase.password=root

jwt.token.secret=1b40411fd99042ea1322ae6abaa549f3f7b867ee846aa3e6f66087003b5b35a6
jwt.token.expired=3600000

spring.main.allow-circular-references=true
</code></div></div></pre><h3>Running the Application</h3><p>Navigate to the root directory of the project and run the following command:</p><pre><div class="bg-black rounded-md"><div class="flex items-center relative text-gray-200 bg-gray-800 dark:bg-token-surface-primary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><button class="flex gap-1 items-center"><svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" class="icon-sm"><path fill-rule="evenodd" clip-rule="evenodd" d="M12 4C10.8954 4 10 4.89543 10 6H14C14 4.89543 13.1046 4 12 4ZM8.53513 4C9.22675 2.8044 10.5194 2 12 2C13.4806 2 14.7733 2.8044 15.4649 4H17C18.6569 4 20 5.34315 20 7V19C20 20.6569 18.6569 22 17 22H7C5.34315 22 4 20.6569 4 19V7C4 5.34315 5.34315 4 7 4H8.53513ZM8 6H7C6.44772 6 6 6.44772 6 7V19C6 19.5523 6.44772 20 7 20H17C17.5523 20 18 19.5523 18 19V7C18 6.44772 17.5523 6 17 6H16C16 7.10457 15.1046 8 14 8H10C8.89543 8 8 7.10457 8 6Z" fill="currentColor"></path></svg></button></div><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-bash">./mvnw spring-boot:run
</code></div></div></pre><p>Alternatively, you can use the following Docker Compose command:</p><pre><div class="bg-black rounded-md"><div class="flex items-center relative text-gray-200 bg-gray-800 dark:bg-token-surface-primary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><button class="flex gap-1 items-center"><svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" class="icon-sm"><path fill-rule="evenodd" clip-rule="evenodd" d="M12 4C10.8954 4 10 4.89543 10 6H14C14 4.89543 13.1046 4 12 4ZM8.53513 4C9.22675 2.8044 10.5194 2 12 2C13.4806 2 14.7733 2.8044 15.4649 4H17C18.6569 4 20 5.34315 20 7V19C20 20.6569 18.6569 22 17 22H7C5.34315 22 4 20.6569 4 19V7C4 5.34315 5.34315 4 7 4H8.53513ZM8 6H7C6.44772 6 6 6.44772 6 7V19C6 19.5523 6.44772 20 7 20H17C17.5523 20 18 19.5523 18 19V7C18 6.44772 17.5523 6 17 6H16C16 7.10457 15.1046 8 14 8H10C8.89543 8 8 7.10457 8 6Z" fill="currentColor"></path></svg></button></div><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-bash">docker-compose up
</code></div></div></pre><p>This will bring up both the MySQL and MyFit API containers, and your application will be accessible at <a target="_new" href="http://localhost:8080">http://localhost:8080</a>.</p><h2>API Authentication and Authorization</h2><p>Authentication and authorization are implemented using Spring Security. The <code>AuthenticationRestController</code> class handles authentication and registration requests. The following endpoints are available:</p><ul><li><code>POST /api/v1/auth/register</code>: Register a new user.</li><li><code>POST /api/v1/auth/authentication</code>: Authenticate a user.</li></ul><h3>Example Usage:</h3><p>Register a new user:</p><pre><div class="bg-black rounded-md"><div class="flex items-center relative text-gray-200 bg-gray-800 dark:bg-token-surface-primary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><button class="flex gap-1 items-center"><svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" class="icon-sm"><path fill-rule="evenodd" clip-rule="evenodd" d="M12 4C10.8954 4 10 4.89543 10 6H14C14 4.89543 13.1046 4 12 4ZM8.53513 4C9.22675 2.8044 10.5194 2 12 2C13.4806 2 14.7733 2.8044 15.4649 4H17C18.6569 4 20 5.34315 20 7V19C20 20.6569 18.6569 22 17 22H7C5.34315 22 4 20.6569 4 19V7C4 5.34315 5.34315 4 7 4H8.53513ZM8 6H7C6.44772 6 6 6.44772 6 7V19C6 19.5523 6.44772 20 7 20H17C17.5523 20 18 19.5523 18 19V7C18 6.44772 17.5523 6 17 6H16C16 7.10457 15.1046 8 14 8H10C8.89543 8 8 7.10457 8 6Z" fill="currentColor"></path></svg></button></div><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-bash">curl -X POST http://localhost:8080/api/v1/auth/register -H <span class="hljs-string">"Content-Type: application/json"</span> -d <span class="hljs-string">'{"username": "your_username", "password": "your_password", "email": "your_email"}'</span>
</code></div></div></pre><p>Authenticate a user:</p><pre><div class="bg-black rounded-md"><div class="flex items-center relative text-gray-200 bg-gray-800 dark:bg-token-surface-primary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><button class="flex gap-1 items-center"><svg width="24" height="24" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg" class="icon-sm"><path fill-rule="evenodd" clip-rule="evenodd" d="M12 4C10.8954 4 10 4.89543 10 6H14C14 4.89543 13.1046 4 12 4ZM8.53513 4C9.22675 2.8044 10.5194 2 12 2C13.4806 2 14.7733 2.8044 15.4649 4H17C18.6569 4 20 5.34315 20 7V19C20 20.6569 18.6569 22 17 22H7C5.34315 22 4 20.6569 4 19V7C4 5.34315 5.34315 4 7 4H8.53513ZM8 6H7C6.44772 6 6 6.44772 6 7V19C6 19.5523 6.44772 20 7 20H17C17.5523 20 18 19.5523 18 19V7C18 6.44772 17.5523 6 17 6H16C16 7.10457 15.1046 8 14 8H10C8.89543 8 8 7.10457 8 6Z" fill="currentColor"></path></svg></button></div><div class="p-4 overflow-y-auto"><code class="!whitespace-pre hljs language-bash">curl -X POST http://localhost:8080/api/v1/auth/authentication -H <span class="hljs-string">"Content-Type: application/json"</span> -d <span class="hljs-string">'{"username": "your_username", "password": "your_password"}'</span>
</code></div></div></pre><p>Handle authentication and registration exceptions with appropriate error messages.</p><p>Feel free to explore and extend the functionality of MyFit API as needed for your project. If you encounter any issues or have questions, please refer to the documentation or reach out to the project maintainers.</p></div>