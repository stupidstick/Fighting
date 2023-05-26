package dto;

import java.io.Serializable;

public class HpInfoDTO implements Serializable {
    double leftHP;
    double rightHP;

    public HpInfoDTO(double leftHP, double rightHP) {
        this.leftHP = leftHP;
        this.rightHP = rightHP;
    }

    public double getLeftHP() {
        return leftHP;
    }

    public void setLeftHP(double leftHP) {
        this.leftHP = leftHP;
    }

    public double getRightHP() {
        return rightHP;
    }

    public void setRightHP(double rightHP) {
        this.rightHP = rightHP;
    }
}
