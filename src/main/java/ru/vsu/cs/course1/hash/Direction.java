package main.java.ru.vsu.cs.course1.hash;

public class Direction {

    private String name;
    private Integer costPerMinute;

    public Direction() {

    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the costPerMinute
     */
    public Integer getCostPerMinute() {
        return costPerMinute;
    }

    /**
     * @param costPerMinute the costPerMinute to set
     */
    public void setCostPerMinute(Integer costPerMinute) {
        this.costPerMinute = costPerMinute;
    }

    @Override
    public String toString() {
        return String.format("%s %d", name, costPerMinute);
    }
}
