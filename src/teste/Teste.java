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
import Data.Caption;
import Data.Photo;
import Gerencia.GerenciaGetTag;
import Gerencia.Instagram;
import Gerencia.MetodosAdicionais;
import Gerencia.Recents;
import au.com.bytecode.opencsv.CSVWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.json.JSONObject;

public class Teste {

    static String fileSeparator = System.getProperty("file.separator");

    static String CLIENT_ID = "";
    static String CLIENT_SECRET = "";
    static String ACESS_TOKEN = "";

    static Map<String, String> configurations = new HashMap<>();

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main_outra(String[] args) throws IOException {

//        getWindows();
        //teste
//        args = new String[]{"--tag", "aftersexy","-f", "images_download.csv"};
        args = new String[]{"--tag", "labic", "--time", "1000", "--directory", "Pesquisa", "--downloadimages", "n", "--downloadvideos", "n"};
//        args = new String[]{"--locationID", "1671488","--service", "locations", "--time", "1425254400", "--directory", "teste", "--downloadimages", "n", "--downloadvideos", "n"};

        //define os parametros que serão passados para ser chamado no programa. 
        //nessa primeira versão os valores são adicionados manualmentes. em breve serão lidos de um txt que servirá também de ajuda para entendimento desses parametros.
        HashSet<String> allparameters = new HashSet<>();
        allparameters.add("--tag");
        allparameters.add("-t");
        allparameters.add("--locationID");
        allparameters.add("-L");
        allparameters.add("--service");
        allparameters.add("-s");
        allparameters.add("--minutos");
        allparameters.add("-m");
        allparameters.add("--userblock");
        allparameters.add("-B");
        allparameters.add("--output");
        allparameters.add("-o");
        allparameters.add("--directory");
        allparameters.add("-D");
        allparameters.add("--delimiter");
        allparameters.add("-d");
        allparameters.add("--time");
        allparameters.add("-T");
        allparameters.add("--configurations");
        allparameters.add("-c");

        allparameters.add("--downloadimages");
        allparameters.add("-i");
        allparameters.add("--downloadvideos");
        allparameters.add("-v");

        allparameters.add("--downloadfile");
        allparameters.add("-f");


        /*essa linha monta um mapa com os parametros e argumentos de entrada da função.
         exemplo:             
         args = new String[]{"--tag", "bolsonaro", "--minutos", "500000", "--directory", "teste","-i","sim","-v","não"};
         */
        Map<String, String> parameters = getParameters(args, allparameters);

        //define os valores de inicialização padrão, para caso faltem alguns parametros de entrada.
        String configs = "configurations.txt";
        String search = "labic";
        String locationID = "1671488";
        String minutos = "60";
        String usersblockpath = "userblock.txt";
        Calendar calendar = Calendar.getInstance();
        String output = String.valueOf(calendar.get(Calendar.YEAR)) + "-" + String.valueOf(calendar.get(Calendar.MONTH)) + "-" + String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)) + "_" + calendar.get(Calendar.HOUR_OF_DAY) + "h" + calendar.get(Calendar.MINUTE) + "m" + calendar.get(Calendar.SECOND);
        String directory = "Pesquisa";
        char delimiter = ',';
        int quantidadeBaixadas = 100;
        long currentTimestamp = System.currentTimeMillis() / 1000;
        long targetTimestamp = 0;
        long targetTimestampMAIOR = 0;
        long timefinal = currentTimestamp - 60 * 60;
        boolean downloadimages;
        boolean downloadvideos = false;
        String service = "tags";
        String downloadfile = "";

        //faz a leitura dos parametros.        
        downloadfile = setParameter("--downloadfile", configs, parameters);
        downloadfile = setParameter("-f", configs, parameters);

        configs = setParameter("--configurations", configs, parameters);
        configs = setParameter("-c", configs, parameters);

        service = setParameter("--service", service, parameters);
        service = setParameter("-s", service, parameters);

        if (service.equalsIgnoreCase("tags")) {
            search = setParameter("--tag", search, parameters);
            search = setParameter("-t", search, parameters);
        }

        if (service.equalsIgnoreCase("locations")) {
            search = setParameter("--locationID", search, parameters);
            search = setParameter("-L", search, parameters);
        }

        minutos = setParameter("--minutos", minutos, parameters);
        minutos = setParameter("-m", minutos, parameters);

        usersblockpath = setParameter("--userblock", usersblockpath, parameters);
        usersblockpath = setParameter("-B", usersblockpath, parameters);

        output = setParameter("--output", output, parameters);
        output = setParameter("-o", output, parameters);

        timefinal = Long.valueOf(setParameter("--time", String.valueOf(timefinal), parameters));
        timefinal = Long.valueOf(setParameter("-T", String.valueOf(timefinal), parameters));

        directory = setParameter("--directory", directory, parameters);
        directory = setParameter("-d", directory, parameters);
        if (!directory.equalsIgnoreCase("")) {
            directory = directory + fileSeparator;
        }

        ArrayList<String> usersblocks = getUserBlocks(usersblockpath);

        delimiter = setParameter("--delimiter", String.valueOf(delimiter), parameters).toCharArray()[0];
        delimiter = setParameter("-d", String.valueOf(delimiter), parameters).toCharArray()[0];

        downloadimages = setParameter("--downloadimages", "sim", parameters).toLowerCase().startsWith("s");
        downloadvideos = setParameter("--downloadvideos", "sim", parameters).toLowerCase().startsWith("s");

        downloadimages = setParameter("-i", "sim", parameters).toLowerCase().startsWith("s");
        downloadvideos = setParameter("-v", "sim", parameters).toLowerCase().startsWith("s");

        output = search + "_" + output;

        System.out.println("download file: " + downloadfile);

        /*
         cria uma mapa des configurações para acesso (autenticação) do instagram, deve conter um arquivo configurations.txt com os nomes CLIENT_ID, CLIENT_SECRET E ACESS_TOKEN com seus respectivos valores separados por ponto e virgula.
         */
        configurations = getConfigurations(configs);

        //faz a leitura de cada valor de autenticação.
        CLIENT_ID = configurations.get("CLIENT_ID");
        CLIENT_SECRET = configurations.get("CLIENT_SECRET");
        ACESS_TOKEN = configurations.get("ACESS_TOKEN");

        //cria um novo objeto instagram com as autenticações
        Instagram instagram = new Instagram(CLIENT_ID, CLIENT_SECRET, ACESS_TOKEN);

        GerenciaGetTag gerenciaGetTag = new GerenciaGetTag();

        //cria as pastas para armazenamento do csv, download das imagens e dos videos.
        String searchDir = directory + search + fileSeparator;
        String imagesDir = searchDir + "images" + fileSeparator;
        String videosDir = searchDir + "videos" + fileSeparator;
        String csvDir = searchDir + "csv" + fileSeparator;

        //cria os csv com os nomes
        String principal_csv = csvDir + "principal.csv";
        String links_csv = csvDir + "links.csv";
        String images_csv = csvDir + "images_download.csv";
        String videos_csv = csvDir + "videos_download.csv";
        String image_cloud_csv = csvDir + "image_cloud.csv";

        //checa se já tenha feito alguma pesquisa anterior e retorna o nome da pasta csv, caso não tenha, retorna ""
        boolean check = checkIsExistingCsvDir(searchDir);

        //se existe a pasta csv
        if (check) {
            principal_csv = csvDir + "principal_temp.csv";
            links_csv = csvDir + "links_temp.csv";
            images_csv = csvDir + "images_download_temp.csv";
            videos_csv = csvDir + "videos_download_temp.csv";
            image_cloud_csv = csvDir + "image_cloud_temp.csv";
        }

        String last_link = getLastLink(csvDir);
        
        
        
        System.out.println("last link:" + last_link);

        File pasta = new File(directory);
        if (!pasta.exists()) {
            new File(directory).mkdir();
            pasta.mkdir();
        }
        directory = searchDir;
        pasta = new File(searchDir);
        if (!pasta.exists()) {
            new File(imagesDir).mkdir();
            pasta.mkdir();
        }
        pasta = new File(csvDir);
        if (!pasta.exists()) {
            new File(imagesDir).mkdir();
            pasta.mkdir();
        }
        pasta = new File(imagesDir);
        if (!pasta.exists()) {
            new File(imagesDir).mkdir();
            pasta.mkdir();
        }
        pasta = new File(videosDir);
        if (!pasta.exists()) {
            new File(videosDir).mkdir();
            pasta.mkdir();
        }

        //dá um print na tela com os respectivos diretórios.
        System.out.println("mainDir:    " + directory);
        System.out.println("imagesDir:  " + imagesDir);
        System.out.println("videosDir:  " + videosDir);
        System.out.println("csvDir:  " + csvDir);

        if (downloadfile.contains(".csv")) {
            if (downloadfile.contains("videos")) {
                new MetodosAdicionais()
                        .download(downloadfile, "", videosDir, delimiter, "videos");
            }
            if (downloadfile.contains("images")) {
                new MetodosAdicionais()
                        .download(downloadfile, "", imagesDir, delimiter, "imagens");
            }

            System.exit(5);
        }

        //cria os JSONOBJECTS para extrair as informações
        JSONObject JSON_tagRecent = instagram.getRecentTag(search, "", "", service);
        JSONObject JSON_media_count = instagram.getMedia_Count(search, service);
        JSONObject JSON_tempMedia_count = JSON_media_count.getJSONObject("data");

        if (check) {
            JSON_tagRecent = new JSONObject(new MetodosAdicionais().getPage(last_link));
        }

        //cria um objeto de tagrecents para controle das midias que estão vindo.
        Recents tags_recents = gerenciaGetTag.getTagsRecentsNEW(JSON_tagRecent);

        //código de error.    
        int code = tags_recents.getMeta().getCode();
        String error_type = tags_recents.getMeta().getError_type();
        String error_message = tags_recents.getMeta().getError_message();
        System.out.println("error type:\t" + error_type);
        System.out.println("error message:\t" + error_message);

        //dá um print da busca pesquisada.
        System.out.println("code:\t" + code);
        System.out.println("tag:\t" + JSON_tempMedia_count.getString("name"));
        int media_count = 0;
        if (JSON_tempMedia_count.has("media_count")) {
            media_count = JSON_tempMedia_count.getInt("media_count");
        }
        System.out.println("total media:    " + media_count);

        //cria os CSVWriter  para criação dos CSVs
        CSVWriter cSVWriter_data = null;
        CSVWriter cSVWriter_links = null;
        CSVWriter cSVWriter_images_download = null;
        CSVWriter cSVWriter_videos_download = null;
        CSVWriter cSVWriter_image_cloud = null;

        //inicializa os CsvWriter com os path, delimitadores ..
        try {

            cSVWriter_data = new CSVWriter(new FileWriter(new File(principal_csv)), delimiter, CSVWriter.DEFAULT_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER);
            cSVWriter_links = new CSVWriter(new FileWriter(new File(links_csv)), delimiter, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.NO_ESCAPE_CHARACTER);
            cSVWriter_images_download = new CSVWriter(new FileWriter(new File(images_csv)), delimiter);
            cSVWriter_videos_download = new CSVWriter(new FileWriter(new File(videos_csv)), delimiter);
            cSVWriter_image_cloud = new CSVWriter(new FileWriter(new File(image_cloud_csv)), delimiter);

            String[] fistLine_image_cloud = {"imagem_name", "like", "time"};
            String[] fistLine = {"url", "user_username", "like", "link", "location_name", "location_id", "location_latitude", "location_longitude", "filter", "created_time", "user_profile_picture", "user_full_name", "user_id", "data_legivel", "caption", "comments"};
            cSVWriter_data.writeNext(fistLine);
            cSVWriter_image_cloud.writeNext(fistLine_image_cloud);

        } catch (IOException iOException) {
            System.out.println("error 1: falha ao tentar criar os arquivos de escrita, verifique a sua permissão de usuário e/ou se a pasta existe.");
            System.exit(1);
        }

        int i = 0;

        ArrayList<String> caracteresMalucos = new ArrayList<>();
        caracteresMalucos.add("\\|");
        caracteresMalucos.add("\\,");
        caracteresMalucos.add(String.valueOf(delimiter));
        caracteresMalucos.add(String.valueOf("\""));
        caracteresMalucos.add(String.valueOf("'"));
        caracteresMalucos.add(String.valueOf("\'"));
        caracteresMalucos.add(String.valueOf("\n"));
        caracteresMalucos.add(String.valueOf("\t"));
        caracteresMalucos.add(String.valueOf("\r"));
        caracteresMalucos.add(String.valueOf("\b"));
        caracteresMalucos.add(String.valueOf("\r\n"));

        do {

            cSVWriter_links.writeNext(new String[]{tags_recents.getPagination().getNext_url()});

            System.out.println("numero midias:" + i * 20);
            try {
                if (code == 429) {
                    if (downloadimages) {
                        new MetodosAdicionais().download(directory + output + "_images_download.csv", "", imagesDir, delimiter, "imagens");
                    }
                    if (downloadvideos) {
                        new MetodosAdicionais().download(directory + output + "_videos_download.csv", "", videosDir, delimiter, "imagens");
                    }
                    System.err.println("error_message: The maximum number of requests per hour has been exceeded.");

                    try {
                        try {
                            cSVWriter_data.flush();
                            cSVWriter_data.close();
                            cSVWriter_links.flush();
                            cSVWriter_links.close();
                            cSVWriter_images_download.flush();
                            cSVWriter_images_download.close();
                            cSVWriter_videos_download.flush();
                            cSVWriter_videos_download.close();

                        } catch (IOException ex) {

                        }
                        long timeSleep = 10;
                        System.out.println("tempo dormindo: " + timeSleep + " minutos.");
                        new Thread().sleep(1000 * 60 * timeSleep);
                    } catch (Exception e) {
                    }
                }
                targetTimestampMAIOR = 0;
                //                System.out.println("progresso: " + (i * 100.0 / (sizeFor)) + "%");
                ArrayList<Long> listTarget = new ArrayList<>();

                for (Photo p : tags_recents.getData().getPhoto()) {
                    String tempUser = p.getUser().getUsername().toLowerCase();

                    if (usersblocks.contains(tempUser)) {
                        continue;
                    }

                    String tempUserFullName = p.getUser().getFull_name().replaceAll("\"", "");

                    targetTimestamp = Long.parseLong(p.getCreated_time());

                    if (p.getComments().getCount() > 0) {
                        if (p.getComments().getData().size() > 0) {
                            targetTimestamp = Long.parseLong(p.getComments().getData().get(0).getCreated_time());
                        }

                    }

                    String dataLegivel = getDataLegivel(targetTimestamp, calendar, "dd-MM-yyyy - HH:mm:ss");

                    String comments = "";
                    for (int indexComments = 0; indexComments < p.getComments().getData().size(); indexComments++) {

                        comments = comments + "[" + p.getComments().getData().get(indexComments).getText().replaceAll("(\\r\\n|\\n)", "<br />") + "]";

                    }

                    String caption = p.getCaption().getText();
                    if (caption != null) {
                        caption = caption.replaceAll("\n", "");
                    }

                    String[] tempLine = {p.getImages().getLow_resolution().getUrl(), p.getUser().getUsername(), String.valueOf(p.getLikes().getCount()), p.getLink(), p.getLocation().getName(), p.getLocation().getId(), p.getLocation().getLatitude(), p.getLocation().getLongitude(), p.getFilter(), p.getCreated_time(), p.getUser().getProfile_picture(), tempUserFullName, p.getUser().getId(), dataLegivel, caption, comments};

                    String[] tempLine_image_cloud = {p.getId() + ".jpg", String.valueOf(p.getLikes().getCount()), p.getCreated_time()};

                    if (p.getType().compareTo("image") == 0) {
                        cSVWriter_images_download.writeNext(new String[]{p.getImages().getLow_resolution().getUrl(), p.getId()});
                    }
                    if (p.getType().compareTo("video") == 0) {
                        cSVWriter_videos_download.writeNext(new String[]{p.getVideos().getLow_resolution().getUrl(), p.getId()});
                    }

                    cSVWriter_data.writeNext(tempLine);
                    cSVWriter_image_cloud.writeNext(tempLine_image_cloud);

                    listTarget.add(targetTimestamp);

                }

                targetTimestamp = getMaxValue(listTarget);

                cSVWriter_data.flush();
                cSVWriter_images_download.flush();
                cSVWriter_videos_download.flush();
                cSVWriter_links.flush();
                cSVWriter_image_cloud.flush();

                String url = tags_recents.getPagination().getNext_url();

                if (url == null) {
                    url = "https://api.instagram.com/v1/" + service + "/" + search + "?access_token=" + ACESS_TOKEN + "&min_id=" + tags_recents.getPagination().getNext_min_id();
                    break;
                }

                JSONObject temptag = new JSONObject("{}");

                try {
                    //                    System.out.println(url);
                    temptag = new JSONObject(new MetodosAdicionais().getPage(url));
                } catch (IOException ex) {

                    System.err.println("JSON" + url + "vazio");
                }

                tags_recents = gerenciaGetTag.getTagsRecentsNEW(temptag);

            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;

            System.out.print("data atual: " + getDataLegivel(targetTimestamp, calendar, "dd-MM-yyyy - HH:mm:ss") + "\t");
            System.out.println("data final: " + getDataLegivel(timefinal, calendar, "dd-MM-yyyy - HH:mm:ss") + " ");

//        } while (currentTimestamp - targetTimestamp <= 60 * minutosAnalise);
//        } while (targetTimestamp >= timefinal || (i * 20) > media_count);
        } while (targetTimestamp >= timefinal);

        try {
            cSVWriter_data.flush();
            cSVWriter_data.close();
            cSVWriter_images_download.close();
            cSVWriter_videos_download.close();
            cSVWriter_links.close();
            cSVWriter_image_cloud.close();

        } catch (IOException ex) {

        }

        if (downloadimages) {
            new MetodosAdicionais()
                    .download(images_csv, "", imagesDir, delimiter, "imagens");
        }
        if (downloadvideos) {
            new MetodosAdicionais()
                    .download(videos_csv, "", videosDir, delimiter, "videos");
        }

    }

    public static void join_csv_with_temp(String csvDir) {

        File path = new File(csvDir);
        FilenameFilter textFilter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                return (lowercaseName.startsWith("principal"));
            }
        };
        File[] files = path.listFiles(textFilter);
        String filename_output = "principal.csv";
        File output = new File(filename_output);
        
        mergeFiles(files, output);
        
        
       

    }
    
    	public static void mergeFiles(File[] files, File mergedFile) {
 
		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			fstream = new FileWriter(mergedFile, true);
			 out = new BufferedWriter(fstream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
 
		for (File f : files) {
			System.out.println("merging: " + f.getName());
			FileInputStream fis;
			try {
				fis = new FileInputStream(f);
				BufferedReader in = new BufferedReader(new InputStreamReader(fis));
 
				String aLine;
				while ((aLine = in.readLine()) != null) {
					out.write(aLine);
					out.newLine();
				}
 
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
 
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}

    public static boolean checkIsExistingCsvDir(String searchDir) {

        boolean check = false;
        File path = new File(searchDir);
        FilenameFilter textFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                return lowercaseName.startsWith("csv");
            }
        };
        File[] files = path.listFiles(textFilter);
        for (File file : files) {
            if (file.isDirectory()) {
                check = true;
            }
        }
        return check;
    }

    private static String getDataLegivel(Long targetTimestamp, Calendar calendar, String format) {

        calendar.setTimeInMillis(targetTimestamp);

        Date date = new Date(targetTimestamp * 1000);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy - HH-mm-ss");

        return dateFormat.format(date);
    }

    public static long getMaxValue(ArrayList<Long> listTarget) {
        long maior = 0;
        for (Long tempTarget : listTarget) {
            if (tempTarget > maior) {
                maior = tempTarget;
            }
        }
        return maior;
    }

    public static void getWindows() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaPrincipal().setVisible(true);
            }
        });
    }

    public static ArrayList<String> getUserBlocks(String path) {
        ArrayList<String> userblocks = new ArrayList<>();
        System.out.println("Usuários Banidos:   ");
        File arquivo = new File(path);
        try {
            FileReader MeuArquivo = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(MeuArquivo);

            while (br.ready()) {

                String linha = br.readLine();
                userblocks.add(linha);
                System.out.println("\t" + linha);
            }
        } catch (IOException e) {

            System.out.println("não foi possível ler a lista de usuarios banidos");
        }

        return userblocks;
    }

    public static Map<String, String> getConfigurations(String path) {
        Map<String, String> tempConfigurations = new HashMap<>();

        File arquivo = new File(path);
        try {
            FileReader MeuArquivo = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(MeuArquivo);

            while (br.ready()) {

                String linha = br.readLine().toLowerCase();

                String colunas[] = linha.split(";");                
                if (colunas.length >1){
                tempConfigurations.put(colunas[0].toUpperCase(), colunas[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(""
                    + "Arquivo configurations.txt não está presente ou não está no padrão.\n"
                    + "Tente: \n"
                    + "CLIENT_ID;9cd1f78e9e424b109489a9efc87c1638\n"
                    + "CLIENT_SECRET;9894688d6b344b0e90ef9c887135abd8\n"
                    + "access_token;144397518.1fb235f.b81ed940c95245d7995ef661f0618afe");
        }

        return tempConfigurations;
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

    //você parou aqui, como append in csv existente.
    private static String getLastLink(String csvDir) {
        String last_link = "";
        
        File arquivo = new File(csvDir + "links_temp.csv");
       
        try {
            FileReader MeuArquivo = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(MeuArquivo);

            while (br.ready()) {
                
                last_link = br.readLine();
                System.out.println(last_link);
            }
        } catch (IOException e) {
            
            System.err.print("file " + csvDir + " not exist.");
        }
        return last_link;
    }

}
