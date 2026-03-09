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

##### Supports escape sequences

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

##### Invalid Example

`nin.out "Score: " score;`

---

### nin.setvar

**Creates and initializes a variable of any supported data type.**

##### Syntax

`nin.setvar dataType variableName value;`

##### Example

`nin.setvar int score 10;`

This creates a variable:

- Name: **score**
- Type: **int**
- Value: **10**

##### Notes

- Variables of different data types can share the same name.

##### Example

`nin.setvar int value 5;`

`nin.setvar str value "five";`

##### Constraints

1. `nin.setvar` cannot be used inside loops.
2. Variables must be initialized when created.

---

### nin.delay

**Pauses execution before the next line runs.**

##### Syntax

`nin.delay milliseconds;`

##### Examples

`nin.delay;`

Waits **1 second (1000ms)**.

`nin.delay 3000;`

Waits **3 seconds**.

##### Notes

If no time is given, the command waits **1 second (1000ms)** before executing the next line.

---

### nl

**Adds a new line character since nin.out does not add one by default.**

##### Syntax

`nl;`

##### Example

`nin.out "Hello";`  
`nl;`  
`nin.out "World";`

Output:

Hello  
World

---

### nin.in

**Takes input from the user and stores it inside a variable.**

##### Syntax

`nin.in dataType variableName;`

##### Example

`nin.in str playerName;`

`nin.in int score;`

##### Notes

- The variable must not already exist.
- Input is taken from the user during execution.

---

## New Features

### nin.math

**Performs arithmetic operations and stores the result in a new integer variable.**

##### Supported operators

`+`  
`-`  
`*`  
`/`

##### Syntax

`nin.math resultVariable value1 operator value2;`

##### Example

`nin.math sum 5 + 3;`

Result:

sum = 8

##### Using Variables

`nin.math total score + bonus;`

##### Notes

- Values can be **integer literals or integer variables**.
- The result variable must **not already exist**.

---

### if

**Executes one command if a condition is true, otherwise executes another command.**

##### Syntax

`if condition : trueAction : falseAction;`

##### Example

`if score > 10 : nin.out "You Win!" : nin.out "Try Again";`

##### Using Boolean Variable

`if gameOver : nin.out "Game Finished" : pass;`

##### Supported comparison operators

`==`  
`>`  
`<`  
`>=`  
`<=`

##### Notes

- Only **one command per branch** is supported.

---

### nin.loop

**Repeats a set of commands multiple times.**

##### Syntax

`nin.loop iterations : command1 : command2 : command3;`

##### Example

`nin.loop 3 : nin.out "Hello" : nl;`

Output:

Hello  
Hello  
Hello

##### Notes

- Commands are separated using `:`
- `nin.setvar` cannot be used inside loops.

---

### nin.rand

**Generates a random integer within a given range.**

##### Syntax

`nin.rand variableName min max;`

##### Example

`nin.rand number 1 10;`

This creates an integer variable:

number = random value between 1 and 10

##### Using Variables

`nin.rand value minVar maxVar;`

##### Notes

- The variable must **not already exist**.
- `min` and `max` can be **integer literals or integer variables**.

---

### pass

**Does nothing. Used when no action is required.**

##### Syntax

`pass;`

##### Example

`if score > 10 : nin.out "Winner" : pass;`

---

## Comments

Lines starting with `#` are treated as comments and ignored during execution.

##### Example

`# This is a comment`

`nin.out "Hello";`

---

## Script File

NinCode reads instructions from the file:

`script.nc`

The interpreter executes each line sequentially until the program finishes or an error occurs.

---

## Execution Result

If the script runs successfully:

`Code Executed Successfully!`

If an error occurs:

`Error at line X!`

`Code ended with error!`
