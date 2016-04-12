package com.processo.leao.verde.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.Priority;
import org.apache.log4j.RollingFileAppender;
import org.xml.sax.InputSource;
 

@SuppressWarnings("restriction")
public class LVLog 
{

	private static Logger log = Logger.getLogger(LVLog.class);
	private static LogParametros logParametros ;
	
	private final static String EXTENSAO  = ".java";
	private final static String LAYOUT_00 = "%m%n";
	private static String LAYOUT_01 = "%-2d{ HH:mm:ss.SSS}  %5p  - %m%n";
	private final static String SEPARADOR =  "---------------------------------------------------------------------------";
	private final static String VAZIO 	  =  ""; 
	private final static int LIGADO =1;


	private static long tempoInicio;
	private static long tempo;
	 
	protected static String nome;
	protected static String classe;
	protected static String metodo;
	protected static String sessao="";
	protected static String nsu="";
	protected static Logger rootLogger = Logger.getRootLogger();


	protected LVLog(String nome,String nsu) throws Exception {
		  	
		
		logParametros =new LogParametros();
		
		LVLog.LAYOUT_01=logParametros.getLayout();
	
		LVLog.nsu = nsu;
		
		LVLog.nome = nome;

		log = Logger.getLogger(nome);
 
		tempoInicio = System.currentTimeMillis();
		   
		relatorio_abertura();
		
		cabecalho(); 
		
		propriedades(rootLogger,LAYOUT_01);
		
	}
	 

	 
	
	public static LVLog NovoLog(String nome,String nsu) throws Exception {
		return new LVLog(nome,nsu);
	}
	
 
	
	@SuppressWarnings("deprecation")
	private static void cabecalho() throws Exception
	{ 	
		rootLogger.removeAllAppenders();
		propriedades(rootLogger,LAYOUT_00);
	
		log(VAZIO , Priority.INFO, VAZIO);
		log(VAZIO , Priority.INFO, SEPARADOR);
		log(VAZIO , Priority.INFO, "Processo: ".concat(nome));
		log(VAZIO , Priority.INFO, "Usuario : ".concat("Jasiel pereira"));
		
		if(!sessao.isEmpty()) 
			log(VAZIO , Priority.INFO, "Sessao  : ".concat(sessao));
		
		if(!nsu.isEmpty()) 
			log(VAZIO , Priority.INFO, "Nsu  : ".concat(nsu));
		 
		log(VAZIO , Priority.INFO, VAZIO);
	  
		rootLogger.removeAllAppenders();
	}
	 
	protected static String data() {
		return new SimpleDateFormat("ddMMyyyy").format(new Date());
	}
	
	
	public static String getSessao(){
		return sessao;
	}
	
	public static void setSessao(String sSessao){
		sessao = sSessao;
	}
	
	public static String getNSU(){
		return nsu;
	}
	 
	public static String classeChamadora(boolean metodo) {
		
		Throwable thr = new Throwable();

		thr.fillInStackTrace();

		String nome_classe ;
		
		LVLog.classe = thr.getStackTrace()[2].getFileName().toString().replace(EXTENSAO, VAZIO);
		LVLog.metodo = thr.getStackTrace()[2].getMethodName();
		
		if(metodo)
			nome_classe = "Classe: ".concat(LVLog.classe.concat(" - Metodo: ").concat(LVLog.metodo));
		else
			nome_classe = "Classe: ".concat(LVLog.classe);

		return nome_classe.contains(EXTENSAO) ? nome_classe.replace(EXTENSAO, VAZIO).concat(" - ") : nome_classe.concat(" - ");
	}

	/**
	 * Metodo responsavel por substituir o arquivo de propriedades padrao do
	 * 	Log4j, facilitando assim a customizacao da classe de log.
	 * @param rootLogger
	 * @param sLayout 
	 * @author jasiel.santana
	 * @throws Exception 
	 * 
	 */
	protected static void propriedades(Logger rootLogger,String sLayout) throws Exception {

		rootLogger.setLevel(Level.DEBUG);

		PatternLayout layout = new PatternLayout(sLayout);

		/** Adiciona o layout de cada linha do Log.*/
		rootLogger.addAppender(new ConsoleAppender(layout));
		String path = "c:\\log\\";
		try {
			/** Define Local para Salvar o arquivo de log */
			RollingFileAppender fileAppender = new RollingFileAppender(layout,path.concat("\\".concat( nome ).concat( "_").concat( data()).concat( ".log")));

			// Adiciona as definicoes ao log
			rootLogger.addAppender(fileAppender);

			
		} catch (Exception e) {
			System.out.println("Erro "+e.getMessage());
		}
	}
	
	 
	
	/**
	 * 
	 * @author jasiel.santana
	 * @param objeto
	 * @param legenda
	 * @throws Exception
	 * 
	 * @PreRequisito Quando passar um objeto para essa classe Certifique-se que
	 *               a Classe possui a seguinte anotacao @XmlRootElement
	 *               conforme exemplo abaixo: 
	 *               
	 *               <code>
	 * 
	 			@XmlRootElement 
	 			public class Response 
	 			{
	 * 
	 *                 public Response() { 
	 *                  
	 *                 }
	 * 
	 *                 String Nome; 
	 *                 public String getNome() 
	 *                 { 
	 *                 			return Nome; 
	 * </code>
	 */ 
	@SuppressWarnings("deprecation")
	public static void logXml(Object objeto,String legenda) throws Exception{
	 
			log(VAZIO , Priority.DEBUG, legenda);
		    log(VAZIO , Priority.DEBUG,objectToXML(objeto));	
		 
		
	}

	
	@SuppressWarnings("deprecation")
	public static void logQuery(Object objeto,String legenda) throws Exception{
	 
			log(VAZIO , Priority.DEBUG, legenda);
		    log(VAZIO , Priority.DEBUG, objeto.toString());	
		
	}
	
	@SuppressWarnings("deprecation")
	public static void logInfo(String message) throws Exception { 
		classeChamadora(true);
		log(classeChamadora(false), Priority.INFO, message);
	}
	
	public static void InfoInicioCalc() {
		tempo = System.currentTimeMillis();
	}
	 
	@SuppressWarnings({ "deprecation", "resource" })
	public static void InfoTempo(String metodo) throws Exception 
	{
			log("Tempo de Execucao", Priority.DEBUG, new Formatter().format("\nO Metodo %s foi executado em %.3f ms%n", metodo, (System.currentTimeMillis() - tempo) / 1000d).toString());
	 }

	

	@SuppressWarnings("deprecation")
	public static void logDebug(String message)throws Exception {
		log(classeChamadora(true), Priority.DEBUG, message);
	}

	@SuppressWarnings("deprecation")
	public static void logFatal(String message) throws Exception {
		log(classeChamadora(true), Priority.FATAL, message);
	}
	
	@SuppressWarnings("deprecation")
	public static void logErro(String message) throws Exception {
		log(classeChamadora(true), Priority.ERROR, message);
	}
	
	@SuppressWarnings("deprecation")
	public static void logErro(Exception erro) throws Exception 
	{
		
		log(VAZIO , Priority.ERROR, "Classe: " .concat(erro.getStackTrace()[0].getFileName().toString()));
		log(VAZIO , Priority.ERROR, "Metodo: " .concat(erro.getStackTrace()[0].getMethodName().toString()));
		log(VAZIO , Priority.ERROR, "Linha: " .concat(String.valueOf(erro.getStackTrace()[0].getLineNumber())));
		log(VAZIO , Priority.ERROR, "Descricao: " .concat(erro.getMessage()));
		
	}
	
	

	@SuppressWarnings("deprecation")
	public static void logWarn(String message) throws Exception {
		log(classeChamadora(true), Priority.WARN, message);
	}

	@SuppressWarnings({ "resource", "deprecation", "static-access" })
	public static void finalProcessoSucesso() throws Exception 
	{
		rootLogger.removeAllAppenders();
		
		propriedades(rootLogger,LAYOUT_00);
		
		log(VAZIO , Priority.INFO, VAZIO);
		log(VAZIO , Priority.INFO, "Processo: ".concat(nome).concat(" Finalizado com sucesso."));
		log(VAZIO , Priority.INFO, new Formatter().format("\nTempo Execucao %.3f ms%n", (System.currentTimeMillis() - tempoInicio) / 1000d).toString());
		log.removeAllAppenders();
		log.shutdown(); 
	}

	@SuppressWarnings({ "resource", "deprecation", "static-access" })
	public static void finalProcessoErro() throws Exception {
		rootLogger.removeAllAppenders();
		
		propriedades(rootLogger,LAYOUT_00);
		
		log(VAZIO , Priority.ERROR, VAZIO);
		log(VAZIO , Priority.ERROR, "Processo: ".concat(nome).concat(" Finalizado com Falha."));
		String mensagem = new Formatter().format("\nTempo Execucao %.3f ms%n", (System.currentTimeMillis() - tempoInicio) / 1000d).toString();
		log(VAZIO , Priority.ERROR,mensagem);
		log.removeAllAppenders();
		log.shutdown();
	 
	}

	protected static void log(String classe, Priority priority, String menssage) throws Exception {
		 
		//System.out.println("\n".concat(priority.toString().concat(" ").concat(classe.concat(menssage))));
	
		if(logParametros.valida_nivel_arquivo(priority))
				log.log(priority, classe.concat(menssage)); 
		 
	}
	
	@SuppressWarnings({ "deprecation" })
	private void relatorio_abertura() throws Exception{
		 
		String path = "c:\\log\\";
		
		if(!new File(path.concat("\\".concat( nome ).concat( "_").concat( data()).concat( ".log"))).exists())
		{
			propriedades(rootLogger,LAYOUT_00);
			
			 
			log(VAZIO , Priority.INFO, VAZIO);
		    log(VAZIO , Priority.INFO, SEPARADOR);
			rootLogger.removeAllAppenders();
		} 
	}
	  
	
	
	private static <T> String objectToXML(Object object) 
	{

		StringWriter sw = new StringWriter();
		try
		{
			
		 
			Marshaller marshaller = JAXBContext.newInstance(object.getClass()).createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.marshal(object, sw);

			return formatXml(sw.toString());
		} catch (JAXBException e) {
			System.out.println(e.getCause().toString());
		}
		finally
		{
			closeQuietly(sw);
		}
		return "";

	}
	
	 

	private static void closeQuietly(Closeable c)
	{

		try
		{
			if (c != null)
			{
				c.close();
			}
		}
		catch (Exception e)
		{
			// nao faz nada
		}
	}
	
	/**
	 * @author jasiel.santana
	 * @Descricao Metodo Responsavel por formatar uma String Xml
	 * 				Com o objetivo de identar e facilitar a viasualizacao do mesmo no momento do log.
	 */
	 public static String formatXml(String xml)
	 {
	        try{
	            Transformer serializer= SAXTransformerFactory.newInstance().newTransformer();
	            serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	           
	            serializer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2"); 
	            Source xmlSource = new SAXSource(new InputSource(new ByteArrayInputStream(xml.getBytes())));
	            StreamResult res =  new StreamResult(new ByteArrayOutputStream());            
	            serializer.transform(xmlSource, res);
	            return new String(((ByteArrayOutputStream)res.getOutputStream()).toByteArray());
	        }catch(Exception e){
	            
	            return xml;
	        }
	    }
	 
	 private class LogParametros{
		 
			public LogParametros( ) 
			{ 
				
				 
			}
			
			@SuppressWarnings({ "static-access", "deprecation" })
			public boolean valida_nivel_arquivo(Priority priority){
				
				if(priority.equals(priority.ERROR))
					return (logParametros.getNivel_arquivo_erro() == LIGADO);
				else if(priority.equals(priority.DEBUG))
					return (logParametros.getNivel_arquivo_debug() == LIGADO);
				else if(priority.equals(priority.FATAL))
					return (logParametros.getNivel_arquivo_fatal() == LIGADO);
				else if(priority.equals(priority.INFO))
					return (logParametros.getNivel_arquivo_info() == LIGADO);
				else if(priority.equals(priority.WARN))
					return (logParametros.getNivel_arquivo_warn() == LIGADO);
				else return false;
				
				
			}	
			
			 
			
			public String getLayout() {
				return layout;
			}
	 
			public int getNivel_arquivo_info() {
				return nivel_arquivo_info;
			}
			public int getNivel_arquivo_erro() {
				return nivel_arquivo_erro;
			}
			public int getNivel_arquivo_debug() {
				return nivel_arquivo_debug;
			}
			public int getNivel_arquivo_fatal() {
				return nivel_arquivo_fatal;
			}
			public int getNivel_arquivo_warn() {
				return nivel_arquivo_warn;
			}
			
			
			private String layout="%-2d{ HH:mm:ss.SSS}  %5p  - %m%n";
		 
			private int nivel_arquivo_info=LIGADO;
			private int nivel_arquivo_erro=LIGADO;
			private int nivel_arquivo_debug=LIGADO;
			private int nivel_arquivo_fatal=LIGADO;
			private int nivel_arquivo_warn=LIGADO;

		
		}

}
