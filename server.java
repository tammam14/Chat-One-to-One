// Mohamed Hussein 5AIA
import java.net.*;
import java.io.*;

public class server {
    
    public static void main(String[]args)
    {
        ServerSocket sSocket;
        Socket connessione = null;
        int porta = 2345;
        InputStreamReader in, input;
        BufferedReader sIn, t;
        OutputStream out;
        PrintWriter sOut;
        String msgMand, msgRec;
        try
        {
            sSocket = new ServerSocket(porta);
            System.out.println("In attesa di un User...");
            while(true)
            {
                connessione = sSocket.accept(); // Socket Accept

                out = connessione.getOutputStream(); 
                sOut = new PrintWriter(out); // Invio al user
                in = new InputStreamReader(connessione.getInputStream());
                sIn = new BufferedReader(in); // lettura dal user
                input = new InputStreamReader(System.in);
                t = new BufferedReader(input);

                // Inserimento del UserName
                System.out.println("Inserisci il tuo nome:");
                String userName = t.readLine();
                sOut.println(userName);
                sOut.flush();

                String userName1 = sIn.readLine();

                System.out.println("In Attesa di un messaggio da " + userName1);
                while(true)
                {
                    //Invio messaggio
                    msgRec = sIn.readLine(); // Arrivo del messaggio
                    if (msgRec == null)
                    {
                        System.out.println("Il Client ha interrotto la connessione");
                        break;
                    }
                    
                    System.out.println("| "+ userName1 +": " + msgRec);
                    msgMand = t.readLine(); // Scrittura del messaggio
                    sOut.println(msgMand); // Invio del messaggio
                    sOut.flush();                    
                }
            }
        }
        catch (IOException e)
        {
            System.out.println("Errore nel caricamento");
        }
            try
            {
                connessione.close();
            }
            catch (IOException e)
            {
                System.out.println("Errore nella chiusura");
            }
    }
}