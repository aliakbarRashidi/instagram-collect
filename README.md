leticia
=======
como usar:
- faça o download do aplicativo (https://github.com/andreibastos/leticia/archive/master.zip)
- extraia
- entre na pasta dist
- altere o arquivo configurations.txt com sua "CLIENT_ID" "CLIENT_SECRET" e  "ACESS_TOKEN"
- dentro da pasta dist entre em um terminal (no Windows,segure shift+botão direito abrir terminal aqui, no linux, abra o terminal e caminha até a pasta ex. cd /leticia-master/dist/)
- com o terminal aberto, digite: java -jar Leticia.jar comandos	
		
##Comandos

--tag

	hashtag a ser buscada
	- exemplo: --tag labic

--time 

	o tempo final de pesquisa no formato timestamp
	- exemplo: --time 1410691218

--director

	diretório de saídas dos arquivos
	- exemplo: --directory "C:\\home\\labic\\downloads"

--downloadimages 

	habilita a opção de baixar as imagens, o padrão é yes 
	- exemplo: --downloadimages yes 

--downloadvideos 

	habilita a opção de baixar os videos, o padrão é yes
	- exemplo: --downloadvideos no 

--delimiter 

	delimitador de texto para saida do arquivo, o padrão é ,
	- exemplo: --delimiter ,


exemplos de pesquisa:
- busca genérica:
	buscando a tag labic de agora até a data 1410691218  (http://www.unixtimestamp.com/ to convert) salvando no diretório  "/home/labic/downloads" habilitando o download das imagens e dos videos gerando um csv delimitado por vírgula
	
```sh
java -jar Leticia.jar --tag labic  --time 1410691218  --directory "/home/labic/downloads" --downloadimages yes --downloadvideos yes  --delimiter ,
```

--download através de um arquivo csv:
	baixar as imagens de uma tag aftersexy de um arquivo images_download.csv
```sh
java -jar Leticia.jar --tag aftersexy -f images_download.csv
```

--busca por location ID:
	buscando o locationID  1671488 de agora até a data 1410691218  (http://www.unixtimestamp.com/ to convert) salvando no diretório  "/home/labic/downloads" habilitando o download das imagens e dos videos gerando um csv delimitado por vírgula
```sh
java -jar Leticia.jar --locationID 1671488  --time 1410691218  --directory "/home/labic/downloads" --downloadimages yes --downloadvideos yes  --delimiter ,
```

o program está sendo desenvolvido.
