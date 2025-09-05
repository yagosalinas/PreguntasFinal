package com.yago.preguntas;
import com.yago.preguntas.bd.Pregunta;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.yago.preguntas.bd.PreguntaLab;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements PreguntaFragment.gestionarPulsacionRespuesta {
    private ArrayList<Pregunta> arrayListPreguntas;
    private ArrayList<Pregunta> arrayListPreguntasRealizadas = new ArrayList<Pregunta>();
    private PreguntaLab myPLab;
    int totalp;
    TextView aciertos, fallos, mensajeInicio, tvuser;
    Button btEmpezar;
    int indicePR = 0;
    String fechainicio, fechafin;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String time = "";
    int sec =0;
    String user;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        fallos = findViewById(R.id.fallos);
        aciertos = findViewById(R.id.aciertos);
        btEmpezar = findViewById(R.id.botonEmpezar);
        mensajeInicio = findViewById(R.id.mensageInicio);
        tvuser = findViewById(R.id.usuariotv);
        tvuser.setSelected(true);

        if (getIntent().getStringExtra("username") != null) {
            user = getIntent().getStringExtra("username");
            id = getIntent().getIntExtra("iduser", 0);
            tvuser.setText(user);
        }
        myPLab = PreguntaLab.get(this);
        /*Pregunta pa=myPLab.getPregunta("Película ganadora del óscar del 96");
        if(pa==null){
            System.out.println("no esta");
        }else{
            System.out.println("si esta"+pa.toString());
        }
        //Pregunta p26 = new Pregunta("Película ganadora del óscar del 95", "Braveheart", "Rocky III", "Piratas del caribe", "Forrest Gump", "Forrest Gump");
        //Pregunta p27 = new Pregunta("Película ganadora del óscar del 96", "Braveheart", "Rocky III", "Piratas del caribe", "Forrest Gump", "Braveheart");
        //myPLab.delete(pa);*/

        if (myPLab.getPreguntas().isEmpty()) {
            cargarPreguntas();
        }
        List<Pregunta> listaPreguntas = myPLab.getPreguntas();
        System.out.println("tamaño: "+listaPreguntas.size());
        arrayListPreguntas = listToArrayList(listaPreguntas);


    }

    public void cargarPreguntas() {
        myPLab = PreguntaLab.get(this);

        Pregunta p1 = new Pregunta("¿En que fecha se produjo el desembarco de normandia?", "6 de Junio de 1944", "7 de Agosto de 1943", "25 de Diciembre de 1943", "1 de Enero se 1944", "6 de Junio de 1944");

        Pregunta p2 = new Pregunta("¿Quién pintó 'el Expolio'?", "El Greco", "Diego Velázquez", "Antonio Machado", "Nicolás Redondo", "El Greco");

        Pregunta p3 = new Pregunta("¿Del deportivo de la coruña?", "Herculino", "Fracasado", "Carballón", "Montañés", "Herculino");

        Pregunta p4 = new Pregunta("¿Autor de 'La Peste'?", "José María Aznar", "Isabel Allende", "Albert Camus", "Nostale Webber", "Albert Camus");

        Pregunta p5 = new Pregunta("¿Como se llama al consejero del califa?", "Visir", "Muladi", "Qadí", "Ulema", "Visir");

        Pregunta p6 = new Pregunta("¿Qué significa isonomía?", "Tener dos lados iguales", "Igualdad ante la ley", "Derecho a la palabra", "No poder dormir bien", "Igualdad ante la ley");

        Pregunta p7 = new Pregunta("¿A qué grupo pertenecía Rosendo?", "Leño", "Fitipaldis", "Marea", "La fuga", "Leño");

        Pregunta p8 = new Pregunta("¿Quién escribió ‘El Sastrecillo valiente’?", "Steve Larson", "Dan Brown", "Los hermanos Grimm", "Charles Dickinson", "Los hermanos Grimm");

        Pregunta p9 = new Pregunta("¿Quién escribió ‘Aladino y la lámpara mágica’?", "Herman Kennie", "Walt Disney", "John Payne", "Anónimo", "Anónimo");

        Pregunta p10 = new Pregunta("¿De quién es la canción ‘Walk like an egypcian’?", "U2", "ACDC", "The Bangles", "The Beatles", "The Bangles");

        Pregunta p11 = new Pregunta("¿Cuál es la capital de Macedonia?", "Macedonia", "Riga", "Skopje", "Termas", "Skopje");

        Pregunta p12 = new Pregunta("¿Cómo se llamaba la bomba que fue lanzada en Hiroshima?", "Fat Joe", "Biggie", "Little boy", "Fat Man", "Little boy");

        Pregunta p13 = new Pregunta("¿Cómo se llamaba la bomba que fue lanzada en Nagasaki?", "Fat Joe", "Biggie", "Little boy", "Fat Man", "Fat Man");

        Pregunta p14 = new Pregunta("Unidad de presión en el Sistema Internacional", "Newton", "Metro", "Pascal", "Radián", "Pascal");

        //Pregunta p15= new Pregunta("Para minimizar las pérdidas nutritivas en los alimentos, se recomienda…", "Usar siempre alimentos congelados", "Cubrir las verduras con agua fría y poner a hervir", "Freír las verduras en aceite a más de 120º", "No dejar verduras y vegetales a remojo por largo tiempo", "No dejar verduras y vegetales a remojo por largo tiempo");

        Pregunta p16 = new Pregunta("¿Cuántas cifras tiene un SUDOKU?", "8", "9", "10", "12", "9");

        Pregunta p17 = new Pregunta("¿Qué tipo de palabra es ‘diálogo’?", "Esdrújula", "Llana", "Aguda", "Deshacer un diptongo", "Esdrújula");

        Pregunta p18 = new Pregunta("¿Donde nació Miguel de Cervantes?", "Madrid", "Valladolid", "Alcalá de Henares", "En un lugar de la Mancha", "Alcalá de Henares");

        Pregunta p19 = new Pregunta("¿Con qué nombre se conoce coloquialmente la ciudad de Cádiz?", "La salinera", "La cortesana", "La tacita de plata", "El puerto de América", "La tacita de plata");

        Pregunta p20 = new Pregunta("¿Dónde nació Leonardo di Ser Piero?", "Mantua", "Anchiano", "Chernionese", "Vinchi", "Vinchi");

        Pregunta p21 = new Pregunta("¿Qué famoso escritor británico nació en Portsmouth?", "Dan Brown", "Stephen King", "Steve Larson", "Charles Dickens", "Charles Dickens");

        Pregunta p22 = new Pregunta("¿Cuál de los siguientes países no pertenecía a la Comunidad Europea del Carbón y Acero en el momento de su constitución?", "Alemania", "Bélgica", "Noruega", "Francia", "Noruega");

        Pregunta p23 = new Pregunta("¿Cuál es el participio del verbo romper?", "Rompiendo", "Haber roto", "Roto", "Romper", "Roto");

        Pregunta p24 = new Pregunta("¿Dónde nació Pedro Almodóvar?", "Calzada de Calatrava", "Logroño", "Tudela", "León", "Calzada de Calatrava");

        Pregunta p25 = new Pregunta("¿Cuáles son los 2 tipos de memoria electrónica que forman parte de una computadora?", "ROM y RAM", "ALU y RAM", "CPU y ALU", "CPU y RAM", "ROM y RAM");

        Pregunta p26 = new Pregunta("Película ganadora del óscar del 95", "Braveheart", "Rocky III", "Piratas del caribe", "Forrest Gump", "Forrest Gump");
        Pregunta p36 = new Pregunta("Película ganadora del óscar del 96", "Braveheart", "Rocky III", "Piratas del caribe", "Forrest Gump", "Braveheart");

        Pregunta p27 = new Pregunta("¿Quién es el autor del discóbolo de Mirón?", "Miguel Ángel", "Rafael", "Mirón", "Fidias", "Mirón");

        Pregunta p28 = new Pregunta("¿Qué actor hacía pareja con Jack Lemmon en la película ‘La extraña pareja’?", "Walter Matthau", "Cary Grant", "Tony Curtis", "Paul Newman", "Walter Matthau");

        Pregunta p29 = new Pregunta("¿Cuántos jugadores juegan en un equipo de balonmano?", "7", "6", "5", "8", "7");

        Pregunta p30 = new Pregunta("¿Cómo era la religión griega?", "Atea", "Monoteísta", "Politeísta", "Todas son incorrectas", "Politeísta");

        Pregunta p31 = new Pregunta("¿En qué año nació el skater Tony Hawk?", "1973", "1971", "1968", "1978", "1968");

        Pregunta p32 = new Pregunta("¿Qué comparte Newton con Gottfried Leibniz?", "El descubrimiento de los colores primarios", "La propuesta de la teoría de la relatividad", "El descubrimiento de los átomos", "El desarrollo del cálculo integral y diferencial", "El desarrollo del cálculo integral y diferencial");

        Pregunta p33 = new Pregunta("El Golfo de Vizcaya está en el mar…", "Cantábrico", "Mediterráneo", "Caribe", "del Norte", "Cantábrico");

        Pregunta p34 = new Pregunta("Calcula el 21% de 3,00", "0,64", "0,65", "0,63", "0,72", "0,63");

        Pregunta p35 = new Pregunta("¿Con qué método se extendió la doctrina católica?", "Catecismo", "Publicidad", "Fama", "Poder", "Catecismo");

        // Pregunta p36= new Pregunta("Órganos del aparato respiratorio", "Fosas nasales, faringe, laringe, tráquea, bronquios y pulmones", "Boca, laringe, faringe, estómago, intestino y ano", "Fosas nasales y pulmones", "Fosas nasales, faringe, laringe, estómago, tráquea, bronquios y pulmones", "Fosas nasales, faringe, laringe, tráquea, bronquios y pulmones");

        Pregunta p37 = new Pregunta("Una serpiente pertenece al grupo de los…", "Invertebrados", "Floreados", "Vertebrados", "Ungulados", "Vertebrados");

        Pregunta p38 = new Pregunta("¿Quién escribió ‘Nuestra Señora de París’?", "Jacinto Benavente", "Rafael Urbano", "Víctor Hugo", "Enrique Granados", "Víctor Hugo");

        Pregunta p39 = new Pregunta("¿Cuál es el gentilicio de los habitantes de Tarragona?", "Tarragoneses", "Tarraconenses", "Tarragonienses", "Tarroganos", "Tarraconenses");

        Pregunta p40 = new Pregunta("¿Qué ave puede volar hacia atrás?", "Jilguero", "Urraca", "Colibrí", "Mirlo", "Colibrí");

        Pregunta p41 = new Pregunta("¿Qué cantante se llama en realidad Georgios Kyriacos Panaiotou?", "Freddie Mercury", "George Harrison", "George Michael", "Boy George", "George Michael");

        Pregunta p42 = new Pregunta("¿El municipio de Castañeda se encuentra en la Comunidad de…", "Aragón", "Navarra", "La Rioja", "Cantabria", "Cantabria");

        Pregunta p43 = new Pregunta("¿De quién es el tema ‘You Sexy Thing’?", "Hot Chocolate", "Rage Against the Machine", "Escorbuto", "Dover", "Hot Chocolate");

        Pregunta p44 = new Pregunta("¿Cuál es el valor simplificado de la fracción quince veinteavos?", "Dos quintos", "Tres cuartos", "Tres quintos", "Dos décimos", "Tres cuartos");

        Pregunta p45 = new Pregunta("¿Cuál es el intento del golpe de estado del general Sanjurjo?", "1932", "1936", "1931", "1933", "1932");

        Pregunta p46 = new Pregunta("¿De qué color era el caballo blanco de Santiago?", "Rojo", "Blanco", "Azul", "Santiago no tenía caballo, iba en burro", "Blanco");

        Pregunta p47 = new Pregunta("Qué es la natalidad?", "Es que nace un nuevo ser vivo", "Es practicar sexo para tener hijos", "Es el número de nacimientos que se producen en un lugar con el total de la población de ese lugar", "Es la media de años que se espera que viva una persona desde su nacimiento hasta su muerte", "Es el número de nacimientos que se producen en un lugar con el total de la población de ese lugar");

        Pregunta p48 = new Pregunta("¿Quién fue el padre del psicoanálisis?", "Kafka", "Freud", "Sabonis", "Gilmour", "Freud");

        Pregunta p49 = new Pregunta("¿Quién fue el marido de Juana la Loca?", "Agustín Pantoja", "Felipe el Hermoso", "Fernando el Católico", "Carlos I", "Felipe el Hermoso");

        Pregunta p50 = new Pregunta("¿Cuál es la mayoría de edad en España?", "16 años", "18 años", "21 años", "25 años", "18 años");

        Pregunta p51 = new Pregunta("¿A cuántas pesetas equivale un euro?", "166,386", "122", "500.000", "138,345", "166,386");

        Pregunta p52 = new Pregunta("¿En qué año se descubrió América?", "1356", "1502", "1808", "1492", "1492");

        Pregunta p53 = new Pregunta("¿Quién escribió Los Tres Mosqueteros?", "Julio Verne", "Emilio Salgari", "Alejandro Dumas", "Arturo Pérez Reverte", "Alejandro Dumas");

        Pregunta p54 = new Pregunta("Voces masculinas ordenadas de agudo a grave", "Mezzosoprano, contralto y soprano", "Aguda, llana y esdrújula", "Tenor, barítono y bajo", "contralto, mezzosoprano y soprano", "Tenor, barítono y bajo");

        Pregunta p55 = new Pregunta("¿Cuál es la capital de Ruanda?", "Utus", "Tutsis", "Kigali", "Killman", "Kigali");

        Pregunta p56 = new Pregunta("¿Cuál es la capital de Bielorrusia?", "Kiev", "Lieja", "Minsk", "Bucarest", "Minsk");

        Pregunta p57 = new Pregunta("¿Cuántos peones de cada color hay en ajedrez?", "6", "8", "10", "12", "8");

        Pregunta p58 = new Pregunta("¿Qué clase de palabra es ‘desplegable’?", "Compuesta", "Sigla", "Parasintética", "Ninguna de las anteriores", "Ninguna de las anteriores");

        Pregunta p59 = new Pregunta("El municipio de Tudela se encuentra en la Comunidad Autónoma de…", "Navarra", "Aragón", "Cantabria", "Galicia", "Navarra");

        Pregunta p60 = new Pregunta("¿Cómo se denomina el techo curvo de una nave?", "Friso", "Arco", "Bóveda", "Dintel", "Bóveda");

        Pregunta p61 = new Pregunta("¿Cuando una empresa tiene 56 trabajadores, qué tipo de empresa es?", "Grande", "Mediana", "Pequeña", "Enorme", "Mediana");

        Pregunta p62 = new Pregunta("¿Qué función tiene el alcázar en una ciudad musulmana?", "Proteger la ciudad", "Es el mercado", "Es un edificio religioso", "Es un museo de arte", "Proteger la ciudad");

        Pregunta p63 = new Pregunta("¿Cuál es el nombre del guitarrista de Queen?", "Freddie Mercury", "Brian May", "Roger Taylor", "John Deacon", "Brian May");

        Pregunta p64 = new Pregunta("Nada, sólo éramos estos gatos….", "2", "3", "1", "4", "4");

        Pregunta p65 = new Pregunta("Los egipcios escribían en….", "Tablas de arcilla", "Papel", "Papiros", "Pergaminos", "Papiros");

        Pregunta p66 = new Pregunta("Los moluscos pueden ser…", "Terrestres", "Acuáticos", "Terrestres y acuáticos", "Salados y dulces", "Terrestres y acuáticos");

        Pregunta p67 = new Pregunta("¿Cuál es el gentilicio de los nacidos en Calahorra?", "Calorros", "Calahorranos", "Calahorrenses", "Calagurritanos", "Calagurritanos");

        Pregunta p68 = new Pregunta("¿Cuál de las siguientes películas fue dirigida por John Ford?", "Centauros del Desierto", "El Padrino", "En Busca del Arca Perdida", "Silverado", "Centauros del Desierto");

        Pregunta p69 = new Pregunta("¿Cuántas esposas tuvo Enrique VIII?", "6", "2", "1", "Ninguna", "6");

        Pregunta p70 = new Pregunta("¿Quién mató a Abel?", "Adán", "Florentino Pérez", "Caín", "Sem", "Caín");

        Pregunta p73 = new Pregunta("¿Qué estilo artístico vino después del Románico?", "Barroco", "Gótico", "Plateresco", "Clásico", "Gótico");

        Pregunta p74 = new Pregunta("¿Cuántos Campeonatos del Mundo ha ganado la selección absoluta española de fútbol?", "1", "2", "3", "Ninguno", "1");

        Pregunta p75 = new Pregunta("¿Cómo murió Nino Bravo?", "En un accidente de tráfico", "En un tiroteo", "De sobredosis", "Durmiendo", "En un accidente de tráfico");

        Pregunta p76 = new Pregunta("¿Qué colecciona un filatélico?", "Monedas", "Hilos", "Mariposas", "Sellos", "Sellos");

        Pregunta p77 = new Pregunta("¿Con qué apodo era conocido Alfredo Di Stéfano?", "La Saeta Rubia", "La Galerna del Cantábrico", "El Pasmo de Triana", "El Matador", "La Saeta Rubia");

        Pregunta p78 = new Pregunta("¿Qué es un Whisky on the rocks?", "Whisky con agua", "Whisky de garrafón", "Whisky con hielo", "Whisky con música", "Whisky con hielo");

        Pregunta p79 = new Pregunta("¿Quién dirigió la primera expedición que alcanzó el Polo Sur?", "Scott", "Drake", "Chaplin", "Amundsen", "Amundsen");

        Pregunta p80 = new Pregunta("¿Cuál fue  el cantante de tangos más famoso?", "Gardel", "Valentino", "Piazzola", "Perón", "Gardel");

        Pregunta p81 = new Pregunta("¿Quién pintó Las Meninas?", "Goya", "Miró", "Dalí", "Velázquez", "Velázquez");

        Pregunta p82 = new Pregunta("¿De qué país es un transalpino?", "Francia", "Suiza", "Italia", "Austria", "Italia");

        Pregunta p83 = new Pregunta("¿Quién dirigió la saga de El Padrino?", "Spielberg", "Scorsese", "Coppola", "Tarantino", "Coppola");

        Pregunta p84 = new Pregunta("¿Qué tenista ha ganado más torneos de Roland Garros?", "Djokovic", "Federer", "Borg", "Nadal", "Nadal");

        Pregunta p85 = new Pregunta("¿Cuál de los siguientes animales no es un felino?", "León", "Tigre", "Lobo", "Leopardo", "Lobo");

        Pregunta p86 = new Pregunta("¿Qué futbolista española ha ganado el Balón de oro?", "Putellas", "Hermoso", "Díaz Ayuso", "Boquete", "Putellas");

        Pregunta p87 = new Pregunta("¿Qué general alemán fue conocido durante la II Guerra Mundial como El Zorro del Desierto?", "Rommel", "Choltitz ", "Canaris", "Clausen", "Rommel");

        Pregunta p88 = new Pregunta("¿A qué dinastía pertenecía Carlomagno?", "Merovingia", "Borbónica", "Carolingia", "Austríaca", "Carolingia");

        Pregunta p89 = new Pregunta("¿Dónde nació Napoleón?", "Córcega", "Cerdeña", "París", "Marsella", "Córcega");

        Pregunta p90 = new Pregunta("¿Quién era el padre de los dioses en la mitología griega?", "Júpiter", "Hércules", "Apolo", "Zeus", "Zeus");

        Pregunta p91 = new Pregunta("¿Cómo se denomina un depósito generalmente subterráneo que sirve para almacenar agua?", "Acequia", "Aljibe", "Taracea", "Alguacil", "Aljibe");

        Pregunta p92 = new Pregunta("¿En qué batalla fue derrotado Rodrigo, rey de los visigodos, en el año 711?", "Navas de Tolosa", "Covadonga", "Guadalete", "Bailén", "Guadalete");

        Pregunta p93 = new Pregunta("¿Cuántos hemisferios tiene la tierra", "4", "2", "3", "1", "2");

        Pregunta p94 = new Pregunta("¿De qué carecen los animales invertebrados?", "De piernas", "De dos pies", "De columna vertebral", "De cuello", "De columna vertebral");

        Pregunta p95 = new Pregunta("¿De qué estado de EEUU es capital Atlanta?", "Texas", "Carolina del Sur", "Georgia", "Nebraska", "Georgia");

        Pregunta p96 = new Pregunta("¿Cómo se llamaba el tercer hijo de Adán y Eva?", "Set", "Sem", "Jonás", "Noé", "Set");

        Pregunta p97 = new Pregunta("¿Qué instrumento tocaba el músico de Jazz John Coltrane?", "Saxofón", "Piano", "Trompeta", "Guitarra", "Saxofón");

        Pregunta p98 = new Pregunta("¿Con qué escudería debutó Lewis Hamilton en F1?", "McLaren", "Mercedes", "Jaguar", "Ferrari", "McLaren");

        Pregunta p99 = new Pregunta("¿Cuál es la capital de la Comunidad Autónoma Gallega?", "La Coruña", "Lugo", "Santiago de Compostela", "Vigo", "Santiago de Compostela");

        Pregunta p100 = new Pregunta("¿De qué país es capital Helsinki?", "Finlandia", "Islandia", "Suecia", "Bielorrusia", "Finlandia");

        Pregunta p101 = new Pregunta("¿Dónde nació Federico García Lorca?", "Sevilla", "Málaga", "Granada", "Córdoba", "Granada");

        Pregunta p103 = new Pregunta("¿A qué famosa novela pertenecen Jo, Meg, Beth y Amy March?", "Matar a un ruiseñor", "Tom Sawyer", "Mujercitas", "Moby Dick", "Mujercitas");

        Pregunta p104 = new Pregunta("¿Cuál fue el primer refresco que se llevó al espacio? ", "Pepsi", "Tang", "Coca Cola", "Fanta", "Coca Cola");

        Pregunta p105 = new Pregunta("¿Quién escribió un diario mientras se escondía de los nazis en Ámsterdam?", "Marie Curie", "Bridget Jones", "Anne Frank", "Greg", "Anne Frank");

        //Pregunta p106= new Pregunta("En ‘Los tres cerditos’, de qué está hecha la casa más resistente?", "Ladrillos", "Madera", "Piedra", "Cemento", "Ladrillos");

        Pregunta p108 = new Pregunta("¿En qué país está Praga?", "Rumanía", "República Checa", "Alemania", "Polonia", "República Checa");

        Pregunta p109 = new Pregunta("¿Cuál es el apellido de Chandler, el personaje de ‘Friends’?", "John", "Bing", "Green", "Geller", "Bing");

        Pregunta p110 = new Pregunta("¿Cuántos minutos dura un partido de rugby?", "80", "30", "120", "90", "80");

        Pregunta p111 = new Pregunta("¿Cuál era el nombre original de Nueva York?", "New London", "New Amsterdam", "The Big Apple", "Gotham", "New Amsterdam");

        Pregunta p112 = new Pregunta("¿Qué desastre natural se mide con escala Richter?", "Tornados", "Terremotos", "Huracanes", "Inundaciones", "Terremotos");

        Pregunta p113 = new Pregunta("¿Cómo llama cariñosamente Lady Gaga a sus fans?", "Babies", "Followers", "Little Monsters", "Little Cats", "Little Monsters");

        Pregunta p114 = new Pregunta("¿Qué miembro del elenco de The Big Bang Theory tiene un doctorado real?", "Mayim Bialik", "Jim Parsons", "Kunal Nayyar", "Johnny Galecki", "Mayim Bialik");

        Pregunta p115 = new Pregunta("¿De qué película es el príncipe azul?", "Cenicienta", "La bella durmiente", "Blancanieves", "Mulán", "Cenicienta");

        Pregunta p116 = new Pregunta("¿Cómo se llama el jefe de Mortadelo y Filemón?", "Superintendente Vicente", "Profesor Bacterio", "Ofelia", "Magín el Mago", "Superintendente Vicente");

        Pregunta p117 = new Pregunta("¿Cuál es la personalidad secreta de Bruce Wayne?", "Superman", "Spiderman", "Batman", "Capitán América", "Batman");

        Pregunta p118 = new Pregunta("¿Cómo se llama el padre de Zipi y Zape?", "Pantuflo Zapatilla", "Anacleto", "Carpanta", "Don Minervo", "Pantuflo Zapatilla");

        Pregunta p119 = new Pregunta("¿Qué sistema operativo tienen los teléfonos móviles Samsung?", "Android", "iOS", "MS-DOS", "Windows", "Android");

        Pregunta p120 = new Pregunta("¿Cómo se llama la mujer de Pedro Picapiedra?", "Betty", "Vilma", "Pebbles", "Rose", "Vilma");


        myPLab.insertarPregunta(p1);
        myPLab.insertarPregunta(p2);
        myPLab.insertarPregunta(p3);
        myPLab.insertarPregunta(p4);
        myPLab.insertarPregunta(p5);
        myPLab.insertarPregunta(p6);
        myPLab.insertarPregunta(p7);
        myPLab.insertarPregunta(p8);
        myPLab.insertarPregunta(p9);
        myPLab.insertarPregunta(p10);
        myPLab.insertarPregunta(p11);
        myPLab.insertarPregunta(p12);
        myPLab.insertarPregunta(p13);
        myPLab.insertarPregunta(p14);
        //myPLab.insertarPregunta(p15);
        myPLab.insertarPregunta(p16);
        myPLab.insertarPregunta(p17);
        myPLab.insertarPregunta(p18);
        myPLab.insertarPregunta(p19);
        myPLab.insertarPregunta(p20);
        myPLab.insertarPregunta(p21);
        myPLab.insertarPregunta(p22);
        myPLab.insertarPregunta(p23);
        myPLab.insertarPregunta(p24);
        myPLab.insertarPregunta(p25);
        myPLab.insertarPregunta(p26);
        myPLab.insertarPregunta(p27);
        myPLab.insertarPregunta(p28);
        myPLab.insertarPregunta(p29);
        myPLab.insertarPregunta(p30);
        myPLab.insertarPregunta(p31);
        myPLab.insertarPregunta(p32);
        myPLab.insertarPregunta(p33);
        myPLab.insertarPregunta(p34);
        myPLab.insertarPregunta(p35);
        myPLab.insertarPregunta(p36);
        myPLab.insertarPregunta(p37);
        myPLab.insertarPregunta(p38);
        myPLab.insertarPregunta(p39);
        myPLab.insertarPregunta(p40);
        myPLab.insertarPregunta(p41);
        myPLab.insertarPregunta(p42);
        myPLab.insertarPregunta(p43);
        myPLab.insertarPregunta(p44);
        myPLab.insertarPregunta(p45);
        myPLab.insertarPregunta(p46);
        //myPLab.insertarPregunta(p47);
        myPLab.insertarPregunta(p48);
        myPLab.insertarPregunta(p49);
        myPLab.insertarPregunta(p50);
        myPLab.insertarPregunta(p51);
        myPLab.insertarPregunta(p52);
        myPLab.insertarPregunta(p53);
        myPLab.insertarPregunta(p54);
        myPLab.insertarPregunta(p55);
        myPLab.insertarPregunta(p56);
        myPLab.insertarPregunta(p57);
        myPLab.insertarPregunta(p58);
        myPLab.insertarPregunta(p59);
        myPLab.insertarPregunta(p60);
        myPLab.insertarPregunta(p61);
        myPLab.insertarPregunta(p62);
        myPLab.insertarPregunta(p63);
        myPLab.insertarPregunta(p64);
        myPLab.insertarPregunta(p65);
        myPLab.insertarPregunta(p66);
        myPLab.insertarPregunta(p67);
        myPLab.insertarPregunta(p68);
        myPLab.insertarPregunta(p69);
        myPLab.insertarPregunta(p70);
        //myPLab.insertarPregunta(p71);
        myPLab.insertarPregunta(p73);
        myPLab.insertarPregunta(p74);
        myPLab.insertarPregunta(p75);
        myPLab.insertarPregunta(p76);
        myPLab.insertarPregunta(p77);
        myPLab.insertarPregunta(p78);
        myPLab.insertarPregunta(p79);
        myPLab.insertarPregunta(p80);
        //myPLab.insertarPregunta(p80);
        myPLab.insertarPregunta(p81);
        myPLab.insertarPregunta(p82);
        myPLab.insertarPregunta(p83);
        myPLab.insertarPregunta(p84);
        myPLab.insertarPregunta(p85);
        myPLab.insertarPregunta(p86);
        myPLab.insertarPregunta(p87);
        myPLab.insertarPregunta(p88);
        myPLab.insertarPregunta(p89);
        myPLab.insertarPregunta(p90);
        myPLab.insertarPregunta(p91);
        myPLab.insertarPregunta(p92);
        myPLab.insertarPregunta(p93);
        myPLab.insertarPregunta(p94);
        myPLab.insertarPregunta(p95);
        myPLab.insertarPregunta(p96);
        myPLab.insertarPregunta(p97);
        myPLab.insertarPregunta(p98);
        myPLab.insertarPregunta(p99);
        myPLab.insertarPregunta(p100);
        myPLab.insertarPregunta(p101);
        //myPLab.insertarPregunta(p102);
        myPLab.insertarPregunta(p103);
        myPLab.insertarPregunta(p104);
        myPLab.insertarPregunta(p105);
        //myPLab.insertarPregunta(p106);
        //myPLab.insertarPregunta(p107);
        myPLab.insertarPregunta(p108);
        myPLab.insertarPregunta(p109);
        myPLab.insertarPregunta(p110);
        myPLab.insertarPregunta(p111);
        myPLab.insertarPregunta(p112);
        myPLab.insertarPregunta(p113);
        myPLab.insertarPregunta(p114);
        myPLab.insertarPregunta(p115);
        myPLab.insertarPregunta(p116);
        myPLab.insertarPregunta(p117);
        myPLab.insertarPregunta(p118);
        myPLab.insertarPregunta(p119);
        myPLab.insertarPregunta(p120);


        /*Pregunta px= new Pregunta("Pregunta?","r1","r2","r3","r4","r1");
        myPLab.insertarPregunta(px);
        Pregunta p1= new Pregunta("En que fecha se produjo el desembarco de normandia?","6 de Junio de 1944","7 de Agosto de 1943","25 de Diciembre de 1943","1 de Enero se 1944","6 de Junio de 1944");
        if(myPLab.getPregunta(p1.getPregunta())!=null){
            System.out.println("ya esta");
        }else {
            myPLab.insertarPregunta(p1);
        }
        Pregunta p2= new Pregunta("Quién pintó 'el Expolio'?","El Greco","Diego Velázquez","Antonio Machado","Nicolás Redondo","El Greco");
        if(myPLab.getPregunta(p2.getPregunta())!=null){
            System.out.println("ya esta");
        }else {
            myPLab.insertarPregunta(p2);
        }
        Pregunta p3= new Pregunta("Del deportivo de la coruña?","Herculino","Fracasado","Carballón","Montañés","Herculino");
        if(myPLab.getPregunta(p3.getPregunta())!=null){
            System.out.println("ya esta");
        }else {
            myPLab.insertarPregunta(p3);
        }
        Pregunta p4= new Pregunta("Autor de 'La Peste'?","José María Aznar","Isabel Allende","Albert Camus","Nostale Webber","Albert Camus");
        if(myPLab.getPregunta(p4.getPregunta())!=null){
            System.out.println("ya esta");
        }else {
            myPLab.insertarPregunta(p4);
        }
        Pregunta p5= new Pregunta("Como se llama al consejero del califa","Visir","Muladi","Qadí","Ulema","Visir");
        if(myPLab.getPregunta(p1.getPregunta())!=null){
            System.out.println("ya esta");
        }else {
            myPLab.insertarPregunta(p5);
        }*/


    }

    @Override
    protected void onStart() {
        super.onStart();
        final Random myRandom = new Random();
        btEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indicePR == 0) {
                    Date dt = new Date();
                    fechainicio = dateFormat.format(dt);
                    System.out.println(fechainicio);
                }


                mensajeInicio.setVisibility(View.INVISIBLE);
                totalp = arrayListPreguntas.size();
                btEmpezar.setText("SIGUIENTE");
                btEmpezar.setVisibility(View.INVISIBLE);
                System.out.println(totalp);
                int random = myRandom.nextInt(totalp);
                Pregunta pregunta = arrayListPreguntas.get(random);
                System.out.println("random " + random);
                arrayListPreguntasRealizadas.add(pregunta);
                arrayListPreguntas.remove(pregunta);
                String p = pregunta.getPregunta();
                String r1 = pregunta.getRespuesta1();
                String r2 = pregunta.getRespuesta2();
                String r3 = pregunta.getRespuesta3();
                String r4 = pregunta.getRespuesta4();
                String correcta = pregunta.getCorrecta();
                Bundle datos = new Bundle();
                datos.putString("pregunta", p);
                datos.putString("r1", r1);
                datos.putString("r2", r2);
                datos.putString("r3", r3);
                datos.putString("r4", r4);
                datos.putString("correcta", correcta);
                PreguntaFragment preguntaFragment = new PreguntaFragment();
                preguntaFragment.setArguments(datos);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame, preguntaFragment)
                        .commit();
            }
        });

    }

    public static ArrayList<Pregunta> listToArrayList(List<Pregunta> myList) {
        ArrayList<Pregunta> arl = new ArrayList<Pregunta>();
        for (Pregunta object : myList) {
            arl.add((Pregunta) object);
        }
        return arl;

    }

    @Override
    public void enviarDatos(String respuesta, boolean resultado) {
        btEmpezar.setVisibility(View.VISIBLE);
        Pregunta p = arrayListPreguntasRealizadas.get(indicePR);
        p.setResultado(resultado);
        p.setSeleccionada(respuesta);
        indicePR++;
        if (resultado) {
            int a = Integer.parseInt(aciertos.getText().toString());
            a++;
            aciertos.setText(String.valueOf(a));
            if (a == 10) {
                for (Pregunta pregunta : arrayListPreguntasRealizadas) {
                    System.out.println(pregunta.toString());
                }

                guardarResultados();


                Intent intent = new Intent(MainActivity.this, GanadorActivity.class);

                if (getIntent().getStringExtra("username") != null) {
                    intent.putExtra("username",user);
                    intent.putExtra("iduser",id);
                    intent.putExtra("tiempo",time);
                }else{
                    intent.putExtra("tiempo",calculartiempo());
                }

                intent.putExtra("aciertos",aciertos.getText().toString());
                intent.putExtra("fallos",fallos.getText().toString());

                intent.putExtra("list", arrayListPreguntasRealizadas);
                startActivity(intent);

                finish();
            }

        } else {
            int f = Integer.parseInt(fallos.getText().toString());
            f++;
            fallos.setText(String.valueOf(f));
            if (f == 3) {
                for (Pregunta pregunta : arrayListPreguntasRealizadas) {
                    System.out.println(pregunta.toString());
                }

                guardarResultados();

//SELECT u.usuario, r.aciertos, r.fallos, r.tiempo FROM usuarios u INNER JOIN resultados r ON u.id =r.id_usuario WHERE r.aciertos=10 ORDER BY r.segundos ASC
//SELECT u.usuario, r.aciertos, r.fallos, r.tiempo FROM usuarios u INNER JOIN resultados r ON u.id =r.id_usuario WHERE r.id_usuario=7 ORDER BY r.fallos=3, r.segundos


                if (getIntent().getStringExtra("username") != null) {

                }
                Intent intent = new Intent(MainActivity.this, FracasaoActivity.class);

                if (getIntent().getStringExtra("username") != null) {
                    intent.putExtra("username",user);
                    intent.putExtra("iduser",id);
                    intent.putExtra("tiempo",time);
                }else{
                    intent.putExtra("tiempo",calculartiempo());
                }

                intent.putExtra("aciertos",aciertos.getText().toString());
                intent.putExtra("fallos",fallos.getText().toString());

                intent.putExtra("list", arrayListPreguntasRealizadas);
                startActivity(intent);
                finish();
            }
        }

    }

    public String calculartiempo() {
        Date dt2 = new Date();
        fechafin = dateFormat.format(dt2);
        System.out.println(fechafin);
        Date inicio = new Date();
        Date fin = new Date();
        try {
            inicio = dateFormat.parse(fechainicio);
            fin = dateFormat.parse(fechafin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long l = inicio.getTime() - fin.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        sec = Math.abs((int)(l / 1000));

        System.out.println("time:"+sec);
        
        if (day != 0) {
            time = time + Math.abs(day) + " d ";
        }
        if (hour != 0) {
            time = time + Math.abs(hour) + " h ";
        }
        if (min != 0) {
            time = time + Math.abs(min) + " min ";
        }
        if (s != 0) {
            time = time + Math.abs(s) + " s";
        }

        return time;
    }

    public void guardarResultados() {
        if (getIntent().getStringExtra("username") != null) {

            Response.Listener<String> respoListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                    try {
                        JSONObject jsonResponse = new JSONObject(response);
                        int success = jsonResponse.getInt("success");

                        if (success == 1) {
                            Toast.makeText(getApplicationContext(),
                                    "Partida guardada...", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Error al guardar partida...", Toast.LENGTH_SHORT).show();

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            };

            int ac = Integer.parseInt(String.valueOf(aciertos.getText()));
            int fa = Integer.parseInt(String.valueOf(fallos.getText()));
            time = calculartiempo();

            ResultadosRequest resultadosRequest = new ResultadosRequest(id, fechainicio, ac, fa, time, sec, respoListener);
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            queue.add(resultadosRequest);
        }
    }

//    public static ArrayList<String> listToArrayList(List<Object> myList) {
//        ArrayList<String> arl = new ArrayList<String>();
//        for (Object object : myList) {
//            arl.add((String) object);
//        }
//        return arl;
//
//    }
}