package com.nagarro.utils.project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.nagarro.config.Configs;

public class Util_Lib {

    /****
     * Description : this function will validate if items in list are soreted or not Usage : parameter : list of items
     */
    public static boolean validate_List_Sorted(final List<String> list) {
        final List tmp = new ArrayList(list);
        Collections.sort(tmp);
        final boolean sorted = tmp.equals(list);
        return sorted;
    }

    /****
     * Description : this function will move results from current folder to archived folder Usage :
     */
    public static void moveResults() {
        final String strsrc = Configs.strResultPath;
        final String strDest = Configs.strArchReportPath;
        final File f1 = new File(strsrc);
        final File f2 = new File(strDest);
        moveDir(f1.toPath(), f2.toPath());
    }

    /****
     * Description : this function will move dir from src to desination Usage : parameter : src and destination paths
     */
    private static boolean moveDir(final Path src, final Path dest) {
        if (src.toFile().isDirectory()) {
            for (final File file : src.toFile().listFiles()) {
                moveDir(file.toPath(), dest.resolve(src.relativize(file.toPath())));
            }
        }

        try {
            Files.move(src, dest);
            return true;
        } catch (final IOException e) {
            return false;
        }
    }

    public static String replace(final String str, final String replaced, final String toReplaceWith) {

        return str.replace(replaced, toReplaceWith);
    }
}
