package HW6;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/*
 * Фирма(firm) 
Процессор(processor)
Операционная система(operating system)
Цвет(color)
 */
class Laptop {
    private String firm;
    private String processor;
    private String operatingSystem;
    private String color;

    public Laptop(String firm, String processor, String operatingSystem, String color) {
        this.firm = firm;
        this.processor = processor;
        this.operatingSystem = operatingSystem;
        this.color = color;
    }

    public String getFirm() {
        return firm;
    }

    public String getProcessor() {
        return processor;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getColor() {
        return color;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("firm %s, processor %s, operatingSystem %s, color %s", firm, processor, operatingSystem,
                color);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Laptop cat = (Laptop) obj;
        return firm.equals(cat.firm) && color.equals(cat.color)
                && processor.equals(cat.processor)
                && operatingSystem.equals(cat.operatingSystem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firm, processor, operatingSystem, color);
    }

    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", "ICi5", "Windows", "grey"));
        laptops.add(new Laptop("HP", "ICi7", "Linux", "silver"));
        laptops.add(new Laptop("Lenovo", "ICi9", "Windows", "black"));
        laptops.add(new Laptop("HP", "ICi9", "Windows", "black"));
        laptops.add(new Laptop("Lenovo", "ICi7", "Windows", "grey"));

        Map<String, Object> filters = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите критерии ноутбука:");
        System.out.println("1 - Фирма");
        System.out.println("2 - Процессор");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("0 - Завершить выбор");

        int enter;
        while (true) {
            enter = scanner.nextInt();
            if (enter == 0) {
                break;
            }
            switch (enter) {
                case 1:
                    System.out.println("Фирма?");
                    filters.put("firm", scanner.next());
                    break;
                case 2:
                    System.out.println("Процессор?");
                    filters.put("processor", scanner.next());
                    break;
                case 3:
                    System.out.println("Операционная система?");
                    filters.put("operatingSystem", scanner.next());
                    break;
                case 4:
                    System.out.println("Цвет?");
                    filters.put("color", scanner.next());
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }

        }
        scanner.close();
        Set<Laptop> filteredLaptops = laptops.stream()
                .filter(laptop -> filters.getOrDefault("firm", "").equals("") ||
                        laptop.firm.equalsIgnoreCase((String) filters.getOrDefault("firm", "")))
                .filter(laptop -> filters.getOrDefault("processor", "").equals("") ||
                        laptop.processor.equalsIgnoreCase((String) filters.getOrDefault("processor",
                                "")))
                .filter(laptop -> filters.getOrDefault("operatingSystem", "").equals("") ||
                        laptop.operatingSystem.equalsIgnoreCase((String) filters.getOrDefault("operatingSystem", "")))
                .filter(laptop -> filters.getOrDefault("color", "").equals("") ||
                        laptop.color.equalsIgnoreCase((String) filters.getOrDefault("color", "")))
                .collect(Collectors.toSet());

        System.out.println("Ноутбуки соответствующие фильтру:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }

    }
}