package com.pigutu.app.utils;

import org.springframework.boot.Banner;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * Desc: Pigutu banner
 * Mail: chk@terminus.io
 * Created by IceMimosa
 * Date: 2017/9/10
 */
public class PigutuBanner implements Banner {
    private static final String[] BANNER = {
          " ________  ___  ________  ___  ___  _________  ___  ___     \n" +
                  "|\\   __  \\|\\  \\|\\   ____\\|\\  \\|\\  \\|\\___   ___\\\\  \\|\\  \\    \n" +
                  "\\ \\  \\|\\  \\ \\  \\ \\  \\___|\\ \\  \\\\\\  \\|___ \\  \\_\\ \\  \\\\\\  \\   \n" +
                  " \\ \\   ____\\ \\  \\ \\  \\  __\\ \\  \\\\\\  \\   \\ \\  \\ \\ \\  \\\\\\  \\  \n" +
                  "  \\ \\  \\___|\\ \\  \\ \\  \\|\\  \\ \\  \\\\\\  \\   \\ \\  \\ \\ \\  \\\\\\  \\ \n" +
                  "   \\ \\__\\    \\ \\__\\ \\_______\\ \\_______\\   \\ \\__\\ \\ \\_______\\\n" +
                  "    \\|__|     \\|__|\\|_______|\\|_______|    \\|__|  \\|_______|\n" +
                  "                                                            \n" +
                  "                                                            \n" +
                  "                                                            \n"
    };

    /**
     * Print the banner to the specified print stream.
     *
     * @param environment the spring environment
     * @param sourceClass the source class for the application
     * @param out         the output print stream
     */
    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        for (String line : BANNER) {
            out.println(line);
        }
        String version = "(v1.0)";
        StringBuilder padding = new StringBuilder();
        while(padding.length() < 42 - (version.length() + " :: Powered by Pigutu.inc :: ".length())) {
            padding.append(" ");
        }
        out.println(AnsiOutput.toString(AnsiColor.GREEN, " :: Powered by Pigutu.inc :: ", AnsiColor.DEFAULT, padding.toString(), AnsiStyle.FAINT, version));
        out.println();
    }
}
