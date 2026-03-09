import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

//Creater - Shrawil Srivastava

public class NinCode {
    static boolean error = false;
    static int lineCount = 0;

    static ArrayList<String> strVarName = new ArrayList<>();
    static ArrayList<String> strVal = new ArrayList<>();
    static ArrayList<String> intVarName = new ArrayList<>();
    static ArrayList<Integer> intVal = new ArrayList<>();
    static ArrayList<String> boolVarName = new ArrayList<>();
    static ArrayList<Boolean> boolVal = new ArrayList<>();

    public static void callForError() {
        error = true;
        System.out.println("\nError at line " + lineCount + "!");
    }

    public static void addVar(String type, String name, String value) {
        if (type.equals("str") && !strVarName.contains(name)) {
            strVarName.add(name);
            strVal.add(value);
        } 

        else if (type.equals("int") && !intVarName.contains(name)) {
            try {
                intVarName.add(name);
                intVal.add(Integer.parseInt(value));
            } 

            catch (NumberFormatException e) {
                callForError();
            }
        } 

        else if (type.equals("bool") && !boolVarName.contains(name)) {
            try {
                boolVarName.add(name);
                boolVal.add(Boolean.parseBoolean(value));
            }
            catch (NumberFormatException e) {
                callForError();
            }
        } 

        else {
            System.out.println("Either wrong type entered or the variable already exists!");
            callForError();
        }
    }

    public static String getVar(String type, String name) {
        int index = -1;
        if (type.equals("str")) index = strVarName.indexOf(name);

        else if (type.equals("int")) {
            index = intVarName.indexOf(name);
            if (index != -1) return String.valueOf(intVal.get(index));
        } 

        else if (type.equals("bool")) {
            index = boolVarName.indexOf(name);
            if (index != -1) return String.valueOf(boolVal.get(index));
        }

        if (index != -1 && type.equals("str")) return strVal.get(index);
        return null;
    }

    public static void execute(String line, Scanner userInput) throws Exception {
        if (line.isEmpty() || line.startsWith("#") || line.equals("pass;")) return;

        if (line.endsWith(";")) {
            line = line.substring(0, line.length() - 1);
        } 

        else {
            System.out.println("Syntax Error: Expected ';' at end of line.");
            callForError();
            return;
        }

        String[] lineArgs = line.split(" ");
        String command = lineArgs[0].toLowerCase();

        if (command.equals("nin.out")) {
            if (line.contains("\"")) {
                int start = line.indexOf("\"") + 1;
                int end = line.lastIndexOf("\"");
                String content = line.substring(start, end);
                content = content.replace("\\n", "\n").replace("\\t", "\t").replace("\\b", "\b").replace("\\r", "\r").replace("\\\"", "\"");
                System.out.print(content);
            } else if (lineArgs.length == 3) {
                String val = getVar(lineArgs[1], lineArgs[2]);
                if (val != null) System.out.print(val);
                else callForError();
            } else {
                callForError();
            }
        } 
        
        
        else if (command.equals("nl")) {
            System.out.println();
        } 
        
        // nin.setvar dataType varName value; [Variable with this name must not already exist!]
        else if (command.equals("nin.setvar") && lineArgs.length == 4) {
            addVar(lineArgs[1], lineArgs[2], lineArgs[3]);
        } 
        
        // nin.delay;
        // nin.delay x; 
        // [Where x is any positive integer number!]
        else if (command.equals("nin.delay") && lineArgs.length <= 2) {
            if (lineArgs[1] < 1) {
                System.out.println("Delay value can not be smaller or equal to 0!");
                callForError();
                return;
            }
            int time = (lineArgs.length == 2) ? Integer.parseInt(lineArgs[1]) : 1000;
            Thread.sleep(time);
        }

        // nin.math var1 x + y; [Where x and y must already exist!]
        else if (command.equals("nin.math") && lineArgs.length == 5) {
            try {
                int v1 = intVarName.contains(lineArgs[2]) ? Integer.parseInt(getVar("int", lineArgs[2])) : Integer.parseInt(lineArgs[2]);
                int v2 = intVarName.contains(lineArgs[4]) ? Integer.parseInt(getVar("int", lineArgs[4])) : Integer.parseInt(lineArgs[4]);
                int res = 0;
                if(lineArgs[3].equals("+")) res = v1 + v2;
                else if(lineArgs[3].equals("-")) res = v1 - v2;
                else if(lineArgs[3].equals("*")) res = v1 * v2;
                else if(lineArgs[3].equals("/")) res = v1 / v2;
                addVar("int", lineArgs[1], String.valueOf(res));
            } catch (Exception e) { callForError(); }
        }

        // nin.in dataType varName;
        else if (command.equals("nin.in") && lineArgs.length == 3) {
            if (userInput.hasNextLine()){
                String val = userInput.nextLine();
                addVar(lineArgs[1], lineArgs[2], val);
            }
        }

        // if condition : true : false;
        else if (command.equals("if") && lineArgs.length >= 5) {
            boolean res = false;

            String[] ifPart = line.split(":");
            String condition = ifPart[0].substring(2).trim();
            String action1 = ifPart[1].trim();
            String action2 = ifPart[2].trim();

            if (boolVarName.contains(condition)) {
                res = Boolean.parseBoolean(getVar("bool", condition));
            }
        
            else if (condition.contains(" ")) {
                int v1 = intVarName.contains(lineArgs[1]) ? Integer.parseInt(getVar("int", lineArgs[1])) : Integer.parseInt(lineArgs[1]);
                String opr = lineArgs[2];
                int v2 = intVarName.contains(lineArgs[3]) ? Integer.parseInt(getVar("int", lineArgs[3])) : Integer.parseInt(lineArgs[3]);
                if (opr.equals("==")) res = (v1 == v2);
                else if (opr.equals(">=") || opr.equals("=>")) res = (v1 >= v2);
                else if (opr.equals("<=") || opr.equals("=<")) res = (v1 <= v2);
                else if (opr.equals(">")) res = (v1 > v2);
                else if (opr.equals("<")) res = (v1 < v2);
            }
        
            if (res) {
                if(!action1.contains(";")) action1 += ";";
                execute(action1, userInput);
            } 
        
            else {
                if(!action2.contains(";")) action2 += ";";
                execute(action2, userInput);
            }
        }
        
        // nin.loop x : action1 : action2 : action3...;
        else if (command.equals("nin.loop")) {
            if (!lineArgs[3].equals("nin.setvar")) {
                if (!line.contains(":")) {
                    System.out.println("Not enough argument to perform through loop!");
                    callForError();
                    return;
                }
                String[] parts = line.split(":");
                int iterate = Integer.parseInt(lineArgs[1]);
                for(int i = 0; i < iterate && !error; i++) {
                    for(int j = 1; j < parts.length && !error; j++) {
                        if (!parts[j].contains(";")) parts[j] += ";";
                        execute(parts[j].trim(), userInput);
                    }
                }
            }
            else { System.out.println("You can not run \'nin.setvar\' in a loop!"); callForError(); } 
        }

        // nin.rand a min max;
        /*else if (command.equals("nin.rand")) {
            if (lineArgs.length == 4) {
                Random random = new Random();
                int min, max;
                if (intVal.contains(lineArgs[2])) min = Integer.parseInt(intVal.get(lineArgs[2]));
                else min = Integer.parseInt(lineArgs[2]);
                if (intval.contains(lineArgs[3])) max = Integer.parseInt(intVal.get(lineArgs[3]));
                else max = Integer.parseInt(lineArgs[3]);
                int val = random.nextInt(min, max);
                addVar("int", lineArgs[1], val);
            }
            else {
                System.out.println("Not enough values!\nnin.rand <non-existing variable name> <min> <max>;");
                callForError();
            }
        }*/

        else {
            callForError();
        }
    }

    public static void main(String[] args) {
        try {
            File myFile = new File("script.nc");
            if (!myFile.exists()) {
                System.out.println("Error: script.nc not found!");
                return;
            }
            Scanner scanner = new Scanner(myFile);
            Scanner userInput = new Scanner(System.in);

            while (scanner.hasNextLine() && !error) {
                lineCount++;
                String line = scanner.nextLine().trim();
                execute(line, userInput);
            }
            scanner.close();
            userInput.close();

        } 

        catch (Exception e) {
            error = true;
            System.out.println("System Error: " + e.getMessage());
        }
        
        if (!error) System.out.println("\nCode Executed Successfully!");
        else System.out.println("Code ended with error!");
    }
}
