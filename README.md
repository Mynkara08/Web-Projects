# Rule Engine with AST

## Overview
This project is a simple 3-tier rule engine application designed to determine user eligibility based on attributes such as age, department, income, spend, etc. The system leverages an **Abstract Syntax Tree (AST)** to represent conditional rules dynamically. The rule engine allows for the creation, combination, and modification of rules based on user-defined conditions.

## Objective
The goal of this application is to create a flexible rule engine where rules can be built, combined, and evaluated to determine eligibility criteria for users. This is achieved using AST to represent these rules dynamically, providing a structure that can easily be manipulated and expanded.

## Architecture
The project follows a **3-tier architecture**:
1. **Frontend (UI)**: A simple interface where users can define rules and evaluate user eligibility based on attributes.
2. **API Layer**: Serves as the middle layer, handling user requests and interactions between the frontend and backend.
3. **Backend (Data)**: Manages the rule logic, including rule creation, combination, and evaluation using AST.

## Features
- **Rule Creation**: Users can define individual rules based on user attributes (e.g., age, income).
- **Rule Combination**: Multiple rules can be combined using logical operators (AND, OR) to form more complex eligibility conditions.
- **AST Representation**: Every rule and combination of rules is represented as an AST, enabling flexibility in rule manipulation.
- **Dynamic Evaluation**: User eligibility can be evaluated dynamically based on the provided rules and input data.

## API Design
1. **create_rule(rule_string)**:  
   This function takes a string representing a rule & rule name. It returns a `Node` object representing the corresponding AST.

   Example:
   ```bash
   https://rule-engine-64c2.onrender.com/api/v1/rules/create
   ```
   
   ```bash
   {
    "rule": "((age > 50 AND department = 'Tax') OR (age < 21 AND department = 'Employee')) AND (salary > 60000 OR experience > 6)",
    "name": "Rule1"
   }
   ```
   **Sample Response**
   ```bash
   {
    "success": true,
    "message": "AST Tree Created Successfully and saved",
    "AST": {
        "rule_id": 25,
        "name": "Rule8",
        "ast": {
            "left": {
                "left": {
                    "left": {
                        "left": null,
                        "type": "operand",
                        "right": null,
                        "value": "age > 50"
                    },
                    "type": "AND",
                    "right": {
                        "left": null,
                        "type": "operand",
                        "right": null,
                        "value": "department = 'Tax'"
                    },
                    "value": null
                },
                "type": "OR",
                "right": {
                    "left": {
                        "left": null,
                        "type": "operand",
                        "right": null,
                        "value": "age < 21"
                    },
                    "type": "AND",
                    "right": {
                        "left": null,
                        "type": "operand",
                        "right": null,
                        "value": "department = 'Employee'"
                    },
                    "value": null
                },
                "value": null
            },
            "type": "AND",
            "right": {
                "left": {
                    "left": null,
                    "type": "operand",
                    "right": null,
                    "value": "salary > 60000"
                },
                "type": "OR",
                "right": {
                    "left": null,
                    "type": "operand",
                    "right": null,
                    "value": "experience > 6"
                },
                "value": null
            },
            "value": null
        }
    }
    }
2. **combine_rules(rules)**:
This function takes multiple rule strings and combines them into a single AST using the operator provided. The function returns the root node of the combined AST.

  Example: 
  ```bash
  https://rule-engine-64c2.onrender.com/api/v1/rules/combine
   ```
   
   ```bash
   {
    "rules": [
        "((age > 50 AND department = 'Tax') OR (age < 21 AND department = 'Employee')) AND (salary > 60000 OR experience > 6)",
        "((age <25 50 OR department = 'Income') OR (age > 51 OR department = 'Employee')) OR (salary > 60000 OR experience < 8)"
    ],
    "operator": "AND"
  }
   ```
**Sample Response**
```bash
{
    "success": true,
    "message": "AST Tree Created Successfully and saved",
    "AST": {
        "rule_id": 26,
        "name": "combined1729621685154",
        "ast": {
            "left": {
                "left": {
                    "left": {
                        "left": {
                            "left": null,
                            "type": "operand",
                            "right": null,
                            "value": "age > 50"
                        },
                        "type": "AND",
                        "right": {
                            "left": null,
                            "type": "operand",
                            "right": null,
                            "value": "department = 'Tax'"
                        },
                        "value": null
                    },
                    "type": "OR",
                    "right": {
                        "left": {
                            "left": null,
                            "type": "operand",
                            "right": null,
                            "value": "age < 21"
                        },
                        "type": "AND",
                        "right": {
                            "left": null,
                            "type": "operand",
                            "right": null,
                            "value": "department = 'Employee'"
                        },
                        "value": null
                    },
                    "value": null
                },
                "type": "AND",
                "right": {
                    "left": {
                        "left": null,
                        "type": "operand",
                        "right": null,
                        "value": "salary > 60000"
                    },
                    "type": "OR",
                    "right": {
                        "left": null,
                        "type": "operand",
                        "right": null,
                        "value": "experience > 6"
                    },
                    "value": null
                },
                "value": null
            },
            "type": "OR",
            "right": {
                "left": {
                    "left": {
                        "left": {
                            "left": null,
                            "type": "operand",
                            "right": null,
                            "value": "age < 25"
                        },
                        "type": "OR",
                        "right": {
                            "left": null,
                            "type": "operand",
                            "right": null,
                            "value": "department = 'Income'"
                        },
                        "value": null
                    },
                    "type": "OR",
                    "right": {
                        "left": {
                            "left": null,
                            "type": "operand",
                            "right": null,
                            "value": "age > 51"
                        },
                        "type": "OR",
                        "right": {
                            "left": null,
                            "type": "operand",
                            "right": null,
                            "value": "department = 'Employee'"
                        },
                        "value": null
                    },
                    "value": null
                },
                "type": "OR",
                "right": {
                    "left": {
                        "left": null,
                        "type": "operand",
                        "right": null,
                        "value": "salary > 60000"
                    },
                    "type": "OR",
                    "right": {
                        "left": null,
                        "type": "operand",
                        "right": null,
                        "value": "experience < 8"
                    },
                    "value": null
                },
                "value": null
            },
            "value": null
        }
    }
  }
```

3. **evaluate_rule(JSON data)**:
This function takes a JSON representing the combined rule's AST and a dictionary containing user attributes.
The function evaluates the rule against the provided data and returns **True** if the user is eligible based on the rule, **False** otherwise.

  Example:
  ```bash
  https://rule-engine-64c2.onrender.com/api/v1/rules/evaluate
  ```

  ```bash
  {
    "ast": "((age > 50 AND department = 'Tax') OR (age < 21 AND department = 'Employee')) AND (salary > 60000 OR experience > 6)",
    "data": {
        "age": 35,
        "department": "Sales",
        "salary": 60000,
        "experience": 3
    }
  }
  ```
**Sample Response**
```bash
{ "success":true,
  "result":false
}
```

