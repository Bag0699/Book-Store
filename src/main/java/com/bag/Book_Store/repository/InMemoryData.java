package com.bag.Book_Store.repository;

import com.bag.Book_Store.model.entity.Author;
import com.bag.Book_Store.model.entity.Book;
import com.bag.Book_Store.model.entity.Category;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class InMemoryData {

//    public final List<Book> books;
//    public final List<Category> categories;
//    public final List<Author> authors;
//
//    public InMemoryData() {
//        this.books = new ArrayList<>();
//        this.categories = new ArrayList<>();
//        this.authors = new ArrayList<>();
//
//        books.add(new Book(1L, "Don Quijote de la Mancha", author1,
//                    "La novela narra las aventuras de Alonso Quijano, un hidalgo de la Mancha de unos cincuenta años que, tras leer numerosas novelas de caballerías, pierde el juicio y cree ser un caballero andante al que llama don Quijote. Acompañado de su fiel escudero, el labrador Sancho Panza, que monta un asno, juntos vivirán una serie de cómicas y a veces trágicas aventuras en nombre de su dama, Dulcinea del Toboso, una campesina imaginaria.",
//                    BigDecimal.valueOf(29.99), "978-84-667-0703-2", "Una obra cumbre de la literatura española y universal que explora temas como la realidad y la ilusión, la cordura y la locura, y la naturaleza humana a través de las hilarantes andanzas de un caballero soñador y su pragmático escudero.",
//                    "/img/don-quijote.webp",category1));
//        books.add(new Book(2L, "Harry Potter y la Piedra Filosofal", author2,
//                    "Harry Potter es un niño huérfano que vive con sus desagradables tíos y su primo Dudley. En su undécimo cumpleaños, recibe una carta que revela que es un mago y que ha sido aceptado en el Colegio Hogwarts de Magia y Hechicería. Allí, Harry descubre un mundo mágico lleno de amigos, desafíos y un destino ligado a un mago oscuro llamado Lord Voldemort, quien asesinó a sus padres.",
//                    BigDecimal.valueOf(39.99), "978-84-7888-445-4", "El primer libro de la aclamada serie que introduce a los lectores al mágico mundo de Hogwarts, a los entrañables personajes de Harry, Ron y Hermione, y al misterio que rodea la Piedra Filosofal y el regreso de Quien-No-Debe-Ser-Nombrado.",
//                    "/img/harry-potter-piedra-filosofal.webp",category1));
//        books.add(new Book(3L, "1984", author3,
//                    "La novela se desarrolla en Oceanía, un estado totalitario gobernado por el Partido Único y su líder omnipresente, el Gran Hermano. Winston Smith trabaja en el Ministerio de la Verdad, donde se encarga de reescribir la historia para que coincida con la propaganda del Partido. Sin embargo, Winston secretamente odia al Partido y sueña con la rebelión, iniciando una peligrosa relación con Julia y uniéndose a una misteriosa hermandad.",
//                    BigDecimal.valueOf(25.99), "978-04-5152-493-5", "Una escalofriante distopía que explora los peligros del totalitarismo, la vigilancia masiva, la manipulación del lenguaje y la supresión del pensamiento individual. Una poderosa advertencia sobre el futuro que sigue resonando en la actualidad.",
//                    "/img/1984.webp",category3));
//        books.add(new Book(4L, "La Ciudad y los Perros", author4,
//                    "La novela está ambientada en el Colegio Militar Leoncio Prado de Lima y sigue las vidas de un grupo de cadetes, incluyendo a Alberto 'El Poeta', Ricardo Arana 'El Esclavo' y Javier Heraud 'El Jaguar'. A través de sus experiencias, la novela explora temas de brutalidad, jerarquía social, corrupción, masculinidad y la pérdida de la inocencia en un entorno opresivo y violento.",
//                    BigDecimal.valueOf(12.99), "978-84-663-3627-8", "Una obra fundamental del Boom Latinoamericano que ofrece una mirada cruda y compleja a la sociedad peruana a través de la lente de un colegio militar, utilizando técnicas narrativas innovadoras como múltiples puntos de vista y saltos temporales.",
//                    "/img/la-ciudad-y-los-perros.webp",category4));
//        books.add(new Book(5L, "La Rebelión en la Granja", author3,
//                    "Los animales de la Granja Manor, cansados de la tiranía del granjero Jones, se rebelan bajo el liderazgo de los cerdos Napoleón y Snowball. Tras expulsar a los humanos, establecen una sociedad igualitaria basada en siete mandamientos. Sin embargo, gradualmente, los cerdos toman el control y establecen una nueva forma de opresión aún más brutal que la anterior, parodiando la Revolución Rusa y el estalinismo.",
//                    BigDecimal.valueOf(10.99), "978-04-5152-634-2", "Una alegoría satírica mordaz sobre la corrupción del poder y la traición de los ideales revolucionarios. A través de una fábula sencilla pero profunda, Orwell critica el totalitarismo y la manipulación política.",
//                    "/img/rebelion-en-la-granja.webp",category1));
//        books.add( new Book(6L, "Tokio Blues", author5,
//                    "La novela narra la historia de Toru Watanabe, quien recuerda sus años universitarios en Tokio a finales de la década de 1960. A través de sus reflexiones, explora sus complejas relaciones con dos mujeres muy diferentes: la misteriosa y emocionalmente frágil Naoko, la novia de su mejor amigo fallecido, y la vivaz y extrovertida Midori. La novela aborda temas de pérdida, amor, sexualidad, soledad y la búsqueda de significado en la juventud.",
//                    BigDecimal.valueOf(49.99), "978-84-8393-265-2", "Una conmovedora y evocadora novela sobre el crecimiento, la pérdida y las complejidades del amor en el Japón de finales del siglo XX. Con su prosa lírica y personajes inolvidables, Murakami captura la melancolía y la intensidad de las relaciones humanas.",
//                    "/img/tokio-blues.webp",category1));
//        books.add(new Book(7L, "El Principito", author6,
//                    "Un piloto se encuentra perdido en el desierto del Sahara después de que su avión sufriera una avería. Allí conoce a un joven príncipe proveniente de un pequeño asteroide. A través de sus conversaciones, el piloto descubre las profundas reflexiones del príncipe sobre la vida, la amistad, el amor y la naturaleza humana.",
//                    BigDecimal.valueOf(15.50), "978-84-9838-141-4", "Una joya de la literatura que, con su sencillez y poesía, ofrece profundas reflexiones sobre la existencia, la amistad y el valor de las cosas importantes de la vida, ideal para lectores de todas las edades.",
//                    "/img/el-principito.webp", category7));
//        books.add( new Book(8L, "Sapiens: De Animales a Dioses", author7,
//                    "Este libro examina la historia de la humanidad desde la aparición del Homo sapiens hasta el presente. Explora cómo nuestra especie logró dominar el planeta, las fuerzas que impulsaron el desarrollo de las sociedades humanas, la creación de mitos y religiones, y el impacto de la revolución científica y tecnológica.",
//                    BigDecimal.valueOf(22.75), "978-84-9992-622-5", "Una obra fascinante que ofrece una perspectiva amplia y provocadora sobre la historia de la humanidad, combinando conocimientos de la biología, la antropología, la paleontología y la historia para comprender nuestro pasado y futuro.",
//                    "/img/sapiens.webp", category8));
//        books.add(new Book(9L, "Los Juegos del Hambre", author8,
//                    "En un futuro distópico, la nación de Panem obliga a cada uno de sus doce distritos a enviar a dos tributos adolescentes para competir en los Hunger Games, una lucha a muerte televisada a nivel nacional. Katniss Everdeen, una joven del Distrito 12, se ofrece como voluntaria para ocupar el lugar de su hermana menor, desencadenando una serie de eventos que desafiarán al Capitolio y a las reglas del juego.",
//                    BigDecimal.valueOf(18.99), "978-84-9800-658-7", "El primer libro de una emocionante trilogía que combina acción, aventura, crítica social y dilemas morales en un mundo distópico donde la supervivencia y la rebelión van de la mano.",
//                    "/img/los-juegos-del-hambre.webp", category9));
//        books.add(new Book(10L, "El Jardín Secreto", author9,
//                    "Mary Lennox, una niña huérfana y malcriada, es enviada desde la India a vivir con su tío en una mansión sombría en Inglaterra. Allí descubre un jardín secreto abandonado y, junto con su primo inválido Colin y el joven Dickon, se dedica a devolverle la vida, transformando sus propias vidas en el proceso.",
//                    BigDecimal.valueOf(14.20), "978-84-9764-697-2", "Un clásico de la literatura infantil que celebra la amistad, la naturaleza y el poder curativo del cuidado y la esperanza.",
//                    "/img/el-jardin-secreto.webp", category7));
//        books.add(new Book(11L, "Breve Historia del Tiempo", author10,
//                "Este libro explora las preguntas fundamentales sobre el universo, como su origen, su destino, la naturaleza del espacio y el tiempo, los agujeros negros y la teoría del Big Bang, presentado de manera accesible para el público no científico.",
//                BigDecimal.valueOf(21.50), "978-84-672-2025-8", "Una obra divulgativa clave que acerca los misterios del cosmos a los lectores, explicando conceptos complejos de la física moderna de forma clara y concisa.",
//                "/img/breve-historia-del-tiempo.webp", category8));
//        books.add(new Book(12L, "Bajo la Misma Estrella", author11,
//                "Hazel Grace Lancaster y Augustus Waters son dos adolescentes con cáncer que se conocen en un grupo de apoyo. A pesar de sus circunstancias, desarrollan una profunda conexión y viven una intensa historia de amor mientras enfrentan los desafíos de su enfermedad.",
//                BigDecimal.valueOf(16.75), "978-607-311-894-2", "Una emotiva y conmovedora novela juvenil que aborda temas de amor, pérdida, esperanza y la búsqueda de significado frente a la adversidad.",
//                "/img/bajo-la-misma-estrella.webp", category9));
//
//        categories.add(category1);
//        categories.add(category2);
//        categories.add(category3);
//        categories.add(category4);
//        categories.add(category5);
//        categories.add(category6);
//        categories.add(category7);
//        categories.add(category8);
//        categories.add(category9);
//
//        authors.add(author1);
//        authors.add(author2);
//        authors.add(author3);
//        authors.add(author4);
//        authors.add(author5);
//        authors.add(author6);
//        authors.add(author7);
//        authors.add(author8);
//        authors.add(author9);
//        authors.add(author10);
//        authors.add(author11);
//    }
//
//    private final Category category1 = new Category(1L, "Ficción", "En La Catedral, encontrará una amplia variedad de libros de ficción que van desde historias de intriga, aventuras, romance y más.");
//    private final Category category2 = new Category(2L, "Fantasía", "En La Catedral, sumérgete en mundos mágicos y emocionantes con nuestra selección de libros de fantasía llenos de criaturas míticas, hechizos y épicas batallas.");
//    private final Category category3 = new Category(3L, "Distopía", "En La Catedral, explora futuros sombríos y sociedades controladoras a través de nuestra colección de novelas distópicas que invitan a la reflexión.");
//    private final Category category4 = new Category(4L, "Literatura Latinoamericana", "En La Catedral, descubre la riqueza y diversidad de la literatura latinoamericana con obras de autores influyentes y narrativas apasionantes.");
//    private final Category category5 = new Category(5L, "Sátira", "En La Catedral, ríe y reflexiona con nuestra selección de libros satíricos que utilizan el humor y la ironía para criticar la sociedad y la política.");
//    private final Category category6 = new Category(6L, "Literatura Contemporánea", "En La Catedral, explora las tendencias y voces actuales de la literatura con nuestra cuidada selección de novelas contemporáneas.");
//    private final Category category7 = new Category(7L, "Infantil", "En La Catedral, los más pequeños descubrirán un mundo de imaginación y aprendizaje con nuestros encantadores libros infantiles llenos de historias y personajes entrañables.");
//    private final Category category8 = new Category(8L, "Historia", "En La Catedral, viaja a través del tiempo y explora el pasado con nuestra fascinante colección de libros de historia que abarcan civilizaciones, eventos y personajes que moldearon nuestro mundo.");
//    private final Category category9 = new Category(9L, "Juvenil", "En La Catedral, los jóvenes lectores encontrarán emocionantes novelas de aventuras, crecimiento personal, amistad y los desafíos de la adolescencia.");
//
//
//    private final Author author1 = new Author(1L, "Miguel de Cervantes Saavedra");
//    private final Author author2 = new Author(2L, "J.K. Rowling");
//    private final Author author3 = new Author(3L, "George Orwell");
//    private final Author author4 = new Author(4L, "Mario Vargas Llosa");
//    private final Author author5 = new Author(5L, "Haruki Murakami");
//    private final Author author6 = new Author(6L, "Antoine de Saint-Exupéry");
//    private final Author author7 = new Author(7L, "Yuval Noah Harari");
//    private final Author author8 = new Author(8L, "Suzanne Collins");
//    private final Author author9 = new Author(9L, "Frances Hodgson Burnett");
//    private final Author author10 = new Author(10L, "Stephen Hawking");
//    private final Author author11 = new Author(11L, "John Green");

}
