package br.com.ribeiro.fernando.unzipsantander;

import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import java.util.Arrays;
import java.io.File;

public class UnzipParameters
{
    public static void unzip(final String folder, final String destination) throws ZipException {
        final File path = new File(folder);
        final List<File> listOfFiles = Arrays.asList(path.listFiles());
        for (final File x : listOfFiles) {
            final net.lingala.zip4j.core.ZipFile zipFile = new ZipFile(x);
            try {
                zipFile.extractAll(destination);
                System.out.println(String.valueOf(x.getName()) + " - Descompactado.");
            }
            catch (ZipException e) {
                System.out.println(String.valueOf(x.getName()) + " - Está corrompido!");
            }
        }
    }
}
