package learn.foraging.models;

import java.math.BigDecimal;
import java.math.*;
import java.time.LocalDate;
import java.util.Objects;

public class Forage {

    private String id;
    private LocalDate date;
    private Forager forager;
    private Item item;
    private Double kilograms;

    public Forage() {
    }

    public Forage(Forager forager, Item item, Double Kilograms) {
        this.forager = forager;
        this.item = item;
        this.kilograms = Kilograms;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Forager getForager() {
        return forager;
    }

    public void setForager(Forager forager) {
        this.forager = forager;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Double getKilograms() {
        return kilograms;
    }

    public void setKilograms(Double kilograms) {
        this.kilograms = kilograms;
    }

    public BigDecimal getValue() {
        if (item == null || item.getDollarPerKilogram() == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal kilos = new BigDecimal(kilograms).setScale(4, RoundingMode.HALF_UP);
        return item.getDollarPerKilogram().multiply(kilos);

    }

    public void Forage(Forage forage) {
    }
}
