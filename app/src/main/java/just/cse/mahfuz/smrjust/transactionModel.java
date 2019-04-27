package just.cse.mahfuz.smrjust;

public class transactionModel {
    String date,time,roll,ref,purpose,amount,authorized;

    public transactionModel() {
    }

    public transactionModel(String date, String time, String roll, String ref, String purpose, String amount, String authorized) {
        this.date = date;
        this.time = time;
        this.roll = roll;
        this.ref = ref;
        this.purpose = purpose;
        this.amount = amount;
        this.authorized = authorized;
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

    public String getAuthorized() {
        return authorized;
    }

    public void setAuthorized(String authorized) {
        this.authorized = authorized;
    }
}
