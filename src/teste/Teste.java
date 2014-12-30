/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

/**
 *
 * @author andrei
 */
import Data.Photo;
import Gerencia.GerenciaGetTag;
import Gerencia.Instagram;
import Gerencia.MetodosAdicionais;
import Gerencia.TagsRecents;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONObject;

public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        long currentTimestamp = System.currentTimeMillis() / 1000 ;
        long targetTimestamp = 0;

        //padrão
        int quantidadeBaixadas = 100;

        String tag = "enem";
        int minutosAnalise = 60 * 1;
        char separatorCSV = ',';
        
        
        ArrayList<String> blocks = new ArrayList<>();
        
        blocks.add("oficialyasmin");
        blocks.add("haroldocerqueiira");

        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        int hora = calendar.get(Calendar.HOUR_OF_DAY);

        System.out.println();
        System.out.println();
        String fileName = String.valueOf(ano) + "-" + String.valueOf(mes) + "-" + String.valueOf(dia) + "_horario=" + 2 + hora + "h.csv";

        if (args.length > 0) {
            tag = args[0];

            if (args.length > 1) {
                minutosAnalise = Integer.parseInt(args[1]);

            }

            if (args.length > 2) {
                fileName = args[2] + ".csv";

            }
            if(args.length > 3)
            {
                System.out.println(args[3]);
                separatorCSV = args[3].charAt(0);
            }

        }

        System.out.println(currentTimestamp);

        // TODO code application logic here
        String CLIENT_ID = "9bf0e68e9e424b109489a9efc87c1638";
        String CLIENT_SECRET = "8894688d6b344b0d90ef9b887135abd8";
        String access_token = "644397518.1fb234f.b81ed940c95245d7995ef661f0618afe";

        Instagram mi = new Instagram(CLIENT_ID, CLIENT_SECRET, access_token);

        GerenciaGetTag gerenciaGetTag = new GerenciaGetTag();

        String fileSeparator = System.getProperty("file.separator");
        String mainDir = "/var/www/hash-web/public/instagram" + fileSeparator;

        String imagesDir = mainDir + "images" + fileSeparator;

        File pasta = new File(mainDir);

        if (!pasta.exists()) {
            new File(mainDir).mkdir();

            pasta.mkdir();
//            System.out.println(pasta.getAbsolutePath() +file_separador +"download"+file_separador);

        }
        pasta = new File(imagesDir);

        if (!pasta.exists()) {
            new File(imagesDir).mkdir();

            pasta.mkdir();
//            System.out.println(pasta.getAbsolutePath() +file_separador +"download"+file_separador);

        }

        System.out.println(
                "mainDir:    " + mainDir);
        System.out.println(
                "imagesDir:  " + imagesDir);

        JSONObject tagRecent = mi.getRecentTag(tag, "", "");
        JSONObject Media_count = mi.getMedia_Count(tag);
        JSONObject tempMedia_count = Media_count.getJSONObject("data");

        TagsRecents tagsRecents = gerenciaGetTag.getTagsRecentsNEW(tagRecent);

        int code = tagsRecents.getMeta().getCode();

        System.out.println(
                "tag:    " + tempMedia_count.getString("name"));

        int media_count = tempMedia_count.getInt("media_count");

        //System.out.println("quantidade de midias:   " + quantidadeBaixadas);
        double horas = quantidadeBaixadas * (1.1 / 6000);
        double minutos = ((horas - (long) horas) * 60);
        double segundos = (minutos - (long) minutos) * 60;
//        System.out.println(segundos);
        // System.out.println("tempo aproximado de coleta: " + (long) horas + " horas " + (long) minutos + " minutos e " + (long) segundos + " segundos");

        CSVWriter cSVWriter_data = null;
        CSVWriter cSVWriter_links = null;
        CSVWriter cSVWriter_images_download = null;

        try {
            cSVWriter_data = new CSVWriter(new FileWriter(new File(mainDir + fileName)),separatorCSV, '\0', '\0');
            cSVWriter_links = new CSVWriter(new FileWriter(new File(mainDir + "links.csv")),separatorCSV);
            cSVWriter_images_download = new CSVWriter(new FileWriter(new File(mainDir + "images_download.csv")),separatorCSV);

            String[] fistLine = {"url", "user_username", "like", "link", "location_name", "location_id", "location_latitude", "location_longitude", "filter", "created_time", "user_profile_picture", "user_full_name", "user_id", "data_legivel"};

            cSVWriter_data.writeNext(fistLine);

        } catch (IOException iOException) {

            System.out.println("error 1: falha ao tentar criar os arquivos de escrita, verifique a sua permissão de usuário.");
            System.exit(1);
        }

        int sizeFor = quantidadeBaixadas / 20;

        if (sizeFor
                < 1) {  
            sizeFor = 1;
        }
        int i = 0;

        
         
                    ArrayList<String > caracteresMalucos = new ArrayList<>();
                    caracteresMalucos.add("\\|");
                    caracteresMalucos.add("\\,");
                    caracteresMalucos.add(String.valueOf(separatorCSV));
                    caracteresMalucos.add(String.valueOf("\""));
                    caracteresMalucos.add(String.valueOf("'"));
                    caracteresMalucos.add(String.valueOf("\'"));
                    caracteresMalucos.add(String.valueOf("\n"));
                    caracteresMalucos.add(String.valueOf("\t"));
                    caracteresMalucos.add(String.valueOf("\r"));
                    caracteresMalucos.add(String.valueOf("\b"));
                    
        do {

            cSVWriter_links.writeNext(new String[]{tagsRecents.getPagination().getNext_url()});
            System.out.println("numero midias:" + i * 20 + "    ");
            try {
                if (code == 429) {
                    new MetodosAdicionais().download(mainDir + "images_download.csv", "", imagesDir,separatorCSV);
                    System.err.println("error_message: The maximum number of requests per hour has been exceeded.");

                    try {
                        try {
                            cSVWriter_data.flush();
                            cSVWriter_data.close();
                            cSVWriter_links.flush();
                            cSVWriter_links.close();
                            cSVWriter_images_download.flush();
                            cSVWriter_images_download.close();

                        } catch (IOException ex) {

                        }
                        long timeSleep = 10;
                        System.out.println("tempo dormindo: " + timeSleep + " minutos.");
                        new Thread().sleep(1000 * 60 * timeSleep);
                    } catch (Exception e) {
                    }
                }

//                System.out.println("progresso: " + (i * 100.0 / (sizeFor)) + "%");
                for (Photo p : tagsRecents.getData().getPhoto()) {
                    String tempUser = p.getUser().getUsername().toLowerCase();                    
                    
                    if (blocks.contains(tempUser))
                    {
                        continue;
                    }
                    
                    String tempUserFullName = p.getUser().getFull_name().replaceAll("\"", "");
                    
                    
                        
                   
                    
                    targetTimestamp = Long.parseLong(p.getCreated_time());                                                                                                                                                    //,"location_name","location_id","location_latitude","location_longitude","filter","created_time","user_profile_picture","user_full_name","user_id","data_legivel"}

                    calendar.setTimeInMillis(targetTimestamp);

                    Date date = new Date(targetTimestamp * 1000 + 3600*1000*2);
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy - HH:mm:ss");

                    //calendar.
                    String dataLegivel = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-" + String.valueOf(calendar.get(Calendar.YEAR)) + " - " + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(calendar.get(Calendar.MINUTE)) + ":" + String.valueOf(calendar.get(Calendar.SECOND));
                    dataLegivel = dateFormat.format(date);
                    System.out.println(dataLegivel);
                    
                    
                   
                    
                    String[] tempLine = {p.getImages().getLow_resolution().getUrl(), p.getUser().getUsername(), String.valueOf(p.getLikes().getCount()), p.getLink(), p.getLocation().getName(), p.getLocation().getId(), p.getLocation().getLatitude(), p.getLocation().getLongitude(), p.getFilter(), p.getCreated_time(), p.getUser().getProfile_picture(), tempUserFullName, p.getUser().getId(), dataLegivel};
                    
                   /*
                     for (int k=0 ; k<tempLine.length ; k ++)
                    {
                       String tempElementLine = tempLine[k];
                       
                       
                        for (String caracteresMaluco : caracteresMalucos) {
                            
                            if (tempElementLine.contains(caracteresMaluco)){
                                tempLine[k] = tempElementLine.replaceAll(caracteresMaluco, "");
                            }
                                
                        }
                    }
                    System.out.println(tempLine.length);
                    System.out.println(caracteresMalucos.size());
                    
                    */
                    if (p.getType().compareTo("image") == 0) {
                        cSVWriter_images_download.writeNext(new String[]{p.getImages().getLow_resolution().getUrl(), p.getId()});
                    }

                    if (p.getComments().getData().size() > 0) {

                        targetTimestamp = Long.parseLong(p.getComments().getData().get(0).getCreated_time());
                    }

                    cSVWriter_data.writeNext(tempLine);

                }
                cSVWriter_data.flush();
                cSVWriter_images_download.flush();
                cSVWriter_links.flush();
                String url = tagsRecents.getPagination().getNext_url();

                if (url == null) {
                    System.out.println(tagsRecents.getPagination().getNext_min_id());
                    System.out.println(tagsRecents.getPagination().getNext_max_id());

                    url = "https://api.instagram.com/v1/tags/" + tag + "?access_token=644397518.1fb234f.b81ed940c95245d7995ef661f0618afe&min_id=" + tagsRecents.getPagination().getNext_min_id();
                    break;
                }

                JSONObject temptag = new JSONObject();

                try {
//                    System.out.println(url);
                    temptag = new JSONObject(new MetodosAdicionais().getPage(url));
                } catch (IOException ex) {

                    System.err.println("erro");
                }

                tagsRecents = gerenciaGetTag.getTagsRecentsNEW(temptag);

            } catch (Exception e) {
            }
            i++;

            System.out.print(currentTimestamp + " ");
            System.out.println(targetTimestamp);
            System.out.println((1.0 * (currentTimestamp - targetTimestamp) / (60)));
        } while (currentTimestamp - targetTimestamp <= 60 * minutosAnalise);

        System.out.println(
                "numero midias:" + i * 20 + "    ");

        try {
            cSVWriter_data.flush();
            cSVWriter_data.close();
            cSVWriter_images_download.close();
            cSVWriter_links.close();

        } catch (IOException ex) {

        }

        new MetodosAdicionais()
                .download(mainDir + "images_download.csv", "", imagesDir,separatorCSV);

    }
    
    
    public static Map<String, String> getParameters(String args[],HashSet<String> allparameters) {
        
        String key;
        String value ;
        Map<String, String> parameters = new HashMap<>();
        if (args.length<2){
            key = args[0];
            if (key.toLowerCase().contains("--help")||key.toLowerCase().contains("-h")){
                help(allparameters);
                System.exit(0);
            }
        }
        for (int i = 0; i < args.length - 1; i++) {

            key = args[i];
            value = args[i + 1];
            i++;
            
            
            if (allparameters.contains(key) && (!allparameters.contains(value))) {
                parameters.put(key, value);
            } else {
                System.out.println("unknown option: \"" + key + " " + value+"\"");
                System.out.println("usage: ");
                for(String parameter:allparameters)
                    System.out.print("[" + parameter + "] ");
            
            
                System.exit(1);
            }
        }

        return parameters;
    }
    
    public static void help(HashSet<String> allparameters){
        System.out.println("usage: ");
                for(String parameter:allparameters)
                    System.out.print("[" + parameter + "] ");
            
        
    }
}
