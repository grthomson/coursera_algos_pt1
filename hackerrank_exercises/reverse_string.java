public class StringPrograms {

    public static void main(String[] args) {

        // Create a String variable and store "123" inside it
        String str = "123";

        // Call the reverse method and print the result
        // Because reverse() is static, we can call it directly
        // without creating a StringPrograms object
        System.out.println(reverse(str));
    }

    public static String reverse(String in) {

        // If someone passes null instead of a real string,
        // we stop the program and throw an error.
        // This prevents the program from crashing later.
        if (in == null)
            throw new IllegalArgumentException("Null is not valid input");

        // StringBuilder is used to build a new string step by step.
        // We use it because normal Strings cannot be changed once created.
        StringBuilder out = new StringBuilder();

        // Convert the string into an array of characters.
        // Example: "123" becomes ['1', '2', '3']
        char[] chars = in.toCharArray();

        // Start from the last character and move backwards
        for (int i = chars.length - 1; i >= 0; i--) {

            // Add each character to StringBuilder
            // This builds the reversed version
            out.append(chars[i]);
        }

        // Convert the StringBuilder back into a normal String
        // and return it
        return out.toString();
    }

}
