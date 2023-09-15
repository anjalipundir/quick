# quick
A quick sample project

What to consider when doing Low level system designing:

```
System overview: This system will be a RESTFul API that exposes endpoints for different services
Data model: The data model will consist of some entities. As the project grows we could explore on it.
API endpoints: The API will expose the following endpoints:
/endpoint: GET, POST, PUT, DELETE
Security: The API will be secured using OAuth 2.0
Logging: The API will be logged using a centralized logging system.
Monitoring: The API will be monitored using a monitoring system.

MVP:
* Expense 
    * Entry: Add new expenses with fields for date, description, category and amount.
    * List: Display a list of all entered expenses, showing date, description, category, and amount.
    * Total: Get the total expenses for the selected date range.
    * Editing: Edit update expenses.
    * Deletion: Delete expenses entry.
    
    
    // TODO - Data Validation
    // TODO Notification/log for deleted records
```
