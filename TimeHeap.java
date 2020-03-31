import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.io.File;
/*
Repita a questão de Ordenação por Seleção, contudo, usando o algoritmo
Heapsort, fazendo com que a chave de pesquisa seja o atributo estadio. O nome do arquivo de
log será matrı́cula heapsort.txt.
*/



class TimeHeap {

    private String nome, apelido, estadio, tecnico, liga, nomeArquivo;
    private int capacidade, fundacaoDia, fundacaoMes, fundacaoAno;
    private long paginaTam;

    public TimeHeap() {
        nome = apelido = estadio = tecnico = liga = nomeArquivo = "";
        paginaTam = capacidade = fundacaoDia = fundacaoMes = fundacaoAno = 0;
    }
    public TimeHeap(String nomeArq) throws Exception{
        ler(nomeArq);
    }

    public static void main(String[] args) throws Exception {

        long inicio = new Date().getTime();
         MyIO.setCharset("UTF-8");

        String[] entradas = new String[1000];
        int quantidadeDeFrases = 0;


        //ler a entrada com os times até inserir FIM
        do{
            entradas[quantidadeDeFrases] = MyIO.readLine();
        }while(!estaNoFinal(entradas[quantidadeDeFrases++]));

        //tirar o fim da contagem
        quantidadeDeFrases--;
        

        //array de referencias a objetos de "Time"
        TimeHeap[] conjuntoTimes = new TimeHeap[quantidadeDeFrases];
        
        //criar os objetos de acordo.
        for (int i = 0; i < quantidadeDeFrases; i++) {
            //criar o objeto e chamar o construtor
            conjuntoTimes[i] = new TimeHeap(entradas[i]);
        }
        
        int totalComparacoes = ordenarHeap(conjuntoTimes);

        for (int i = 0; i < quantidadeDeFrases; i++) {
            conjuntoTimes[i].imprimir();
        }
        

        long fim = new Date().getTime();

        long execucao = fim-inicio;
        Arq.openWrite("649651_heapsort.txt");
        
        Arq.print("649651\t"+execucao+"\t"+totalComparacoes+"\t");

        Arq.close();
        //fim da main
    }
    //funcao de realizar o swap de dois itens de um array recebido por param
    public static void swap(TimeHeap array[], int menor, int i){
        TimeHeap aux = array[i];
        array[i] = array[menor];
        array[menor] = aux;

        //return array;
    }

    //selecao propriamente dita. Retorna o numero de comparacoes realizadas
    public static int ordenarHeap(TimeHeap array[]){
        int comparacoes = 0;

        int n = array.length;
        
        //array tmp com a cabeça vazia
        TimeHeap tmp[] = new TimeHeap[array.length+1];
        for (int i = 1; i < tmp.length; i++) {
            tmp[i] = array[i-1];
        }

            //Contrucao do heap
        for (int tam = 2; tam <= n; tam++){
            comparacoes += constroi(tmp, tam);
        }

            //Ordenacao propriamente dita
        int tam = n;
        while (tam > 1){
            swap(tmp, 1, tam--);
            comparacoes += reconstroi(tmp, tam);
        }

        //empurrar de volta pra posicao 0
        for (int i = 0; i < array.length; i++) {
            array[i] = tmp[i+1];
        }
     
        
        return comparacoes;
    }

    public static int constroi(TimeHeap[] array, int tam){
        int comparacoes = 0;
        
        // compareTo retorna um numero positivo se o objeto é "maior" que o parâmetro
        for (int i = tam; i > 1 &&  array[i/2].getEstadio().compareTo(array[i].getEstadio()) <= 0; i /= 2){
            comparacoes++;
            swap(array, i, i/2);
        }
        return comparacoes;
    }

    public static int reconstroi(TimeHeap[] array, int tam){
        int i = 1;
        int comparacoes = 0;
        while (i <= (tam/2)){
            int filho = getMaiorFilho(array, i, tam);
            // versao identica pra contar operações
            comparacoes += getMaiorFilhoOperacoes(array, i, tam);

            // se o objeto precede o parâmetro
            if (array[i].getEstadio().compareTo(array[filho].getEstadio()) < 0)  {
                comparacoes++;
                swap(array, i, filho);
                i = filho;
            } 
            else {
                comparacoes++;
                i = tam;
            }
        }
        return comparacoes;
    }

    public static int getMaiorFilho(TimeHeap[] array, int i, int tam){
        int filho;

        if (2*i == tam || array[2*i].getEstadio().compareTo(array[2*i+ 1].getEstadio()) > 0)  {
            filho = 2*i;
        } 
        else {
            filho = 2*i + 1;
        }
        return filho;
    }

    public static int getMaiorFilhoOperacoes(TimeHeap[] array, int i, int tam){
        int comparacoes = 0;

        if (2*i == tam || array[2*i].getEstadio().compareTo(array[2*i+ 1].getEstadio()) > 0)  {
            comparacoes++;
        } 
        else {
            comparacoes++;
        }

        return comparacoes;
    }

    //metodos set e get

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getFundacaoDia() {
        return fundacaoDia;
    }

    public void setFundacaoDia(int fundacaoDia) {
        this.fundacaoDia = fundacaoDia;
    }

    public int getFundacaoMes() {
        return fundacaoMes;
    }

    public void setFundacaoMes(int funcacaoMes) {
        this.fundacaoMes = funcacaoMes;
    }

    public int getFundacaoAno() {
        return fundacaoAno;
    }

    public void setFundacaoAno(int fundacaoAno) {
        this.fundacaoAno = fundacaoAno;
    }

    public long getPaginaTam() {
        return paginaTam;
    }

    public void setPaginaTam(long paginaTam) {
        this.paginaTam = paginaTam;
    }
    //metodo clone
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void imprimir() {
        System.out.println(toString());
    }

    public String toString() {
        String resp = nome + " ## " + apelido + " ## ";
        resp += ((fundacaoDia > 9) ? "" : "0") + fundacaoDia + ((fundacaoMes > 9) ? "/" : "/0") + fundacaoMes + "/"
                + fundacaoAno + " ## ";
        resp += estadio + " ## " + capacidade + " ## " + tecnico + " ## " + liga + " ## " + nomeArquivo + " ## "
                + paginaTam + " ## ";
        return resp;
    }


    public String removePunctuation(String campo) {
        campo = campo.replace(".", "");
        campo = campo.replace(",", "");
        campo = campo.replace(";", "");
        String resp = "";
        for (int i = 0; i < campo.length(); i++) {
            if (Character.isDigit(campo.charAt(i)))
                resp += campo.charAt(i);
            else
                i = campo.length();
        }

        return resp;
    }

    public int getMes(String campo) {
        int resp = 0;
        if (campo.contains("january") == true) {
            resp = 1;
        } else if (campo.contains("february") == true) {
            resp = 2;
        } else if (campo.contains("march") == true) {
            resp = 3;
        } else if (campo.contains("april") == true) {
            resp = 4;
        } else if (campo.contains("may") == true) {
            resp = 5;
        } else if (campo.contains("june") == true) {
            resp = 6;
        } else if (campo.contains("july") == true) {
            resp = 7;
        } else if (campo.contains("august") == true) {
            resp = 8;
        } else if (campo.contains("september") == true) {
            resp = 9;
        } else if (campo.contains("october") == true) {
            resp = 10;
        } else if (campo.contains("november") == true) {
            resp = 11;
        } else if (campo.contains("december") == true) {
            resp = 12;
        }
        return resp;
    }

    public String getSubstringEntre(String s, String antes, String depois) {
        String resp = "";
        int posInicio, posFim;

        posInicio = s.indexOf(antes) + antes.length();

        if (antes.compareTo(depois) != 0) {
            posFim = s.indexOf(depois);
        } else {
            posFim = s.indexOf(depois, posInicio);
        }

        if (0 <= posInicio && posInicio < posFim && posFim < s.length()) {
            resp = s.substring(posInicio, posFim);
        }

        return resp;
    }

    public String removeTags(String str) {

        String strRegEx = "<[^>]*>";
        
        str = str.replace("&#91;8&#93;", "");
        str = str.replace("\"", "");
		str = str.replace("&#91;1&#93;", "");
		str = str.replace(";note 1&#93;", " ");
		str = str.replace("&#91;1&#93;", " ");
		str = str.replace("&amp;", " ");
		str = str.replace("&#91;", " ");
		str = str.replace("&#91", " ");
		str = str.replace("1&#93", " ");
		str = str.replace("&#160;", " ");
		
		

        str = str.replaceAll(strRegEx, "");
			/*
			//replace &nbsp; with space
			str = str.replace("&nbsp;", " ");
			//replace &amp; with &
			str = str.replace("&amp;", "&");
			*/

        //OR remove all HTML entities
        str = str.replaceAll("&.*?;", " ");

        return str;
    }

    public void ler(String nomeArquivo) throws Exception {

        FileReader file = new FileReader(nomeArquivo);
        BufferedReader buffer = new BufferedReader(file);
        String html = "";
        String line = buffer.readLine();
        while (line != null) {
            html += line;
            line = buffer.readLine();
        }

        buffer.close();
        file.close();

        html = html.substring(html.indexOf("Full name"));
        html = html.substring(0, html.indexOf("<table style"));
        String campos[] = html.split("<tr>");

        this.nomeArquivo = nomeArquivo;

        for (String campo : campos) {
            // Full name
            if (removeTags(campo).contains("Full name")) {
                campo = removeTags(campo);
                this.nome = campo.substring(9).trim();

                // Nickname(s)
            } else if (removeTags(campo).contains("Nickname(s)")) {
                campo = removeTags(campo);
                this.apelido = campo.substring(11).trim();

                // Founded
            } else if (removeTags(campo).toLowerCase().contains("founded")) {
                campo = removeTags(campo.split("<br />")[0]);
                this.fundacaoMes = this.getMes(campo.toLowerCase());

                if (this.fundacaoMes == 0) {
                    this.fundacaoDia = 0;
                    campo = campo.substring(7);
                    this.fundacaoAno = Integer.parseInt(campo.substring(0, 4));
                } else {
                    campo = campo.substring(7);
                    String data[] = campo.split(" ");
                    if (data.length < 3) {
                        this.fundacaoDia = 0;
                        this.fundacaoAno = Integer.parseInt(data[1].substring(0, 4));
                    } else {
                        if (campo.contains(",")) {
                            this.fundacaoDia = Integer.parseInt(data[1].replace("th", "").replace(",", ""));
                            this.fundacaoAno = Integer.parseInt(data[2].substring(0, 4));
                        } else if (Character.isDigit(data[0].charAt(0))) {
                            this.fundacaoDia = Integer.parseInt(data[0]);
                            this.fundacaoAno = Integer.parseInt(data[2].substring(0, 4));
                        } else {
                            this.fundacaoDia = 0;
                            this.fundacaoAno = Integer.parseInt(data[1].substring(0, 4));
                        }
                    }
                }

                // Ground
            } else if (removeTags(campo).toLowerCase().contains("ground")) {
                campo = removeTags(campo);
                this.estadio = campo.substring(6).trim();

                // Capacity
            } else if (removeTags(campo).toLowerCase().contains("capacity")) {
                campo = campo.split("<br")[0];
                campo = removeTags(campo.split("</td>")[0].replace(" ", ""));
                this.capacidade = Integer.parseInt(removePunctuation(campo.substring(8).split(";")[0]));

                // Coach
            } else if (removeTags(campo).toLowerCase().contains("coach") || campo.toLowerCase().contains("manager")) {
                campo = removeTags(campo).replace("(es)", "").trim();

                if (campo.toLowerCase().contains("coach")) {
                    int index = campo.toLowerCase().indexOf("coach");
                    this.tecnico = campo.substring(index + 5).trim();
                }

                else if (campo.toLowerCase().contains("manager") && (this.tecnico == null || this.tecnico.isEmpty())) {
                    int index = campo.toLowerCase().indexOf("manager");
                    this.tecnico = campo.substring(index + 7).trim();
                }
                // League
            } else if (removeTags(campo).contains("League") && (this.liga == null || this.liga.isEmpty())) {
                campo = removeTags(campo);
                this.liga = campo.substring(6).trim();
            }
        }

        File f = new File(nomeArquivo);
        setPaginaTam(f.length());

    }
    

    public static boolean estaNoFinal(String frase){
        return (frase.length() >= 3 && frase.charAt(0) == 'F' && frase.charAt(1) == 'I' && frase.charAt(2) == 'M');
    }

}
