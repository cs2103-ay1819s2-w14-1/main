package braintrain.model.user;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSV {

    private static final char DEFAULT_SEPARATOR = ',';

    public static void writeLine(Writer write, List<String> values) throws IOException {
        writeLine(write, values, DEFAULT_SEPARATOR, ' ');
    }

    public static void writeLine(Writer write, List<String> values, char separators) throws IOException {
        writeLine(write, values, separators, ' ');
    }

    private static String followFormat(String value) {
        String result = value;

        if (result.contains("\"")){
            result = result.replace("\"","\"\"");
        }

        return result;
    }

    public static void writeLine(Writer write, List<String> values, char separators, char customQuote) throws IOException {
        boolean first = true;

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR;
        }

        StringBuilder sb = new StringBuilder();

        for (String value: values) {

            if(!first) {
                sb.append(separators);
            }

            if(customQuote == ' ') {
                sb.append(followFormat(value));
            }

            else {
                sb.append(customQuote).append(followFormat(value)).append(customQuote);
            }
        }
        sb.append("\n");
        write.append(sb.toString());
    }
}
