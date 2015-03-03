leticia
=======
como usar:
- faça o download do aplicativo (https://github.com/andreibastos/leticia/archive/master.zip)
- extraia
- entre na pasta dist
- dentro da pasta dist entre em um terminal (no Windows,segure shift+botão direito abrir terminal aqui, no linux, abra o terminal e caminha até a pasta ex. cd /leticia-master/dist/)
- com o terminal aberto, digite: java -jar Leticia.jar <comandos>	
		
os comandos são:

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


exemplo de pesquisa:
```sh
java -jar Leticia.jar --tag labic  --time 1410691218  --directory "/home/labic/downloads" --downloadimages yes --downloadvideos yes  --delimiter ,
```


o program está sendo desenvolvido.
