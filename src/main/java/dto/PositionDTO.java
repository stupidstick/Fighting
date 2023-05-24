package dto;

import java.io.Serializable;

public class PositionDTO implements Serializable {
    private double cordX;

    public PositionDTO(double cordX) {
        this.cordX = cordX;
    }

    public double getCordX() {
        return cordX;
    }

    public void setCordX(double cordX) {
        this.cordX = cordX;
    }
}
