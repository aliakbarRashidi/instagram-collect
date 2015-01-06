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

    static String fileSeparator = System.getProperty("file.separator");

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        //teste
        args = new String[]{"--tag", "bolsonaro", "--minutos", "500000", "--directory", "teste"};

        HashSet<String> allparameters = new HashSet<>();
        allparameters.add("--tag");
        allparameters.add("-t");
        allparameters.add("--minutos");
        allparameters.add("-m");
        allparameters.add("--userblock");
        allparameters.add("-B");
        allparameters.add("--output");
        allparameters.add("-o");
        allparameters.add("--directory");
        allparameters.add("-D");
        allparameters.add("--separator");
        allparameters.add("-s");
        allparameters.add("--time");
        allparameters.add("-T");
        

        Map<String, String> parameters = getParameters(args, allparameters);

        //inicialização padrão
        String tag = "labic";
        //string int
        String minutos = "60";
        String userblock = "userblock.txt";

        Calendar calendar = Calendar.getInstance();
        String output = String.valueOf(calendar.get(Calendar.YEAR)) + "-" + String.valueOf(calendar.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "_" + calendar.get(Calendar.HOUR_OF_DAY) + "h" + calendar.get(Calendar.MINUTE) + "m" + calendar.get(Calendar.SECOND) + "s.csv";

        String directory = "";
        char separator = ',';
        int quantidadeBaixadas = 100;

        long currentTimestamp = System.currentTimeMillis() / 1000;
        long targetTimestamp = 0;
        long timefinal = 0;

        //fazer um código que ler do arquivo txt
        ArrayList<String> blocks = new ArrayList<>();
        blocks.add("oficialyasmin");
        blocks.add("haroldocerqueiira");

        tag = setParameter("--tag", tag, parameters);
        tag = setParameter("-t", tag, parameters);

        minutos = setParameter("--minutos", minutos, parameters);
        minutos = setParameter("-m", minutos, parameters);

        userblock = setParameter("--userblock", userblock, parameters);
        userblock = setParameter("-B", userblock, parameters);

        output = setParameter("--output", output, parameters);
        output = setParameter("-o", output, parameters);
        
        timefinal = Long.valueOf(setParameter("--time", String.valueOf(timefinal), parameters));
        timefinal = Long.valueOf(setParameter("-T", String.valueOf(timefinal), parameters));
        

        directory = setParameter("--directory", directory, parameters);
        directory = setParameter("-d", directory, parameters);
        if (!directory.equalsIgnoreCase("")) {
            directory = directory + fileSeparator;
        }

        separator = setParameter("--separator", String.valueOf(separator), parameters).toCharArray()[0];
        separator = setParameter("-s", String.valueOf(separator), parameters).toCharArray()[0];

        output = tag + "_" + output;

        int minutosAnalise = Integer.parseInt(minutos);

        System.out.println(currentTimestamp);

        // TODO code application logic here
        String CLIENT_ID = "9bf0e68e9e424b109489a9efc87c1638";
        String CLIENT_SECRET = "8894688d6b344b0d90ef9b887135abd8";
        String access_token = "644397518.1fb234f.b81ed940c95245d7995ef661f0618afe";

        Instagram mi = new Instagram(CLIENT_ID, CLIENT_SECRET, access_token);

        GerenciaGetTag gerenciaGetTag = new GerenciaGetTag();

        String imagesDir = directory + "images" + fileSeparator;

        File pasta = new File(directory);

        if (!pasta.exists()) {
            new File(directory).mkdir();

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
                "mainDir:    " + directory);
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
        System.out.println("total media:    "+media_count);
        //System.out.println("quantidade de midias:   " + quantidadeBaixadas);
        double horas = quantidadeBaixadas * (1.1 / 6000);
        double minutes = ((horas - (long) horas) * 60);
        double segundos = (minutes - (long) minutes) * 60;
//        System.out.println(segundos);
        // System.out.println("tempo aproximado de coleta: " + (long) horas + " horas " + (long) minutos + " minutos e " + (long) segundos + " segundos");

        CSVWriter cSVWriter_data = null;
        CSVWriter cSVWriter_links = null;
        CSVWriter cSVWriter_images_download = null;
        
        
        try {
            cSVWriter_data = new CSVWriter(new FileWriter(new File(directory + output)), separator, CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER);
            cSVWriter_links = new CSVWriter(new FileWriter(new File(directory + "links.csv")), separator);
            cSVWriter_images_download = new CSVWriter(new FileWriter(new File(directory + "images_download.csv")), separator);

            String[] fistLine = {"url", "user_username", "like", "link", "location_name", "location_id", "location_latitude", "location_longitude", "filter", "created_time", "user_profile_picture", "user_full_name", "user_id", "data_legivel"};

            cSVWriter_data.writeNext(fistLine);

        } catch (IOException iOException) {

            System.out.println("error 1: falha ao tentar criar os arquivos de escrita, verifique a sua permissão de usuário e/ou se a pasta existe.");
            System.exit(1);
        }

        int sizeFor = quantidadeBaixadas / 20;

        if (sizeFor
                < 1) {
            sizeFor = 1;
        }
        int i = 0;

        ArrayList<String> caracteresMalucos = new ArrayList<>();
        caracteresMalucos.add("\\|");
        caracteresMalucos.add("\\,");
        caracteresMalucos.add(String.valueOf(separator));
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
                    new MetodosAdicionais().download(directory + "images_download.csv", "", imagesDir, separator);
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

                    if (blocks.contains(tempUser)) {
                        continue;
                    }

                    String tempUserFullName = p.getUser().getFull_name().replaceAll("\"", "");

                    targetTimestamp = Long.parseLong(p.getCreated_time());

                    if (p.getComments().getCount() > 0) {
                        long last_targetTimestamp = Long.parseLong(p.getComments().getData().get(0).getCreated_time());

                        targetTimestamp = last_targetTimestamp;

                    }

                    //,"location_name","location_id","location_latitude","location_longitude","filter","created_time","user_profile_picture","user_full_name","user_id","data_legivel"}
                    calendar.setTimeInMillis(targetTimestamp);

                    Date date = new Date(targetTimestamp * 1000);
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy - HH:mm:ss");

                    //calendar.
                    String dataLegivel = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "-" + String.valueOf(calendar.get(Calendar.MONTH) + 1) + "-" + String.valueOf(calendar.get(Calendar.YEAR)) + " - " + String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(calendar.get(Calendar.MINUTE)) + ":" + String.valueOf(calendar.get(Calendar.SECOND));
                    dataLegivel = dateFormat.format(date);
                    System.out.print(dataLegivel + " ");
                    System.out.println(targetTimestamp);

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
//                    System.out.println(tagsRecents.getPagination().getNext_min_id());
//                    System.out.println(tagsRecents.getPagination().getNext_max_id());

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

//            System.out.print(currentTimestamp + " ");
//            System.out.println(targetTimestamp);
//            System.out.println((1.0 * (currentTimestamp - targetTimestamp) / (60)));
                
        } while (currentTimestamp - targetTimestamp <= 60 * minutosAnalise);
//        } while ( targetTimestamp >= timefinal || (i*20)>media_count);


       

        try {
            cSVWriter_data.flush();
            cSVWriter_data.close();
            cSVWriter_images_download.close();
            cSVWriter_links.close();

        } catch (IOException ex) {

        }

        new MetodosAdicionais()
                .download(directory + "images_download.csv", "", imagesDir, separator);

    }

    public static String setParameter(String key, String value, Map<String, String> parameters) {

        if (parameters.containsKey(key)) {
            System.out.print(key + ": ");
            System.out.println(parameters.get(key));
            return parameters.get(key);

        } else {
            return value;
        }
    }

    public static Map<String, String> getParameters(String args[], HashSet<String> allparameters) {

        String key;
        String value;
        Map<String, String> parameters = new HashMap<>();
        if (args.length < 2) {
            key = args[0];
            if (key.toLowerCase().contains("--help") || key.toLowerCase().contains("-h")) {
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
                System.out.println("unknown option: \"" + key + " " + value + "\"");
                System.out.println("usage: ");
                for (String parameter : allparameters) {
                    System.out.print("[" + parameter + "] ");
                }

                System.exit(1);
            }
        }

        return parameters;
    }

    public static void help(HashSet<String> allparameters) {
        System.out.println("usage: ");
        for (String parameter : allparameters) {
            System.out.print("[" + parameter + "] ");
        }

    }
}
