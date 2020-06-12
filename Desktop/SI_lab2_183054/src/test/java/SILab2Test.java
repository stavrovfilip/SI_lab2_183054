import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SILab2Test {

    private List<String> CreateUsers(String... elems){
        return new ArrayList<>(Arrays.asList(elems));
    }

    @Test
    public void MultipleConditionTest() {
        //“if (user.getUsername()==null || user.getPassword()==null)”
        //T || X
        //F || T
        //F || F
        User u1 = new User(null, "pass1", "user1@email.com");
        User u2 = new User("Filip", null, "filip@gmail.com");
        User u3 = new User ("Filip", "filip123", "filip123@gmail.com");

        List<String> users = CreateUsers("Jovan", "Jovana");

        RuntimeException ex;

        //TX
        ex = assertThrows(RuntimeException.class, ()-> SILab2.function(u1, users));
        assertTrue(ex.getMessage().contains("The user is missing some mandatory information"));

        //FT
        ex = assertThrows(RuntimeException.class, ()-> SILab2.function(u2, users));
        assertTrue(ex.getMessage().contains("The user is missing some mandatory information"));

        //FF
        assertEquals(false, () -> SILab2.function(u3, users));

        //"if (!digit || !upper || !special)"
        //TXX
        //FTX
        //FFT
        //FFF

        User u4 = new User("Filip", "lozinka", "filip@gmail.com");
        User u5 = new User("Filip", "lozinka12", "filip@gmail.com");
        User u6 = new User("Filip", "LoZiNkA12", "filip@gmai.com");
        User u7 = new User("Filip", "#LozinkA12!", "filip@gmail.com");

        //TXX
        assertFalse(SILab2.function(u4, users));

        //FTX
        assertFalse(SILab2.function(u5, users));

        //FFT
        assertFalse(SILab2.function(u6, users));

        //FFF
        assertTrue(SILab2.function(u7, users));

    }

    @Test
    public void EveryPathTest() {

        RuntimeException ex;
        List<String> users = CreateUsers("Jovan", "Jovana");

        //1,2,3 -> 4 ->26
        // userot e ednakov na null
        User u1 = null;
        ex = assertThrows(RuntimeException.class, ()-> SILab2.function(u1, users));
        assertTrue(ex.getMessage().contains("The user is not instantiated"));

        //1,2,3 -> 5 -> 6 -> 26
        //nema username ili password (vo slucajov password)
        User u2 = new User("Filip", null, "filip@gmail.com");
        ex = assertThrows(RuntimeException.class, ()-> SILab2.function(u2, users));
        assertTrue(ex.getMessage().contains("The user is missing some mandatory information"));

        //1,2,3 -> 5 -> 7,8,9 -> 10 -> 26
        //passwordot go sodrzi usernameot
        User u3 = new User("Filip", "filip12", "filip@gmail.com");
        assertFalse(SILab2.function(u3, users));

        //1,2,3 ->5 -> 7,8,9 -> 11,12 ->26
        //dolzinata na passwordot e pomala od 8
        User u4 = new User("Filip", "lozinka", "filip@gmail.com");
        assertFalse(SILab2.function(u4, users));

        //1,2,3 ->5 -> 7,8,9 -> 13, 14 -> 15.1 -> 15.2 -> 23 -> 24 ->26
        //ne e vozmozna bidejki vo for ciklusot se proveruva dolzinata na passwordot, koja e proverena pogore, pa dokolku passwordot < 0 kje
        //zavrsi uste tamu

        //1,2,3 ->5 -> 7,8,9 -> 13, 14 -> 15.1 -> 15.2 -> 23 -> 25 ->26 ne e vozmozna
        //ne e vozmozna od istata pricina pogore. Dopolnitelno, dokolku ne vleze vo for ciklusot ne moze da zavrsi so true

        //1,2,3 ->5 -> 7,8,9 -> 13, 14 -> 15.1 -> 15.2 -> (16 -> 17 -> 18 -> 20 -> 22 -> 15.3 ->15.2) -> 23 -> 24 ->26
        //ovde postojat golem broj na moznosti; da sodrzi cifri ili da ne sodrzi cifri; da nema golemi bukvi ili da ima golemi bukvi;
        //da ima ili nema specijalni znaci; Mozat da se napravat golem broj kombinacii; Vo ovoj slucaj kje pokazeme user cij sto password ne gi
        //ispolnuva site zadadeni uslovi i terminira so false, a vo sledniot i posleden kje pokazeme user cij sto password sodrzi i cifri i golemi
        //bukvi i specijalni znaci i terminira so true.
        //------> lozinkata sodrzi cifri no ne sodrzi golemi bukvi i specijalni znaci
        User u5 = new User("Filip", "12lozinka", "filip@gmail.com");
        assertFalse(SILab2.function(u5, users));

        //1,2,3 ->5 -> 7,8,9 -> 13, 14 -> 15.1 -> 15.2 -> (16 -> 17 -> 18 -> 19 -> 20 -> 21 -> 22 -> 15.3 ->15.2) -> 23 -> 25 ->26
        //Ovaa pateka ja izminuva user cij sto password sodrzi cifri, golemi bukvi i specijalni znaci.
        User u6 = new User("Filip", "1Lozinka#2#", "filip@gmail.com");
        assertTrue(SILab2.function(u6, users));

        //1,2,3 ->5 -> 7,8,9 -> 13, 14 -> 15.1 -> 15.2 -> (16 -> 17 -> 18 -> 19 -> 20 -> 21 -> 22 -> 15.3 ->15.2) -> 23 -> 24 ->26
        //Ovaa pateka e nevozmozna bidejki ne moze da gi pomine site jazli vo for ciklusot, znaci passwordot sodrzi i cifri i golemi bukvi
        //i specijalni znaci i da terminira so false
    }


}