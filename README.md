## Втора лабораториска вежба по Софтверско инженерство
## Филип Ставров, 183054

## Група на код: 3


## Control Flow Graph


![graf](https://user-images.githubusercontent.com/63545476/84504811-dffdc200-acbc-11ea-8352-df6a1c135293.png)

За креирање на овој Control Flow Graph, ја означив секоја од дадените линии код со број од 1 до 26, последователно. Секој број претставува
еден јазол, меѓутоа постојат линии на код кои секогаш ќе се извршат една по друга, па така направив спојвување на повеќе јазли во еден јазол.
Во Contorol Flow Graph-от постојат неколку разгранувања, како и еден фор циклус. Во второто прашање од квизот, означен е секој ред од функцијата
со соодветниот број за полесно да се добие претстава за тоа кој јазол што означува.

## Цикломатска комплексност

1. Доколку ги броиме регионите добиваме 9, плус надворешниот знали 10.
2. Цикломатската комплексност можеме да ја пресметаме и како број_ребра - број_јазли + 2 односно  30 - 22 + 2 = 10.
3. Третиот и последен начин е бројот на предикатни јазли + 1, и повторно добиваме 10.


## Тест случаи според критериумот Multiple Choice

Во оваа фунцкија имаме само два if-ови во кои имаме повеќе услови. Едниот од нив е
“if (user.getUsername()==null || user.getPassword()==null)”, а другиот е “if (!digit || !upper || !special)”. 

1. “if (user.getUsername()==null || user.getPassword()==null)”

Овој услов ги проверува корисничкото име на корисникот, како и неговиот пасворд. Доколку корисничкото име е null, изразот евалуира во
true. Доколку корисничкото име не е null, а пасвордот е null, изразот повторно евалуира во truе. Но, доколку и корисничкото име не е null,
и пасвордот не е null, тогаш изразот евалуира во false.

Овој израз може да се евалуира на три начини, а тоа се: 

-> доколку првиот услов е точен, вториот може да биде и точен и неточен, бидејќи не е од интерес

-> доколку првиот услов е неточен, вториот е точен

*во овие два случаи изразот оди во иста гранка, бидејќи главниот услов кој се состои од 2 подуслови е исполнет

-> доколку двата услови се неточни, тогаш изразот оди во друга гранка

Ова е подетално прикажано во следната табела:

![if1](https://user-images.githubusercontent.com/63545476/84513854-5ce36880-acca-11ea-95ad-68572f172db2.png)

Тестовите кои ги искористив за да го проверам овој израз се:

-> за првите два(кои евалуираат во точно) најпрво assertThrows, a потоа assertEquals
за да проверам дали е фрлен вистинскиот
исклучок.Ова е подетално прикажано во кодот, но сепак во прилог следува еден од овие два тестови:
        
      User u1 = new User(null, "pass1", "user1@email.com");
      ex = assertThrows(RuntimeException.class, ()-> SILab2.function(u1, users));
      assertTrue(ex.getMessage().contains("The user is missing some mandatory information"));
      
-> за третиот случај, искористив assertEquals, каде може да стои вредност true или falsе во зависност
од пасвордот кој го испраќаме.
    
        assertEquals(false, () -> SILab2.function(u3, users));
       
2. "if (!digit || !upper || !special)"

Овој услов проверува дали пасвордот на корисникот содржи цифри, големи букви и специјални карактери.
Изразот може да се евалуира на 4 начини,и притоа првите три се разгрануваат во иста гранка т.е евалуираат во ture, а последниот
евалуира во false.

Како што реков, изразот може да се евалуира во 4 насоки, а тие се:

  -> првиот подуслов да е точен, а останатите да се било што. т.е ако пасвордот не содржи цифри, изразот евалуира во точен без разлика дали
  пасвордот содржи големи букви или специјални карактери
  
  -> првиот подуслов да е неточен, вториот да е точен, а третиот да е било што. т.е пасвордот да содржи цифри, да  не содржи големи букви и 
  неважно е дали содржи специјални карактери, повторно евалуира во точен израз
  
  -> првиот и вториот подуслов да се неточни, а третиор да е точен, т.е изразот евалуира во точен и во случај пасвордот да содржи
  цифри и големи букви, но да не содржи специјални карактери.
  
  *доколку условот е исполнет тој преминува во следниот јазол, а тоа е 24, што знали враќа false
  
  -> и на крај доколку сите подуслови се неточни, т.е пасвордот содржи и цифри и големи букви, а и специјални знаци, условот евалуира
  како неточен и оди во јазол 25, односно враќа true.
  
  Ова е подетално прикажано во следнава табела:
  
  ![if2](https://user-images.githubusercontent.com/63545476/84518259-b8b0f000-acd0-11ea-85be-9a145905390b.png)
  
  Овде искористив само два теста и тоа, за првите три случаи, кои евалуираат во true и одат во јазол 24(значи враќаат false)
  искористив assertFalse(во примеров изразот евалуира во точен уште на првиот подуслов бидејќи лозинката не содржи цифри, а штом
  евалуира во true, значи дека оди во јазол 24 и враќа false)
  
      User u4 = new User("Filip", "lozinka", "filip@gmail.com");
      assertFalse(SILab2.function(u4, users)
      
  За последниот случај во кој пасвордот ги содржи сите потребни карактеристики и оди во јазол 25 каде враќа true искористив
  assertTrue
    
      User u7 = new User("Filip", "#LozinkA12!", "filip@gmail.com");
      assertTrue(SILab2.function(u7, users));
      
 
 ## Тест случаи според критериумот Every Path
 
 Според овој критериум ние треба да ги изминеме сите можни патеки од почетниот до крајниот јазол. Од Control Flow Graph-от можеме да видеме
 дека до последниот јазол водат 6 гранки. Ова значи дека постојат минимум 6 патеки(а сигурно повеќе поради фор циклусот) од почетниот до
 крајниот јазол.
 
 Ќе разгледаме некои од случаите, како и тестовите за нив:
 
 1. 1,2,3 -> 4 -> 26 // нема инстанца од класата User, што значи дека фрла исклуок The user is not instantiated, па тоа и го тестираме
  
     User u1 = null;
    ex = assertThrows(RuntimeException.class, ()-> SILab2.function(u1, users));
    assertTrue(ex.getMessage().contains("The user is not instantiated"));
  
  2. 1,2,3 -> 5 -> 6 -> 26 // нема username или password (vo slucajov password), што значи фрла исклучок The user is missing
  some mandatory information, па тоа го тестираме
  
      User u2 = new User("Filip", null, "filip@gmail.com");
      ex = assertThrows(RuntimeException.class, ()-> SILab2.function(u2, users));
      assertTrue(ex.getMessage().contains("The user is missing some mandatory information"));
      
  3. 1,2,3 -> 5 -> 7,8,9 -> 10 -> 26 //passwordot го содржи usernameot, а доколку го содржи враќа false, па таков е и тестот
  
      User u3 = new User("Filip", "filip12", "filip@gmail.com");
      assertFalse(SILab2.function(u3, users));
      
  4. 1,2,3 ->5 -> 7,8,9 -> 11,12 ->26 //должината на passwordot е помала 8, што доколку е исполнето враќа false
  
      User u4 = new User("Filip", "lozinka", "filip@gmail.com");
      assertFalse(SILab2.function(u4, users));
      
  5. 1,2,3 ->5 -> 7,8,9 -> 13, 14 -> 15.1 -> 15.2 -> (16 -> 17 -> 18 -> 20 -> 22 -> 15.3 ->15.2) -> 23 -> 24 ->26
     // во овој случај која влегува во фор циклусот постојат многу можности, односно да содржи некој од потребните карактери,
     а други да не ги содржи и слично. Во конкретниот пример подолу пасвордост содржи цифри, а не содржи големи букви и специјални
     знаци, што значи ќе заврши во јазол 24, т.е ќе врати false.
        
      User u5 = new User("Filip", "12lozinka", "filip@gmail.com");
      assertFalse(SILab2.function(u5, users));
      
  6. 1,2,3 ->5 -> 7,8,9 -> 13, 14 -> 15.1 -> 15.2 -> (16 -> 17 -> 18 -> 19 -> 20 -> 21 -> 22 -> 15.3 ->15.2) -> 23 -> 25 ->26
        // Оваа патека се изминува само кога пасвордот на корисникот е поголем од 8 карактери, содржи цифри, големи букви и 
        специјални знаци. Оваа патека е единствената патека која завршува во јазол 25 и враќа краен резултат true.
        
      User u6 = new User("Filip", "1Lozinka#2#", "filip@gmail.com");
      assertTrue(SILab2.function(u6, users));
      
      
## Објаснување на напишаните unit tests

Unit тестовите ги напишав во класата /src/test/java/SILab2Test. Оваа класа се состои од две посебни функции, една за тестовите
за критериумот Multiple Choice, а друга за тестовите за критериумот Every Path. Притоа тесовите кои ги искористив се
assertThrows, assertEquals, assertFalse и AssertTrue.
       


  
  
 
