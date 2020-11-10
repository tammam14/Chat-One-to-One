//  Mohamed Hussein 5AIA
import java.net.*;
import java.io.*;

public class client {
    
    public static void main(String[]args) 
    {
        Socket connessione = null;
        String server = "localhost";
        int porta = 2345;
        InputStreamReader in, input;
        BufferedReader sIn, t;
        OutputStream out;
        PrintWriter sOut;
        String msgMand, msgRec;

        try
        {
            connessione = new Socket(server, porta); // Creazione del Socket
            System.out.println("Connessione Effettuta!");
        }
        catch (IOException e)
        {
            System.out.println("Errore nel caricamento");
            System.exit(-1);
        }
            try
            {
                in = new InputStreamReader(connessione.getInputStream());
                sIn = new BufferedReader(in);   // lettura dal user
                out = connessione.getOutputStream();
                sOut = new PrintWriter(out); // Invio al user
                input = new InputStreamReader(System.in);
                t = new BufferedReader(input);

                // Inserimento UserName
                System.out.println("Inserisci il tuo nome:");
                String userName = t.readLine();
                sOut.println(userName);
                sOut.flush();

                String userName1 = sIn.readLine();

                System.out.println("Chat caricata con "+ userName1 +", ora puoi scrivere!");
                while(true)
                {
                    // Invio del messaggio
                    msgMand = t.readLine(); // Scrittura del messaggio

                    if (msgMand.equals("FINE")) break;

                    sOut.println(msgMand); // Invio del messaggio
                    sOut.flush();

                    msgRec = sIn.readLine(); //Messaggio ricevuto 
                    System.out.println("| "+ userName1 +": " + msgRec);
                }
                
            }
            catch (IOException e)
            {
                System.out.println("Errore nell'invio");
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