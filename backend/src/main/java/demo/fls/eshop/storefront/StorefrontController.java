package demo.fls.eshop.storefront;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class StorefrontController {
    Collection<Product> products = Arrays.asList(
            new Product(UUID.randomUUID(), "Samsung Galaxy",
                    "Процессор: i7-1360P (i7, 1360P, 12 ядер, 2.2-5.0 Ггц, кеш 18 мб)\n" +
                            "Оперативная память: 16gb DDR5\n" +
                            "Установленные накопители и возможности: 1 Tb SSD NVMe\n" +
                            "Общий объем накопителей: 1024Gb\n" +
                            "Экран: 16.0\" ([2880x1800], AMOLED, Сенсорный, Глянцевый)\n" +
                            "Видеокарта: Intel Iris Xe Graphics\n" +
                            "Операционная система: win11 Home\n" +
                            "Материал корпуса: Металлический корпус\n" +
                            "Вес: 1.66кг\n" +
                            "Время работы от батареи: до 19 ч.\n" +
                            "Подсветка: Подсветка\n" +
                            "Сканер отпечатка: Сканер отпечатка\n" +
                            "Мощность блока питания: 65W\n" +
                            "Стилус: Стилус\n" +
                            "Прочее:\n" +
                            "Цвет: Graphite", URI.create("/img/acer.jpg"), 2215.0),
            new Product(UUID.randomUUID(), "Apple MacBook Air",
                    "Важным и неоспоримым преимуществом для дизайнеров и специалистов, использующих графику в работе, является способность Макбука 15 отображать миллиард цветов, включая полутона и сложное оттенки.\n" +
                            "Серия поддерживает True Tone, работает в цветовых решениях DCI-P3. Максимально возможной яркости в 500 нит будет достаточно для самых сложных условий работы. При необходимости увеличить монитор, можно использовать Display Port через Type-C.\n" +
                            "Комфорт не роскошь\n" +
                            "Механизм \"ножницы\" и Touch ID экономит время пользователя, избавляя от необходимости вводить пароли.", URI.create("/img/acer-aspire.jpg"), 1759.35),
            new Product(UUID.randomUUID(), "Lenovo Legion 5", "Ноутбуки Legion 5 15 сочетают в элегантных и минималистичных корпусах производительные процессоры AMD Ryzen серии 4000, видеокарты NVIDIA® GeForce® и опциональные твердотельные накопители, а за охлаждение всех компонентов отвечает система Legion Coldfront 2.0. Геймеры любого уровня получат возможность подобрать модель, соответствующую их ожиданиям и потребностям. Наслаждайтесь идеальной точностью цветопередачи на дисплее стандарта FHD с невероятной частотой обновления, а также чувствительными элементами управления клавиатуры TrueStrike, которая позволит вам оставить конкурентов далеко позади.", URI.create("/img/hewlett-packard.jpg"), 1191.50),
            new Product(UUID.randomUUID(), "DELL Latitude 5400", "Самый компактный в мире 14-дюймовый ноутбук бизнес-класса с широкими возможностями масштабирования, высокой производительностью и технологией ExpressCharge позволяет работать быстрее, чем когда-либо. Ноутбук Latitude 5400 более мобилен и портативен, чем когда-либо ранее, благодаря усовершенствованной конструкции, которая отличается тонкостью и отменным дизайном. Клавиатура с нижним креплением вносит вклад в его изящный вид.", URI.create("/img/lenovo-thinkpad.jpg"), 295.45),
            new Product(UUID.randomUUID(), "ASUS Vivobook 17", "Тип: Ноутбук\n" +
                    "PartNumber/Артикул Производителя: 90NB10F2-M000T0\n" +
                    "Материал корпуса: высококачественный пластик\n" +
                    "Соотношение сторон: 16:9\n" +
                    "Количество ядер процессора: 6-ядерный\n" +
                    "Объем SSD: 512\n" +
                    "Цветовое решение: синий\n" +
                    "Веб-камера: ДА\n" +
                    "Поддержка технологии Bluetooth: ДА\n" +
                    "Поддержка технологии Wi-Fi: ДА\n" +
                    "Видеокарта : Intel UHD Graphics\n" +
                    "Семейство: VivoBook 17\n" +
                    "Диагональ экрана в дюймах: 17.3\n" +
                    "Разрешение: 1920x1080", URI.create("/img/panasonic-toughbook.jpg"), 615.15)
    );

    @GetMapping
    public Collection<Product> find(@RequestParam(value = "q", required = false) String query) {
        if (query != null) {
            return products.stream().filter((product -> product.title().toLowerCase().contains(query.toLowerCase()) || product.description().toLowerCase().contains(query.toLowerCase()))).toList();
        }

        return products;
    }

    @GetMapping("/{ids}")
    public Collection<Product> getById(@PathVariable Collection<UUID> ids) {
        return products.stream().filter((product) -> ids.contains(product.id())).toList();
    }
}