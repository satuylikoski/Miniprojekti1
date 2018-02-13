package fi.academy.json.esimerkki;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeuraavaJuna {

/*    private lahtoAsema(String lahtoasema){
        return lAsema;
    }

    private kohdeAsema(String kohdeasema){
        return kAsema;
    }*/

    public static void seuraavaJuna(String lahtoasema, String maaraasema) {
/*        Scanner lukija = new Scanner(System.in);
    // Kysytään käyttäjältä lähtöasema
        System.out.println("Miltä asemalta lähdet? ");
        String lAsema = lukija.nextLine();
    // Kysytään käyttäjältä määränpääasema
        System.out.println("Minne olet menossa? ");
        String kAsema = lukija.nextLine();*/

        String lAsema = lahtoasema;
        String kAsema = maaraasema;

        String baseurl = "https://rata.digitraffic.fi/api/v1";
        String hakuehdot = "include_nonstopping=false";



        try {
            //Syötetään hakuehdot URLiin
            URL url = new URL(baseurl+"/live-trains/station/"+lAsema+"/"+kAsema+"?"+hakuehdot);
            ObjectMapper mapper = new ObjectMapper();
            CollectionType tarkempiListanTyyppi = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Juna.class);
            List<Juna> junat = mapper.readValue(url, tarkempiListanTyyppi);  // pelkkä List.class ei riitä tyypiksi

            System.out.println("\n\n");

            System.out.println("Seuraava juna välillä: " +lAsema +" - " + kAsema);

            int i = 0;
            System.out.printf("Juna %s - %s \n\t Lähtee: %s\n\t Liikkeellä: %s\n\t Junan tyyppi: %s\n %s\n"
                    ,junat.get(i).getTrainType()
                    ,junat.get(i).getTrainNumber()
                    ,junat.get(i).getDepartureDate()
                    ,junat.get(i).isRunningCurrently()
                    ,junat.get(i).getTrainCategory()
                    ,junat.get(i).getTimeTableRows());
            System.out.println("----------------------------------------");

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) {
        seuraavaJuna("HKI","PSL");
    }
}