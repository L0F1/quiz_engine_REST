# Quiz engine REST API
This is a simple Spring REST service for solving quizzes. The service allows you to receive and solve quizzes, as well as create, modify and delete your own quizzes. The service provides user registration. Authentication is via HTTP Basic auth. The embedded H2 is used as the data storage.
## Getting Started
In application.properties you can specify the name of the database and username and password to access the database.

The service provides a built-in user with the username "admin". The application reads the password from the environment variable "ADMIN_PASS". To set the password, you must set an environment variable with this name, otherwise the password "password" will be used.
# {{Request.Name}}

{{Request.Description}}

### Prerequisites

One of the following scopes are required to execute this request:

{{#foreach Request.Scopes}}
* {{Name}}
{{/foreach}}

### HTTP Request

```
{{Request.ExampleRequestUrl}}
```
### Request parameters

In the request URL, provide the following query parameters with values.

| Parameter | Type | Description |
|:----------|:-----|:------------|

### Optional request headers

| Name | Value |
|:-----|:------|

### Request body

Do not supply a request body with this method.

### Example

##### Request

```http
```

##### Response

```http
```

# Resources

## 1. Quiz
Represents a quiz to send and receive from the API.
#### JSON representation
Here is a JSON representation of `Quiz`.

<!-- { "blockType": "resource",
"@type": "author",
"optionalProperties": [] } -->
```json
{
"title": "Math",
"text": "Which of the following is equal to 4?",
"options": ["1+3","2+2","8-1"],
"answer" : [0,1]
}
```
### Properties
| Property | Constraint | Description |
|:---------|:-----|:------------|
| **title**   | not null | Quiz name |
| **text** | not null | Quiz description |
| **options** | not null | Answer options |
| **answer** | nullable | Correct answers |

## 2. ResponseAnswer
Used to respond to a user's answer to a quiz.
#### JSON representation
Here is a JSON representation of `ResponseAnswer`.

<!-- { "blockType": "resource",
"@type": "author",
"optionalProperties": [] } -->
```json
{
"success": "true",
"feedback": "Congratulations, you're right!",
}
```
### Properties
| Property | Constraint | Description |
|:---------|:-----|:------------|
| **success**   | not null | Boolean |
| **feedback** | not null | Feedback about user answer |

