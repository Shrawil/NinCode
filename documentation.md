# NinCode v1.3 Documentation

## Data Types

NinCode currently supports the following data types:

### "int"

Stores integer values.

##### Example:

10
-5
0

---

### "astr"

Stores string values.
A single character is also treated as a string.

##### Example:

"Hello"
"A"
"Game Over"

---

### "bool"

Stores boolean values.

##### Allowed values:

true
false

##### Notes:

- "0" and "1" are not allowed as boolean values.

---

Note: "float" support is planned for future versions.

---

## Current Features

### nin.out

**Outputs either a string literal or a variable value.**

##### Supports escape sequences:

`\n  new line`

`\t  tab`

`\b  backspace`

`\r  carriage return`

##### Syntax

`nin.out argument;`

##### Examples

`nin.out "Hello World!";`

`nin.out variableName;`

##### Constraints

- You cannot use a string literal and a variable together in the same command.

##### Invalid example:

nin.out "Score: " score;

---

### nin.setvar

**Creates and initializes a variable of any supported data type.**

##### Syntax

`nin.setvar dataType variableName value;`

##### Example

`nin.setvar int score 10;`

This creates a variable:

- Name: "score"
- Type: "int"
- Value: "10"

##### Notes

- Variables of different data types can share the same name.

##### Example:

`nin.setvar int value 5;`

`nin.setvar str value "five";`

##### Constraints

1. "nin.setvar" cannot be used inside loops.
2. Variables must be initialized when created.

---

### nin.delay

**Pauses execution before the next line runs.**

#### Syntax

`nin.delay seconds;`

#### Example

`nin.delay;` This will wait 1s.

`nin.delay 3000;` This Will wait 3s.

###### [NOTE] If no time is given, the command will by default wait for 1 second (1000ms) before executing next line.