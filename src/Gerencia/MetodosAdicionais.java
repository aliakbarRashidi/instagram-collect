/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gerencia;

import Data.Photo;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author labic
 */
public class MetodosAdicionais {

    /**
     *
     * @param url_
     * @return
     * @throws IOException
     */
    public String getPage(String url_) throws IOException {

        URL url = new URL(url_);

        String texto = "";

        try {
            if (conexao(url.toString())) {
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                //  BufferedWriter out = new BufferedWriter(new FileWriter(file));
                String inputLine;
                texto = "";
                int i = 1;
                while ((inputLine = in.readLine()) != null) {

                    texto += inputLine + "\n";

                    i++;

                }

                in.close();
            } else {

                System.out.println("acesse: \n" + url_);
            }

        } catch (Exception e) {
        }

        return texto;
    }

    public void writeCSV() {

    }

    public String setHtml(Photo p) {

        String legend = p.getCaption().getText();
        if (legend == null) {
            legend = "";
        }
        String html = "";

        if (p.getType().compareTo("video") == 0) {

            html = "<center>" + legend + "</center>\n"
                    + "\n"
                    + "<center>\n"
                    + "<p><b>Usuário: </b>" + p.getUser().getFull_name() + " &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<b>Tags:</b> " + p.getTags().replace(" ", ",") + "<span style=\"line-height: 20.7999992370605px;\">&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span><b>Likes:</b>" + p.getLikes().getCount() + "</p>\n"
                    + "\n"
                    + "<video src=\"" + p.getVideos().getLow_resolution().getUrl() + "\" width=\"" + p.getVideos().getLow_resolution().getWidth() + "px\" height=\"" + p.getVideos().getLow_resolution().getWidth() + "px\" controls>\n"
                    + "<p>If you are reading this, it is because your browser does not support the 'video' element. Try using the 'object' element listed further down the page.</p>\n"
                    + "</video>"
                    + "</center>\n"
                    + "\n"
                    + "<hr/>";

        } else {

            html = "<center>" + legend + "</center>\n"
                    + "\n"
                    + "<center>\n"
                    + "<p><b>Usuário: </b>" + p.getUser().getFull_name() + " &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;<b>Tags:</b> " + p.getTags().replace(" ", ",") + "<span style=\"line-height: 20.7999992370605px;\">&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span><b>Likes:</b>" + p.getLikes().getCount() + "</p>\n"
                    + "\n"
                    + "<p><a href=\"" + p.getLink() + "\"><img alt=\"" + p.getCaption().getText() + "\" src=\"" + p.getImages().getStandard_resolution().getUrl() + "\" style=\"max-width:100%\" /></a></p>\n"
                    + "</center>\n"
                    + "\n"
                    + "<hr/>";

        }
        return html;
    }

    /**
     *
     * @param local
     * @param url_s
     * @return
     * @throws IOException
     */
    public boolean getImagem(String local, String url_s) throws IOException {

        //  url_s +=format;
        URL url = new URL(url_s);
        boolean resposta = false;

        URLConnection urrl = url.openConnection();

        InputStream in = null;
        try {
            in = url.openStream();
        } catch (Exception e) {

            System.out.println("nao");
            resposta = false;
        }
        //   in = url.openStream();

        if (in != null) {
            resposta = true;
            OutputStream out = new FileOutputStream(local);
            byte[] buf = new byte[100000];
            int n = 0;
            int qnt = 0;

//            System.out.print(" tamanho: " + urrl.getContentLengthLong() / 1000.0 + "kb [");
            do {
                n = in.read(buf);
                qnt += n;
                out.write(buf, 0, n);
                out.flush();
//                System.out.print(" " + qnt * 100 / urrl.getContentLengthLong() + "% ");

            } while (qnt < urrl.getContentLength());
//            System.out.println("]");
            in.close();
            out.close();
            //    System.out.println("} Foto  baixada com sucesso! Tamanho: ~"+urrl.getContentLengthLong()/1000+"kb\n");

        }

        return resposta;

    }

    /**
     *
     * @param url
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public boolean conexao(String url) throws MalformedURLException, IOException {
        boolean resposta;
        URL teste = new java.net.URL(url);
        URLConnection conn = teste.openConnection();
        HttpURLConnection httpConn = (HttpURLConnection) conn;
        httpConn.connect();

        resposta = httpConn.getResponseCode() == 200;
        // System.out.println(resposta);    
        return resposta;

    }

    public void download(String caminho_origem, String last_link, String caminho_destino, char separator, String tipo) throws IOException {

        Leitura leitura = new Leitura(caminho_origem);
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);

        int i = 0;
        int j;
        System.out.println("Iniciando Download de \t" + tipo);
        int qnt = leitura.lista.size();
        for (j = 0; j < qnt; j++) {
            if (j % 20 == 0 && j > 0) {

                long timeSleep = 5;
                System.out.println("progresso de  " +tipo+" "+ df.format(j * 100.0 / qnt) + "%");
                System.out.println("tempo dormindo: " + timeSleep + " segundos.");
                try {
                    Thread.sleep(1000 * timeSleep);
                } catch (InterruptedException ex) {
                    System.out.println("error ao dormir no download");
                }

            }
            String col1 = "";
            String col2 = String.valueOf(i);

            String tempLine = leitura.lista.get(i);

            String tempLineVector[] = tempLine.split(String.valueOf(separator));
            col1 = tempLineVector[0].replace("\"", "");
            if (tempLineVector.length > 1) {
                col2 = tempLineVector[1].replace("\"", "");
            }

//                String tempVector[] = col1.split("\\.");
            String formato = "";
            if (col1.contains(".")) {
                formato = col1.split("\\.")[col1.split("\\.").length - 1];
            }

//            try {
//                new Thread().sleep(0000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(MetodosAdicionais.class.getName()).log(Level.SEVERE, null, ex);
//            }
            String file = caminho_destino + col2 + "." + formato;

            if (!new File(file).exists()) {
                System.out.println("Salvo em:\t " + file);
                getImagem(file, col1);
            }
            i++;

        }

        if (qnt == 0) {
            qnt = 1;
            j = 1;
        }
        System.out.println("progresso de  " +tipo+" "+ df.format(j * 100.0 / qnt) + "% finalizado!");
        System.out.println("foram baixados: " + j + " " + tipo + ".");

    }
}
