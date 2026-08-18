\*\*\* Course Recommendation AI Agent



\## What it does

This AI agent takes a student's profile (background, goals, skills) and recommends a personalized learning path with 3 courses in order with explanations.



\-----------------------------------------------------------------------------------------------------------



\*\*\* How to Run



\*\*\*Prerequisites

\- Java 17 or higher

\- Maven

\- Groq API Key (free from console.groq.com)



\*\*\*Setup Instructions

1\. Clone this repository

2\. Add your Groq API key in application.properties`

3\. Build: mvn clean compile

4\. Run: mvn spring-boot:run



\-------------------------------------------------------------------------------------------------------



\*\*\*Test the API



\*\*\* Using curl:

&#x20;bash

curl -X POST http://localhost:8082/api/recommendations -H "Content-Type: application/json" -d "{\\"name\\":\\"Rahul\\",\\"background\\":\\"CS student\\",\\"goals\\":\\"Data Scientist\\",\\"skills\\":\\"Python\\"}"



\-------------------------------------------------------------------------------------------------------

\## Sample Student Profiles



| Name | Background | Goal | Skills |

|------|-----------|------|--------|

| Rahul Sharma | Computer Science graduate | Data Scientist | Python, SQL, C++ |

| Priya Patel | Self-taught developer | Full Stack Developer | HTML, CSS, JavaScript, React |

| Amit Kumar | Cloud Engineer | DevOps Engineer | AWS, Linux, Docker, Python |

| Sneha Reddy | MBA in Marketing | Data Analytics | Excel, SQL, Basic Python |

\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_-



\## Design Choices



\- \*\*Framework:\*\* Spring Boot for REST API

\- \*\*AI Model:\*\* Groq (free, no credit card required)

\- \*\*Course Catalog:\*\* 10 courses with prerequisites

\- \*\*Storage:\*\* In-memory (no database needed)

\- \*\*Fallback:\*\* Rule-based logic if AI fails

\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_



\## Tradeoffs



\- In-memory storage (data lost on restart)

\- Limited to 10 courses

\- Simple fallback recommendations

\- No authentication or frontend

\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_



\## Future Improvements



\- Add database for course persistence

\- Expand course catalog

\- Create frontend UI

\- Add user authentication

\- Support more AI models

\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_\_



\## Project Deliverables



\- ✅ Course catalogue (10 courses)

\- ✅ 4 sample student profiles

\- ✅ Recommendation with rationales

\- ✅ Working API endpoint

\- ✅ README with instructions

