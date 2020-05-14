package br.com.ribeiro.fernando.unzipsantander;

import java.io.File;

public class StartButtonMethod
{
    static void Start() throws Exception {
        final File inputFolder = new File(Path.getInput());
        final File outputFolder = new File(Path.getOutput());
        System.out.println("Criando diretorios...");
        inputFolder.mkdir();
        outputFolder.mkdir();
        System.out.println("Verificando se diretorio ja contem arquivos salvos...");
        DeleteFiles.deleteInput();
        DeleteFiles.deleteOutput();
        try {
            UuEncodeAttachmentDownload.downloadAttachment();
            UnzipParameters.unzip(Path.getInput(), Path.getOutput());
            SendEmail.sendAttachment();
        }
        catch (EmailNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
