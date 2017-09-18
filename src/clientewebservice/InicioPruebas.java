package clientewebservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

/**
 *
 * @author Mario.Pabón
 */
public class InicioPruebas 
{
    private static final Logger LOG = Logger.getLogger(InicioPruebas.class.getName());
    
    
    private final static String DIRECCION_WS = "http://localhost:9998/direccionWebService";
    
    
    public static void main(String arg[])
    {
//        presentacionWebService();
        
//        llamadaGET_esperaJSON();
        
//        llamadaPost_EnviaJSONValueObject();
        
        llamadaPost_enviaValueObjectFinal();
    }
    
    public static void llamadaPost_enviaValueObjectFinal() {

        try {

            URL direccionURL = new URL( DIRECCION_WS + "/segundaRutaRecibeValueObject" );
            
            HttpURLConnection conexionHTTP = (HttpURLConnection) direccionURL.openConnection();
            
            conexionHTTP.setDoOutput(true);
            conexionHTTP.setRequestMethod("POST");
            conexionHTTP.setRequestProperty("Content-Type", "application/json");
            conexionHTTP.setRequestProperty("Accept", "application/json");

            String textoEnviarWS = "{\"valorEntero\":200,\"valorTextox\":\"iPad 4\"}"; //<==========al meterle la X al 'valorTexto' no carga esa propiedad, el servidor dice: ValueObject{valorTexto=null, valorEntero=100}
            
            System.out.println("JSON PREPARADO: " + textoEnviarWS);

            OutputStream outputStream = conexionHTTP.getOutputStream();
            outputStream.write(textoEnviarWS.getBytes());
            outputStream.flush();

            if ( conexionHTTP.getResponseCode() != HttpURLConnection.HTTP_CREATED ) 
            {
                throw new RuntimeException("Falló : código error HTTP: " + conexionHTTP.getResponseCode());
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            conexionHTTP.getInputStream()
                    )
            );

            String textoRetornado;
            System.out.println("Esto responde el servidor: \n");

            while (
                    (textoRetornado = br.readLine()) 
                    != null
                  ) 
            {
                System.out.println(textoRetornado);
            }

            //****************************
            conexionHTTP.disconnect();
            //****************************

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
    
    public static void llamadaPost_EnviaJSONValueObject() 
    {
        try 
        {
            URL url = new URL( DIRECCION_WS + "/primeraRutaPost");

            HttpURLConnection conexionHTTP = (HttpURLConnection) url.openConnection();
            
            conexionHTTP.setDoOutput(true);
            conexionHTTP.setRequestMethod("POST");
            conexionHTTP.setRequestProperty("Content-Type", "application/json");
//            conexionHTTP.setRequestProperty("Accept", "application/json");

            String jsonEnviar = "{\"primeraCosa\":100,\"segundaCosa\":\"iPad 4\"}";
            
            OutputStream outputStream = conexionHTTP.getOutputStream();
            
            outputStream.write(jsonEnviar.getBytes());
            outputStream.flush();

            if ( conexionHTTP.getResponseCode() != 200 ) 
            {
                throw new RuntimeException("Falló : código error HTTP: " + conexionHTTP.getResponseCode());
            }

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            conexionHTTP.getInputStream()
                    )
            );

            String textoDevueltoWS;
            
            System.out.println("Output from Server .... \n");
            
            while (
                    (textoDevueltoWS = bufferedReader.readLine())
                    != null
            ) 
            {
                System.out.println(textoDevueltoWS);
            }

            //*****************************
            conexionHTTP.disconnect();
            //*****************************

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
    
    public static void presentacionWebService() 
    {
        try 
        {
            URL direccion_url = new URL( DIRECCION_WS );

            HttpURLConnection conexionHttp = (HttpURLConnection) direccion_url.openConnection();
            
            conexionHttp.setRequestMethod("GET");

            if ( conexionHttp.getResponseCode() != 200 ) 
            {
                throw new RuntimeException( "Fallo : Código error HTTP: " + conexionHttp.getResponseCode() );
            }

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            conexionHttp.getInputStream()
                    )
            );

            String textoSalida;
            
            System.out.println("Salida del servidor: \n");
            
            while (
                    (textoSalida = bufferedReader.readLine()) 
                    != null
            ) 
            {
                System.out.println(textoSalida);
            }

            //*******************************
            conexionHttp.disconnect();
            //*******************************

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
    
    //getJSON
    public static void llamadaGET_esperaJSON() {

        try {

            URL url = new URL(DIRECCION_WS + "/rutaGetDevuelveJSON");

            HttpURLConnection conexionHTTP = (HttpURLConnection) url.openConnection();
            
            conexionHTTP.setRequestMethod("GET");

//            conexionHTTP.setRequestProperty("Accept", "application/json");
//            conn.setRequestProperty("Accept", "text/plain");

            if ( conexionHTTP.getResponseCode() != 200 ) 
            {
                throw new RuntimeException( "Falló : codigo error HTTP : " + conexionHTTP.getResponseCode() );
            }

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(
                            conexionHTTP.getInputStream() 
                    )
            );

            String textoRetornado;
            
            System.out.println("Esto se retornó del servidor:");
            
            while ( 
                    ( textoRetornado = bufferedReader.readLine() ) 
                    != null 
            ) {
                System.out.println(textoRetornado);
            }

            //******************************
            conexionHTTP.disconnect();
            //******************************

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }
}
