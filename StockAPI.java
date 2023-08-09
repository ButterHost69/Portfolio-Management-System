import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.FileOutputStream;


public class StockAPI {

    static Scanner scanner = new Scanner(System.in);
    
    // Downloads The Site From GOOGLE Finances
    public void download_site_data(String ticker , String exchange , Boolean logs) 
    {
        String queryUrl = "https://www.google.com/finance/quote/" + ticker + ":" + exchange + "?hl=en";
        String destination = "logs\\" + ticker + ".html";

        try 
        {
            URL url = new URL(queryUrl);
            try(InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");
                BufferedReader bufferReader = new BufferedReader(reader);
                FileOutputStream fileOutput = new FileOutputStream(destination))
            {
                if(logs){System.out.println("File Created");}
                String line;
                while ((line = bufferReader.readLine()) != null) 
                {
                    fileOutput.write(line.getBytes());
                    fileOutput.write(System.lineSeparator().getBytes());
                }
                if(logs){System.out.println("Stock Of " + ticker +" Stored");}
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) 
    {
        String ticker, exchange;
        StockAPI stockAPI = new StockAPI();
        

        System.out.println("Enter Ticker Name and Stock Exchange: ");
        ticker = scanner.next();
        exchange = scanner.next();

        System.out.println("Your Ticker: " + ticker + " and Exchange: " + exchange);
        System.out.println("---------------------------------------");
        stockAPI.download_site_data(ticker, exchange,true);

        

    }
    
}
