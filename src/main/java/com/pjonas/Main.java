package com.pjonas;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class Main {
    public static void main(String[] args) {
        try{

            String dados = "https://github.com/pjonas21";

            // configuração para definir o nível de correção de erros e a margem
            Hashtable<EncodeHintType, Object> config = new Hashtable<>();
            config.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            config.put(EncodeHintType.MARGIN, 1);

            // criação do qrcode utilizando BitMatrix
            MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix bitMatrix = writer.encode(dados, BarcodeFormat.QR_CODE, 300, 300, config);

            // criação do arquivo de saída
            File arquivo = new File("qr_code.png");
            try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(arquivo))) {
                MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            }

            System.out.println("QR Code gerado com sucesso.");

        } catch (Exception ex) {
            System.err.println("Erro ao gerar QR Code: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}