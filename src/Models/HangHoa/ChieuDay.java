/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models.HangHoa;

/**
 *
 * @author sangt
 */
public class ChieuDay {
    private int Id;
    private double DoDay;

    public ChieuDay() {
    }

    public ChieuDay(double DoDay) {
        this.DoDay = DoDay;
    }
    
    public ChieuDay(int Id, double DoDay) {
        this.Id = Id;
        this.DoDay = DoDay;
    }
    
    
    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public double getDoDay() {
        return DoDay;
    }

    public void setDoDay(double DoDay) {
        this.DoDay = DoDay;
    }
    
    
}
