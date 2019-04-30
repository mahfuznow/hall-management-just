package just.cse.mahfuz.smrjust;

public class PendingTransactionModel {
    String date,time,roll,ref,purpose,amount,hall;

    public PendingTransactionModel() {
    }

    public PendingTransactionModel(String date, String time, String roll, String ref, String purpose, String amount, String hall) {
        this.date = date;
        this.time = time;
        this.roll = roll;
        this.ref = ref;
        this.purpose = purpose;
        this.amount = amount;
        this.hall = hall;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getHall() {
        return hall;
    }

    public void setHall(String hall) {
        this.hall = hall;
    }

}
